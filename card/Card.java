package card;
import card.executor.CardExecutor;
import card.icon.CardIcon;
import game.Game;
import processing.core.PApplet;
import processing.core.PVector;

public class Card {
	
	public static final int DEFAULT_WIDTH = 50;
	public static final int DEFAUL_HEIGHT = 75;

	protected float width, height;
	protected PVector position;
	
	private PVector target;
	private CardIcon icon;
	
	private CardExecutor executor;
	
	public Card(float x, float y, CardIcon icon, CardExecutor executor) {
		position = new PVector(x, y);
		width = DEFAULT_WIDTH;
		height = DEFAUL_HEIGHT;
		
		this.icon = icon;
		this.executor = executor;
	}
	
	public void setPosition(PVector pos) {
		this.position = pos.copy();
	}
	
	public void setExecutor(CardExecutor executor) {
		this.executor = executor;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public boolean execute() {
		return executor != null && executor.execute();
	}
	
	public void update() {
		if(target != null)
			position = PVector.lerp(position, target, 0.2f);
	}
	
	public PVector getTarget() {
		return target;
	}
	
	public void moveTo(PVector target) {
		this.target = target.copy();
	}
	
	public boolean isStable() {
		return PVector.dist(position, target) < width * 0.1;
	}
	
	public void show() {
		show(containsMouse());
	}
	
	public void show(boolean highlight) {
		Game game = Game.game;
		
		int color = highlight? game.color(200) : game.color(255);
		
		game.fill(color);
		game.strokeWeight(2);
		game.stroke(0);
		
		game.pushMatrix();
		game.translate(position.x, position.y);
		game.rectMode(PApplet.CENTER);
		game.rect(0, 0, width, height, width / 10);
		showValue();
		game.popMatrix();
	}
	
	public void showValue() {
		icon.show(this);
	}
	
	public boolean containsMouse() {
		return Math.abs(Game.game.mouseX - position.x) < width/2 && Math.abs(Game.game.mouseY - position.y) < height/2 ; 
	}
}
