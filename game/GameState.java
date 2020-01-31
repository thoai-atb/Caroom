package game;

public interface GameState {
	public void setup();
	public void update();
	public void show();
	public void mousePressed();
	public void keyPressed();
}
