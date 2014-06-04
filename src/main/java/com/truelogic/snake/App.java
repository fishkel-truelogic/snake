package com.truelogic.snake;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.truelogic.snake.ui.Board;

/**
 * 
 * @author lfishkel
 *
 */
public class App extends JFrame {
	
	private static final long serialVersionUID = -197881580219904464L;

	public App() {
		add(new Board());
		setTitle("Snake");
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new App();
				frame.setVisible(true);
			}

		});
    }
}
