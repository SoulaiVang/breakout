package breakout;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;

/**
 * Author: Soulai Vang
 * 
 * Description: The game of Breakout. This creates a canvas that creates all of the necessary
 * components for the game Breakout, including labels, the bricks, the ball, the paddle, and
 * the window border. The game ends when the user deletes all the bricks on the board or if
 * the player loses all three lives.
 * 
 * Acknowledgements: Preceptor Jacqueline helped me a lot with this homework. She helped me
 * implement the code for the logic of the ball bouncing around the border, along with
 * helping me with the baseline of the code for the ball interactions with the paddle and
 * the bricks.
 */

public class BreakoutGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;

    private CanvasWindow canvas;
    private Ball ball;
    private Paddle paddle;
    private Border border;
    private BrickGrid brickGrid;
    private int numOfLives = 3;

    private GraphicsText gameLoss;
    private GraphicsText gameWin;
    private GraphicsText numOfLivesText;

    public BreakoutGame() {
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);

        ball = new Ball(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
        ball.addToCanvas(canvas);
        paddle = new Paddle(CANVAS_WIDTH / 2 - 25, CANVAS_HEIGHT - 200);
        paddle.addToCanvas(canvas);
        border = new Border(CANVAS_WIDTH, CANVAS_HEIGHT);
        border.addToCanvas(canvas);
        brickGrid = new BrickGrid(canvas, CANVAS_WIDTH, CANVAS_HEIGHT);

        gameLoss = new GraphicsText();
        gameLoss.setFont(FontStyle.BOLD, 50);
        gameLoss.setText("You Lose!");

        gameWin = new GraphicsText();
        gameWin.setFont(FontStyle.BOLD, 50);
        gameWin.setText("You Win!");

        numOfLivesText = new GraphicsText();
        numOfLivesText.setFont(FontStyle.BOLD, 25);
        numOfLivesText.setText(numOfLives + " ❤️");
        canvas.add(numOfLivesText);
        numOfLivesText.setCenter(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2 + 300);

        canvas.onDrag(event -> paddle.move(event.getPosition(), CANVAS_HEIGHT));

        canvas.draw();
        canvas.pause(2500);

        canvas.animate(() -> {

                if (ball.getY() > CANVAS_HEIGHT - 180) {
                    ball.resetBall(canvas, CANVAS_WIDTH, CANVAS_HEIGHT);
                    numOfLives -= 1;
                    numOfLivesText.setText(numOfLives + " ❤️");
                }

                ball.testHit(this.canvas, this.brickGrid);
                ball.updatePosition(CANVAS_WIDTH, CANVAS_HEIGHT);

                if (brickGrid.checkBricksLeft() == false) {
                    canvas.removeAll();
                    canvas.add(gameWin);
                    gameWin.setCenter(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
                }

                if (numOfLives == 0) {
                    canvas.removeAll();
                    canvas.add(gameLoss);
                    gameLoss.setCenter(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2);
                }
        });
    }

    public static void main(String[] args){
        new BreakoutGame();
    }
}
