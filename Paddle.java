package BreakoutGit;
import java.awt.event.MouseEvent;

import acm.graphics.GRect;

public class Paddle extends Breakout {

	
	void newPaddle() {
		paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT); //створення об'єкту "ракетка" з розмірами вказаними в константах
		((GRect) paddle).setFilled(true); //фон ракетки "чорний"
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
	private GRect paddle;