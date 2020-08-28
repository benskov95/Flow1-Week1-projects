Benjamin Skovgaard - cph-bs190@cphbusiness.dk
# Important notes
- I use JDK 11. Not sure if it could cause problems but I figured I would mention it.
- I've included a 'Databases' folder with an sql file which will create all of the databases used in the projects when imported (includes all generated tables etc.). 

# Monday
Spent most of the day ensuring that all of the new programs were set up correctly. I have added a file in the 'Monday' folder which is just a quick checklist of everything that I have installed and configured.

# Tuesday
Created and worked on 3 projects - 'first_jpa_application', 'point' and 'customer'. I became more familiar with JPA (entities, EntityManager etc.) and JPQL through working with these projects, and now feel better equipped to use them in future projects. I had some problems with a specific recurring error message in the 'point' and 'customer' projects - '(object) is not a known entity type'. I resolved the problem by recreating both projects as 'Java with Maven/Web Application' projects but I am not sure why doing this fixed it.

# Wednesday
Created and worked on 1 project - 'rest1'. I became more familiar with REST and JSON through this project, and now have a better understanding of the various annotations we can use for different purposes to create endpoints with different functions on a website. We only used JSON (and GSON) to convert objects into strings to be displayed on a browser but learned that it can also be used to do the opposite - i.e. convert strings into objects.

# Thursday
Created and worked on 2 projects - 'jpql-demo-for-day4' and 'week1-simple-jpa-rest'. Learned more about JPQL queries and their syntax through the first project, and became familiar with DTO concept through the second. I used DTO to convert ordinary entity objects into simpler versions of themselves, with the purpose of masking certain information about (in this case) employees, since they do not necessarily need to know all of the data you have on them when looking at their own information. I also deployed 'week1-simple-jpa-rest' on my Droplet. 

Link: http://161.35.22.202:8081/jpa_rest_startup-1.0/

# Friday
Created and worked on 1 project - 'jpa_rest_dto'. This project was very similar to Thursday's 'week1-simple-jpa-rest' project but was a better example of using DTO to mask information that is irrelevant to a user (Bank customer in this case). This project was also deployed on my Droplet.

Link: http://161.35.22.202:8081/jpa_rest_dto-1.0/
