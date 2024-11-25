# Device Management Service

This is a Spring Boot-based REST service for managing a device database. The service uses **Gradle** as the build tool, an **H2 in-memory database** for persistence, and includes a **Postman collection** for testing the API.

## Features

The service supports the following operations:
1. **Add a Device**: Add a new device to the database.
2. **Get a Device by Identifier**: Retrieve device details using its unique ID.
3. **List All Devices**: View all devices stored in the database.
4. **Update a Device**: Update device details (supports both full and partial updates).
5. **Delete a Device**: Remove a device from the database.
6. **Search Devices by Brand**: Find devices by their brand name.

### Entities

**Device**  
The `Device` entity is represented with the following attributes:
- `deviceName` (String): The name of the device.
- `deviceBrand` (String): The brand of the device.
- `creationTime` (DateTime): The timestamp of when the device was created.

---

## Prerequisites

- **Java 17** or higher.
- **Gradle 7.0** or higher.
- A tool like Postman to import the collection for testing.

---

## Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd <repository-directory> 
```

### 2. Use Gradle to build the project:

```bash
Copy code
./gradlew build
```

### 3. Run the Application
   Start the Spring Boot application:

```bash
Copy code
./gradlew bootRun
```

The service will be available at http://localhost:8080.

### API Endpoints
#### Base URL

http://localhost:8080/devices

### Endpoints
| Method | Endpoint                  | Description                  |
|--------|---------------------------|------------------------------|
| POST   | `/`                       | Add a new device             |
| GET    | `/{id}`                   | Get a device by ID           |
| GET    | `/`                       | List all devices             |
| PATCH  | `/{id}`                   | Partially update a device    |
| PUT    | `/{id}`                   | Fully update a device        |
| DELETE | `/{id}`                   | Delete a device by ID        |
| GET    | `/?brand={brand}`   | Search devices by brand name |


### Testing the API
A Postman collection is included in the 
```
src/main/resources
```

Open Postman.
Import the file **DeviceManagementService.postman_collection.json** from the src/main/resources directory.
Use the pre-configured requests to test the API.

### H2 Database Console
The application uses an H2 in-memory database, which is accessible at:
http://localhost:8080/h2-console

#### Use the following credentials:

**JDBC URL:** jdbc:h2:mem:testdb

**Username**: sa

**Password**: (leave blank)

### Building and Running Tests
To run the unit tests, execute:

```bash
Copy code
./gradlew test
```


### Future Enhancements
Implement authentication and authorization.
Add validation for device attributes.
Support for pagination and sorting in list, search endpoints, logging and additional test cases.
