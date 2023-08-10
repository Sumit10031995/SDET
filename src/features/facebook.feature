@web
Feature: FB testing 

Background:
  Given user should be on login page 


Scenario Outline: test FB login feature
When user provide valid userID and password<id>, <password>
And click on login button
Then user should be landed on FB home page
Examples:
|id                  ||password  |
|naiksumit7@gmail.com||9937798945|
