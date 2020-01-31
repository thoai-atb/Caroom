package room;

import game.Game;
import processing.core.PVector;

public class FinishRoom extends Room {

	public FinishRoom(PVector center, float width, float height, int id) {
		super(center, width, height, id);
		this.shutAllDoors();
	}
	
	public FinishRoom(Room room) {
		this(room.getCenter(), room.getWidth(), room.getHeight(), room.getID());
	}
	
	@Override
	protected int backgroundColor() {
		return Game.game.color(200, 255, 200);
	}

}
