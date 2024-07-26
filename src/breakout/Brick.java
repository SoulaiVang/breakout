package breakout;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;

public class Brick extends Rectangle{

    public static final double BRICK_WIDTH = 43;
    public static final double BRICK_HEIGHT = 15;

    public Brick(double x, double y) {
        super(x, y, BRICK_WIDTH, BRICK_HEIGHT);
    }
    
    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(this);
    }
    
    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(this);
    }
}
