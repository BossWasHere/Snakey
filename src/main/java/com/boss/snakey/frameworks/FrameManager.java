package com.boss.snakey.frameworks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.boss.snakey.App;

public class FrameManager {

	private static final int PIXEL_PER_SQUARE = 20;
	private static final int SQUARES = 32;
	
	public static JPanel[][] blocks;
	private static JFrame frameJ;
	
	public static void openFrame() {
		JFrame frame = new JFrame("Snake - Length: 1");
		frame.setSize(SQUARES * PIXEL_PER_SQUARE + 7, SQUARES * PIXEL_PER_SQUARE + 8);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frameJ = frame;
		
		addComponents(frame);
		addListeners(frame);
		
		frame.setVisible(true);
	}
	
	private static void addComponents(JFrame frame)  {
		blocks = new JPanel[SQUARES][SQUARES];
		for (int x = 0; x < SQUARES; x++) {
			for (int y = 0; y < SQUARES; y++) {
				JPanel template = new JPanel();
				setFrameEmpty(template);
				template.setBounds(x * PIXEL_PER_SQUARE, y * PIXEL_PER_SQUARE, PIXEL_PER_SQUARE, PIXEL_PER_SQUARE);
				frame.add(template);
				blocks[x][y] = template;
			}
		}
		
		JPanel start = blocks[(int) SQUARES / 2][(int) SQUARES / 2];
		start.setBackground(Color.GREEN);
		App.setFront(new Dimension((int) SQUARES / 2, (int) SQUARES / 2));
		generateRandomGrow();
	}
	
	public static void reset() {
		App.path.clear();
		App.length = 0;
		for (JPanel[] rows : blocks) {
			for (JPanel block : rows) {
				setFrameEmpty(block);
			}
		}
		
		JPanel start = blocks[(int) SQUARES / 2][(int) SQUARES / 2];
		start.setBackground(Color.GREEN);
		App.setFront(new Dimension((int) SQUARES / 2, (int) SQUARES / 2));
		generateRandomGrow();
	}
	
	public static void updateScore() {
		frameJ.setTitle("Snake - Length: " + App.length);
	}
	
	public static void generateRandomGrow(Dimension head) {
		int x = 0, y = 0;
		Random rand = new Random();
		
		do {
			x = rand.nextInt(SQUARES);
			y = rand.nextInt(SQUARES);
		} while ((x == head.width && y == head.height) || blocks[x][y].getBackground().equals(Color.GREEN));
		
		setFrameGrow(blocks[x][y]);
	}
	
	public static void generateRandomGrow() {
		generateRandomGrow(new Dimension((int) SQUARES / 2, (int) SQUARES / 2));
	}
	
	public static void setFrameEmpty(JPanel panel) {
		panel.setBackground(Color.GRAY);
	}
	
	public static void setFrameSnakeBody(JPanel panel) {
		panel.setBackground(Color.GREEN);
	}
	
	public static void setFrameGrow(JPanel panel) {
		panel.setBackground(Color.RED);
	}
	
	public static void setMiddleEmpty() {
		setFrameEmpty(blocks[(int) SQUARES / 2][(int) SQUARES / 2]);
	}
	
	private static void addListeners(JFrame frame) {
		frame.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				switch (e.getKeyChar()) {
				case 'w':
					App.setFacing(Facing.NORTH);
					break;
				case 'a':
					App.setFacing(Facing.WEST);
					break;
				case 's':
					App.setFacing(Facing.SOUTH);
					break;
				case 'd':
					App.setFacing(Facing.EAST);
					break;
				case 'l':
					App.stop();
					break;
				default:
				}
			}
			
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyChar()) {
				case 'w':
					App.setFacing(Facing.NORTH);
					break;
				case 'a':
					App.setFacing(Facing.WEST);
					break;
				case 's':
					App.setFacing(Facing.SOUTH);
					break;
				case 'd':
					App.setFacing(Facing.EAST);
					break;
				case 'l':
					App.stop();
					break;
				default:
				}
			}
			
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
				case 'w':
					App.setFacing(Facing.NORTH);
					break;
				case 'a':
					App.setFacing(Facing.WEST);
					break;
				case 's':
					App.setFacing(Facing.SOUTH);
					break;
				case 'd':
					App.setFacing(Facing.EAST);
					break;
				case 'l':
					App.stop();
					break;
				default:
				}
			}
		});
	}
	
}
