package ua.repository.impl;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import ua.domain.filter.ApartmentFilter;
import ua.domain.view.ApartmentIndex;
import ua.entity.Apartment;
import ua.entity.Apartment_;
import ua.entity.Area;
import ua.entity.Area_;
import ua.entity.RentType;
import ua.entity.RentType_;
import ua.entity.Street;
import ua.entity.Street_;
import ua.repository.ApartmentIndexRepository;

@Repository
public class ApartmentIndexRepositoryImpl implements ApartmentIndexRepository{

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public Page<ApartmentIndex> findAllApartmentIndex(ApartmentFilter filter, Pageable pageable) {
		QueryCreator creator = new QueryCreator(em, filter, pageable.getSort());
		CriteriaQuery<ApartmentIndex> query = creator.getQuery();
		List<ApartmentIndex> content = em.createQuery(query).getResultList();
		QueryCreator countCreator = new QueryCreator(em, filter, pageable.getSort());
		return PageableExecutionUtils.getPage(content, pageable, ()->em.createQuery(countCreator.getCountQuery()).getSingleResult());
	}
	
	private static class QueryCreator{
		
		private final ApartmentFilter filter;
		
		private final Sort sort;
		
		private final CriteriaBuilder cb;
		
		private Root<Apartment> root;
		
		private Join<Apartment, Area> areaJoin;
		
		private Join<Apartment, RentType> rentTypeJoin;
		
		private final List<Specification<Apartment>> specifications = new ArrayList<>();
		
		public QueryCreator(EntityManager em, ApartmentFilter filter, Sort sort) {
			this.filter = filter;
			this.sort = sort;
			cb = em.getCriteriaBuilder();
		}
		
		public CriteriaQuery<ApartmentIndex> getQuery(){
			CriteriaQuery<ApartmentIndex> query = cb.createQuery(ApartmentIndex.class);
			createRoot(query);
			select(query);
			filter();
			query.where(buildPredicates(query));
			query.orderBy(toOrders(sort, root, cb));
			return query;
		}
		
		public CriteriaQuery<Long> getCountQuery(){
			CriteriaQuery<Long> query = cb.createQuery(Long.class);
			createRoot(query);
			selectCount(query);
			filter();
			query.where(buildPredicates(query));
			return query;
		}
		
		public void createRoot(CriteriaQuery<?> query) {
			root = query.from(Apartment.class);
			areaJoin = root.join(Apartment_.area);
			rentTypeJoin = root.join(Apartment_.rentType);
		}
		
		private void select(CriteriaQuery<ApartmentIndex> query) {
			query.multiselect(root.get(Apartment_.id),
					root.get(Apartment_.photoUrl),
					root.get(Apartment_.version),
					root.get(Apartment_.price),
					rentTypeJoin.get(RentType_.name),
					areaJoin.get(Area_.name),
					root.get(Apartment_.rate),
					root.get(Apartment_.rooms));
		}
		
		private void selectCount(CriteriaQuery<Long> query) {
			query.select(cb.count(root));
		}
		
		private Predicate[] buildPredicates(CriteriaQuery<?> query) {
			return specifications.stream().map(spec->spec.toPredicate(root, query, cb)).toArray(Predicate[]::new);
		}
		
		private void filter() {
			filterByMinMax();
			filterByStreet();
			filterByMinRate();
			filterByRentType();
			filterByArea();
		}
		
		private void filterByMinMax() {
			if(!filter.getMin().isEmpty()) {
				specifications.add((root, query, cb)-> cb.ge(root.get(Apartment_.price), new BigDecimal(filter.getMin())));
			}
			if(!filter.getMax().isEmpty()) {
				specifications.add((root, query, cb)-> cb.le(root.get(Apartment_.price), new BigDecimal(filter.getMax())));
			}
		}
		
		private void filterByStreet() {
			if(!filter.getStreet().isEmpty()) {
				specifications.add((root, query, cb)-> {
					Join<Apartment, Street> streetJoin = root.join(Apartment_.street);
					return cb.like(streetJoin.get(Street_.name), filter.getStreet()+"%");
				});
			}
		}
		
		private void filterByMinRate() {
			if(!filter.getMinRate().isEmpty()) {
				specifications.add((root, query, cb)-> cb.ge(root.get(Apartment_.rate), new BigDecimal(filter.getMinRate())));
			}
		}
		
		private void filterByRentType() {
			if(!filter.getRentType().isEmpty()) {
				specifications.add((root, query, cb)-> cb.equal(rentTypeJoin.get(RentType_.name), filter.getRentType()));
			}
		}
		
		private void filterByArea() {
			if(!filter.getAreas().isEmpty()) {
				specifications.add((root, query, cb)-> areaJoin.get(Area_.name).in(filter.getAreas()));
			}
		}
	}
}
