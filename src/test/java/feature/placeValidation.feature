Feature: Validating Place API's
@AddPlace
Scenario Outline: Verify that add new place functionality is working fine
	Given Add place Payload "<name>" "<langugage>" "<address>"
	When user calls add "AddPlaceAPI" with "POST" http request
	Then API call is success and response code is 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "getPlaceAPI"
	#And verify the place is deleted when user calls "delPlaceAPI"
	
	Examples:
		|name| langugage | address| 	
		|Govind | English|3163 PB|
#		|Likant | English | 3163 PN|
@DeletePlace
Scenario: Verify that place is deleted successfully
	Given Delete place api
	When user calls add "delPlaceAPI" with "POST" http request
#	When user calls "delPlaceAPI" with "POST" http request
	Then API call is success and response code is 200
	And "status" in response body is "OK"
