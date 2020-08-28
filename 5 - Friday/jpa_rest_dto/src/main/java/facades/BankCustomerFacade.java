package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;


public class BankCustomerFacade {

    private static BankCustomerFacade instance;
    private static EntityManagerFactory emf;
    
    
    private BankCustomerFacade() {}
    
    
    public static BankCustomerFacade getBankCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankCustomerFacade();
        }
        return instance;
    }
    
    public CustomerDTO getCustomerByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery
            ("SELECT b FROM BankCustomer b WHERE b.id = :id", BankCustomer.class);
            query.setParameter("id", id);
            BankCustomer bankCustomer = (BankCustomer) query.getSingleResult();
            return new CustomerDTO(bankCustomer);
        } finally {
            em.close();
        }
    }
    
    public List<CustomerDTO> getCustomerByName(String name) {
        EntityManager em = emf.createEntityManager();
        List<CustomerDTO> dtoList = new ArrayList<>();
        try {
            TypedQuery query = em.createQuery
            ("SELECT b FROM BankCustomer b WHERE b.firstName = :name", BankCustomer.class);
            query.setParameter("name", name);
            List<BankCustomer> customers = query.getResultList();
            for (BankCustomer b : customers) {
                dtoList.add(new CustomerDTO(b));
            }
            return dtoList;
        } finally {
            em.close();
        }
    }
    
    public BankCustomer addCustomer(BankCustomer customer) {
        EntityManager em = instance.getEntityManager();
        try {
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        return customer;
        } finally {
            em.close();
        }      
    }
    
    public List<BankCustomer> getAllBankCustomers() {
        EntityManager em = instance.getEntityManager();
        try {
            TypedQuery query = em.createQuery
            ("SELECT b FROM BankCustomer b", BankCustomer.class);
            List<BankCustomer> bCustomers = query.getResultList();
            return bCustomers;
        } finally {
            em.close();
        }
    }
    
    public void deleteBankCustomer(String name) { // For testing
        EntityManager em = instance.getEntityManager();
        try {
        em.getTransaction().begin();
        TypedQuery query = 
        em.createQuery("DELETE FROM BankCustomer b WHERE b.firstName = :name", BankCustomer.class);
        query.setParameter("name", name);
        query.executeUpdate();
        em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
