Feature: Validating place API's

Scenario: Verify if place is being added successfully using AddPlaceAPI
Given Add place payload
When User calls "addPlaceAPI" with "post" http request
Then The API call success with status code 200
And "status" in responce body is "OK"
And "scope" in responce body is "APP"

Scenario Outline: Verify if place is being added successfully using AddPlaceAPI
Given Add place payload "<name>" "<language>" "<address>"
When User calls "addPlaceAPI" with "post" http request
Then The API call success with status code 200
And "status" in responce body is "OK"
And "scope" in responce body is "APP"
And Verify place_ID created maps to "<name>" using "getPlaceAPI"

Examples:
| name | language | address |
|Test1 | US North | TC01 US |
|Test2 | US South | TC02 US |
|Test3 | US East  | TC03 US |
|Test4 | US West  | TC04 US |


#@DeletePlace
#Scenario: Verify that delete place functionality is working
#Given Deleteplace payload
#When User calls "deletePlaceAPLI" with "post" http request
#Then The API call success with status code 200
#And "status" in responce body is "OK"


