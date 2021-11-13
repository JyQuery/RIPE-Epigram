# RIPE Random Epigram
Build a website that prints a random epigram on every refresh (like the
`fortune` command-line tool, but on the web).

Build an HTML page that dynamically loads a random epigram from your server, and
build a backend in java to support it.

## Install
1. Import the SQL file into your database
2. Update the database connection details in src/resources/application.properties

## API Reference
| Method | URI                       | Attributes                     | Description                                                      |
|--------|---------------------------|--------------------------------|------------------------------------------------------------------|
| GET    | /epigram                  |                                | Get all epigrams                                                   |
| GET    | /epigram/random           |                                | Get a random epigram                                  |
| GET    | /epigram/{id}             |                                | Get an epigram by id                                       |
| POST   | /epigram                  | content: string                | Create a new epigram
| PUT    | /epigram/{id}             | content: string                | Update the content of an epigram
| DELETE | /epigram/{id}             |                                | Delete an epigram by id

## Status Definition
| Code | Description         |
|------|---------------------|
| 200  | Everything OK       |
| 400  | Invalid request due to missing parameter(s) |
| 404  | Resource not found  |
| 405  | Method not allowed  |
| 50x  | Server Internal Error|
