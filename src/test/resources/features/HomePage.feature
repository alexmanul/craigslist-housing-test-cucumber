@all @homePage
Feature: Home page

  @homePageHousing
  Scenario: Check that housing page could be opened from home page
    Given opened home page
    When user click housing
    Then housing page is opened