package ua.lviv.lgs.dao.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.lviv.lgs.dao.AuthorDao;
import ua.lviv.lgs.domain.Author;

@Repository
public class AuthorDaoImpl implements AuthorDao {

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
		// TODO Auto-generated method stub
		return em.createQuery("from Author").getResultList();
	}

	public Author findByName(String name) {
		// TODO Auto-generated method stub
		return (Author) em.createNamedQuery("Author.FindByName")
				.setParameter("name", name).setMaxResults(1).getSingleResult();
	}

	public List<Author> findByAgePeriod(int from, int till) {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Author.FindByAgePeriod")
				.setParameter("from", from).setParameter("till", till)
				.getResultList();
	}

}
