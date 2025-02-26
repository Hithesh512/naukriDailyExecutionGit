Feature: Update resume and location functionality

  Background: User navigates to the login page
    Given user navigate to the Naukri login page
    When user Click on Login button
    When user enter valid username "hithesh512@gmail.com" and password "AlgoHithi512#"
    And user click on the login button
    Then I should be redirected to the homepage

  Scenario: Update resume and location functionality
    When user see Naukri Home page
    And user click on the profile drawer Icon on Home page
    And user click on view and update profile Button on Home Page
    Then Page should display Profile Page
    And user upload resume on Profile Page
    Then Page should display Success message on profile Page
    And user click on edit profile button on Profile Page
    And User clears the current location on Basic details Form
    And User Enters the new location on Basic details Form
    And User click on the save button on Basic details Form
    Then User verify the newly added address on profile Page
