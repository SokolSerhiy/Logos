package ua.lviv.lgs.dao.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.lgs.dao.AuthorDao;
import ua.lviv.lgs.domain.Author;

@Repository
public class AuthorDaoImpl implements AuthorDao{
	@PersistenceContext(unitName = "Primary")
	private EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}
	
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	public void insertAuthor(Author author) {
		em.persist(author);
	}
	
	@Transactional
	public List<Author> getAllAuthors() {
		return em.createQuery("from Author").getResultList();
	}

	@Transactional
	public Author findByNameAndAge(String name, int age) {
		return (Author) em.createNamedQuery("Author.getByNameAndAge")
				.setParameter("name", name).setParameter("age", age)
				.getSingleResult();
	}
}
