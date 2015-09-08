package ua.lviv.lgs.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ua.lviv.lgs.domain.Author;

public class AuthorDao {
	// SessionFactory property, described in appContext.xml, dao bean.
	// SessionFactory поле класу, описане в appContext.xml, dao bean.
	private SessionFactory factory;
	
	// Spring requires getter and setter for prorepty.
	// Spring вимагає гетери і сетери для властивостей класу, щоб встановити в них значення, прописане в контексті (sessionFactory bean).
	public SessionFactory getFactory() {
		return factory;
	}
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public void insertAuthor(Author author) {
		Session s = factory.openSession();
		s.beginTransaction();
		s.save(author);
		s.getTransaction().commit();
		s.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Author> getAllAuthors() {
		Session s = factory.openSession();
		return s.createQuery("from Author").list();
	}
}
