package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankCustomerFacadeTest {
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("pu");
    private static final BankCustomerFacade TEST_FACADE = BankCustomerFacade.getBankCustomerFacade(EMF);
    
    public BankCustomerFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
//        Add code to setup entities for test before running any test methods
    }
    
    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }
    
    @BeforeEach
    public void setUp() {
//        Put the test database in a proper state before each test is run
    }
    
    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testGetCustomerByID() {
        CustomerDTO test = TEST_FACADE.getCustomerByID(3);
        assertTrue(test.getFullName().equals("August Johansson"));
    }

    @Test
    public void testGetCustomerByName() {
        List<CustomerDTO> result = TEST_FACADE.getCustomerByName("Claudia");
        assertTrue(result.get(0).getCustomerID() == 4);
    }

    @Test
    public void testAddCustomer() {
        BankCustomer test = TEST_FACADE.addCustomer(new BankCustomer("Test", "Testsen", "12345", 1, 1, "Testing"));
        List<CustomerDTO> confirm = TEST_FACADE.getCustomerByName("Test");
        assertTrue(confirm.get(0).getAccountNumber().equals(test.getAccountNumber()));
        TEST_FACADE.deleteBankCustomer("Test");
    }
    
    @Test
    public void testGetAllEmployees() {
        List<BankCustomer> result = TEST_FACADE.getAllBankCustomers();
        assertTrue(result.size() == 5);
    }
    
}
