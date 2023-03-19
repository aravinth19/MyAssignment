package steps;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ChangeRequestSteps {

	public static RequestSpecification resSpecification;
	public static Response response;

	// Pre-Conditions
	@Given("set baseUri")
	public void setBaseUri() {
		RestAssured.baseURI = "https://dev140572.service-now.com/api/now/table/";
	}

	@Given("set Authorization")
	public void setAuthorization() {
		RestAssured.authentication = RestAssured.basic("admin", "P-qQ7@umSYz8");
	}

	// Test-Conditions
	@When("set content type {string}")
	public void setContentType(String contentType) {
		if (contentType.equalsIgnoreCase("Json"))
			resSpecification = RestAssured.given().contentType(ContentType.JSON);
		else
			resSpecification = RestAssured.given().contentType(ContentType.JSON);
	}

	public void setRequestBody(File fileName) {
		resSpecification.when().body(fileName);
	}

	public void setQueryParams(Map<String, String> qPs) {
		resSpecification = RestAssured.given().queryParams(qPs);
	}

	@When("create change requests with file {string}")
	public void createChangeRequestWithFile(String fileName) {
		File file = new File("./src/test/resources/data/" + fileName);
		setContentType("JSON");
		setRequestBody(file);
		response = resSpecification.post("change_request");
		printResponse();
	}

	@When("get change requests with query params")
	public void getChangeRequests(DataTable dt) {
		Map<String, String> qPs = dt.asMap();
		setQueryParams(qPs);
		response = resSpecification.get("change_request");
		printResponse();
	}

	public void printResponse() {
		response.prettyPrint();
	}

	// Post-Conditions
	@Then("verify status code is {int}")
	public void verifyResponse(int statusCode) {
		response.then().assertThat().statusCode(Matchers.equalTo(statusCode));
	}

}
