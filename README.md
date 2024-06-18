# Hotel-Management-System By Afefa Karakra & Ahmad Yahya
resource : 
* Customer: to enable users to register, login, and manage user profiles
* Employee: manage the hotel employees and staff
* Housekeeping: scheduling and tracking housekeeping tasks 
* Reservation: enable the customer to book room(s), modify, and cancel a reservation
* Room: managing room price, facilities, capacity, size, types, availability, and status 
* Billing: manage invoices for customer reservations.

Hotel Management System  contains a Spring Boot application that manages Hotel System with database schema,with secured  API endpoints and available Swagger documentation,also includes a Docker configuration for easy setup .
## To building the application:
You can clone the source code or download the repository as a (.zip) file.
## Prerequisites:
before getting started, ensure that you have the following installed :
* Java 17 or higher
* Gradle
* Docker
* Postman
## Getting Started:
Follow the steps below :
### Clone the Repository
Clone the project repository to your local machine using the following command:
git clone <repository_url>

### Navigate to the project directory:
cd <project_directory>

### Build the Project:
./gradlew build

(git clone https://github.com/afefa-karakra/Hotel-Management-System.git cd Hotel-Management-System)

### Add Postman Collection
To add the Postman collection for Swagger documentation follow steps below:

Open Postman then click on "Import".

Select the Postman collection file provided (<collection_file_name>.json).

The Swagger documentation requests will be available in the imported collection.

### API Documentation
to have documentation includes information about all endpoints, authentication, and models. at http://localhost:8080/swagger-ui/index.html while the application is running.

### To containerize with Docker
To containerize the application using Docker follow steps below:

Build the Docker image:
docker build -t <image_name> .

Run the Docker container:
docker run -p 8080:8080 <image_name>

The application should now be running in a Docker container on http://localhost:8080.

#### what we have learned from this project:
Everything has been mentioned previously
