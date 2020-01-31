package card.executor;

import game.Game;
import game.PlayingState;
import room.Direction;
import room.Room;

public class VerticalExecutor implements CardExecutor {
	
	public PlayingState playingState;
	
	public VerticalExecutor(PlayingState playingState) {
		this.playingState = playingState;
	}

	@Override
	public boolean execute() {
		Room room = playingState.getRoom();
		boolean up = room.doorState.get(Direction.UP);
		boolean down = room.doorState.get(Direction.DOWN);
		if(!up && !down)
			return false;
		
		if(!down)
			playingState.go(Direction.UP);
		else if(!up)
			playingState.go(Direction.DOWN);
		else {
			if(Game.game.random(1) < 0.5f)
				playingState.go(Direction.UP);
			else
				playingState.go(Direction.DOWN);
		}
		
		return true;
	}

}