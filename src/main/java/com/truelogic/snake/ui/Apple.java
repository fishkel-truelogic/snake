package com.truelogic.snake.ui;

import java.util.Random;

public class Apple extends Pixel {

	public Apple(Pixel[][] board) {
		Random r = new Random();
		do {
			int h_low = 0;
			int h_high = Board.M_HEIGHT;
			this.y = r.nextInt(h_high - h_low) + h_low;
			int w_low = 0;
			int w_high = Board.M_WIDTH;
			this.x = r.nextInt(w_high - w_low) + w_low;
		} while (board[y][x] != null);
	}
	

}
