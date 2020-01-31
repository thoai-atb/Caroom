package room;

import java.util.ArrayList;

import card.Card;
import game.Game;
import processing.core.PApplet;

public class Chest {
	
	private ArrayList<Card> cards = new ArrayList<Card>();
	private float width = 20;
	private float height = 20;
	
	public Chest() {
		
	}
	
	public void show() {
		Game.game.fill(204, 153, 0);
		Game.game.stroke(0);
		Game.game.strokeWeight(2);
		
		Game.game.rectMode(PApplet.CENTER);
		Game.game.rect(0, 0, width, height);
		Game.game.line(-width/2, -height*0.1f, width/2, -height*0.1f);
		Game.game.rect(0, 0, width*0.3f, height*0.3f);
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public void removeCard(Card card) {
		cards.remove(card);
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}
}
