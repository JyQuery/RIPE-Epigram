# RIPE Random Epigram
Build a website that prints a random epigram on every refresh (like the
`fortune` command-line tool, but on the web).

Build an HTML page that dynamically loads a random epigram from your server, and
build a backend in java to support it.

## Install
1. Import the SQL file `database/epigram.sql` into your database
2. Update the database connection strings in `src/resources/application.properties`
3. Update the API endpoint in `src/resources/static/js/app.js` if necessary

## API Reference
| Method | URI                       | Attributes                     | Description                                                      |
|--------|---------------------------|--------------------------------|------------------------------------------------------------------|
| GET    | /epigram                  |                                | Get all epigrams                                                   |
| GET    | /epigram/random           |                                | Get a random epigram                                  |
| GET    | /epigram/{id}             |                                | Get an epigram by id                                       |
| POST   | /epigram                  | content: string                | Create a new epigram
| PUT    | /epigram/{id}             | content: string                | Update the content of an epigram
| DELETE | /epigram/{id}             |                                | Delete an epigram by id

## API Status Definition
| Code | Description         |
|------|---------------------|
| 200  | Everything OK       |
| 400  | Invalid request due to missing parameter(s) |
| 404  | Resource not found  |
| 405  | Method not allowed  |
| 50x  | Server Internal Error|

## Database Schema
Table ``epigram``

| Column | Datatype | Description |
|--------|----------|------------|
| id     | bigint  | Auto increment id |
| content | text | The epigram content |
| created_at | datetime | The creation time of the row |
| updated_at | datetime | The update time of the row |

## Data Source
The epigram data is from [shlomif/fortune-mod](https://github.com/shlomif/fortune-mod/tree/master/fortune-mod/datfiles)

## Components

Back-end: Java Spring Boot <br>
ORM: Hibernate <br>
Front-end: Bootstrap 5, Vue.js 3


## Author
Junyan Li (i@jybb.me)