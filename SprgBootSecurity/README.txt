
Project Name
SprgBootSecurity

DESCRIPTION
SprgBootSecurity is a Spring Boot-based application that demonstrates the
implementation of user authentication and role-based access control using
Spring Security. The project includes features such as user management,
password encoding, and custom user details service for authentication.

FEATURES
•	User Authentication: Implements login functionality via Spring Security.
•	Role-Based Access Control: Supports multiple roles (e.g., USER, ADMIN).
•	Password Encoding: Secure storage using the PasswordEncoder interface.
•	Custom UserDetailsService: Custom logic for loading authenticated users.
•	CRUD Operations: Managed via repository for DemoUser entities.

TECHNOLOGIES USED
•	Java
•	Spring Boot
•	Spring Security
•	Spring Data JPA
•	Maven

PROJECT STRUCTURE
•	repository: Contains DemoRepository for database operations.
•	service: Includes DemoUserDetailsService and DemoUserService.
•	entity: Defines the DemoUser entity for database mapping.
•	SprgBootSecurityApplication: Main bootstrap class.

HOW TO RUN
1.	Clone the repository.
2.	Configure the database connection in 'application.properties'.
3.	Build the project using Maven:
mvn clean install
4.	Run the application:
mvn spring-boot:run
5.	Access the application at: http://localhost:8080


