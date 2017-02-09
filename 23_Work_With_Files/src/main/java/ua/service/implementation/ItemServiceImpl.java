package ua.service.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.dto.filter.ItemFilter;
import ua.dto.form.ItemForm;
import ua.dto.form.SpecificationDigitalForm;
import ua.entity.Item;
import ua.entity.SpecificationDigital;
import ua.repository.CategoryRepository;
import ua.repository.ItemRepository;
import ua.repository.MeasuringSystemRepository;
import ua.repository.NameOfSpecificationDigitalRepository;
import ua.repository.ProducerRepository;
import ua.repository.SpecificationDigitalRepository;
import ua.repository.SpecificationStringRepository;
import ua.service.FileWriter;
import ua.service.FileWriter.Folder;
import ua.service.ItemService;
import ua.service.specification.ItemSpecification;
@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SpecificationStringRepository specificationStringRepository;

	@Autowired
	private SpecificationDigitalRepository specificationDigitalRepository;
	
	@Autowired
	private NameOfSpecificationDigitalRepository nameOfSpecificationDigitalRepository;
	
	@Autowired
	private MeasuringSystemRepository measuringSystemRepository;
	
	@Autowired
	private ProducerRepository producerRepository;
	
	@Autowired
	private FileWriter fileWriter;
	
	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public void delete(int id) {
		itemRepository.delete(id);
	}

	@Override
	@Transactional
	public void save(ItemForm itemForm) {
		List<SpecificationDigital> sds = new ArrayList<>();
		for(SpecificationDigitalForm specDidit : itemForm.getSpecificationDigitals()){
			SpecificationDigital sd = specificationDigitalRepository.findByNosdValue(specDidit.getNameOfSpecificationDigital().getId(), new BigDecimal(specDidit.getValue().replace(',', '.')));
			if(sd==null){
				sd = new SpecificationDigital();
				sd.setMeasuringSystems(specDidit.getMeasuringSystems());
				sd.setNameOfSpecificationDigital(specDidit.getNameOfSpecificationDigital());
				sd.setValue(new BigDecimal(specDidit.getValue()));
			}
			specificationDigitalRepository.save(sd);
			sds.add(sd);
		}
		Item item = new Item();
		item.setId(itemForm.getId());
		item.setCategory(itemForm.getCategory());
		item.getMeasuringSystems().addAll(itemForm.getMeasuringSystems());
		item.setName(itemForm.getName());
		item.setPrice(new BigDecimal(itemForm.getPrice().replace(',', '.')));
		item.setProducer(itemForm.getProducer());
		item.setSpecificationStrings(itemForm.getSpecificationStrings());
		itemRepository.saveAndFlush(item);
		item.setSpecificationDigitals(sds);
		if(fileWriter.write(Folder.ITEM, itemForm.getFile(), item.getId())){
			if(item.getVersion()==null)item.setVersion(0);
			else item.setVersion(item.getVersion()+1);
		}
		itemRepository.save(item);
	}

	@Override
	public Page<Item> findAll(ItemFilter filter, Pageable pageable) {
		Page<Item> items = itemRepository.findAll(new ItemSpecification(filter),pageable);
		return items;
	}
}
