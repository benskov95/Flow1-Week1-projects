package rest;

import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.AnimalNoDB;


@Path("animals")
public class AnimalDemo {
    

    @Context
    private UriInfo context;
    
    
    public AnimalDemo() {
    }
      
    @GET
    @Path("/animal_list")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalList() {
        String[] animals = new String[4];
        animals[0] = "Dog";
        animals[1] = "Cat";
        animals[2] = "Mouse";
        animals[3] = "Bird";
        return new Gson().toJson(animals);
    }
    
    @GET
    @Path("/animal")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCarInfo() {
        AnimalNoDB animal = new AnimalNoDB("Duck", "Quack");
        return new Gson().toJson(animal);
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getJson() {
        return "Woof... (Message from a dog)";
    }
 
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
