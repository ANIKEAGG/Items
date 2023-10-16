Feature: Get Requests Tests For Items Services

  Background:
    Given url 'http://localhost:6666/items'
    * def actualResponse = read("jsonData/ResponseData.json")

  Scenario: testing the get call for Items Details
    And path '/getItem/305'
    When method GET
    Then status 302

  Scenario: testing the get call for Items Details and accepting response
    And path '/getItem/305'
    When method GET
    Then status 302
    And match response == actualResponse

  Scenario: testing the get call for Items Details and accepting response
    And path '/getItem/305'
    When method GET
    Then status 302
    And print response
    And match response.itemId == 305
    And match response contains deep {"description": "An chinese Dish"}

  Scenario: testing the get call for Items Details and validation using Fuzzy Matcher
    And path '/getItem/305'
    When method GET
    Then status 302
    And match response == actualResponse
    And match response.itemId == "#? _ == 305"
    And match response.description == "#string? _.length >= 10"

  Scenario: Data Variable in Gherkin Language
    And path '/getItem/305'
    When method GET
    Then status 302
    And match response == actualResponse
    * def ItemId = response.itemId
    * def description = response.description
    * def name = response.name
    * def category = response.category
    * def price = response.price
    And print "Response is ", ItemId, description, name, category, price


  Scenario Outline: test get call for Item Services using Scenario Outline
    Given print '<url>'
    When print '<path>'
    Then print '<method>'
    And print '<status>'
    Examples:
      |url                          | path         |method |status|
      |http://localhost:6666/items  |/getItem/305  |get    |201   |
      |http://localhost:6666/items  |/getItem/305  |get    |201   |