@txn
Feature: Finding a book via REST API
  Background:
    Given packt-books fixture is loaded

  Scenario Outline: Using RESTful API to lookup books by ISBN
    Given catalogue with books
    When requesting url /books/<isbn>
    Then status code will be 200
    And response content contains <title>

    Examples:
      |isbn             |title               |
      |9781|Romeo and Juliet|
      |9783|Orchestrating Docker|