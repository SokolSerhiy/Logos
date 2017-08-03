package ua.service.implementation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.domain.filter.SimpleFilter;
import ua.entity.Area;
import ua.repository.AreaRepository;
import ua.service.AreaService;

@Service
public class AreaServiceImpl extends CrudServiceImpl<Area, Integer> implements AreaService{

	private final AreaRepository repository;
	
	@Autowired
	public AreaServiceImpl(AreaRepository repository) {
		super(repository);
		this.repository = repository;
	}

	@Override
	public List<String> findNames() {
		return repository.findNames();
	}

	@Override
	public Area findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Page<Area> findAll(Pageable pageable, SimpleFilter filter) {
		return repository.findAll(getSpecification(filter), pageable);
	}
	
	private Specification<Area> getSpecification(SimpleFilter filter){
		return new Specification<Area>() {
			@Override
			public Predicate toPredicate(Root<Area> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				if (filter.getSearch().isEmpty()) return null;
				return cb.like(root.get("name"), filter.getSearch()+"%");
			}
		};
	}
}