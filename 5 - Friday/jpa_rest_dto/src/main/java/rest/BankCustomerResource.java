package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CustomerDTO;
import entities.BankCustomer;
import facades.BankCustomerFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("bankcustomer")
public class BankCustomerResource {
    
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    BankCustomerFacade facade =  BankCustomerFacade.getBankCustomerFacade(emf);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    @GET
    @Path("/by_id/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCustomerByID(@PathParam("id") int id) {
        try {
            CustomerDTO customer = facade.getCustomerByID(id);
            String jsonString = gson.toJson(customer);
            return jsonString;
        } catch (Exception e) {
            return  "ERROR: The customer with the specified ID (" + id + ") does not exist.";
        } 
    }
    
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllBankCustomers() {
        try {
            List<BankCustomer> customers = facade.getAllBankCustomers();
            String jsonString = gson.toJson(customers);
            return jsonString;
        } catch (Exception e) {
            return "ERROR: Something went wrong.";
        }
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(BankCustomer entity) {
        throw new UnsupportedOperationException();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(BankCustomer entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
