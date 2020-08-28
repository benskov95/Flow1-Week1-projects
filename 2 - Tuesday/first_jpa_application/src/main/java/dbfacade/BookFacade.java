package dbfacade;

import entity.Book;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class BookFacade {
    
    private static EntityManagerFactory emf; // Only instantiated once since it's static
    private static BookFacade instance; // Only instantiated once since it's static
    
    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("pu");    // "emf" is initialized before it's used in getBookFacade(). 
        getBookFacade(emf);

        Book b1 = instance.addBook("Author 1");
        Book b2 = instance.addBook("Author 2");

        //Find book by ID
        System.out.println("Book1: "+ instance.findBook(b1.getId()).getAuthor());
        System.out.println("Book2: "+ instance.findBook(b2.getId()).getAuthor());

        //Find all books
        System.out.println("Number of books: "+ instance.getAllBooks().size());
  
    }

    /**
     * getBookFacade() is a singleton method which
     * ensures that the "instance" and "emf" variables,
     * which are BookFacade and EntityManagerFactory
     * objects respectively, are only instantiated once.
     * @param _emf
     * The EntityManagerFactory object, emf, is
     * used to create multiple instances of
     * EntityManager objects that can be used
     * by a database.
     * @return 
     * The instantiated BookFacade object, instance.
     */
    public static BookFacade getBookFacade(EntityManagerFactory _emf) { 
        if (instance == null) {
            emf = _emf; // Doesn't seem like there is any point in doing this, can remove without problems (along with the parameter in this method).
            instance = new BookFacade();
        }
        return instance;
    }
    
    /**
     * The addBook() method is used to
     * add a Book object to the database.
     * @param author
     * Part of a Book object's attributes
     * and used here to add a new Book
     * object.
     * @return
     * The created Book object.
     */
    public Book addBook(String author){
        Book book = new Book(author);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
            return book;
        }finally {
            em.close();
        }
    }
    
    /**
     * The findBook() method is used to
     * find a specific book in the database
     * with a provided id.
     * @param id
     * The id of the book that the user
     * wants to find in the database.
     * @return 
     * The book whose id matched
     * with the provided one (if one
     * did).
     */
    public Book findBook(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Book book = em.find(Book.class,id);
            return book;
        }finally {
            em.close();
        }
    }
    
    /**
     * The getAllBooks() method is used to
     * retrieve all of the rows in the BOOK
     * table in the database(i.e. Book objects)
     * and load them into a List.
     * @return 
     * The list of Book objects retrieved from
     * the database.
     */
    public List<Book> getAllBooks(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Book> query = 
                       em.createQuery("Select book from Book book",Book.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }

    
}
