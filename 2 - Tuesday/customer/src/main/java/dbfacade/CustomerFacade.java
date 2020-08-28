package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class CustomerFacade {
    
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;
    
    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("pu");
        getCustomerFacade(emf);
        
        Customer customer = instance.findCustomerById(1);
        System.out.println("\nTesting findCustomerById() : \n" + customer.getFirstName() + " " + customer.getLastName());
        
//        facade.addCustomer("Poul", "Hansen");
//        facade.addCustomer("Yrsa", "Hansen");
//        facade.addCustomer("Niels", "Petersen");

        System.out.println("\nTesting getCustomerByLastName() :");
        for (Customer c : instance.getCustomerByLastName("Petersen", emf)) {
            System.out.println("Name: " + c.getFirstName() + ", " + "Last name: " + c.getLastName() + ", " + "ID: " +c.getId());
        }
        
        System.out.println("\nNumber of customers in the database: " +  instance.getNumberOfCustomers());
        
        System.out.println("\nTesting getAllCustomers() : ");
        for (Customer c : instance.getAllCustomers()) {
            System.out.println(c.toString());
        }
        
    }
    
    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) { 
        if (instance == null) {
            emf = _emf; 
            instance = new CustomerFacade();
        }
        return instance;
    }
    
     public Customer findCustomerById(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Customer customer = em.find(Customer.class,id);
            return customer;
        }finally {
            em.close();
        }
    }
     
     public List<Customer> getCustomerByLastName(String lastName, EntityManagerFactory emf){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = 
                       em.createQuery("Select c from Customer c where c.lastName =  :lastName" , Customer.class);
            query.setParameter("lastName", lastName);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
     
     public int getNumberOfCustomers() {
         EntityManager em = emf.createEntityManager();
         try {
         Query q1 = em.createQuery("SELECT COUNT(c) FROM Customer c");
         return Integer.parseInt(q1.getSingleResult().toString());
         } finally {
             em.close();
         }
     }
     
     public List<Customer> getAllCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
        Query q = em.createQuery("SELECT c FROM Customer c");
        List<Customer> customerList = q.getResultList();
        return customerList;
        } finally {
            em.close();
        }
     }
     
     public Customer addCustomer(String firstName, String lastName){
        Customer customer = new Customer(firstName, lastName);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        }finally {
            em.close();
        }
    }
    
     public void deleteCustomer(String firstName) { // For testing
        
         EntityManager em = emf.createEntityManager();
         try {
             em.getTransaction().begin();
             TypedQuery q = 
                     em.createQuery("DELETE FROM Customer c WHERE c.firstName = :firstName", Customer.class);
             q.setParameter("firstName", firstName);
             q.executeUpdate();
             em.getTransaction().commit();
         } finally {
             em.close();
         }
         
     }
}
