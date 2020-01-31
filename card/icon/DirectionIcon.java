package card.icon;

import card.Card;
import game.Game;
import processing.core.PApplet;
import room.Direction;

public class DirectionIcon implements CardIcon {
	
	Direction direction;
	
	public DirectionIcon(Direction direction) {
		this.direction = direction;
	}

	@Override
	public void show(Card card) {

		float offset = 0.03f * card.getHeight();
		Game.game.fill(0);
		Game.game.textSize(card.getHeight()/3);
		Game.game.textAlign(PApplet.CENTER, PApplet.CENTER);
		Game.game.text(direction.icon(), 0, -offset);
	}

}
