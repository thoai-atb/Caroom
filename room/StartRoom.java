package room;

import game.Game;
import processing.core.PApplet;
import processing.core.PVector;

public class StartRoom extends Room {
	
	private String label;

	public StartRoom(PVector center, float width, float height, String label, int id) {
		super(center, width, height, id);
		this.label = label;
	}
	
	public StartRoom(Room room, String label) {
		this(room.getCenter(), room.getWidth(), room.getHeight(), label, room.getID());
	}
	
	@Override
	protected void backgroundPaint() {
		Game.game.fill(0, 20);
		Game.game.textSize(100);
		Game.game.textAlign(PApplet.CENTER, PApplet.CENTER);
		Game.game.text(label, 0, 0);
	}

}
