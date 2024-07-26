package breakout;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;

public class BrickGrid{
    private static final int BRICK_ROWS = 10;
    private static final int BRICK_COLUMNS = 12;

    private double xStart = 15;
    private double yStart = 75;
    private int row = 1;
    private int totalNumOfBricks = 120;
    
    public BrickGrid(CanvasWindow canvas, int canvasWidth, int canvasHeight) {

        for (int i = 1; i <= BRICK_ROWS; i++) {
            for (int j = 0; j <= BRICK_COLUMNS; j++) {
                if (xStart <= canvasWidth - Brick.BRICK_HEIGHT) {
                    Brick brick = new Brick(xStart, yStart);
                    if (i <= 2) {
                        brick.setFillColor(Color.RED);
                        canvas.add(brick);
                        xStart = xStart + Brick.BRICK_WIDTH + 5;
                    }
                    if (i > 2 && i <= 4) {
                        brick.setFillColor(Color.ORANGE);
                        canvas.add(brick);
                        xStart = xStart + Brick.BRICK_WIDTH + 5;
                    }
                    if (i > 4 && i <= 6) {
                        brick.setFillColor(Color.YELLOW);
                        canvas.add(brick);
                        xStart = xStart + Brick.BRICK_WIDTH + 5;
                    }
                    if (i > 6 && i <= 8) {
                        brick.setFillColor(Color.GREEN);
                        canvas.add(brick);
                        xStart = xStart + Brick.BRICK_WIDTH + 5;
                    }
                    if (i > 8 && i <= 10) {
                        brick.setFillColor(Color.CYAN);
                        canvas.add(brick);
                        xStart = xStart + Brick.BRICK_WIDTH + 5;
                    }
                }
                else if (xStart >= canvasWidth - Brick.BRICK_WIDTH) {
                    xStart = 15;
                    yStart = yStart + Brick.BRICK_HEIGHT + 5;
                    row = row + 1;
                }
            }
        }
    }

    // This method returns a boolean for if there are any bricks left. This is
    // primarily used for making sure that the player wins once they hit all the bricks.
    public boolean checkBricksLeft() {
        return totalNumOfBricks > 0;
    }

    // This method is called whenever a brick is deleted within the testHit method in the
    // Ball class. This reduces the total number or bricks and keeps track of how many
    // bricks are left, before displaying the victory screen if the player manages to destroy every brick.
    public void deleteBrickCount() {
        totalNumOfBricks -=1;
    }
}
