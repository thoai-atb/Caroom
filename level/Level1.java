package level;

import game.PlayingState;
import room.Direction;
import room.Room;
import room.RoomFactory;
import room.RoomTransition;

public class Level1 extends Level {

	public Level1(PlayingState playingState) {
		super(playingState, 1);
	}

	@Override
	public RoomTransition createTransition(PlayingState playingState, Room room, Direction direction) {
		int id = room.getID() + 1;
		
		if(id >= 4) {
			return new RoomTransition(playingState, direction, room, RoomFactory.createFinishRoom(id));
		} 
			
		float prob = 0;
		if(id % 2 == 0)
			prob = 1;
		return new RoomTransition(playingState, direction, room, RoomFactory.createChestRoom(id, createChest(), prob));
	}

}
