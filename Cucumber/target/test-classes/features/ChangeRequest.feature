@changerequest 
Feature: Feature to handle Change Requests 

Background: Test Setup 
	Given set baseUri 
	And set Authorization 
	
@CRs 
Scenario Outline: Create Change Requests using multiple files 
	And create change requests with file "<filename>" 
	Then verify status code is 201 
	Examples: 
		|filename|
		|changeRequest1.json|
		|changeRequest2.json|
		|changeRequest3.json|
		
		@CRs 
		Scenario: Get All Change Requests with multiple QPs 
			When get change requests with query params 
				|sysparm_fields|number,sys_id,type|
				|sysparm_limit|3|
			Then verify status code is 200