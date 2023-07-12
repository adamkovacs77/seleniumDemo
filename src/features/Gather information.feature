Feature: Gather information

  Background:
    Given I navigate to "https://github.com/SeleniumHQ/selenium"

  Scenario: Gather repository information
    When I click on "Code" dialog
    And I get the repository URL
    And I close the "Code" dialog if it's open
    And I get the number of releases
    Then I create the output json file named "selenium-meta-data"

  Scenario: Gather issue information
    When I open the "Issues" tab
    And I clear the issues search bar
    And I insert into issues search bar "sort:comments-desc"
    And I open Label filter menu
    And I search by label filter "C-java"
    And I click on "Open" issues button
    And I get the first issue row
    And I get the issue title
    And I get the issue labels
    And I get the issue number of comments
    And I get the issue id
    And I get the issue author
    And I get the issue creation time
    Then I create the output json file named "most-discussed-java-issue"