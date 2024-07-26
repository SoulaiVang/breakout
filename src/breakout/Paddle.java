package breakout;

import java.awt.Color;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;

public class Paddle extends Rectangle{
    public static final double PADDLE_WIDTH = 75;
    public static final double PADDLE_HEIGHT = 7.5;

    public Paddle(double x, double y) {
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        super.setFillColor(Color.BLACK);
    }
    
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(this);
    }
    
    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(this);
    }

    // This method allows the player to move the paddle only left or right, along
    // with adhering to the canvas size so it cannot go outside the canvas.
    public void move(Point position, int canvasHeight) {
        if (!(position.getX() >= (canvasHeight - 200) - PADDLE_WIDTH)) {
            this.setPosition(position.getX(), canvasHeight - 200);
        }
    }
}
