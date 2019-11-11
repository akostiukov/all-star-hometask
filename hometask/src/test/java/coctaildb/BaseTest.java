package coctaildb;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;


public class BaseTest {

    protected static RequestSpecification requestSpec;

    protected static ResponseSpecification responseSpec;

    SoftAssert soft = new SoftAssert();


    @BeforeClass
    public static void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri(baseURI).
                build();
    }

    @BeforeClass
    public static void createResponseSpecification() {

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @BeforeClass
    public void setupBaseURI() {
        baseURI = "https://www.thecocktaildb.com";
        basePath = "/api/json/v1/1/";
    }
}
