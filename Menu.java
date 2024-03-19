/**
 * A Menu lists all the dishes a restaurant can make for a customer, the price
 * of each dish, and diet flags (ex. vegetarian, not free, etc.).
 * <p>
 * Dishes are grouped into menu categories (main, soup, side, dessert).
 *
 * @author Austyn Mitchev, Molly Palko, Brooke Chenoweth
 */
public class Menu {
    /**
     * Name of the menu
     */
    private String name;

    /**
     * Array of menu section names
     */
    private final String[] sections;

    /**
     * Number of dishes currently in each section
     */
    private final int[] numDishes;

    /**
     * each row holds a different section of the menu
     * each row can have a different number of columns
     */
    private final Dish[][] dishes;


    /**
     * create an empty menu to fill with dishes in later:
     * # rows in menu = sections. length
     * # cols in menu[i] = numDishes[i]
     * the number of dishes (col) in each section (row) can be different
     *
     * @param name     name of menu
     * @param sections fixed order and amount of menu sections
     * @param capacity determines max number of dishes in each section of the menu
     */
    public Menu(String name, String[] sections, int[] capacity) {
        this.name = name;
        this.sections = sections;
        this.numDishes = new int[capacity.length];
        dishes = new Dish[sections.length][];
        for (int i = 0; i < sections.length; i++) {
            dishes[i] = new Dish[capacity[i]];
            numDishes[i] = 0;
        }
    }

    /**
     * Gets the size of the menu
     *
     * @return The number of dishes stored in this menu object.
     */
    public int getMenuSize() {
        // TODO Implement this
        int sizeMenu = 0;
        for (int i = 0; i < dishes.length; i++) {
            sizeMenu += numDishes[i];
        }
        return sizeMenu;
    }

    /**
     * Get the menu name.
     *
     * @return Name of the menu.
     */
    public String getName() {
        // TODO implement this
        return name;
    }

    /**
     * Set the menu name. May not be empty.
     *
     * @param name New menu name.
     */
    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }
    }

    /**
     * Add a dish to the menu.
     * If the dish is already present, does not add duplicate.
     * If the dish is new, add it after the existing dishes in its section
     *
     * @param d Dish to add.
     * @return String describing what happened
     * - "Section not found!" : If the section for this dish is not on the menu
     * - "Dish already exists!" : If the dish already exists on the menu
     * - "No room on the menu!" : If there is no room on the menu for the dish
     * - "Dish added!" : If the dish was successfully added
     */
    public String addDish(Dish d) {
        // TODO implement this
        for (int i = 0; i < sections.length; i++) {
            if (d.getMenuSection().equals(sections[i])) {
                for (int j = 0; j < numDishes[i]; j++) {
                    if (dishes[i][j] != null && dishes[i][j].equals(d)) {
                        return "Dish already exists!";
                    }
                }
                if (numDishes[i] < dishes[i].length) {
                    dishes[i][numDishes[i]] = d;
                    numDishes[i]++;
                    return "Dish added!";
                } else {
                    return "No room on the menu!";
                }
            }
        }
        return "Section not found!";
    }


    /**
     * Add all the dishes in the array to the menu.
     * Should use addDish() to add each one.
     *
     * @param newDishes Dishes to add.
     * @return Array of strings returned by the calls to addDish
     */
    public String[] addDishes(Dish[] newDishes) {
        // TODO implement this
        String[] res = new String[newDishes.length];
        for (int i = 0; i < newDishes.length; i++) {
            res[i] = addDish(newDishes[i]);
        }
        return res;
    }

    /**
     * Get a 1 dimensional array of all the dishes on the menu.
     * Dishes are ordered section by section (row by row)
     *
     * @return Array of all dishes.
     */
    public Dish[] getDishes() {
        // TODO implement this
        int totalDishes = getMenuSize();
        Dish[] allDishes = new Dish[totalDishes];
        int index = 0;
        for (int i = 0; i < dishes.length; i++) {
            for (int j = 0; j < numDishes[i]; j++) {
                allDishes[index] = dishes[i][j];
                index++;
            }
        }
        return allDishes;
    }

    /**
     * Get string representation of the menu.
     * Should be menu name on first line,
     * followed by one dish per line using Dish.toString() format
     *
     * @return String of all dishes on the menu
     */
    public String toString() {
        // TODO implement this
        StringBuilder menuDishes = new StringBuilder(name + "\n");
        for (Dish[] section : dishes) {
            for (Dish dish : section) {
                if (dish != null) {
                    menuDishes.append(dish).append("\n");
                }
            }
        }
        return menuDishes.toString();
    }

    /**
     * Create string of entire menu, grouped by menu section
     *
     * @return String of menu sorted by category in the following format:
     * "*** <Menu Name> ***
     * <Section 0>:
     * - <Dish 0> (in the Dish.toMenuString() format)
     * - <Dish 1>
     * ...
     * - <Dish n>
     * <Section 1>:
     * - <Dish 0> (in the Dish.toMenuString() format)
     * - <Dish 1>
     * ...
     * - <Dish n>
     * ..."
     */
    public String getFullMenu() {
        // TODO implement this
        StringBuilder fullMenu = new StringBuilder("*** " + name + " ***\n");
        for (int i = 0; i < dishes.length; i++) {
            fullMenu.append(sections[i]).append(":\n");
            for (int j = 0; j < numDishes[i]; j++) {

                fullMenu.append("- ").append(dishes[i][j].toMenuString()).append("\n");
            }
        }
        return fullMenu.toString();
    }

    /**
     * Create string of only the vegetarian dishes on the menu in the same format
     * as getFullMenu, but precede vegan dishes with a * instead of a -
     *
     * @return String of vegetarian dishes
     */
    public String getVegetarianMenu() {
        // TODO implement this
        StringBuilder vegetarianMenu = new StringBuilder("*** " + name + " ***\n");

        for (int i = 0; i < dishes.length; i++) {
            vegetarianMenu.append(sections[i]).append(":\n");
            for (int j = 0; j < numDishes[i]; j++) {
                if (dishes[i][j] != null && dishes[i][j].isVegan()) {
                    vegetarianMenu.append("* ").append(dishes[i][j].toMenuString()).append("\n");
                    continue;
                }
                if (dishes[i][j] != null && dishes[i][j].isVegetarian()) {
                    vegetarianMenu.append("- ").append(dishes[i][j].toMenuString()).append("\n");
                }
            }
        }
        return vegetarianMenu.toString();
    }

    /**
     * Deletes dish entirely from the menu.
     * The original size of the array should be maintained, and
     * it should be altered such that 'Dishes' ahead of the
     * removed 'Dish' are moved ahead (i.e. if removed dish at index [i][j],
     * then after removal menu[i][j] = menu[i][j+ 1], menu[i][j+1] = menu[i][j+2],
     * and the last element menu[i][menu[i].length-1] = null
     * Extra Credit
     *
     * @param d Dish to remove.
     * @return String denoting success or failure.
     * * MAKE SURE TO UPDATE OPTIONS AFTER REMOVING
     */
    public boolean removeDish(Dish d) {
        // TODO implement this
        for (int i = 0; i < sections.length; i++) {
            for (int j = 0; j < numDishes[i]; j++) {
                if (dishes[i][j].equals(d)) {
                    for (int x = j; x < numDishes[i] - 1; x++) {
                        dishes[i][x] = dishes[i][x + 1];
                    }
                    dishes[i][numDishes[i] - 1] = null;
                    numDishes[i]--;
                    return true;
                }
            }
        }
        return false;
    }

}