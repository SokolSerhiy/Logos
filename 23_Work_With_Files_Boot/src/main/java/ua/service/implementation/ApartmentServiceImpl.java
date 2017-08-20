package ua.service.implementation;

import static ua.mapper.ApartmentMapper.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.domain.filter.ApartmentFilter;
import ua.domain.request.ApartmentRequest;
import ua.domain.view.ApartmentIndex;
import ua.entity.Apartment;
import ua.repository.ApartmentIndexRepository;
import ua.repository.ApartmentRepository;
import ua.service.ApartmentService;

@Service
public class ApartmentServiceImpl extends CrudServiceImpl<Apartment, Integer> implements ApartmentService{

	private final ApartmentRepository repository;
	
	private final ApartmentIndexRepository executor;
	
	@Value("${file.path}")
	private String path;
	
	@Autowired
	public ApartmentServiceImpl(ApartmentRepository repository,  ApartmentIndexRepository executor) {
		super(repository);
		this.repository = repository;
		this.executor = executor;
	}

//	@Override
//	public List<ApartmentIndex> findAllIndex() {
//		return toApartmentIndex(repository.findAllRentTypeAndAreaLoaded());
//	}
	
	@Override
	public Page<ApartmentIndex> findAllIndex(Pageable pageable) {
		return repository.findApartmentIndex(pageable);
	}

	@Override
	public void save(ApartmentRequest request) {
		Apartment apartment = toApartment(request);
		MultipartFile file = request.getFile();
		if(file!=null&&!file.isEmpty()) {
			String extention = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
			String fileName = UUID.randomUUID().toString()+extention;
			try {
				file.transferTo(new File(path+fileName));
				apartment.setPhotoUrl(fileName);
				apartment.setVersion(1);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(apartment.getPhotoUrl());
		repository.save(apartment);
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

	@Override
	public List<ApartmentIndex> findTop5ByRate() {
		return repository.findTop5ByRate(new PageRequest(0, 5));
	}

	@Override
	public Page<ApartmentIndex> findByFilter(ApartmentFilter filter, Pageable pageable) {
		return executor.findAllApartmentIndex(filter, pageable);
	}
}
