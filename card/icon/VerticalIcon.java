package card.icon;

import card.Card;
import game.Game;

public class VerticalIcon implements CardIcon {

	@Override
	public void show(Card card) {
		float r = 0.1f * card.getHeight();
		Game.game.fill(0);
		Game.game.stroke(0);
		Game.game.strokeWeight(2);
		Game.game.line(0, -r, 0, r);
		Game.game.strokeWeight(5);
		Game.game.point(0, -r);
		Game.game.point(0, r);
	}

}
