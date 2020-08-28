package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
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

@Path("employees")
public class EmployeeResource {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllEmployees() {
        List<EmployeeDTO> wrapped = new ArrayList<>();
        try {
            List<Employee> employees = facade.getAllEmployees();
            for (Employee e : employees) {
                wrapped.add(new EmployeeDTO(e));
            }
            String jsonString = gson.toJson(wrapped);
            return jsonString;
        } catch (Exception e) {
            return "ERROR: Something went wrong.";
        }
    }

    @GET
    @Path("/employee_by_id/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeByID(@PathParam("id") int id) {
        try {
            Employee employee = facade.getEmployeeByID(id);
            EmployeeDTO employeeDTO = new EmployeeDTO(employee);
            String jsonString = gson.toJson(employeeDTO);
            return jsonString;
        } catch (Exception e) {
            return "ERROR: The employee with the specified ID (" + id + ") does not exist.";
        }
    }

    @GET
    @Path("/highest_paid")
    @Produces({MediaType.APPLICATION_JSON})
    public String getHighestPaidEmployee() {
        List<EmployeeDTO> wrapped = new ArrayList<>();
        try {
            List<Employee> employees = facade.getEmployeesWithHighestSalary();
            for (Employee e : employees) {
                wrapped.add(new EmployeeDTO(e));
            }
            String jsonString = gson.toJson(wrapped);
            return jsonString;
        } catch (Exception e) {
            return "ERROR: Something went wrong.";
        }
    }

    @GET
    @Path("/employee_by_name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeByName(@PathParam("name") String name) {
        List<EmployeeDTO> wrapped = new ArrayList<>();
        try {
            List<Employee> employees = facade.getEmployeesByName(name);
            for (Employee e : employees) {
                wrapped.add(new EmployeeDTO(e));
            }
            String jsonString = gson.toJson(wrapped);
            if (employees.isEmpty()) {
                return "ERROR: There are no employees with the specified name (" + name + ").";
            }
            return jsonString;
        } catch (Exception e) {
            return "ERROR: There are no employees with the specified name (" + name + ").";
        } 
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Employee entity) {
        throw new UnsupportedOperationException();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Employee entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
}
