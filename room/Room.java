package room;
import java.util.HashMap;
import java.util.Map;

import game.Game;
import processing.core.PApplet;
import processing.core.PVector;

public class Room {

	public Map<Direction, Boolean> doorState = new HashMap<Direction, Boolean>();
	
	private float width, height;
	private PVector center;
	private Chest chest;
	private int id;
	
	public Room(PVector center, float width, float height, int id) {
		this.id = id;
		this.center = center;
		this.width = width;
		this.height = height;
		
		for(Direction d : Direction.values())
			doorState.put(d, Game.game.random(1.0f) < 0.5f);
		doorState.put(Direction.random(), true);
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	public void shutAllDoors() {
		for(Direction d : Direction.values())
			doorState.put(d, false);
	}
	
	public void setChest(Chest chest) {
		this.chest = chest;
	}
	
	public Chest takeChest() {
		Chest c = chest;
		chest = null;
		return c;
	}
	
	public PVector getCenter() {
		return center;
	}
	
	public void setCenter(float x, float y) {
		center = new PVector(x, y);
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void show() {
		Game.game.pushMatrix();
		Game.game.translate(center.x, center.y);
		
		Game.game.fill(backgroundColor());
		
		Game.game.stroke(0);
		Game.game.strokeWeight(2);
		Game.game.rectMode(PApplet.CENTER);
		Game.game.rect(0, 0, width, height);
		
		backgroundPaint();
		
		showDoors();
		showDirections();
		if(chest != null)
			chest.show();
		Game.game.popMatrix();
	}
	
	private void showDoors() {
		float d = 20;
		Game.game.stroke(214, 154, 0);
		Game.game.strokeWeight(3);
		
		if(doorState.get(Direction.UP)) 
			Game.game.line(-d, -height/2, d, -height/2);
		if(doorState.get(Direction.DOWN))
			Game.game.line(-d, height/2, d, height/2);
		if(doorState.get(Direction.LEFT))
			Game.game.line(-width/2, -d, -width/2, d);
		if(doorState.get(Direction.RIGHT))
			Game.game.line(width/2, -d, width/2, d);
	}
	
	private void showDirections() {
		Game.game.fill(0, 100);
		Game.game.textSize(20);
		Game.game.textAlign(PApplet.CENTER, PApplet.CENTER);

		float offsetX = width * 0.1f;
		float offsetY = height * 0.1f;
	
		if(doorState.get(Direction.UP)) 
			Game.game.text("^", 0, - height/2 + offsetY);
		
		if(doorState.get(Direction.DOWN))
			Game.game.text("v", 0, height/2 - offsetY);
		
		if(doorState.get(Direction.LEFT))
			Game.game.text("<", - width/2 + offsetX, 0);
		
		if(doorState.get(Direction.RIGHT))
			Game.game.text(">", width/2 - offsetX, 0);
	}
	
	protected int backgroundColor() {
		return Game.game.color(255);
	}
	
	protected void backgroundPaint() {
		
	}
}
