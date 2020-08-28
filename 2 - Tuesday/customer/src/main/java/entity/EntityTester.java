package entity;

import javax.persistence.*;

public class EntityTester {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em =  emf.createEntityManager();
        
        Customer customer2 = new Customer("Solvej", "Kofod");
        Customer customer1 = new Customer("Martin", "Eriksen");
        
        try {
        em.getTransaction().begin();
        em.persist(customer1);
        em.persist(customer2);
        em.getTransaction().commit();  
        } finally {
            em.close();
            emf.close();
        }
    }
}