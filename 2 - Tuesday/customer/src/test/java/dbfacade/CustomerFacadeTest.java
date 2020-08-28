package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomerFacadeTest {
    
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("pu");
    private static final CustomerFacade TEST_FACADE = CustomerFacade.getCustomerFacade(EMF);
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetCustomerFacade() {
        System.out.println("getCustomerFacade");
        CustomerFacade testFacade = CustomerFacade.getCustomerFacade(EMF);
        assertNotNull(testFacade);
    }
    
    @Test
    public void testFindCustomerById() {
        System.out.println("findCustomerById");
        int id = 4;
        String expResult = "Poul";
        String result = TEST_FACADE.findCustomerById(id).getFirstName();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetCustomerByLastName() {
        System.out.println("getCustomerByLastName");
        String lastName = "Kofod";
        List<Customer> result;
        result = TEST_FACADE.getCustomerByLastName(lastName, EMF);
        assertEquals(lastName, result.get(0).getLastName());    
    }

    @Test
    public void testGetNumberOfCustomers() {
        System.out.println("getNumberOfCustomers");
        int incorrectSize = 1;
        int result = TEST_FACADE.getNumberOfCustomers();
        assertTrue(incorrectSize < result);
    }

    @Test
    public void testGetAllCustomers() {
        System.out.println("getAllCustomers");
        List<Customer> result = TEST_FACADE.getAllCustomers();
        assertFalse(result.isEmpty());
    }

    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        String firstName = "Test";
        String lastName = "Testsen";
        TEST_FACADE.addCustomer(firstName, lastName);
        List<Customer> result = TEST_FACADE.getCustomerByLastName(lastName, EMF);
        assertFalse(result.isEmpty());
        TEST_FACADE.deleteCustomer(firstName);
    }
    
}
