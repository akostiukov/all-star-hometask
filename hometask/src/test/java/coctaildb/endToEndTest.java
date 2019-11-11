package coctaildb;

import drink.Drink;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class endToEndTest extends BaseTest {

    private String cocktailId;

    private String glass;

    private String drinkName;

    private String drinkCategory;

    private Drink drink = new Drink();

    @Test()
    public void getRandomCocktailTest() {

        Response res = given()
                .when()
                .get("/random.php")
                .then()
                .spec(responseSpec)
                .extract().response();

        cocktailId = res.jsonPath().getString("drinks.idDrink[0]");
        glass = res.jsonPath().getString("drinks.strGlass[0]");
        drinkName = res.jsonPath().getString("drinks.strDrink[0]");
        drinkCategory = res.jsonPath().getString("drinks.strCategory[0]");

        drink.setDrink(drinkName);
        drink.setCocktailId(cocktailId);
        drink.setGlass(glass);
        drink.setDrinkCategory(drinkCategory);

        Assert.assertNotNull(cocktailId, "cocktailId is :" + cocktailId);
        Assert.assertNotNull(glass, "glass is :" + glass);
        Assert.assertNotNull(cocktailId, "drink is :" + drinkName);
    }



    @Test(priority = 1)
    public void getCocktailDetailByIdTest() {

        Response res = given()
                .when()
                .queryParam("i", drink.getCocktailId())
                .get("/lookup.php")
                .then()
                .spec(responseSpec)
                .extract().response();

       Assert.assertEquals(res.jsonPath().getString("drinks.strDrink[0]"), drink.getDrinkName());
    }


    @Test(priority = 2)
    public void getListOfDrinksByGlassesTest() {

        Response res = given()
                .when()
                .queryParam("g", drink.getGlass())
                .get("/filter.php")
                .then()
                .spec(responseSpec)
                .extract().response();

        ResponseBody body = res.getBody();
        Assert.assertTrue(body.asString().contains(drink.getDrinkName()));
    }

    @Test(priority = 2)
    public void getListOfDrinksByCategory() { // issue found product {idDrink: "12450", strDrink: "Victor" strCategory: "Ordinary Drink"} doesn't present in appropriate category list

        Response res = given()
                .when()
                .queryParam("c", drink.getDrinkCategory())
                .get("/filter.php")
                .then()
                .spec(responseSpec)
                .extract().response();

        ResponseBody body = res.getBody();
        Assert.assertTrue(body.asString().contains(drink.getDrinkName()));

    }

    @Test(priority = 3)
    public void getPopularCocktailsTest() {

      given()
                .when()
                .get("/popular.php")
                .then()
                .assertThat()
                .statusCode(404);
    }

}

