package com.truelogic.snake;

import java.util.ArrayList;

import com.truelogic.snake.ui.Board;
import com.truelogic.snake.ui.SnakePixel;

public class Snake {
		
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	
	private ArrayList<SnakePixel> body;
	
	private int direction;
	
	
	public Snake() {
		body = new ArrayList<SnakePixel>();
		body.add(new SnakePixel(0, 0));
		direction = RIGHT;
	}


	public ArrayList<SnakePixel> getBody() {
		return body;
	}


	public void setBody(ArrayList<SnakePixel> body) {
		this.body = body;
	}
	
	public void move(boolean ate) {
		SnakePixel newHead = new SnakePixel(body.get(0).getX(), body.get(0).getY());
		ArrayList<SnakePixel> newBody = new ArrayList<SnakePixel>();
		newBody.add(newHead);
		switch (direction) {
		case LEFT: newHead.setX(newHead.getX() - 1); break;
		case RIGHT: newHead.setX(newHead.getX() + 1); break;
		case UP: newHead.setY(newHead.getY() - 1); break;
		case DOWN: newHead.setY(newHead.getY() + 1); break;
		}
		if (!ate) {
			body.remove(body.size() - 1);
		}
		newBody.addAll(body);
		body = newBody;
	}


	public int getDirection() {
		return direction;
	}


	public void setDirection(int direction) {
		this.direction = direction;
	}


	public boolean crash() {
		SnakePixel head = body.get(0);
		
		for (SnakePixel sp : body) {
			
			if (!sp.equals(head) && sp.inSameSpot(head)) {
				return true;
			}
		}
		
		if (head.getX() > Board.M_WIDTH || head.getX() < 0 || head.getY() < 0 || head.getY() > Board.M_HEIGHT) {
			return true;
		}
		
		return false;
	}


	public boolean isAbove(int x, int y) {
		for (SnakePixel sp: body) {
			if (sp.getX() == x && sp.getY() == y) {
				return true;
			}
		}
		return false;
	}
	
	

	
	
	
}
