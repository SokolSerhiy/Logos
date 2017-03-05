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
import ua.entity.NameOfSpecificationString;

public class NameOfSpecificationStringExludeSpecification implements Specification<NameOfSpecificationString>{

	private final BasicFilter filter;
	
	private final Category category;
	
	private final List<Predicate> predicates = new ArrayList<>();

	public NameOfSpecificationStringExludeSpecification(BasicFilter filter, Category category) {
		this.filter = filter;
		this.category = category;
	}
	
	private void addExludeFilter(Root<NameOfSpecificationString> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!category.getNameOfSpecificationStrings().isEmpty()){
			List<Integer> ids = category.getNameOfSpecificationStrings().stream()
					.map(NameOfSpecificationString::getId).collect(Collectors.toList());
			Predicate predicate = cb.not(root.get("id").in(ids));
			predicates.add(predicate);
		}
	}
	
	private void addFilterByName(Root<NameOfSpecificationString> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getSearch().isEmpty()){
			Predicate predicate = cb.like(root.get("name"), filter.getSearch()+"%");
			predicates.add(predicate);
		}
	}

	@Override
	public Predicate toPredicate(Root<NameOfSpecificationString> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(category.getNameOfSpecificationStrings().isEmpty()&&filter.getSearch().isEmpty())return null;
		addExludeFilter(root, query, cb);
		addFilterByName(root, query, cb);
		Predicate[] result = new Predicate[predicates.size()];
		return cb.and(predicates.toArray(result));
	}
}
