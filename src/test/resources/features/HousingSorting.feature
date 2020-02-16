#info
#tests are written with native page language (Finland)
  #translations:
    #uusin   -> newest
    #hinta ↑ -> price up
    #hinta ↓ -> price down

@all @housingPage @housingSorting
Feature: Validate housing page sorting

  Background:
    Given opened housing page

#This test will be failing, because by default "upcoming" option should not be present
  @housingDefaultSorting
  Scenario: Validate sorting options before search
    When user click drop down menu with sorting options
    Then by default should be following options:
      | uusin   |
      | hinta ↑ |
      | hinta ↓ |

  @housingSearchNavigation
  Scenario Outline: Validate ulr for default filter sorting option: "<option>"
    When user pick sorting filter for search: "<option>"
    Then url should contain following path: "<path>"
    Examples:
      | option  | path                       |
      | uusin   | /search/hhh?sort=date&     |
      | hinta ↑ | /search/hhh?sort=priceasc& |
      | hinta ↓ | /search/hhh?sort=pricedsc& |