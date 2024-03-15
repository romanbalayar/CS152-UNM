import java.awt.*;

/**
 * Sand simulation project adapted from <a href="http://nifty.stanford.edu/2017/feinberg-falling-sand/">...</a>
 * <p>
 * Student name: Roman Balayar
 * <p>
 * TODO: Document expected behavior of various materials here
 */
public class SandSimulator {

    /**
     * Enum for material types of the particles
     */
    public enum Material {
        EMPTY,

        METAL,

        SAND,


        WATER,

        BASE,

        OIL,

        ACID

        //TODO: add constants for additional particle types here


    }

    /**
     * grid of particles of various materials
     */
    private final Material[][] grid;

    /**
     * The display window
     */
    private final SandDisplay display;

    /**
     * Create a new SandSimulator of given size.
     *
     * @param numRows number of rows
     * @param numCols number of columns
     */
    public SandSimulator(int numRows, int numCols) {
        // TODO: Include names for all Materials used in simulation
        //       (Can you do it without manually listing them all?)
        String[] names = new String[]{"Empty", "Metal", "SAND", "WATER", "BASE", "OIL", "ACID"};
        display = new SandDisplay("Falling Sand", names, numRows, numCols);

        // TODO: initialize grid with empty cells
        grid = new Material[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                grid[i][j] = Material.EMPTY;
            }
        }
    }

    /**
     * Called after the user clicks on a location using the given tool
     *
     * @param row  Row of location
     * @param col  Column of location
     * @param tool Name of selected tool
     */
    public void updateFromUser(int row, int col, String tool) {
        // TODO: update grid location with selected material
        grid[row][col] = Material.valueOf(tool.toUpperCase());

    }

    /**
     * copies each element of grid into the display
     */
    public void refreshDisplay() {
        // TODO: update display with colors based on grid contents
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                switch (grid[row][col]) {
                    case EMPTY -> display.setColor(row, col, Color.BLACK);
                    case METAL -> display.setColor(row, col, Color.GRAY);
                    case SAND -> display.setColor(row, col, Color.WHITE);
                    case WATER -> display.setColor(row, col, Color.BLUE);
                    case BASE -> display.setColor(row, col, Color.PINK);
                    case OIL -> display.setColor(row, col, Color.ORANGE);
                    case ACID -> display.setColor(row, col, Color.RED);
                }
            }
        }

    }

    /**
     * Update the simulation by one step.
     * Called repeatedly.
     * Causes one random particle to maybe do something
     */
    public void updateRandomLocation() {
        // TODO: select random location and update the particle if relevant
        int randomRow = (int) (Math.random() * grid.length - 1);
        int randomCol = (int) (Math.random() * grid[0].length);


        while (randomCol == 0 || randomCol == grid[0].length - 1) {
            randomCol = (int) (Math.random() * grid[0].length);
        }
        switch (grid[randomRow][randomCol]) {

            case SAND: {
                if (randomRow < grid.length - 1 && grid[randomRow + 1][randomCol] == Material.EMPTY) {
                    grid[randomRow + 1][randomCol] = Material.SAND;
                    grid[randomRow][randomCol] = Material.EMPTY;
                } else if (randomRow > 0 && grid[randomRow][randomCol - 1] == Material.EMPTY) {

                    grid[randomRow][randomCol - 1] = Material.SAND;
                    grid[randomRow][randomCol] = Material.EMPTY;
                } else if (randomRow < grid[0].length - 1 && grid[randomRow][randomCol + 1] == Material.EMPTY) {
                    grid[randomRow][randomCol + 1] = Material.SAND;
                    grid[randomRow][randomCol] = Material.EMPTY;
                }

                if (grid[randomRow + 1][randomCol] == Material.WATER) {
                    grid[randomRow + 1][randomCol] = Material.SAND;
                }

                if (grid[randomRow + 1][randomCol] == Material.OIL) {
                    grid[randomRow + 1][randomCol] = Material.SAND;
                }
                if (grid[randomRow + 1][randomCol] == Material.BASE) {
                    grid[randomRow + 1][randomCol] = Material.SAND;
                }
                if (grid[randomRow + 1][randomCol] == Material.ACID) {
                    grid[randomRow + 1][randomCol] = Material.SAND;
                }
                break;

            }
            case METAL: {
                if (grid[randomRow + 1][randomCol] == Material.METAL) {
                    grid[randomRow + 1][randomCol] = Material.METAL;
                    grid[randomRow + 1][randomCol] = Material.METAL;
                }
                break;
            }
            case WATER: {
                if (randomRow < grid.length - 1 && (grid[randomRow + 1][randomCol] == Material.EMPTY)) {
                    grid[randomRow + 1][randomCol] = Material.WATER;
                    grid[randomRow][randomCol] = Material.EMPTY;
                } else if (randomCol > 0 && (grid[randomRow][randomCol - 1] == Material.EMPTY)) {

                    grid[randomRow][randomCol - 1] = Material.WATER;
                    grid[randomRow][randomCol] = Material.EMPTY;
                } else if (randomCol < grid[0].length - 1 && (grid[randomRow][randomCol + 1] == Material.EMPTY)) {
                    grid[randomRow][randomCol + 1] = Material.WATER;
                    grid[randomRow][randomCol] = Material.EMPTY;
                }

                if (grid[randomRow + 1][randomCol] == Material.ACID) {
                    grid[randomRow + 1][randomCol] = Material.WATER;
                }
                if (grid[randomRow + 1][randomCol] == Material.BASE) {
                    grid[randomRow + 1][randomCol] = Material.WATER;
                }
                if (grid[randomRow][randomCol] == Material.OIL) {
                    grid[randomRow + 1][randomCol] = Material.WATER;

                }
                break;

            }

            case ACID: {
                if (grid[randomRow + 1][randomCol] == Material.EMPTY) {
                    grid[randomRow + 1][randomCol] = Material.ACID;
                    grid[randomRow][randomCol] = Material.EMPTY;
                }
                // if we react metal with acid then metal convert into gas and other. So, it disappeared
                if (grid[randomRow + 1][randomCol] == Material.METAL) {
                    grid[randomRow + 1][randomCol] = Material.EMPTY;
                }
                // if acid and metal react each they gives water and salt
                if (grid[randomRow + 1][randomCol] == Material.BASE) {
                    grid[randomRow + 1][randomCol] = Material.WATER;
                }
                break;

            }

            case OIL: {
                if (grid[randomRow + 1][randomCol] == Material.EMPTY) {
                    grid[randomRow + 1][randomCol] = Material.OIL;
                    grid[randomRow][randomCol] = Material.EMPTY;
                }
                if (grid[randomRow + 1][randomCol] == Material.WATER) {
                    grid[randomRow][randomCol] = Material.OIL;
                }
                if (grid[randomRow][randomCol] == Material.WATER) {
                    grid[randomRow + 1][randomCol] = Material.OIL;
                }

                break;

            }

            case BASE: {
                if (grid[randomRow + 1][randomCol] == Material.EMPTY) {
                    grid[randomRow + 1][randomCol] = Material.BASE;
                    grid[randomRow][randomCol] = Material.EMPTY;
                }
                // if we react metal with base then metal convert into gas and other. So, it disappeared
                if (grid[randomRow + 1][randomCol] == Material.METAL) {
                    grid[randomRow + 1][randomCol] = Material.EMPTY;
                }
                // acid and base reaction gives water and salt
                if (grid[randomRow + 1][randomCol] == Material.ACID) {
                    grid[randomRow + 1][randomCol] = Material.WATER;
                }
                break;
            }


        }

    }

    /**
     * Run the SandSimulator particle simulation.
     * <p>
     * DO NOT MODIFY THIS METHOD!
     */
    public void run() {
        // keep updating as long as the program is running
        while (true) {
            // update some number of particles, as determined by the speed slider
            for (int i = 0; i < display.getSpeed(); i++) {
                updateRandomLocation();
            }
            // Update the display object's colors
            refreshDisplay();
            // wait for redrawing and for mouse events
            display.repaintAndPause(1);

            int[] mouseLoc = display.getMouseLocation();
            //test if mouse clicked
            if (mouseLoc != null) {
                updateFromUser(mouseLoc[0], mouseLoc[1], display.getToolString());
            }
        }
    }

    /**
     * Creates a new SandSimulator and sets it running
     */
    public static void main(String[] args) {
        SandSimulator sim = new SandSimulator(120, 80);
        sim.run();
    }
}
