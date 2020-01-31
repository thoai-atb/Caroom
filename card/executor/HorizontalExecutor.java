package card.executor;

import game.Game;
import game.PlayingState;
import room.Direction;
import room.Room;

public class HorizontalExecutor implements CardExecutor {
	
	public PlayingState playingState;
	
	public HorizontalExecutor(PlayingState playingState) {
		this.playingState = playingState;
	}

	@Override
	public boolean execute() {
		Room room = playingState.getRoom();
		boolean left = room.doorState.get(Direction.LEFT);
		boolean right = room.doorState.get(Direction.RIGHT);
		if(!left && !right)
			return false;
		
		if(!right)
			playingState.go(Direction.LEFT);
		else if(!left)
			playingState.go(Direction.RIGHT);
		else {
			if(Game.game.random(1) < 0.5f)
				playingState.go(Direction.LEFT);
			else
				playingState.go(Direction.RIGHT);
		}
		
		return true;
	}

}
