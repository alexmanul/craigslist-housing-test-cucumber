# info
# tests are written with native page language (Finland)
  # translations:
    # tulossa    -> upcoming
    # uusin      -> newest
    # asiaankuul -> relevant
    # hinta ↑    -> price up
    # hinta ↓    -> price down
@all @housingPage @housingSorting
Feature: Validate housing page price sorting filter

  Background:
    Given opened housing page

    # This test will be failing, because by default "upcoming" option should not be present
  @housingSortingBs
  Scenario: Validate sorting options before search
    When click drop down menu with price sorting options
    Then should be available following sorting options:
      | uusin   |
      | hinta ↑ |
      | hinta ↓ |

  @housingSortingAs
  Scenario: Validate sorting options after search
    When after "random" search
    And click drop down menu with price sorting options
    Then should be available following sorting options:
      | tulossa    |
      | uusin      |
      | asiaankuul |
      | hinta ↑    |
      | hinta ↓    |

  @housingSearchNavigation
  Scenario Outline: Validate ulr for default filter price sorting option: <option>
    When user pick price sorting filter: "<sorting_option>"
    Then check url with path: "<path>"
    Examples:
      | sorting_option | path                       |
      | uusin          | /search/hhh?sort=date&     |
      | hinta ↑        | /search/hhh?sort=priceasc& |
      | hinta ↓        | /search/hhh?sort=pricedsc& |

    # Note: filtering for nearby areas working only for Finland
  @housingPriceSortingNearBy
  Scenario Outline: Validate prices for nearby areas with sorting: <sorting_option>
    When user pick price sorting filter: "<sorting_option>"
    And set nearby areas
    Then validate "<sorting_option>" sorting
    Examples:
      | sorting_option |
      | hinta ↑        |
      | hinta ↓        |