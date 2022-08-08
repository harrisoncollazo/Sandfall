import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.Timer;

public class Solution {

  // Add constants for particle types here.
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  public static final int ICE = 4;
  public static final int SAND2 = 5;

  public static final String[] NAMES = {"Empty", "Metal", "Sand", "Water", "Ice"};

  // Do not add any more fields as part of Lab 5.
  private int[][] grid;
  private SandDisplayInterface display;
  private RandomGenerator random;

  /**
   * Constructor.
   *
   * @param display The display to use for this run
   * @param random The random number generator to use to pick random points
   */
  public Solution(SandDisplayInterface display, RandomGenerator random) {
    this.display = display;
    this.random = random;

    this.grid = new int[display.getNumRows()][display.getNumColumns()];
  }

  /**
   * Called when the user clicks on a location.
   *
   * @param row
   * @param col
   * @param tool
   */
  private void locationClicked(int row, int col, int tool) {
    // TODO: Populate this method in step 3.
    Random rand = new Random();
    int colorRand = rand.nextInt(2);
    if (tool == SAND) {
      if (colorRand == 0) {
        grid[row][col] = SAND;
      } else if (colorRand == 1) {
        grid[row][col] = SAND2;
      }
    } else if (tool != SAND) {
      grid[row][col] = tool;
    }
  }

  /** Copies each element of grid into the display. */
  public void updateDisplay() {
    // TODO: Populate this method in step 4 and beyond.
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == EMPTY) {
          display.setColor(i, j, Color.BLACK);
        }
        if (grid[i][j] == METAL) {
          display.setColor(i, j, Color.gray);
        }
        if (grid[i][j] == SAND) {
          display.setColor(i, j, Color.yellow);
        }
        if (grid[i][j] == SAND2) {
          display.setColor(i, j, Color.orange);
        }

