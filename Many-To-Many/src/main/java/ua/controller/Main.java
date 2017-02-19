package ua.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ua.entity.Author;
import ua.entity.AuthorBook;
import ua.entity.Book;

public class Main {
	//Початок в Author
	public static void main(String[] args) {
		final EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("primary");
		final EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		//Створюємо і зберігаємо всіх дійових осіб
		Author author1 = new Author("A");
		Author author2 = new Author("B");
		Author author3 = new Author("C");
		Book book1 = new Book("A");
		Book book2 = new Book("B");
		em.persist(author1);
		em.persist(author2);
		em.persist(author3);
		em.persist(book1);
		em.persist(book2);
		em.getTransaction().commit();
		
		
		em.getTransaction().begin();
		//Перший варіант класний тим що не потрібно турбуватись
		//про сторону, захотіли, додали автору книжку,
		//захотіли додали книжці автора
		//не паримось, а додаєм куди взбреде.
		System.out.println("Перший варінт зв'язок----------------------------------------------");
		author1.getBooks1().add(book2);
		book1.getAuthors1().add(author2);
		em.getTransaction().commit();
		
		
		em.getTransaction().begin();
		//Другий варіант: потрібно пам'ятати де відповідальна
		//сторона і тільки туди додаємо.
		//В моєму прикладі можна додавати лише
		//авторів до книжок
		//третій автор так і залишиться без жодної книжки
		System.out.println("Другий варінт зв'язок----------------------------------------------");
		book1.getAuthors2().add(author2);
		book2.getAuthors2().add(author1);
		author3.getBooks2().add(book1);
		em.getTransaction().commit();
		
		
		em.getTransaction().begin();
		System.out.println("Третій варінт зв'язок----------------------------------------------");
		//Жодних колекцій, створюємо ентіті
		//встановлюємо автора і книгу
		//зберігаємо
		//процедуру повторити якщо авторів більше одного
		//книга весь час та сама, автори змінюються
		AuthorBook authorBook1 = new AuthorBook(author2, book1);
		AuthorBook authorBook2 = new AuthorBook(author1, book2);
		em.persist(authorBook1);
		em.persist(authorBook2);
		em.getTransaction().commit();
		
		
		em.getTransaction().begin();
		System.out.println("Перший варінт select----------------------------------------------");
		Book book = em.createQuery("SELECT b FROM Book b JOIN b.authors1 a WHERE a.id = ?1", Book.class)
				.setParameter(1, 1).getSingleResult();
		System.out.println("Назва книги, очікуємо В = "+book.getTitle());
		book = em.createQuery("SELECT b FROM Author a JOIN a.books1 b WHERE a.id = ?1", Book.class)
				.setParameter(1, 1).getSingleResult();
		System.out.println("Назва книги, очікуємо В = "+book.getTitle());
		//Джоіни можна писати з любої сторони звязку, як і в випадку з ManyToOne i OneToMany
		System.out.println("Другий варінт select----------------------------------------------");
		book = em.createQuery("SELECT b FROM Book b JOIN b.authors2 a WHERE a.id = ?1", Book.class)
				.setParameter(1, 1).getSingleResult();
		System.out.println("Назва книги, очікуємо В = "+book.getTitle());
		book = em.createQuery("SELECT b FROM Author a JOIN a.books2 b WHERE a.id = ?1", Book.class)
				.setParameter(1, 1).getSingleResult();
		System.out.println("Назва книги, очікуємо В = "+book.getTitle());
		//Джоіни можна писати з любої сторони звязку тому що це і є ManyToOne i OneToMany
		System.out.println("Третій варінт зв'язок----------------------------------------------");
		book = em.createQuery("SELECT b FROM Book b JOIN b.authorBooks ab JOIN ab.author a WHERE a.id = ?1", Book.class)
				.setParameter(1, 1).getSingleResult();
		System.out.println("Назва книги, очікуємо В = "+book.getTitle());
		book = em.createQuery("SELECT b FROM Author a JOIN a.authorBooks ab JOIN ab.book b WHERE a.id = ?1", Book.class)
				.setParameter(1, 1).getSingleResult();
		System.out.println("Назва книги, очікуємо В = "+book.getTitle());
		em.getTransaction().commit();
		
		
		em.getTransaction().begin();
		//Отже рахуємо кількість книжок в автора 3, в нього їх 0
		long count = em.createQuery("SELECT count(b.id) FROM Author a LEFT JOIN a.books1 b WHERE a.id = ?1", Long.class)
				.setParameter(1, 3).getSingleResult();
		System.out.println("Кількість книжок в 3-го автора = "+count);
		count = em.createQuery("SELECT count(b.id) FROM Author a LEFT JOIN a.books2 b WHERE a.id = ?1", Long.class)
				.setParameter(1, 3).getSingleResult();
		System.out.println("Кількість книжок в 3-го автора = "+count);
		//Третій варіант переможець, порахуйте кількість джоінів в запиті
		//в двох вище по 2 джоіни, в третьому, що нижче 1 джоін
		count = em.createQuery("SELECT count(ab.id) FROM Author a LEFT JOIN a.authorBooks ab WHERE ab.author.id = ?1", Long.class)
				.setParameter(1, 3).getSingleResult();
		System.out.println("Кількість книжок в 3-го автора = "+count);
		em.getTransaction().commit();
		
		
		//тут важлива кількість запитів тому пишу в окремих транзакціях
		//Оновлюємо зв'язок, заміняємо книгу в автора 1 з 2-ї на 1-шу
		//Перший варіант, важлива кількість запитів, для лінтяїв напишу тут 2
		System.out.println("Перший оновлення початок-------------------------------------------");
		em.getTransaction().begin();
		Author author = em.find(Author.class, 1);
		author.getBooks1().removeIf(b->b.getId()==2);
		author.getBooks1().add(book1);
		em.getTransaction().commit();
		System.out.println("Перший оновлення кінець-------------------------------------------");
		//Другий варіант, ужасний просто, тут 4
		System.out.println("Другий оновлення початок-------------------------------------------");
		em.getTransaction().begin();
		book = em.find(Book.class, 2);
		book.getAuthors2().removeIf(a->a.getId()==1);
		book = em.find(Book.class, 1);
		book.getAuthors2().add(author2);
		em.getTransaction().commit();
		System.out.println("Другий оновлення кінець-------------------------------------------");
		//Третій, як на мене самий класний, тут 1
		System.out.println("Третій оновлення початок-------------------------------------------");
		em.getTransaction().begin();
		em.createQuery("UPDATE AuthorBook ab SET ab.book.id=1 WHERE ab.author=1 and ab.book.id = 2").executeUpdate();
		em.getTransaction().commit();
		System.out.println("Третій оновлення кінець-------------------------------------------");
		//Останнє, видалення зв'язку, що б було цікавіше
		//після останніх маніпуляцій в нас вийшло, що в 
		//першої книги 2 автори, їх ми і видалятимемо
		//точніше зв'язок з ними
		em.close();
		//Що б з кешу не тягнуло закрив EntityManager
		//так веселіше
		//знову рахуємо запити
		//знову для лінтяїв перший 3, другий 2, третій 1
		final EntityManager em1 = factory.createEntityManager();
		System.out.println("Перший видалення початок-------------------------------------------");
		em1.getTransaction().begin();
		book = em1.find(Book.class, 1);
		book.getAuthors1().clear();
		em1.getTransaction().commit();
		System.out.println("Перший ввидалення кінець-------------------------------------------");
		System.out.println("Другий видалення початок-------------------------------------------");
		em1.getTransaction().begin();
		book = em1.find(Book.class, 1);
		book.getAuthors2().clear();
		em1.getTransaction().commit();
		System.out.println("Другий ввидалення кінець-------------------------------------------");
		System.out.println("Третій видалення початок-------------------------------------------");
		em1.getTransaction().begin();
		em1.createQuery("DELETE FROM AuthorBook ab WHERE ab.book.id = 1").executeUpdate();
		em1.getTransaction().commit();
		System.out.println("Третій ввидалення кінець-------------------------------------------");
		em1.close();
		factory.close();
		//Коректність роботи гарантується при запуску на свіжу базу, без таблиць
		//всі подальші запуски рекомендую робити після:
		//drop database manytomany;
		//CREATE database manytomany DEFAULT CHARACTER SET utf8;
	}
}
