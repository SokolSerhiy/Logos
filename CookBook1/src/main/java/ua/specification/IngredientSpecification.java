package ua.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.filter.SimpleFilter;
import ua.entity.Ingredient;

public class IngredientSpecification implements Specification<Ingredient>{

	private final SimpleFilter filter;
	
	public IngredientSpecification(SimpleFilter filter) {
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<Ingredient> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(filter.getSearch().isEmpty())return null;
		return cb.like(cb.lower(root.get("name")), filter.getSearch().toLowerCase()+"%");
	}
}