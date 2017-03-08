package ua.specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.filter.AmountFilter;
import ua.entity.Amount;

public class AmountSpecification implements Specification<Amount>{

	private final AmountFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	private final static Pattern PATTERN = Pattern.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");
	
	public AmountSpecification(AmountFilter filter) {
		this.filter = filter;
		if(PATTERN.matcher(filter.getMax()).matches()){
			filter.setMaxValue(new BigDecimal(filter.getMax().replace(',', '.')));
		}
		if(PATTERN.matcher(filter.getMin()).matches()){
			filter.setMinValue(new BigDecimal(filter.getMin().replace(',', '.')));
		}
	}
	
	private void filterByIngredient(Root<Amount> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getIngredientIds().isEmpty()){
			predicates.add(root.get("ingredient").in(filter.getIngredientIds()));
		}
	}
	
	private void filterBySystem(Root<Amount> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getSystemIds().isEmpty()){
			predicates.add(root.get("system").in(filter.getSystemIds()));
		}
	}
	
	private void filterByAmount(Root<Amount> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMaxValue()!=null){
			predicates.add(cb.le(root.get("amount"), filter.getMaxValue()));
		}
		if(filter.getMinValue()!=null){
			predicates.add(cb.ge(root.get("amount"), filter.getMinValue()));
		}
	}
	
	private void fetch(Root<Amount> root, CriteriaQuery<?> query){
		if(!query.getResultType().equals(Long.class)){
			query.distinct(true);
			root.fetch("system");
			root.fetch("ingredient");
		}
	}

	@Override
	public Predicate toPredicate(Root<Amount> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		filterByAmount(root, query, cb);
		filterBySystem(root, query, cb);
		filterByIngredient(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
	}
}
