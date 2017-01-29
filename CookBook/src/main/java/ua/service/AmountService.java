package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Amount;

public interface AmountService extends CrudService<Amount, Long>{

	Page<Amount> findAll(Pageable pageable);

}
