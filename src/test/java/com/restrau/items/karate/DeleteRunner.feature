Feature: Put Requests Tests for Item Service

  Background:
    Given url 'http://localhost:6666/items'


  Scenario: test the delete call for Item Services
    Given path '/deleteItem/253'
    And method delete
    Then status 200
    And match response == 'true'