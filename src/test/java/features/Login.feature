Feature: Application Login

Scenario: Home Page Default Login
Given User is on NetBanking Landing page
When User login intoapplication with username and password
Then Home page is populated
And Cards are displayed