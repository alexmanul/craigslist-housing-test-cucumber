#info
#tests are written with native page language (Finland)
  #translations:
    #uusin   -> newest
    #hinta ↑ -> price up
    #hinta ↓ -> price down

@all @housingPage @housingSorting
Feature: Validate housing page price sorting filter

  Background:
    Given opened housing page

#This test will be failing, because by default "upcoming" option should not be present
  @housingDefaultSorting
  Scenario: Validate price sorting options before search
    When user click drop down menu with price sorting options
    Then by default should be following options:
      | uusin   |
      | hinta ↑ |
      | hinta ↓ |

  @housingSearchNavigation
  Scenario Outline: Validate ulr for default filter price sorting option: "<option>"
    When user pick price sorting filter: "<option>"
    Then check url with path: "<path>"
    Examples:
      | option  | path                       |
      | uusin   | /search/hhh?sort=date&     |
      | hinta ↑ | /search/hhh?sort=priceasc& |
      | hinta ↓ | /search/hhh?sort=pricedsc& |

    @housingPriceUpSorting
    Scenario: Validate price sorting with filter: price up
      When user pick price sorting filter: "hinta ↑"
      Then validate items by price up