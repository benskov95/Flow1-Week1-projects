package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;
    
    private EmployeeFacade() {}
    
    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("pu");
        getEmployeeFacade(emf);      
    }
    
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Employee getEmployeeByID(int id) {
        EntityManager em = instance.getEntityManager();
        try {
        TypedQuery query = 
        em.createQuery("SELECT e FROM Employee e WHERE e.id = :id", Employee.class);
        query.setParameter("id", id);
        Employee employee = (Employee) query.getSingleResult();
        return employee;
        } finally {
            em.close();
        }
    }
    
    public List<Employee> getEmployeesByName(String name) {
        EntityManager em = instance.getEntityManager();
        try {
        TypedQuery query = 
        em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class);
        query.setParameter("name", name);
        List<Employee> employees = query.getResultList();
        return employees;
        } finally {
            em.close();
        }
    }
    
    public List<Employee> getAllEmployees() {
        EntityManager em = instance.getEntityManager();
        try {
        TypedQuery query = 
        em.createQuery("SELECT e FROM Employee e", Employee.class);
        List<Employee> employees = query.getResultList();
        return employees;
        } finally {
            em.close();
        }
    }
    
    public List<Employee> getEmployeesWithHighestSalary() {
        EntityManager em = instance.getEntityManager();
        try {
        TypedQuery query = 
        em .createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)", Employee.class);
        List<Employee> employees = query.getResultList();
        return employees;
        } finally {
            em.close();
        }
    }
    
    public Employee createEmployee(String name, String address, int salary) {
        EntityManager em = instance.getEntityManager();
        try {
        Employee employee = new Employee(name, address, salary);
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        return employee;
        } finally {
            em.close();
        }      
    }
    
     public void deleteEmployee(String name) { // For testing
        EntityManager em = instance.getEntityManager();
        try {
        em.getTransaction().begin();
        TypedQuery query = 
        em.createQuery("DELETE FROM Employee e WHERE e.name = :name", Employee.class);
        query.setParameter("name", name);
        query.executeUpdate();
        em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
}
