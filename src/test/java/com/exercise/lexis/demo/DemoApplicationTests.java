package com.exercise.lexis.demo;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DemoApplicationTests {

	private static MockWebServer mockBackEnd;

	@BeforeAll
	static void setUp() throws IOException {
		mockBackEnd = new MockWebServer();
		mockBackEnd.start(8888);
	}

	@AfterAll
	static void tearDown() throws IOException {
		mockBackEnd.shutdown();
	}

	@Test //happy path integration test
	void test_company_and_officers_successfully_retrieved() throws Exception {
		mockBackEnd.enqueue(new MockResponse()
				.setBody(TestUtils.companyResponse)
				.addHeader("Content-Type", "application/json"));

		mockBackEnd.enqueue(new MockResponse()
				.setBody(TestUtils.officerResponse)
				.addHeader("Content-Type", "application/json"));

		var response = given()
				.contentType("application/json")
				.body(TestUtils.searchRequest)
				.post("/search")
				.then()
				.statusCode(200)
				.extract()
				.response()
				.asString();

		JSONAssert.assertEquals(TestUtils.response, response, JSONCompareMode.LENIENT);

		assertThat(mockBackEnd.getRequestCount(), equalTo(2));
		var companyRequest = mockBackEnd.takeRequest();
		assertThat(companyRequest.getHeader("x-api-key"), equalTo("temp"));
		assertThat(companyRequest.getPath(), equalTo("/Search?Query=06500244"));

		var officerRequest = mockBackEnd.takeRequest();
		assertThat(officerRequest.getPath(), equalTo("/Officers?CompanyNumber=06500244"));
	}

	@Test
	void returns_bad_request_when_no_criteria() {
		var response = given()
				.contentType("application/json")
				.body("{}")
				.post("/search")
				.then()
				.statusCode(400)
				.extract()
				.response()
				.asString();

		assertThat(response, equalTo("No criteria given"));
	}

}
