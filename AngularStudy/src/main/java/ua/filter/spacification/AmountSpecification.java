package ua.filter.spacification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.entity.Amount;
import ua.filter.AmountFilter;

public class AmountSpecification implements Specification<Amount>{
	
	private final AmountFilter filter;
	
	public AmountSpecification(AmountFilter filter) {
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<Amount> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if(query.getResultType()!=Long.class){
			root.fetch("system");
			root.fetch("ingredient");
		}
		return null;
	}

}
