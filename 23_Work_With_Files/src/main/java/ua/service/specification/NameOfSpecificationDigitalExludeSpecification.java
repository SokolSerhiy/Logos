package ua.service.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.dto.filter.BasicFilter;
import ua.entity.Category;
import ua.entity.NameOfSpecificationDigital;

public class NameOfSpecificationDigitalExludeSpecification implements Specification<NameOfSpecificationDigital>{

	private final BasicFilter filter;
	
	private final Category category;
	
	private final List<Predicate> predicates = new ArrayList<>();

	public NameOfSpecificationDigitalExludeSpecification(BasicFilter filter, Category category) {
		this.filter = filter;
		this.category = category;
	}
	
	private void addExludeFilter(Root<NameOfSpecificationDigital> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!category.getNameOfSpecificationDigitals().isEmpty()){
			List<Integer> ids = category.getNameOfSpecificationDigitals().stream()
					.map(NameOfSpecificationDigital::getId).collect(Collectors.toList());
			Predicate predicate = cb.not(root.get("id").in(ids));
			predicates.add(predicate);
		}
	}
	
	private void addFilterByName(Root<NameOfSpecificationDigital> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getSearch().isEmpty()){
			Predicate predicate = cb.like(root.get("name"), filter.getSearch()+"%");
			predicates.add(predicate);
		}
	}

	@Override
	public Predicate toPredicate(Root<NameOfSpecificationDigital> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(category.getNameOfSpecificationDigitals().isEmpty()&&filter.getSearch().isEmpty())return null;
		addExludeFilter(root, query, cb);
		addFilterByName(root, query, cb);
		Predicate[] result = new Predicate[predicates.size()];
		return cb.and(predicates.toArray(result));
	}
}
