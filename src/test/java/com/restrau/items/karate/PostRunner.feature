Feature: Post Requests Tests for Item Service

  Background:
    Given url 'http://localhost:6666/items'

  Scenario: test the post call for Item Services
    Given path '/addItem'
    And request
    """
    {
      "name": "Manchurian",
      "description": "An chinese Dish",
      "category": "INDO_CHINESE",
      "price": 50.0
    }
    """
    And headers { Accept: "application/json", Content-Type: "application/json"}
    When method post
    Then status 201
    And match response ==
    """
    {
      "itemId": "#notnull",
      "name": "Manchurian",
      "description": "An chinese Dish",
      "category": "INDO_CHINESE",
      "price": 50.0
    }
    """


  Scenario: test the post call for Item Services reading data from external Json
    Given path '/addItem'
    * def requestData = read('jsonData/PostRequestData.json')
    * def responseData = read('jsonData/PostResponseData.json')
    And request requestData
    And headers { Accept: "application/json", Content-Type: "application/json"}
    When method post
    Then status 201
    And match response == responseData

  Scenario: test post call for Item Services
    Given path '/addItem'
    * def requestData = read('jsonData/PostRequestData.json')
    * def responseData = read('jsonData/PostResponseData.json')
    * def func = function(agr1) { return "Name of Item Is " + arg1; }
    And request requestData
    And headers { Accept: "application/json", Content-Type: "application/json"}
    When method post
    Then status 201
    And match response == responseData

  Scenario Outline: test the post call for Item Services using Scenario Outline
    Given path '/addItem'
    And request
    """
    {
      "name": "#(name)",
      "description": "#(description)",
      "category": "#(category)",
      "price": "#(price)"
    }
    """
    And headers { Accept: "application/json", Content-Type: "application/json"}
    When method post
    Then status 201
    And match response ==
    """
    {
      "itemId": "#notnull",
      "name": "#(name)",
      "description": "#(description)",
      "category": "#(category)",
      "price": "#(<price>)"
    }
    """
    Examples:
      |name      | description   |category    |price|
      |Manchurian|An chinese Dish|INDO_CHINESE|50.0 |