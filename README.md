# retailer-shop

## Build:
To build run he following

mvn clean install

## Testing:
To run the unit tests run the following

mvn test

This project has been implemented using Java8, SpringBoot, Mockito and TDD.
I took Java RuleBook as rules engine. RuleBook allowed me to specify rules using Java8 Lambda expressions.

Assumptions:
I have assumed that the required quantity of items is always available in the DB.
Orders can be added and removed, but cannot be changed or modified.