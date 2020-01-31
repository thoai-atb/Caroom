package card.icon;

import card.Card;
import game.Game;
import processing.core.PApplet;

public class TextIcon implements CardIcon {
	String text;
	float textSize;
	
	public TextIcon(String text, float textSize) {
		this.text = text;
		this.textSize = textSize;
	}

	@Override
	public void show(Card card) {
		float offset = 0.03f * card.getHeight();
		Game.game.fill(0);
		Game.game.textSize(textSize);
		Game.game.textAlign(PApplet.CENTER, PApplet.CENTER);
		Game.game.text(text, 0, -offset);
	}
}
