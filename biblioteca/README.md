# PRACTICA_WEB
# Link to the repository: [https://github.com/agrelso7383/PRACTICA2/tree/main](https://github.com/agrelso7383/PRACTICA2/blob/main/grupo5_practicaWeb/grupo5_practicaWeb/README.md)

This project is a simple web application for managing a collection of books. It provides a RESTful API that supports basic CRUD operations. You access to the API by **http://localhost:8080/api/books** and to the H2 Console by **http://localhost:8080/h2-console**

Also uses the H2 database in memory, which leds to no external database setup required, having all data stored in memory and lost on application shutdown and accessible via the H2 web console for testing and debugging.

## Features

- **POST /api/books**  
  Adds one or multiple new books in JSON format. The `id` field is automatically generated, so it should not be included when sending data.

- **GET /api/books**  
  Retrieves a list of all books stored in the system.

- **GET /api/books**
  Returns a specific book by its unique ID.

- **PUT /api/books**  
  Updates the details of an existing book by ID.

- **DELETE /api/books**  
  Deletes a book by its ID.

  
This project was developed as part of a web development practice. Each endpoint demonstrates how to handle typical operations (Create, Read, Update, Delete) using a backend API structure.

## Authors

Sofía Agrela Almagro

Ignacio Bautista Llorente

Virginia Pérez Clemente
