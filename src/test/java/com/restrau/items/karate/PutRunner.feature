Feature: Put Requests Tests for Item Service

  Background:
    Given url 'http://localhost:6666/items'

  Scenario: test the post call for Item Services
    Given path '/updateItem'
    And request
    """
    {
      "itemId": 2,
      "name": "Manchurian",
      "description": "An chinese Dish",
      "category": "INDO_CHINESE",
      "price": 50.0
    }
    """
    And headers { Accept: "application/json", Content-Type: "application/json"}
    When method put
    Then status 200
    And match response ==
    """
    {
      "itemId": 2,
      "name": "Manchurian",
      "description": "An chinese Dish",
      "category": "INDO_CHINESE",
      "price": 50.0
    }
    """


  Scenario: test the put call for Item Services reading data from external Json
    Given path '/updateItem'
    * def data = read('PutData.json')
    And request data
    And headers { Accept: "application/json", Content-Type: "application/json"}
    When method put
    Then status 200
    And match response == data


  Scenario Outline: test the post call for Item Services using Scenario Outline
    Given path '/updateItem'
    And request
    """
    {
      "itemId": "#(itemId)",
      "name": "#(name)",
      "description": "#(description)",
      "category": "#(category)",
      "price": "#(price)"
    }
    """
    And headers { Accept: "application/json", Content-Type: "application/json"}
    When method put
    Then status 200
    And match response ==
    """
    {
      "itemId": "#(<itemId>)",
      "name": "#(name)",
      "description": "#(description)",
      "category": "#(category)",
      "price": "#(<price>)"
    }
    """
    Examples:
      |itemId|name      | description   |category    |price|
      |2     |Manchurian|An chinese Dish|INDO_CHINESE|50.0 |