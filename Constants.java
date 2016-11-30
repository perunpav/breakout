import acm.graphics.*;
import acm.program.*;
import acm.program.GraphicsProgram;
import acm.util.*;

import java.awt.*;

public class Constants extends GraphicsProgram {

    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /** Dimensions of game board (usually the same) */
    private static final int WIDTH = APPLICATION_WIDTH;
    private static final int HEIGHT = APPLICATION_HEIGHT;

    /** Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = 30;

    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 10;

    /** Number of rows of bricks */
    private static final int NBRICK_ROWS = 10;

    /** Separation between bricks */
    private static final int BRICK_SEP = 4;

    /** Width of a brick */
    private static final int BRICK_WIDTH =
            (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

    /** Height of a brick */
    private static final int BRICK_HEIGHT = 8;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;

    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

    /** Number of turns */
    private static final int NTURNS = 3;

/* Method: run() */
    /** Runs the Breakout program. */
    public void run() {
        this.setSize(APPLICATION_WIDTH,APPLICATION_HEIGHT);
        createBricks(getWidth()/2, BRICK_Y_OFFSET);
    }
    private void createBricks (int x, int y){
        for (int i = 0; i < NBRICKS_PER_ROW; i++) { // create rows
            for (int j = 0; j < NBRICK_ROWS; j++) { // create columns

                // find start point
                int start_x = x - (NBRICKS_PER_ROW * BRICK_WIDTH)/2 - ((NBRICKS_PER_ROW - 1) * BRICK_SEP)/ 2 +
                        j * BRICK_WIDTH + j * BRICK_SEP;
                int start_y = y + i * BRICK_HEIGHT + i * BRICK_SEP;

                GRect brick = new GRect(start_x,start_y,BRICK_WIDTH,BRICK_HEIGHT);
                brick.setFilled(true);
                // painting

                if (i < 2) {
                    brick.setColor(Color.RED);
                }
                if (i == 2 || i == 3) {
                    brick.setColor(Color.orange);
                }
                if (i == 4 || i == 5) {
                    brick.setColor(Color.YELLOW);
                }
                if (i == 6 || i == 7) {
                    brick.setColor(Color.GREEN);
                }
                if (i == 8 || i == 9) {
                    brick.setColor(Color.cyan);
                }
                add(brick);
            }
        }

    }

}