        // if (grid[i][j] == WATER) {
        // display.setColor(i, j, Color.cyan);
        // }
        if (grid[i][j] == ICE) {
          display.setColor(i, j, Color.WHITE);
        }
      }
    }
  }

  /** Called repeatedly. Causes one random particle to maybe do something. */
  public void step() {
    // TODO: Populate this method in step 6 and beyond.

    // Point randomPoint = random.getRandomPoint();
    // int row = randomPoint.getRow();
    // int col = randomPoint.getColumn();

    Random rand = new Random();
    int row = rand.nextInt(grid.length - 1);
    int col = rand.nextInt(grid[0].length - 1);
    int colorRand = rand.nextInt(2);

    if (grid[row][col] == SAND) {

      if ((row + 1) < grid.length) {
        if (grid[row + 1][col] == EMPTY) {
          grid[row][col] = EMPTY;
          grid[row + 1][col] = SAND;
        } else if (grid[row + 1][col] == WATER) {
          grid[row][col] = WATER;
          grid[row + 1][col] = SAND;
        } else if (grid[row + 1][col] == SAND || grid[row + 1][col] == SAND2) {
          if ((col + 1) < grid[0].length && (col - 1) >= 0) {
            if (grid[row + 1][col - 1] == EMPTY) {
              grid[row][col] = EMPTY;
              grid[row + 1][col - 1] = SAND;
            }
            if (grid[row + 1][col + 1] == EMPTY) {
              grid[row][col] = EMPTY;
              grid[row + 1][col + 1] = SAND;
            }
            if (grid[row + 1][col - 1] == WATER) {
              grid[row][col] = WATER;
              grid[row + 1][col - 1] = SAND;
            }
            if (grid[row + 1][col + 1] == WATER) {
              grid[row][col] = WATER;
              grid[row + 1][col + 1] = SAND;
            }
          }
        } else if (grid[row + 1][col] == METAL) {
          if ((col + 1) < grid[0].length && (col - 1) >= 0 && (row - 1) >= 0) {
            if (grid[row][col - 1] == EMPTY) {
              if (grid[row - 1][col + 1] == SAND || grid[row - 1][col + 1] == SAND2) {
            
              grid[row][col] = EMPTY;
              grid[row][col - 1] = SAND;
              }
            }
            if (grid[row][col + 1] == EMPTY) {
              if (grid[row - 1][col - 1] == SAND || grid[row - 1][col - 1] == SAND2) {
            
              grid[row][col] = EMPTY;
              grid[row][col + 1] = SAND;
              }
            }
          }
        }
      }

    } else if (grid[row][col] == SAND2) {

      if ((row + 1) < grid.length) {
        if (grid[row + 1][col] == EMPTY) {
          grid[row][col] = EMPTY;
          grid[row + 1][col] = SAND2;
        } else if (grid[row + 1][col] == WATER) {
          grid[row][col] = WATER;
          grid[row + 1][col] = SAND2;
        } else if (grid[row + 1][col] == SAND || grid[row + 1][col] == SAND2) {
          if ((col + 1) < grid[0].length && (col - 1) >= 0) {
            if (grid[row + 1][col - 1] == EMPTY) {
              grid[row][col] = EMPTY;
              grid[row + 1][col - 1] = SAND2;
            }
            if (grid[row + 1][col + 1] == EMPTY) {
              grid[row][col] = EMPTY;
              grid[row + 1][col + 1] = SAND2;
            }
            if (grid[row + 1][col - 1] == WATER) {
              grid[row][col] = WATER;
              grid[row + 1][col - 1] = SAND2;
            }
            if (grid[row + 1][col + 1] == WATER) {
              grid[row][col] = WATER;
              grid[row + 1][col + 1] = SAND2;
            }
          }
        } else if (grid[row + 1][col] == METAL) {
          if ((col + 1) < grid[0].length && (col - 1) >= 0 && (row - 1) >= 0) {
            if (grid[row][col - 1] == EMPTY && grid[row - 1][col + 1] == SAND2) {
              grid[row][col] = EMPTY;
              grid[row][col - 1] = SAND2;
            }
            if (grid[row][col + 1] == EMPTY && grid[row - 1][col - 1] == SAND2) {
              grid[row][col] = EMPTY;
              grid[row][col + 1] = SAND2;
            }
          }
        }
      }

    } else if (grid[row][col] == WATER) {
      Color myCyan = new Color(140, 245, 245);
      if (colorRand == 0) {
        display.setColor(row, col, Color.CYAN);
      }
      if (colorRand == 1) {
        display.setColor(row, col, myCyan);
      }

      int DOWN = 0;
      int RIGHT = 1;
      int LEFT = 2;

      int direction = random.getRandomDirection();

      if ((row + 1) < grid.length) {

        if (direction == DOWN && grid[row + 1][col] == EMPTY) {
          grid[row][col] = EMPTY;
          grid[row + 1][col] = WATER;
        }
        if (direction == DOWN && grid[row + 1][col] == ICE) {
          grid[row][col] = ICE;
          grid[row + 1][col] = WATER;
        }
      }

      if (col + 1 < grid[0].length) {

        if (direction == RIGHT && grid[row][col + 1] == EMPTY) {
          grid[row][col] = EMPTY;
          grid[row][col + 1] = WATER;
        }
      }
      if (col - 1 >= 0) {

        if (direction == LEFT && grid[row][col - 1] == EMPTY) {
          grid[row][col] = EMPTY;
          grid[row][col - 1] = WATER;
        }
      }
    } else if (grid[row][col] == ICE) {

      int DOWN = 0;
      int RIGHT = 1;
      int LEFT = 2;

      int direction = random.getRandomDirection();

      Timer timer = new Timer();

      timer.schedule(
          new TimerTask() {
            @Override
            public void run() {
              if (grid[row][col] == ICE) {
                grid[row][col] = WATER;
              }
            }
          },
          4 * 1000);
      if ((row + 1) < grid.length) {

        if (direction == DOWN && grid[row + 1][col] == EMPTY) {
          grid[row][col] = EMPTY;
          grid[row + 1][col] = ICE;
        }
      }

      if (col + 1 < grid[0].length) {

        if (direction == RIGHT && grid[row][col + 1] == EMPTY) {
          grid[row][col] = EMPTY;
          grid[row][col + 1] = ICE;
        }
      }
      if (col - 1 >= 0) {

        if (direction == LEFT && grid[row][col - 1] == EMPTY) {
          grid[row][col] = EMPTY;
          grid[row][col - 1] = ICE;
        }
      }
    }
  }

  /********************************************************************/
  /********************************************************************/
  /**
   * DO NOT MODIFY
   *
   * <p>The rest of this file is UI and testing infrastructure. Do not modify as part of pre-GDA Lab
   * 5.
   */
  /********************************************************************/
  /********************************************************************/

  private static class Point {
    private int row;
    private int column;

    public Point(int row, int column) {
      this.row = row;
      this.column = column;
    }

    public int getRow() {
      return row;
    }

    public int getColumn() {
      return column;
    }
  }

  /**
   * Special random number generating class to help get consistent results for testing.
   *
   * <p>Please use getRandomPoint to get an arbitrary point on the grid to evaluate.
   *
   * <p>When dealing with water, please use getRandomDirection.
   */
  public static class RandomGenerator {
    private static Random randomNumberGeneratorForPoints;
    private static Random randomNumberGeneratorForDirections;
    private int numRows;
    private int numCols;

    public RandomGenerator(int seed, int numRows, int numCols) {
      randomNumberGeneratorForPoints = new Random(seed);
      randomNumberGeneratorForDirections = new Random(seed);
      this.numRows = numRows;
      this.numCols = numCols;
    }

    public RandomGenerator(int numRows, int numCols) {
      randomNumberGeneratorForPoints = new Random();
      randomNumberGeneratorForDirections = new Random();
      this.numRows = numRows;
      this.numCols = numCols;
    }

    public Point getRandomPoint() {
      return new Point(
          randomNumberGeneratorForPoints.nextInt(numRows),
          randomNumberGeneratorForPoints.nextInt(numCols));
    }

    /**
     * Method that returns a random direction.
     *
     * @return an int indicating the direction of movement: 0: Indicating the water should attempt
     *     to move down 1: Indicating the water should attempt to move right 2: Indicating the water
     *     should attempt to move left
     */
    public int getRandomDirection() {
      return randomNumberGeneratorForDirections.nextInt(3);
    }
  }

  public static void main(String[] args) {
    // Test mode, read the input, run the simulation and print the result
    Scanner in = new Scanner(System.in);
    int numRows = in.nextInt();
    int numCols = in.nextInt();
    int iterations = in.nextInt();
    Solution lab =
        new Solution(new NullDisplay(numRows, numCols), new RandomGenerator(0, numRows, numCols));
    lab.readGridValues(in);
    lab.runNTimes(iterations);
    lab.printGrid();
  }

  /**
   * Read a grid set up from the input scanner.
   *
   * @param in
   */
  private void readGridValues(Scanner in) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length; j++) {
        grid[i][j] = in.nextInt();
      }
    }
  }

  /** Output the current status of the grid for testing purposes. */
  private void printGrid() {
    for (int i = 0; i < grid.length; i++) {
      System.out.println(Arrays.toString(grid[i]));
    }
  }

  /** Runner that advances the display a determinate number of times. */
  private void runNTimes(int times) {
    for (int i = 0; i < times; i++) {
      runOneTime();
    }
  }

  /** Runner that controls the window until it is closed. */
  public void run() {
    while (true) {
      runOneTime();
    }
  }

  /**
   * Runs one iteration of the display. Note that one iteration may call step repeatedly depending
   * on the speed of the UI.
   */
  private void runOneTime() {
    for (int i = 0; i < display.getSpeed(); i++) {
      step();
    }
    updateDisplay();
    display.repaint();
    display.pause(1); // Wait for redrawing and for mouse
    int[] mouseLoc = display.getMouseLocation();
    if (mouseLoc != null) { // Test if mouse clicked
      locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
    }
  }

  /**
   * An implementation of the SandDisplayInterface that doesn't display anything. Used for testing.
   */
  static class NullDisplay implements SandDisplayInterface {
    private int numRows;
    private int numCols;

    public NullDisplay(int numRows, int numCols) {
      this.numRows = numRows;
      this.numCols = numCols;
    }

    public void pause(int milliseconds) {}

    public int getNumRows() {
      return numRows;
    }

    public int getNumColumns() {
      return numCols;
    }

    public int[] getMouseLocation() {
      return null;
    }

    public int getTool() {
      return 0;
    }

    public void setColor(int row, int col, Color color) {}

    public int getSpeed() {
      return 1;
    }

    public void repaint() {}
  }

  /** Interface for the UI of the SandLab. */
  public interface SandDisplayInterface {
    public void repaint();

    public void pause(int milliseconds);

    public int[] getMouseLocation();

    public int getNumRows();

    public int getNumColumns();

    public void setColor(int row, int col, Color color);

    public int getSpeed();

    public int getTool();
  }
}
