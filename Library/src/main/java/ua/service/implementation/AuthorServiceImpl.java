package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Author;
import ua.repository.AuthorRepository;
import ua.service.AuthorService;
@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
	private AuthorRepository authorRepository;
	
	@Override
	public Author findOne(int id) {
		return authorRepository.findOne(id);
	}

	@Override
	public List<Author> findAll() {
		return authorRepository.findAll();
	}

	@Override
	public void delete(int id) {
		authorRepository.delete(id);
	}

	@Override
	public void save(Author entity) {
		authorRepository.save(entity);
	}

}
