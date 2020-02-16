@all @housingPage @housingSorting
Feature: Validate housing page sorting

  Background:
    Given opened housing page

# This test will be failing, because by default "upcoming" should not be present
  @housingDefaultSorting
  Scenario: Validate sorting options before search
    When user click search options drop down menu
    Then by default should be following options:
      | uusin   |
      | hinta ↑ |
      | hinta ↓ |