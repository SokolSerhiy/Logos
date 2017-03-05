package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.dto.filter.ItemFilter;
import ua.dto.form.ItemForm;
import ua.entity.Item;

public interface ItemService {

	List<Item> findAll();

	void delete(int id);

	void save(ItemForm item);

	Page<Item> findAll(ItemFilter filter, Pageable pageable);

	int findCount(int id);

	List<Item> findByUserId(int userId);

}
