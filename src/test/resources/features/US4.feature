Feature: As a librarian, I want to know who is the most popular user

  Scenario: verify who is the most_popular user who reads the most
    Given Establish the database connection
    When I execute a query to find the most popular user
    Then verify "Test Student 1" is the user who reads the most
