#Author: Sourav Das || sourav.d@spotdraft.com || emp_Id : XXXXXX  || Date : 03.12.2022
@123
Feature: feature to test functionality of -> Want to read [Story ID - xxxxx]

  @12345
  Scenario Outline: Check specific book add and remove from book list [Scenario ID - xxxxx]

    Given I Login to "goodreads.com" with valid credentials <UserName> and <Password>
    And Search for a specific book title in searchBox <BookName>
    Then navigate to Product details Page of that book
    When I mark it as "want to read"
    Then it should get added in to "My Books"
    When I removed it from Book list
    Then It should get removed from the Book list
    And I logged out from the application
    Examples:
      | UserName             | Password      | BookName                |
      | sourav2864@gmail.com | Good123@Reads | The Sun and Her Flowers |