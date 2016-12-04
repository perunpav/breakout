import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Lida on 04.12.2016.
 */
public class Run extends GraphicsProgram {
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
    private GOval ball;
    private GRect brick;
    private GRect paddle;
    /** how fast ball is moving */
    private double vx, vy;
    /** random generator */
    private RandomGenerator rgen = RandomGenerator.getInstance();



    public void run() {

        setUp();

    }
       private void setUp(){
            this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
            createBricks(getWidth() / 2, BRICK_Y_OFFSET);
           newPaddle();
           setupBall();
           jumpBall();

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
    private void jumpBall() {
        while (true) {
            ballMove();
            checkWall();
            checkForCollisions();
            waitForFewTime();
        }

    }

    private void checkForCollisions() {
        GObject collider = getCollidingObject();
        if(collider!=null){
            if (collider == paddle) {

                vy = -vy;}
            else{
                remove (collider);
                vy = -vy;
            }
        }

    }
    /**creating ball*/
    private void setupBall() {
        ball = new GOval(getWidth() / 2 - BALL_RADIUS, getHeight() / 2 - BALL_RADIUS, BALL_RADIUS, BALL_RADIUS);
        ball.setFilled(true);
        add(ball);
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5))
            vx = -vx;
        vy = 10;

    }

    /** moves the ball in the new coordinates */
    private void ballMove() {
        ball.move(vx, vy);

    }

    private void waitForFewTime() {
        pause(50);

    }

    /** check touching wall */
    private void checkWall() {
		/*
		 * ���������� ��� ������������� ��������� ����, ���� �� ������� �������
		 * ������
		 */
        double diff;
		/* �������� �� �������� �� ��� ���� */
        if (ball.getY() > getHeight() - BALL_RADIUS) {
            vy = -vy;
            diff = ball.getY() - (getHeight() - BALL_RADIUS);
            ball.move(0, -2 * diff);

        }
		/* ��������, �� �������� �� ��� ������� */
        if (ball.getY() < BALL_RADIUS) {
            vy = -vy;
            diff = ball.getY() + BALL_RADIUS;
            ball.move(0, 2 * diff);
        }
		/* ��������, �� �������� �� ��� ������ ����� */
        if (ball.getX() > getWidth() - BALL_RADIUS) {
            vx = -vx;
            diff = ball.getX() - (getWidth() - BALL_RADIUS);
            ball.move(-2 * diff, 0);
        }
		/* �������� �� �������� �� ��� ����� ����� */
        if (ball.getX() < BALL_RADIUS) {
            vx = -vx;
            diff = ball.getX() + BALL_RADIUS;
            ball.move(diff / 4, 0);
        }

    }

    /** check for touching some objects */
    private GObject getCollidingObject() {
        if (getElementAt(ball.getX(), ball.getY()) != null)
            return getElementAt(ball.getX(), ball.getY());
        else if (getElementAt(ball.getX() + BALL_RADIUS, ball.getY()) != null)
            return getElementAt(ball.getX() + BALL_RADIUS, ball.getY());
        else if (getElementAt(ball.getX(), ball.getY() + BALL_RADIUS) != null)
            return getElementAt(ball.getX(), ball.getY() + BALL_RADIUS);
        else if (getElementAt(ball.getX() + BALL_RADIUS, ball.getY() + BALL_RADIUS) != null)
            return getElementAt(ball.getX() + BALL_RADIUS, ball.getY() + BALL_RADIUS);
        else
            return null;
    }
    private void newPaddle(){
        paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT); //створення об'єкту "ракетка" з розмірами вказаними в константах
        paddle.setFilled(true); //фон ракетки "чорний"
        add(paddle); //додавання ракетки на поле гри
        addMouseListeners(); // додавання слухача миші
    }

    public void mouseMoved(MouseEvent e) {
        if ((e.getX() < WIDTH - PADDLE_WIDTH / 2) && (e.getX() > PADDLE_WIDTH / 2)) { //якщо кусор знаходиться в заданому діапазоні то
            paddle.setLocation(e.getX() - PADDLE_WIDTH / 2, y);//розміщувати ракетку в данних координатах
        }
    }

    double y = HEIGHT - PADDLE_Y_OFFSET; // точка по у для створення ракетки
    double x = WIDTH / 2 - PADDLE_WIDTH / 2; //точка по х для створення ракетки
}
