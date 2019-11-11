package coctaildb;

import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class endpointsTest extends BaseTest{


    @DataProvider(name = "ingredientsData")
    public static Object [][] ingredientsData() {
        return new Object[][]{
                {"Vodka", "40", "1"},
                {"Gin", "40", "2"},
                {"Rum", "40", "3"}
        };
    }

    @Test(dataProvider = "ingredientsData")
    public void testSearchIngredientByName(String ingredient, String ingredientType, String ingredientId) {

       Response res =  given()
                 .when()
                 .queryParam("i", ingredient)
                 .get("/search.php")
                 .then()
                 .spec(responseSpec)
                 .extract().response();

        soft.assertEquals(res.jsonPath().getString("ingredients.strABV[0]"), ingredientType);
        soft.assertEquals(res.jsonPath().getString("ingredients.strType[0]"), ingredient);
        soft.assertEquals(res.jsonPath().getString("ingredients.strIngredient[0]"), ingredient);
        soft.assertEquals(res.jsonPath().getString("ingredients.idIngredient[0]"), ingredientId);

        soft.assertAll();

    }

    @DataProvider(name = "cocktailData")
    public static Object [][] cocktailData() {
        return new Object[][]{
                {"Margarita", "11007", "Ordinary Drink", "Tequila"},
                {"Arctic Fish", "14622", "Punch / Party Drink", "Vodka"},
                {"Caribbean Boilermaker", "17065", "Beer", "Corona"},
                {"Zoksel", "15691", "Soft Drink / Soda", "Beer"}
        };
    }

    @Test(dataProvider = "cocktailData")
    public void testGetCocktailByName(String cocktailName, String drinkId, String drinkCategory, String mainIngredient) {

        Response res =  given()
                .when()
                .queryParam("s", cocktailName)
                .get("/search.php")
                .then()
                .spec(responseSpec)
                .extract().response();

        soft.assertEquals(res.jsonPath().getString("drinks.strDrink[0]"), cocktailName);
        soft.assertEquals(res.jsonPath().getString("drinks.idDrink[0]"), drinkId);
        soft.assertEquals(res.jsonPath().getString("drinks.strCategory[0]"), drinkCategory);
        soft.assertEquals(res.jsonPath().getString("drinks.strIngredient1[0]"), mainIngredient);

        soft.assertAll();

    }

    @DataProvider(name = "IdCocktailData")
    public static Object [][] IdCocktailData() {
        return new Object[][]{
                {"15300", "3-Mile Long Island Iced Tea"},
                {"14622", "Arctic Fish"},
                {"15346", "155 Belmont"},
                {"16134", "Absolutly Screwed Up"}
        };
    }

    @Test(dataProvider = "IdCocktailData")
    public void testGetCocktailById(String drinkId, String cocktailName) {

        Response res =  given()
                .when()
                .queryParam("i", drinkId)
                .get("/lookup.php")
                .then()
                .spec(responseSpec)
                .extract().response();

        soft.assertEquals(res.jsonPath().getString("drinks.strDrink[0]"), cocktailName);
        soft.assertEquals(res.jsonPath().getString("drinks.idDrink[0]"), drinkId);

        soft.assertAll();

    }
}
