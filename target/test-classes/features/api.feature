@API
Feature: Testing our api module

  Scenario: Test first post request
    Given user hits the post request with data as
    |name|ayush|
    |job |lead |
    Then status code should be 201
    And validates json schema "postUser"

