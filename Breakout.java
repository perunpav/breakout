import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class Breakout extends GraphicsProgram{
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
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
	/** create ball */
	private GOval ball;
	private GRect brick;
	private GRect paddle;
	/** how fast ball is moving */
	private double vx, vy;
	/** random generator */
	private RandomGenerator rgen = RandomGenerator.getInstance();

	public void run(){

		setupBall();
		jumpBall();
	
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
		 * переменна€ дл€ корректировки положени€ м€ча, если он пересек границу
		 * экрана
		 */
		double diff;
		/* проверка не коснулс€ ли м€ч пола */
		if (ball.getY() > getHeight() - BALL_RADIUS) {
			vy = -vy;
			diff = ball.getY() - (getHeight() - BALL_RADIUS);
			ball.move(0, -2 * diff);

		}
		/* проверка, не коснулс€ ли м€ч потолка */
		if (ball.getY() < BALL_RADIUS) {
			vy = -vy;
			diff = ball.getY() + BALL_RADIUS;
			ball.move(0, 2 * diff);
		}
		/* проверка, не коснулс€ ли м€ч правой стены */
		if (ball.getX() > getWidth() - BALL_RADIUS) {
			vx = -vx;
			diff = ball.getX() - (getWidth() - BALL_RADIUS);
			ball.move(-2 * diff, 0);
		}
		/* проверка не коснулс€ ли м€ч левой стены */
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

}