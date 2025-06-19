# Chatbot API

A Spring Boot-based chatbot service integrated with Google Dialogflow. It responds dynamically using external and custom data sources — including scraped data from IMDb's Top movies list and Chuck Norris jokes from a public API. Users can interact with the chatbot through Dialogflow webhook calls or directly via REST endpoints.

---

## Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **OkHttp**
- **Swagger**
- **Custom IMDb Scraper**
- **Chuck Norris Joke API**
- **Maven**

---

## Features

- Search movie info via IMDb Top 250 list (scraped HTML parsed by regex)
- Retrieve Chuck Norris jokes using a keyword
- Accept Dialogflow-like POST payloads with parameters (`jokeParam` or `product`)
- Unified JSON response format for chatbot compatibility
---

## Project Structure
```text
src/
├── main/
│   ├── java/com/handson/chatbot/
│   │   ├── config/         → Swagger configuration
│   │   ├── controller/     → REST API for chatbot interactions
│   │   ├── service/        → Logic for IMDb scraping and Chuck API integration
│   │   └── ChatbotApplication.java
│   └── resources/
│       └── application.properties
└── test/
```
## API Endpoints
Method	Endpoint	Description
| Method | Endpoint     | Description                                                    |
| ------ | ------------ | -------------------------------------------------------------- |
| GET    | `/bot/imdb`  | Search IMDb's Top movies list (`?keyword=...`)                 |
| GET    | `/bot/jokes` | Fetch a Chuck Norris joke based on a topic (`?keyword=...`)    |
| POST   | `/bot`       | Dialogflow-style POST with `jokeParam` or `product` parameters |

## Example Requests
IMDb Search
```bash
curl "http://localhost:8080/bot/imdb?keyword=inception"
```

Chuck Norris Joke
```bash
curl "http://localhost:8080/bot/jokes?keyword=door"
```
Dialogflow-style POST
```http
POST /bot
Content-Type: application/json

{
  "queryResult": {
    "parameters": {
      "jokeParam": "code"
    }
  }
}
```



## Running the App
Prerequisites
- Java
- Maven

Run Locally
```bash
mvn clean install
mvn spring-boot:run
```
## Configuration
In application.properties:
```Yaml
server.port=8080
```
