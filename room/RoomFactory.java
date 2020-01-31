package room;

import game.Game;

public class RoomFactory {
	
	public static Room createEmptyRoom(int id) {
		float width = Game.game.random(Game.game.width/3, Game.game.width - 100);
		float height = Game.game.random(Game.game.width/3, Game.game.width - 100);
		return new Room(Game.game.getCenter(), width, height, id);
	}
	
	public static Room createStartRoom(int id, String label) {
		Room r = createEmptyRoom(id);
		return new StartRoom(r, label);
	}
	
	public static Room createChestRoom(int id, Chest chest, float probability) {
		Room r = createEmptyRoom(id);
		if(Game.game.random(1) <= probability)
			r.setChest(chest);
		return r;
	}
	
	public static Room createFinishRoom(int id) {
		Room r = createEmptyRoom(id);
		return new FinishRoom(r);
	}
}
