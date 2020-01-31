package card.icon;

import card.Card;
import game.Game;

public class HorizontalIcon implements CardIcon {

	@Override
	public void show(Card card) {
		float r = 0.1f * card.getHeight();
		Game.game.fill(0);
		Game.game.stroke(0);
		Game.game.strokeWeight(2);
		Game.game.line(-r, 0, r, 0);
		Game.game.strokeWeight(5);
		Game.game.point(-r, 0);
		Game.game.point(r, 0);
	}

}
