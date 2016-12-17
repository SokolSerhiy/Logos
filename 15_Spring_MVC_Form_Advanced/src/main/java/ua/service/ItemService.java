package ua.service;

import java.math.BigDecimal;
import java.util.List;

import ua.entity.Item;

public interface ItemService {

	List<Item> findAll();

	void delete(int id);

	void save(int categoryId, String name, int producerId, List<Integer> ssIds,
			List<Integer> nosdIds, List<BigDecimal> sdValues,
			List<Integer> mss, BigDecimal price, int msId);

}
