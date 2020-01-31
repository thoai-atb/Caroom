package room;

import game.Game;
import game.PlayingState;
import processing.core.PVector;

public class RoomTransition {
	
	private Direction direction;
	private float ratio = 0;
	private Room currentRoom;
	private Room nextRoom;
	private PlayingState playingState;
	
	public RoomTransition (PlayingState playingState, Direction direction, Room currentRoom, Room nextRoom) {
		this.playingState = playingState;
		this.direction = direction;
		this.currentRoom = currentRoom;
		this.nextRoom = nextRoom;
	}
	
	public Room currentRoom() {
		return currentRoom;
	}
	
	public Room nextRoom() {
		return nextRoom;
	}
	
	public float getRatio() {
		return ratio;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void finish() {
		playingState.doneTransition();
	}
	
	public void update() {
		boolean done = false;
		ratio += 0.02f;
		if(ratio >= 1) {
			ratio = 1;
			done = true;
		}
		

		PVector center = Game.game.getCenter();
		float w = (currentRoom.getWidth() + nextRoom.getWidth()) / 2;
		float h = (currentRoom.getHeight() + nextRoom.getHeight()) / 2;
		switch(direction) {
			case UP:
				currentRoom.setCenter(center.x, center.y + ratio * h);
				nextRoom.setCenter(center.x, center.y - h + ratio * h);
				break;
			case DOWN:
				currentRoom.setCenter(center.x, center.y - ratio * h);
				nextRoom.setCenter(center.x, center.y + h - ratio * h);
				break;
			case LEFT:
				currentRoom.setCenter(center.x + ratio * w, center.y);
				nextRoom.setCenter(center.x - w + ratio * w, center.y);
				break;
			case RIGHT:
				currentRoom.setCenter(center.x - ratio * w, center.y);
				nextRoom.setCenter(center.x + w - ratio * w, center.y);
				break;
		}
		
		if(done)
			finish();
	}

}
