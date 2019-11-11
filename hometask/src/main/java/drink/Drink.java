package drink;

public class Drink {

    private String cocktailId;

    private String glass;

    private String drinkName;

    private String drinkCategory;

    public String getDrinkCategory() {
        return drinkCategory;
    }

    public void setDrinkCategory(String drinkCategory) {
        this.drinkCategory = drinkCategory;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public void setCocktailId(String cocktailId) {
        this.cocktailId = cocktailId;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public void setDrink(String drink) {
        this.drinkName = drink;
    }

    public String getCocktailId() {
        return cocktailId;
    }

    public String getGlass() {
        return glass;
    }

    public String getDrinkName() {
        return drinkName;
    }



}
