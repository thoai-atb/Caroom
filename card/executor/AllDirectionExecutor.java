package card.executor;

import java.util.ArrayList;
import java.util.Random;

import game.PlayingState;
import room.Direction;

public class AllDirectionExecutor implements CardExecutor {
	
	public PlayingState playingState;
	
	public AllDirectionExecutor(PlayingState playingState) {
		this.playingState = playingState;
	}

	@Override
	public boolean execute() {
		ArrayList<Direction> list = new ArrayList<Direction>();
		for(Direction d : Direction.values())
			if(playingState.getRoom().doorState.get(d))
				list.add(d);
		
		if(list.isEmpty())
			return false;
		
		playingState.go(list.get(new Random().nextInt(list.size())));
		return true;
	}

}
