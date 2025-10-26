📸 Java Play REST API – Image Upload & CRUD
🧩 Project Overview
This project is a modular RESTful backend built with the Java Play Framework that allows users to upload and manage image files. It supports JPG, JPEG, and PNG formats and provides full CRUD operations for image metadata and binary content.

Uploaded images are stored directly in a PostgreSQL database using Hibernate ORM, and all operations are exposed via clean REST endpoints. The system includes global exception handling, file validation, and DTO mapping for safe and scalable API design.


🚀 Features
✅ Upload image files with format validation

📂 Store image metadata and binary data in SQL

🔄 Full CRUD support (Create, Read, Update, Delete)

🧰 Global exception handling with custom error types

🔒 CORS-enabled and CSRF-disabled for API testing

🧪 Tested via Postman/RESTer with edge case coverage

🧼 Clean modular architecture with DI (Guice) and DTO conversion (ModelMapper)

📦 Tech Stack



Layer	Technology
Language	Java 17
Framework	Play Framework
ORM	Hibernate + JPA
Database	PostgreSQL
Dependency Injection	Guice Modules
Object Mapping	ModelMapper
File Upload	Play MultipartFormData
Testing	Postman / RESTer
Build Tool	sbt
🔧 How to Run
Clone the repository

git clone https://github.com/radovan85/image-upload-java-play.git
cd image-upload-play


Configure the database Update HibernateUtil.java with your PostgreSQL credentials:

hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/image-db");
hikariConfig.setUsername("your_username");
hikariConfig.setPassword("your_password");


Run the application

sbt run


Test the API Use Postman or RESTer to hit the following endpoints:

🌐 API Endpoints
Method	Endpoint	Description
GET	/api/images	List all images
GET	/api/images/:id	Get image by ID
POST	/api/images	Upload a new image
PUT	/api/images/:id	Update an existing image
DELETE	/api/images/:id	Delete image by ID
For POST and PUT, use multipart/form-data with a file field.

🛡️ Exception Handling
All exceptions are handled globally via a custom ExceptionFilter. The following error types are supported:

400 Bad Request – Invalid file format

406 Not Acceptable – Upload failure

412 Precondition Failed – Image not found

500 Internal Server Error – Unhandled exceptions

🧪 Testing Tips
Use Postman or RESTer to simulate file uploads and CRUD operations

Test edge cases: invalid formats, missing files, nonexistent IDs

Observe structured JSON error responses and status codes

📜 Licensing & Attribution
This project was created by Milan Radovanović as part of a live coding session for Postwork AI.

Postwork AI reserves the right to use, distribute, and showcase this project for educational, promotional, or platform-related purposes.

All code is original and modularly documented for mentoring, onboarding, and antifraud clarity.