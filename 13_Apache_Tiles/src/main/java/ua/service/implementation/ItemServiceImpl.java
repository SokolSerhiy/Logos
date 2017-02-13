package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Item;
import ua.repository.ItemRepository;
import ua.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public void delete(int id) {
		itemRepository.delete(id);
	}

	@Override
	public List<Item> findByCategoryId(int id) {
		return itemRepository.findByCategoryId(id);
	}

}
