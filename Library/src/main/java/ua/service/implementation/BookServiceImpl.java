package ua.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Book;
import ua.repository.BookRepository;
import ua.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book findOne(int id) {
		return bookRepository.findOne(id);
	}

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public void delete(int id) {
		bookRepository.delete(id);
	}

	@Override
	public void save(Book entity) {
		bookRepository.save(entity);
	}

}
