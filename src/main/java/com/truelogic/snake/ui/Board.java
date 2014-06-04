package com.truelogic.snake.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.truelogic.snake.Snake;

public class Board extends JPanel implements ActionListener {

	private static final long serialVersionUID = 592910003380869323L;

	private static final int DELAY = 80;
	
	public static final int M_WIDTH = 80;

	public static final int M_HEIGHT = 40;

	private static final int B_WIDTH = M_WIDTH * Pixel.SIZE;

	private static final int B_HEIGHT = M_HEIGHT * Pixel.SIZE;
	
	private static final int MARGIN_LEFT = 0;

	private static final int MARGIN_TOP = 0;

	private Timer timer;
	
	private Pixel[][] board;
	
	private Snake snake;
	
	private Apple apple;
	
	private boolean directionLock;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintSnake(g);
		paintApple(g);
	}
	
	private void paintApple(Graphics g) {
		int x = MARGIN_LEFT + apple.getX() * Pixel.SIZE;
		int y = MARGIN_TOP + apple.getY() * Pixel.SIZE;
		g.drawImage(apple.getImage(), x, y, this);
		
	}

	private void paintSnake(Graphics g) {
		for (SnakePixel sp : snake.getBody()) {
			int x = MARGIN_LEFT + sp.getX() * Pixel.SIZE;
			int y = MARGIN_TOP + sp.getY() * Pixel.SIZE;
			g.drawImage(sp.getImage(), x, y, this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (snake.crash()) {
			timer.stop();
			return;
		}
		
		boolean ate = apple.inSameSpot(snake.getBody().get(0));
		snake.move(ate);
		directionLock = false;
		if (ate) {
			apple = new Apple(board);
		}
		repaint();
	}
	
	public Board() {
		super();
		board = new Pixel[M_HEIGHT][M_WIDTH];
		snake = new Snake();
		apple = new Apple(board);
		setBackground(Color.BLACK);
		timer = new Timer(DELAY, this);
		timer.start();
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		setFocusable(true);
		addKeyListener(new SnakeKeyListener(this));
	}
	
	private class SnakeKeyListener extends KeyAdapter {

		private Board board;

		public SnakeKeyListener(Board board) {
			this.board = board;
		}


		@Override
		public void keyPressed(KeyEvent e) {

			switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT: board.onKeyLeft(); break;
				case KeyEvent.VK_RIGHT: board.onKeyRight(); break;
				case KeyEvent.VK_UP: board.onKeyUp(); break;
				case KeyEvent.VK_DOWN: board.onKeyDown(); break;
				case KeyEvent.VK_ENTER: board.onKeyEnter(); break;
				case KeyEvent.VK_R: board.onKeyRestart(); break;
			}
		}

	}
	
	public void onKeyLeft() {
		if (snake.getDirection() != Snake.RIGHT && !directionLock) {
			snake.setDirection(Snake.LEFT);
			directionLock = true;	
		}
	}

	public void onKeyRestart() {
		board = new Pixel[M_HEIGHT][M_WIDTH];
		snake = new Snake();
		apple = new Apple(board);
		setBackground(Color.BLACK);
		timer.restart();
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		setFocusable(true);
		addKeyListener(new SnakeKeyListener(this));
	}

	public void onKeyEnter() {
		
	}

	public void onKeyDown() {
		if (snake.getDirection() != Snake.UP && !directionLock) {
			snake.setDirection(Snake.DOWN);
			directionLock = true;
		}
	}

	public void onKeyUp() {
		if (snake.getDirection() != Snake.DOWN && !directionLock) {
			snake.setDirection(Snake.UP);
			directionLock = true;
		}
	}

	public void onKeyRight() {
		if (snake.getDirection() != Snake.LEFT && !directionLock) {
			snake.setDirection(Snake.RIGHT);
			directionLock = true;
		}
	}
	

}
