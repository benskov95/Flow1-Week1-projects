package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Animal;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("animals_db")
public class AnimalFromDB {
    

    @Context
    private UriInfo context;
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
  
    public AnimalFromDB() {
    }
    
    @GET
    @Path("/animals")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalsFromDB() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            String jsonString = gson.toJson(animals);
            return jsonString;
        } finally {
            em.close();
        }
    }
    
    @GET
    @Path("/animal_by_id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByID(@PathParam("id") int id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("SELECT a FROM Animal a WHERE a.id = :id", Animal.class);
            query.setParameter("id", id);
            Animal animal = (Animal) query.getSingleResult();
            String jsonString = gson.toJson(animal);
            return jsonString;
        } catch (Exception e) {
            return "There is no animal in the database with ID: " + id;
        } finally {
            em.close();
        }
    }
    
    @GET
    @Path("/animal_by_type/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAnimalByType(@PathParam("type") String type) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("SELECT a FROM Animal a WHERE a.type = :type", Animal.class);
            query.setParameter("type", type);
            List<Animal> animals = query.getResultList();
            
            if (animals.isEmpty()) {
                return "There is no animal in the database with the specified type: " + type;
            } else {
            String jsonString = gson.toJson(animals);
            return jsonString;
            }
            
        } catch (Exception e) {
            return "There is no animal in the database with the specified type: " + type;
        } finally {
            em.close();
        }
    }
    
    @GET
    @Path("/random_animal")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomAnimal() {
        EntityManager em = emf.createEntityManager();
        Random random = new Random();
        try {
            TypedQuery query = em.createQuery("SELECT a FROM Animal a", Animal.class);
            List<Animal> animals = query.getResultList();
            int randomNumber = random.nextInt(animals.size());
            String jsonString = gson.toJson(animals.get(randomNumber));
            return jsonString;
        } catch (Exception e) {
            return "There are no animals in the database.";
        } finally {
            em.close();
        }
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
