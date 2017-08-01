package ua.service.implementation;

import static ua.mapper.ApartmentMapper.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.domain.request.ApartmentRequest;
import ua.domain.view.ApartmentIndex;
import ua.entity.Apartment;
import ua.repository.ApartmentRepository;
import ua.service.ApartmentService;

@Service
public class ApartmentServiceImpl extends CrudServiceImpl<Apartment, Integer> implements ApartmentService{

	private final ApartmentRepository repository;
	
	@Autowired
	public ApartmentServiceImpl(ApartmentRepository repository) {
		super(repository);
		this.repository = repository;
	}

//	@Override
//	public List<ApartmentIndex> findAllIndex() {
//		return toApartmentIndex(repository.findAllRentTypeAndAreaLoaded());
//	}
	
	@Override
	public List<ApartmentIndex> findAllIndex() {
		return repository.findApartmentIndex();
	}

	@Override
	public void save(ApartmentRequest request) {
		repository.save(toApartment(request));
	}

	@Override
	public List<String> findRentTypeNames() {
		return repository.findRentTypeNames();
	}

	@Override
	public List<String> findAreaNames() {
		return repository.findAreaNames();
	}

	@Override
	public List<String> findStreetNames() {
		return repository.findStreetNames();
	}
}
