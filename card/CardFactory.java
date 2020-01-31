package card;

import card.icon.TextIcon;
import game.PlayingState;
import processing.core.PVector;

public class CardFactory {
	
	public static Card createCard(PlayingState playingState, CardType type, PVector position) {
		return new Card(position.x, position.y, type.getIcon(), type.createExecutor(playingState));
	}
	
	public static Card createCard(PlayingState playingState, CardType type) {
		return createCard(playingState, type, new PVector());
	}
	
	public static Card createButtonCard(float x, float y, float width, float height, float textSize, String text) {
		Card card = new Card(x, y, new TextIcon(text, textSize), null);
		card.setWidth(width);
		card.setHeight(height);
		
		return card;
	}

}
