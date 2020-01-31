package level;

import card.CardType;
import game.PlayingState;
import room.Direction;
import room.Room;
import room.RoomFactory;
import room.RoomTransition;

public class Level3 extends Level {
	
	public Level3(PlayingState playingState) {
		super(playingState, 3);
		chestCards.add(CardType.ALL_DIRECTION);
		chestCards.add(CardType.HORIZONTAL);
		chestCards.add(CardType.VERTICAL);
	}

	@Override
	public RoomTransition createTransition(PlayingState playingState, Room room, Direction direction) {
		int id = room.getID() + 1;
		
		if(id >= 10) {
			return new RoomTransition(playingState, direction, room, RoomFactory.createFinishRoom(id));
		} 
			
		float prob = 0;
		if(id % 2 == 0)
			prob = 1;
		return new RoomTransition(playingState, direction, room, RoomFactory.createChestRoom(id, createChest(), prob));
	}

}