Weather API - Java Spring Boot

A fun, beginner-friendly Java API that fetches weather data and caches requests locally. Built with Java 25 and Maven.


Requirements
	•	Java 25 installed
	•	Maven installed


Setup

1.	Clone the repository
   
	git clone https://github.com/Cameron-123-jpg/WeatherAPI.git

	cd project-folder

3.	Create a local Spring profile

Inside src/main/resources, create a file named application-local.yml

3.	Add your API credentials
   
Paste the following into application-local.yml:

weather:

  api:
  
weatherKey: YOUR_API_KEY
	
weatherBaseUrl: YOUR_BASE_URL

You can get your API key and base URL from Visual Crossing Weather API.

4.	Run the project
5.	
mvn spring-boot:run -Dspring-boot.run.profiles=local

Usage

As an API

	•	Default endpoint: http://localhost:8080/api/weather/place?place=<locationName>
	•	Example: http://localhost:8080/api/weather/place?place=Carlock
	•	No authentication required other than the API key in your local profile.

As a Console App

	•	Enter requests directly in the terminal in the format: <place>,<hours>
	•	Example: Chicago, 6
	•	If hours are omitted, the default is 6.


Features

	•	Fetches weather data for a place or zip code.
	•	Pretty-printed hourly forecasts in the console.
	•	Caches requests locally in text files (last_request.txt and saved_requests.txt).
	•	Can be extended to connect to a database for persistent storage in the future.


Example Output
```
==================================================================================================
Last request was: New York
Weather forecast for: New York
00:00:00: 56.3°F (feels like 56.3°F), Clear
01:00:00: 54.9°F (feels like 54.9°F), Partially cloudy
02:00:00: 54.9°F (feels like 54.9°F), Partially cloudy
03:00:00: 54.9°F (feels like 54.9°F), Partially cloudy
04:00:00: 54.9°F (feels like 54.9°F), Partially cloudy
05:00:00: 54.1°F (feels like 54.1°F), Partially cloudy
==================================================================================================
