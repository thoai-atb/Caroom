package card;

import card.executor.AllDirectionExecutor;
import card.executor.CardExecutor;
import card.executor.GoExecutor;
import card.executor.HorizontalExecutor;
import card.executor.VerticalExecutor;
import card.icon.AllDirectionIcon;
import card.icon.CardIcon;
import card.icon.DirectionIcon;
import card.icon.HorizontalIcon;
import card.icon.VerticalIcon;
import game.PlayingState;
import room.Direction;

public enum CardType {
	
	UP, DOWN, LEFT, RIGHT, HORIZONTAL, VERTICAL, ALL_DIRECTION;
	
	public CardIcon getIcon() {
		switch(this) {
			case UP:
				return new DirectionIcon(Direction.UP);
			case DOWN:
				return new DirectionIcon(Direction.DOWN);
			case LEFT:
				return new DirectionIcon(Direction.LEFT);
			case RIGHT:
				return new DirectionIcon(Direction.RIGHT);
			case HORIZONTAL:
				return new HorizontalIcon();
			case VERTICAL:
				return new VerticalIcon();
			case ALL_DIRECTION:
				return new AllDirectionIcon();
			default:
				return null;
		}
	}
	
	public CardExecutor createExecutor(PlayingState playingState) {
		switch(this) {
			case UP:
				return new GoExecutor(playingState, Direction.UP);
			case DOWN:
				return new GoExecutor(playingState, Direction.DOWN);
			case LEFT:
				return new GoExecutor(playingState, Direction.LEFT);
			case RIGHT:
				return new GoExecutor(playingState, Direction.RIGHT);
			case HORIZONTAL:
				return new HorizontalExecutor(playingState);
			case VERTICAL:
				return new VerticalExecutor(playingState);
			case ALL_DIRECTION:
				return new AllDirectionExecutor(playingState);
			default:
				return null;
			}
	}

}
