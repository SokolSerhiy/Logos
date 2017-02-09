package ua.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.filter.ItemFilter;
import ua.dto.filter.SpecDigitFilter;
import ua.entity.Item;
import ua.entity.MeasuringSystem;
import ua.entity.SpecificationDigital;
import ua.entity.SpecificationString;

public class ItemSpecification implements Specification<Item>{
	
	private final ItemFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	public ItemSpecification(ItemFilter filter) {
		this.filter = filter;
	}
	
	private void filterBySpecDidital(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		for (SpecDigitFilter digitFilter : filter.getSpecDigitFilters()){
			DigitSpecification specification = new DigitSpecification(digitFilter);
			Predicate predicate = specification.toPredicate(root, query, cb);
			if(predicate!=null){
				System.out.println("Hello");
				predicates.add(predicate);
			}
		}
	}
	
	private void filterByProducer(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!filter.getProducerIds().isEmpty()){
			predicates.add(root.get("producer").in(filter.getProducerIds()));
		}
	}
	
	private void filterBySpecString(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(!filter.getSsIds().isEmpty()){
			Join<Item, SpecificationString> join = root.join("specificationStrings");
			predicates.add(join.get("id").in(filter.getSsIds()));
		}
	}
	
	private void filterByPrice(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMax()!=null&&filter.getMin()!=null){
			predicates.add(cb.between(root.get("price"), filter.getMin(), filter.getMax()));
		}else if(filter.getMax()!=null){
			predicates.add(cb.lessThanOrEqualTo(root.get("price"), filter.getMax()));
		}else if(filter.getMin()!=null){
			predicates.add(cb.greaterThanOrEqualTo(root.get("price"), filter.getMin()));
		}
	}
	
	private void filterBySearch(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getSearch().isEmpty()){
			predicates.add(cb.like(root.get("name"), filter.getSearch()+"%"));
		}
	}
	
	private void fetch(Root<Item> root, CriteriaQuery<?> query){
		if(query.getResultType()!=Long.class){
			root.fetch("category", JoinType.LEFT);
			root.fetch("producer", JoinType.LEFT);
		}
	}

	@Override
	public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		query.distinct(true);
		filterBySearch(root, query, cb);
		filterByPrice(root, query, cb);
		filterBySpecString(root, query, cb);
		filterByProducer(root, query, cb);
		filterBySpecDidital(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		predicates.toArray(array);
		return cb.and(array);
	}
	
	private class DigitSpecification implements Specification<Item>{
		
		private final SpecDigitFilter digitFilter;
		
		private final List<Predicate> predicatesDigit = new ArrayList<>();

		public DigitSpecification(SpecDigitFilter digitFilter) {
			this.digitFilter = digitFilter;
		}
		
		private void filterByValue(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
			if(digitFilter.getMaxValue()!=null&&digitFilter.getMinValue()!=null){
				filterByMinMaxValue(root, query, cb);
			}else if(digitFilter.getMaxValue()!=null){
				filterByMaxValue(root, query, cb);
			}else if(digitFilter.getMinValue()!=null){
				filterByMinValue(root, query, cb);
			}
		}
		
		private void filterByMinValue(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
			filterByNameId(root, query, cb);
			filterByMsId(root, query, cb);
			Join<Item, SpecificationDigital> join = root.join("specificationDigitals");
			predicatesDigit.add(cb.greaterThanOrEqualTo(join.get("value"), digitFilter.getMinValue()));
		}
		
		private void filterByMaxValue(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
			filterByNameId(root, query, cb);
			filterByMsId(root, query, cb);
			Join<Item, SpecificationDigital> join = root.join("specificationDigitals");
			predicatesDigit.add(cb.lessThanOrEqualTo(join.get("value"), digitFilter.getMaxValue()));
		}
		
		private void filterByMinMaxValue(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
			filterByNameId(root, query, cb);
			filterByMsId(root, query, cb);
			Join<Item, SpecificationDigital> join = root.join("specificationDigitals");
			predicatesDigit.add(cb.between(join.get("value"), digitFilter.getMinValue(), digitFilter.getMaxValue()));
		}
		
		private void filterByMsId(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
			if(digitFilter.getMsId()!=null){
				Join<Item, SpecificationDigital> join = root.join("specificationDigitals");
				Join<SpecificationDigital, MeasuringSystem> joinMs = join.join("measuringSystems");
				predicatesDigit.add(cb.equal(joinMs.get("id"), digitFilter.getMsId()));
			}
		}
		
		private void filterByNameId(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb){
			if(digitFilter.getNameId()!=null){
				Join<Item, SpecificationDigital> join = root.join("specificationDigitals");
				predicatesDigit.add(cb.equal(join.get("nameOfSpecificationDigital"), digitFilter.getNameId()));
			}
		}

		@Override
		public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
			filterByValue(root, query, cb);
			if(predicatesDigit.isEmpty())return null;
			filterByMsId(root, query, cb);
			filterByNameId(root, query, cb);
			Predicate[] array = new Predicate[predicatesDigit.size()];
			predicatesDigit.toArray(array);
			return cb.and(array);
		}
	}
}
