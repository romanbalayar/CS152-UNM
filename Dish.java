//Dish file
public class Dish {
    private String name;
    private double price;
    private String menuSection;
    private String foodGroups;

    public Dish() {
        this.name = "UNKNOWN NAME";
        this.price = -543.21;
        this.menuSection = "UNKNOWN SECTION";
        this.foodGroups = "";
    }

    public Dish(String menuSection) {
        this();
        this.menuSection = menuSection;
    }

    public Dish(String name, double price, String menuSection, String foodGroups) {
        this.name = name;
        this.price = price;
        this.menuSection = menuSection;
        this.foodGroups = foodGroups;
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public double getPrice() {
        return this.price;
    }

    public void setMenuSection(String section) {
        this.menuSection = section;
    }

    public String getMenuSection() {
        return this.menuSection;
    }

    public void setFoodGroups(String foodGroups) {
        StringBuilder sanitizedFoodGroups = new StringBuilder();

        for (int i = 0; i < foodGroups.length(); i++) {

            char currentChar = foodGroups.charAt(i);

            // Check if the character is a known food group character
            if (currentChar == FoodConstants.FOOD_GROUP_CHARS[0] ||
                    currentChar == FoodConstants.FOOD_GROUP_CHARS[1] ||
                    currentChar == FoodConstants.FOOD_GROUP_CHARS[2] ||
                    currentChar == FoodConstants.FOOD_GROUP_CHARS[3] ||
                    currentChar == FoodConstants.FOOD_GROUP_CHARS[4]) {

                // Only include valid characters
                if (sanitizedFoodGroups.indexOf(String.valueOf(currentChar)) == -1) {
                    // Avoid duplicates
                    sanitizedFoodGroups.append(currentChar);
                }
            }
        }

        // Set the member variable to the sanitized string
        this.foodGroups = sanitizedFoodGroups.toString();
    }


    public String getFoodGroups() {
        return this.foodGroups;
    }

    public String toString() {
        if (foodGroups.isEmpty() && price < 0) {
            return name + ", " + menuSection;
        } else if (foodGroups.isEmpty()) {
            return name + ", " + menuSection + ": $" + price;
        } else if (price < 0) {
            return name + " (" + foodGroups + "), " + menuSection;
        } else {
            return name + " (" + foodGroups + "), " + menuSection + ": $" + price;
        }
    }

    public String toMenuString() {
        String foodGroupsString = getFoodGroupsDescription();
        return name + ": $" + this.price + "\n" + "     " + foodGroupsString;
    }

    private String getFoodGroupsDescription() {
        StringBuilder description = new StringBuilder();
        if (!foodGroups.isEmpty()) {
            String[] groups = foodGroups.split("");
            for (String group : groups) {
                switch (group) {
                    case "m" -> description.append("MEAT, ");
                    case "v" -> description.append("VEGETABLE, ");
                    case "d" -> description.append("DAIRY, ");
                    case "n" -> description.append("NUTS, ");
                    case "g" -> description.append("GRAINS, ");

                    // Add cases for other known food groups if needed
                }
            }
            description.setLength(description.length() - 2); // Remove the extra comma and space at the end
        }
        return description.toString();
    }

    public boolean isVegetarian() {
        return !foodGroups.contains("m");
    }

    public boolean isVegan() {
        return !foodGroups.contains("m") && !foodGroups.contains("d");
    }

    public boolean isSame(Dish other) {
        if (this == other) {
            return true;
        }
        return this.name.equals(other.name) || this.price == other.price
                || this.menuSection.equals(other.menuSection) || this.foodGroups.equals(other.foodGroups);
    }
}

