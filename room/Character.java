package room;
import game.Game;
import processing.core.PVector;

public class Character {
	
	private PVector position;
	private PVector target;
	private float radius = 10;
	private Room room;
	
	
	public Character(Room room) {
		updateRoom(room, null);
	}
	
	public void setPosition(PVector position) {
		this.position = position;
	}
	
	public void goTo(PVector target) {
		this.target = target.copy();
	}
	
	public void updateRoom(Room newRoom, Direction direction) {
		if(room == null) {
			position = new PVector(0, 0);
		} else {
			switch(direction) {
				case UP:
					position = new PVector(0, room.getHeight()/2 + newRoom.getHeight()/2);
					break;
				case DOWN:
					position = new PVector(0, -room.getHeight()/2 - newRoom.getHeight()/2);
					break;
				case LEFT:
					position = new PVector(room.getWidth()/2 + newRoom.getWidth()/2, 0);
					break;
				case RIGHT:
					position = new PVector(-room.getWidth()/2 - newRoom.getWidth()/2, 0);
					break;		
			}
		}
		
		room = newRoom;
		goTo(new PVector(0, 0));
	}
	
	public void update() {
		if(target != null)
			position = PVector.lerp(position, target, 0.05f);
	}
	
	public void show() {
		Game.game.stroke(0, 200);
		Game.game.strokeWeight(3);
		Game.game.fill(255, 200);
		
		Game.game.pushMatrix();
		Game.game.translate(room.getCenter().x, room.getCenter().y);
		Game.game.translate(position.x, position.y);
		Game.game.ellipse(0, 0, 2*radius, 2*radius);
		Game.game.popMatrix();
	}
}
