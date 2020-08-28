package entities;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class MakeTestData {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(new BankCustomer("Sven", "Eriksen", "492e1", 5733, 2, "Mechanic. Bad spending habits."));
        em.persist(new BankCustomer("Anne", "Klausen", "5a63f", 33549, 6, "Pilot. Reliable."));
        em.persist(new BankCustomer("August", "Johansson", "863vj", 2341, 1, "Student. Lives with parents."));
        em.persist(new BankCustomer("Claudia", "Vestergård", "k4e11", 28934, 4, "Nurse. Has taken out several loans."));
        em.persist(new BankCustomer("Ernst", "Christensen", "932b4", 392442, 8, "Doctor. Owns two properties."));
        em.getTransaction().commit();
    }
}
