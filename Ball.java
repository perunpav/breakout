import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

public class Ball extends GraphicsProgram {
	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;
	/** create ball */
	private GOval ball;
	/** how fast ball is moving */
	private double vy = 10.0;
	private double vx = 0;
	/** random generator */
	private RandomGenerator rgen = RandomGenerator.getInstance();

	public Ball(double vx, GOval ball, double vy) {
	
		this.vx = vx;
		this.ball = ball;
		this.vy = vy;
		this.setupBall();
		this.jumpBall();
		

	}

	public void jumpBall() {
		while (true) {
			ballMove();
			checkWall();
			waitForFewTime();
		}
		
	}

	public void setupBall() {
		ball = new GOval(getWidth() / 2 - BALL_RADIUS, getHeight() / 2 - BALL_RADIUS, BALL_RADIUS, BALL_RADIUS);
		ball.setFilled(true);
		add(ball);
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5))
			vx = -vx;
		

	}

	/** moves the ball in the new coordinates */
	public void ballMove() {
		ball.move(vx, vy);

	}

	public void waitForFewTime() {
		pause(50);

	}

	/** check touching wall */
	public void checkWall() {
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

}
