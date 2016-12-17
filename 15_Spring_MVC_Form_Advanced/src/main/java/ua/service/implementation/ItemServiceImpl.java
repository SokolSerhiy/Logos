package ua.service.implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.entity.Category;
import ua.entity.Item;
import ua.entity.MeasuringSystem;
import ua.entity.Producer;
import ua.entity.SpecificationDigital;
import ua.entity.SpecificationString;
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
	public void save(int categoryId, String name, int producerId,
			List<Integer> ssIds, List<Integer> nosdIds,
			List<BigDecimal> sdValues, List<Integer> mss, BigDecimal price,
			int msId) {
		List<SpecificationDigital> sds = new ArrayList<>();
		for(int i = 0; i < nosdIds.size(); i++){
			SpecificationDigital sd = specificationDigitalRepository.findByNosdValue(nosdIds.get(i), sdValues.get(i));
			if(sd==null){
				sd = new SpecificationDigital();
				sd.setValue(sdValues.get(i));
				sd.setNameOfSpecificationDigital(nameOfSpecificationDigitalRepository.findOne(nosdIds.get(i)));
				specificationDigitalRepository.save(sd);
				sd.getMeasuringSystems().add(measuringSystemRepository.findOne(mss.get(i)));
				specificationDigitalRepository.save(sd);
			}else{
				MeasuringSystem ms = measuringSystemRepository.findOne(mss.get(i));
				if(!sd.getMeasuringSystems().contains(ms)){
					sd.getMeasuringSystems().add(ms);
				}
				specificationDigitalRepository.save(sd);
			}
			sds.add(sd);
		}
		Category category = categoryRepository.findOne(categoryId);
		List<SpecificationString> sss = specificationStringRepository.findAll(ssIds);
		MeasuringSystem ms = measuringSystemRepository.findOne(msId);
		Producer producer = producerRepository.findOne(producerId);
		Item item = new Item();
		item.setCategory(category);
		item.setName(name);
		item.setProducer(producer);
		item.setPrice(price);
		itemRepository.save(item);
		item.getMeasuringSystems().add(ms);
		item.setSpecificationDigitals(sds);
		item.setSpecificationStrings(sss);
		itemRepository.save(item);
	}

}
