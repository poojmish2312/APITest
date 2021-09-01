Feature: Validating Place API

@AddPlace @Regression
Scenario Outline: Verify if place is being added successfully using AddPlace API
Given Add Place Payload with "<name>" "<languauge>" "<address>"
When User calls "AddPlaceAPI" with "POST" http request
Then API call is success with status code 200
#And "status" in response body is "OK"
#And verify place_Id created maps to "<name>" using "GetPlaceAPI"

Examples:
| name | language | address |
| firstname | English | world cross center |
#| secondname | Spanish | city cross center |

@DeletePlace @Regression
Scenario: Verify if delete place functionality is working
Given Delete Place Payload
When User calls "DeletePlaceAPI" with "POST" http request
Then API call is success with status code 200
And "status" in response body is "OK"