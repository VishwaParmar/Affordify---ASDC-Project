# Affordify
Affordify is a web application built with Spring Boot as the backend framework and React as the frontend library. This platform allows users to search for various categories of food products, including fruits, vegetables, and meat, at affordable prices.

With Affordify, users can easily search and filter their desired food products based on a variety of criteria, such as pricing, customer reviews, and special offers. Our search engine uses web scraping technology to extract data from a list of ecommerce websites, ensuring that you get the best deals and the freshest products available.


#### Build Instructions
###### Prerequisites
1. maven 4.0.0
2. Java 17

###### Dependencies
1. 'org.springframework.boot:spring-boot-starter-data-jdbc'

   Spring Boot starter to interact with a relational database in Spring application.


2. 'org.springframework.boot:spring-boot-starter-web'

   Spring Boot starter support for building web applications using Spring MVC (Model-View-Controller) framework.


3. 'org.springframework.boot:spring-boot-starter-oauth2-resource-server'

   Provides support for JSON Web Tokens (JWT) and JSON Object Signing and Encryption


4. 'org.apache.httpcomponents:httpcore'

   For performing HTTP requests and handling responses


5. 'com.google.code.gson:gson:2.10'

   Simple API for converting Java objects to JSON and vice versa

6. 'org.springframework.boot:spring-boot-starter-mail'

   Starter package for the Spring Boot framework that provides an easy way to configure and use email functionality in a Spring Boot application.

7. 'org.springframework.boot:spring-boot-starter-test scope - test'

   It provides several useful testing libraries and frameworks such as JUnit, Spring Test, Mockito, AssertJ, and others.
 8. 'org.jsoup:jsoup: version - 1.15.4' 

Jsoup can be used to extract data from HTML pages on the web. It provides a simple API for navigating the HTML document tree and selecting specific elements based on their tag name, attributes, or content.
     



### Getting Started

To run Affordify locally, you'll need to have Java and Node.js installed on your computer. Once you have these installed, follow these steps:

1. Clone the repository: git clone **[https://github.com/VishwaParmar/Affordify---ASDC-Project.git]**

2. Navigate to the **group24** directory: **cd group24**

There will be two directories:
- frontend
- backend
3. Start the backend, navigate to the **backend** directory: **cd backend**

4. Build the backend : **mvn build**
5. Start the backend : **mvn spring-boot:run**

6. Navigate to the **frontend** directory: **cd frontend**

7. Install the frontend dependencies: **npm install**

8. Start the frontend server: **npm start**

9. Open your browser and go to **http://localhost:3000**


###### Milestones
1.	Login and Registration - Achieved
2.	Email Notification Service - Achieved
3.	Frontend Pages - Achieved
      - Home Page
      - Web scrapping Search Page
      - Favourites
      - Product Price chart
      - Contact
      - User Profile Page
4.	Search features (Based on Filters) - Achieved
5. Web Scraping - Achieved
6. User Inteface to show project-level information using charts and tables – Achieved
7. Newsletter Feature(Showing Price Difference)

###### Milestones added after the mid-term review
1.	Web scraping at regular time interval - Achieved


## Features
### Website link http://csci5308vm24.research.cs.dal.ca:3000/
Affordify includes several high-priority features, including:

- Sign Up
- Email at SignUp
- Login
- User profile page
- Home page
- Search box to search for items with Filters
- Product details page (name, image, price, shop name)
- Order button (link to original website)
- Favorite products page
- Price Change Notification
- Newsletter Feature

In addition to these features, Affordify also includes
- a web crawling bot that extracts data from ecommerce websites and stores it in a database. The platform also includes a search engine, price chart functionality, and newsletter feature
- 
## Smells
The smells identified in the project are majorly justifiable.
Feature concentration and Unstable Dependency shows in our project is filled with more classes than it should, however we have ensured that they follow Single Responsibility Principle(SRP) and are related to each other and can not be split into multiple packages.

Other majorly detected smells are unutilized abstraction, however, all the classes are used up somewhere and are required in this project.

We resolved all the magic number implementation smells apeared in our project.
Most implementation smells including long parameter list are the ones from constructors and there is a need for all the params as stated and hence can not be broken down. Also the long statements are used for object initialization and are non-breakable.
The link to smells list and their justifications is added
"SummaryCodeSmell.xlsx".


### Test coverage
-  The test class coverage in our project is 90%.


