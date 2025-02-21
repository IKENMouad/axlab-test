# GUIDELANCE

- Clone the project into your local and run it (default port is 8080). 
 
- Import the Postman collection "Axlab.postman_collection.json" and select an existing postman environment or create an empty one. 

- Create accounts with USER/ADMIN roles using the endpoint => (/auth/register).

- Use an account to login => (/auth/login)

- Create a flight (Only ADMIN) => (/flights).  

- View flight information using flightId => (flights/{id})

- View list of flights => (/flights)

- Paginate and sort the list of flights => (/flights?page=0&size=10)

- Delete a flight by using flightId (Only ADMIN) => (flights/{id})