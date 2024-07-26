package breakout;

import java.awt.Color;
import java.util.Random;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.GraphicsObject;

public class Ball {
    public static final double BALL_RADIUS = 10;
    public static final double BALL_VELOCITY = 3;
    private final Random random = new Random();

    private Ellipse ballShape;
    private double x;
    private double y;
    private double ballSpeedX = BALL_VELOCITY;
    private double ballSpeedY = BALL_VELOCITY;

    public Ball(double x, double y) {
        ballShape = new Ellipse(x, y, BALL_RADIUS * 2, BALL_RADIUS * 2);
        ballShape.setFillColor(Color.BLACK);
        this.x = x;
        this.y = y;
    }

    public void addToCanvas(CanvasWindow canvas) {
        canvas.add(ballShape);
    }

    public void removeFromCanvas(CanvasWindow canvas) {
        canvas.remove(ballShape);
    }

    public double getX() {
        return ballShape.getX();
    }

    public double getY() {
        return ballShape.getY();
    }

    // This method tests whether or not the ball hits an object either on its top side,
    // its bottom, its left or its right. Once it detects where it gets hit, the brick
    // that it hits gets deleted and subtracted from a total brick count, along with
    // changing the direction of the ball depending on where it is hit. If it hits
    // the paddle, its direction is changed as well but it does not delete the paddle.
    public void testHit(CanvasWindow canvas, BrickGrid bricks) {
        GraphicsObject top = canvas.getElementAt(ballShape.getCenter().getX(), ballShape.getCenter().getY() - BALL_RADIUS - 0.1);
        GraphicsObject bottom = canvas.getElementAt(ballShape.getCenter().getX(), ballShape.getCenter().getY() + BALL_RADIUS + 0.1);
        GraphicsObject right = canvas.getElementAt(ballShape.getCenter().getX() + BALL_RADIUS, ballShape.getCenter().getY() + 0.1);
        GraphicsObject left = canvas.getElementAt(ballShape.getCenter().getX() - BALL_RADIUS, ballShape.getCenter().getY() - 0.1);

        if (top != null) {
            if (top instanceof Brick) {
                canvas.remove(top);
                ballSpeedY = BALL_VELOCITY;
                bricks.deleteBrickCount();
            }
        }

        if (bottom != null) {
            if (bottom instanceof Brick) {
                canvas.remove(bottom);
                ballSpeedY = -BALL_VELOCITY;
                bricks.deleteBrickCount();
            }
            if (bottom instanceof Paddle) {
                if (random.nextBoolean()) {
                    ballSpeedY = -BALL_VELOCITY;
                }
                else {
                    ballSpeedY = -BALL_VELOCITY;
                    ballSpeedX = BALL_VELOCITY;
                }
            }
        }

        if (left != null) {
            if (left instanceof Brick) {
                canvas.remove(left);
                ballSpeedX = BALL_VELOCITY;
                bricks.deleteBrickCount();
            }
        }

        if (right != null) {
            if (right instanceof Brick) {
                canvas.remove(right);
                ballSpeedX = -BALL_VELOCITY;
                bricks.deleteBrickCount();
            }
        }
    }

    // This resets the ball to the center of the screen whenever a player makes
    // the ball fall past the paddle.
    public void resetBall(CanvasWindow canvas, int canvasWidth, int canvasHeight) {
        canvas.remove(ballShape);
        ballShape.setCenter(canvasWidth / 2, canvasHeight / 2);
        y = ballShape.getY();
        x = ballShape.getX();
        canvas.add(ballShape);
        canvas.draw();
        canvas.pause(500);
    }

    // This is the main method used to always keep the ball in motion when
    // it does not hit the paddle or the bricks. This method takes in
    // parameters of the canvas size and subtracts it based on the standard
    // size of the black border around the canvas that I created. 
    public void updatePosition(int canvasWidth, int canvasHeight) {

        if (x >= canvasWidth - 30) {
            ballSpeedX = -BALL_VELOCITY;
        }
        else if (x <= 10) {
            ballSpeedX = BALL_VELOCITY;
        }
        else if (y >= canvasHeight - 30) {
            ballSpeedY = -BALL_VELOCITY;
        }
        else if (y <= 10) {
            ballSpeedY = BALL_VELOCITY;
        }

        x = x + ballSpeedX;
        y = y + ballSpeedY;
        ballShape.setPosition(x, y);
    }
}


