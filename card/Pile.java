package card;
import java.util.ArrayList;

import processing.core.PVector;

public class Pile {
	
	private PVector position;
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	private Card highlightCard = null;
	private float distBetweenCards;
	private float raiseHeight;
	
	public Pile(float x, float y) {
		distBetweenCards = Card.DEFAULT_WIDTH * 0.8f;
		raiseHeight = Card.DEFAUL_HEIGHT * 0.2f;
		position = new PVector(x, y);
	}
	
	public PVector getPosition() {
		return position;
	}
	
	public void setRaiseHeight(float height) {
		raiseHeight = height;
	}
	
	public void setDistBetweenCards(float dist) {
		distBetweenCards = dist;
	}
	
	public boolean isStable() {
		for(Card c : cards)
			if(!c.isStable())
				return false;
		return true;
	}
	
	public void update() {
		for(Card card : cards)
			card.update();
		
		// update highlightCard
		Card previousCard = highlightCard;
		highlightCard = null;
		for(int i = cards.size() - 1; i >= 0; i--)
			if(cards.get(i).containsMouse()) {
				highlightCard = cards.get(i);
				break;
			}
		
		if(previousCard != highlightCard) {
			locateCards();
		}
	}
	
	public void show() {
		for(Card card : cards) {
			card.show(card == highlightCard);
		}
	}
	
	public void mousePressed() {
		if(highlightCard != null && highlightCard.execute()) {
			cards.remove(highlightCard);
			locateCards();
		}
	}
	
	// ============================ Card List Methods

	public void addCards(ArrayList<Card> cards2) {
		cards.addAll(cards2);
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public void removeCard(Card card) {
		cards.remove(card);
	}
	
	public void clear() {
		cards.clear();
	}
	
	public int amount() {
		return cards.size();
	}
	
	// ============================= Card Location Methods
	
	public void resetCardPositions() {
		for(Card card : cards)
			card.position = position.copy();
	}
	
	public void locateCards() {
		spread();
		if(highlightCard != null)
			raiseCard(highlightCard);
	}
	
	public void compress() {
		for(Card card : cards) {
			card.moveTo(position);
		}
	}
	
	public void spread() {
		float d = distBetweenCards;
		PVector current = new PVector(position.x - (amount()-1) * 0.5f * d, position.y);
		
		for(Card card : cards) {
			card.moveTo(current);
			current.x += d;
		}
	}
	
	public void raiseCard(Card card) {
		PVector target = card.getTarget();
		card.moveTo(target.add(0, - raiseHeight));
	}
}
