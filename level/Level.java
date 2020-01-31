package level;

import java.util.ArrayList;
import java.util.Random;

import card.Card;
import card.CardFactory;
import card.CardType;
import game.PlayingState;
import room.Chest;
import room.Direction;
import room.Room;
import room.RoomFactory;
import room.RoomTransition;

public abstract class Level {
	
	protected ArrayList<CardType> initialCards = new ArrayList<CardType>();
	protected ArrayList<CardType> chestCards = new ArrayList<CardType>();

	private int label;
	private PlayingState playingState;
	
	public Level(PlayingState playingState, int label) {
		this.playingState = playingState;
		this.label = label;
		
		initialCards.add(CardType.UP);
		initialCards.add(CardType.DOWN);
		initialCards.add(CardType.LEFT);
		initialCards.add(CardType.RIGHT);
		
		chestCards.addAll(initialCards);
	}
	
	public Chest createChest() {
		Random random = new Random();
		Chest chest = new Chest();
		for(int i = 0; i<random.nextInt(2) + 1; i++) {
			Card card = CardFactory.createCard(playingState, chestCards.get(random.nextInt(chestCards.size())));
			chest.addCard(card);
		}
		return chest;
	}
	
	public ArrayList<CardType> getInitialCards() {
		return initialCards;
	}
	
	public Room getFirstRoom() {
		return RoomFactory.createStartRoom(0, label+"");
	}

	public abstract RoomTransition createTransition(PlayingState playingState, Room room, Direction direction);
}
