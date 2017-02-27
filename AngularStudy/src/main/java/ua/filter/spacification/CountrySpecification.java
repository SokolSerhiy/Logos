package ua.filter.spacification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.Country;
import ua.filter.BasicFilter;

public class CountrySpecification implements Specification<Country>{

	private final BasicFilter filter;

	public CountrySpecification(BasicFilter filter) {
		this.filter = filter;
	}
	
	@Override
	public Predicate toPredicate(Root<Country> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if(filter.getSearch().isEmpty()) return null;
		return cb.like(root.get("name"), filter.getSearch()+"%");
	}
}
