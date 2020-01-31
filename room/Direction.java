package room;

import java.util.Random;

public enum Direction {
	UP, DOWN, LEFT, RIGHT;
	
	public String icon() {
		switch(this) {
			case UP:
				return "^";
			case DOWN:
				return "v";
			case LEFT:
				return "<";
			case RIGHT:
				return ">";
			default:
				return "No Icon";
		}
	}
	
	public static Direction random() {
		return values()[new Random().nextInt(values().length)];
	}
}
