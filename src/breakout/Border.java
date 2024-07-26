package breakout;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.GraphicsGroup;

public class Border {
    private GraphicsGroup border;
    private Rectangle borderWallLeft;
    private Rectangle borderWallRight;
    private Rectangle borderUpperWall;
    private Rectangle borderLowerWall;

    public Border(int canvasWidth, int canvasHeight) {
        border = new GraphicsGroup();

        borderWallLeft = new Rectangle(0, 0, 10, canvasHeight);
        borderWallLeft.setFillColor(Color.BLACK);
        borderWallRight = new Rectangle(canvasWidth - 10, 0, 10, canvasHeight);
        borderWallRight.setFillColor(Color.BLACK);
        borderUpperWall = new Rectangle(10, 0, canvasWidth - 20, 10);
        borderUpperWall.setFillColor(Color.BLACK);
        borderLowerWall = new Rectangle(10, canvasHeight - 10, canvasWidth - 20, 10);
        borderLowerWall.setFillColor(Color.BLACK);

        border.add(borderLowerWall);
        border.add(borderUpperWall);
        border.add(borderWallLeft);
        border.add(borderWallRight);
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(border);
    }

    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(border);
    }
}
