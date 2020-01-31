package level;

import card.CardType;
import game.PlayingState;
import room.Direction;
import room.Room;
import room.RoomFactory;
import room.RoomTransition;

public class Level2 extends Level {
	
	public Level2(PlayingState playingState) {
		super(playingState, 2);
		chestCards.add(CardType.HORIZONTAL);
		chestCards.add(CardType.VERTICAL);
	}

	@Override
	public RoomTransition createTransition(PlayingState playingState, Room room, Direction direction) {
		int id = room.getID() + 1;
		
		if(id >= 6) {
			return new RoomTransition(playingState, direction, room, RoomFactory.createFinishRoom(id));
		} 
			
		float prob = 0;
		if(id % 2 == 0)
			prob = 1;
		return new RoomTransition(playingState, direction, room, RoomFactory.createChestRoom(id, createChest(), prob));
	}

}
