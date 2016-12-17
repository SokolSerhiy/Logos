package ua.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Item;
import ua.entity.SpecificationDigital;
import ua.repository.CategoryRepository;
import ua.repository.ItemRepository;
import ua.repository.MeasuringSystemRepository;
import ua.repository.NameOfSpecificationDigitalRepository;
import ua.repository.ProducerRepository;
import ua.repository.SpecificationDigitalRepository;
import ua.repository.SpecificationStringRepository;
import ua.service.ItemService;
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
	public void save(Item item) {
		List<SpecificationDigital> sds = new ArrayList<>();
		for(SpecificationDigital specDidit : item.getSpecificationDigitals()){
			SpecificationDigital sd = specificationDigitalRepository.findByNosdValue(specDidit.getNameOfSpecificationDigital().getId(), specDidit.getValue());
			if(sd==null) sd = specDidit;
			specificationDigitalRepository.save(sd);
			sds.add(sd);
		}
		itemRepository.save(item);
		item.setSpecificationDigitals(sds);
		itemRepository.save(item);
	}
}
