package com.boss.snakey;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;

import com.boss.snakey.frameworks.Facing;
import com.boss.snakey.frameworks.FrameManager;

public class App {
	
	protected static Facing snakePlay = Facing.EAST;
	protected static Dimension snakeFront;
	public static List<Dimension> path = new ArrayList<Dimension>();
	public static int length = 0;
	public static int redX = -1, redY = -1;
	
	private static boolean active = true;
	
	public static void main(String[] args) {
		FrameManager.openFrame();
		begin();
	}
	
	public static void setFacing(Facing facing) {
		if (!snakePlay.isOpposite(facing)) snakePlay = facing;
	}
	
	public static void setFront(Dimension dimension) {
		snakeFront = dimension;
	}
	
	private static void begin() {
		while (active) {
			try {
				Thread.sleep(200);
			} catch (Exception e) {}
			
			try {
				JPanel head;
				Dimension d;
				switch (snakePlay) {
				case NORTH:
					head = FrameManager.blocks[snakeFront.width][snakeFront.height - 1];
					d = new Dimension(snakeFront.width, snakeFront.height - 1);
					if (head.getBackground().equals(Color.RED)) {
						length++;
						FrameManager.generateRandomGrow(d);
					}
					checkPaths(d);
					setFront(d);
					break;
				case EAST:
					head = FrameManager.blocks[snakeFront.width + 1][snakeFront.height];
					d = new Dimension(snakeFront.width + 1, snakeFront.height);
					if (head.getBackground().equals(Color.RED)) {
						length++;
						FrameManager.generateRandomGrow(d);
					}
					checkPaths(d);
					setFront(d);
					break;
				case SOUTH:
					head = FrameManager.blocks[snakeFront.width][snakeFront.height + 1];
					d = new Dimension(snakeFront.width, snakeFront.height + 1);
					if (head.getBackground().equals(Color.RED)) {
						length++;
						FrameManager.generateRandomGrow(d);
					}
					checkPaths(d);
					setFront(d);
					break;
				default:
					head = FrameManager.blocks[snakeFront.width - 1][snakeFront.height];
					d = new Dimension(snakeFront.width - 1, snakeFront.height);
					if (head.getBackground().equals(Color.RED)) {
						length++;
						FrameManager.generateRandomGrow(d);
					}
					checkPaths(d);
					setFront(d);
					break;
				}
				if (redX > -1 && redY > -1) {
					JPanel p = FrameManager.blocks[redX][redY];
					System.out.println(p.getBackground().toString() + ", " + redX + ", " + redY);
					if (!p.getBackground().equals(Color.RED)) FrameManager.generateRandomGrow(d);
				}
			} catch (Exception e) {
				FrameManager.reset();
			}
		}
		System.exit(0);
	}
	
	private static void checkPaths(Dimension head) {
		Dimension node;
		Iterator<Dimension> paths = path.iterator();
		while (paths.hasNext()) {
			node = paths.next();
			if (node.width == head.width && node.height == head.height) {
				FrameManager.reset();
				return;
			}
		}
		if (path.isEmpty()) {
			path.add(head);
			return;
		}
		
		path.add(head);
		FrameManager.setFrameSnakeBody(FrameManager.blocks[head.width][head.height]);
		
		if (length == 0) {
			FrameManager.setMiddleEmpty();
			length++;
		}
		
		if (path.size() > length) {
			FrameManager.setFrameEmpty(FrameManager.blocks[path.get(0).width][path.get(0).height]);
			path.remove(0);
		}
		
		FrameManager.updateScore();
		
	}
	
	public static void stop() {
		active = false;
	}
}
