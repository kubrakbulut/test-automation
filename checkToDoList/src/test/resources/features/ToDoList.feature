@functional
Feature: ToDo list

  Scenario: Filling a to-do list
    Given I, sees empty to do list
    When I write buy some milk to text box and press enter
    Then I should see buy some milk item in ToDo list

    Given I, to do list with buy some milk item
    When I write enjoy the assignment to text box and press enter
    Then I should see enjoy the assignment item inserted to ToDo list below buy some milk item

    Given I, to do list with buy some milk item
    When I click on checkbox next to buy some milk item
    Then I should see buy some milk item marked as DONE

    Given I, to do list with marked item
    When I click on checkbox next to item
    Then I should see buy some milk item marked as UNDONE

    And I write rest for a while to text box and press enter

    Given I, to do list with rest for a while item
    When I click on delete button next to rest for a while item
    Then List should be empty

    And I write rest for a while to text box and press enter
    And I write drink water to text box and press enter

    Given I, to do list with rest for a while and drink water item in order
    When I click on delete button next to rest for a while item
    Then I should see drink water item in to do list










