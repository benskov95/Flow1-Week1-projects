package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeFacadeTest {
    
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("pu");
    private static final EmployeeFacade TEST_FACADE = EmployeeFacade.getEmployeeFacade(EMF);
    
    public EmployeeFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
//        Put the test database in a proper state before each test is run --- have not made test db.
    }
    
    @AfterEach
    public void tearDown() {
      
    }

    @Test
    public void testGetEmployeeByID() {
        Employee test = TEST_FACADE.getEmployeeByID(1);
        assertTrue(test.getName().equals("Joe"));
    }
    
    @Test
    public void testGetEmployeesByName() {
        List<Employee> result = TEST_FACADE.getEmployeesByName("Hans");
        assertTrue(result.size() == 2);
    }
    
    @Test
    public void testGetAllEmployees() {
        List<Employee> result = TEST_FACADE.getAllEmployees();
        assertTrue(result.size() > 5);
    }
    
    @Test
    public void testGetEmployeesWithHighestSalary() {
        List<Employee> result = TEST_FACADE.getEmployeesWithHighestSalary();
        int expSalary = 345100;
        assertTrue(result.get(0).getSalary() == expSalary && result.size() > 1);
}
    
    @Test
    public void testCreateEmployee() {
        Employee test = TEST_FACADE
                .createEmployee("Test", "Testvej 1", 10000);
        List<Employee> confirm = TEST_FACADE.getEmployeesByName("Test");
        assertTrue(confirm.get(0).getName().equals(test.getName()));
        TEST_FACADE.deleteEmployee("Test");
    }
    
}
