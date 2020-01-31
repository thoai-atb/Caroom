package timer;

public class Timer {
	private TimerTask task;
	private float speed;
	private float current = 0;
	
	public Timer(float speed) {
		this.speed = speed;
	}
	
	public float getCurrent() {
		return current;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public void setTask(TimerTask task) {
		current = 0;
		this.task = task;
	}
	
	public void cancel() {
		task = null;
	}
	
	public void skip() {
		current = 1;
	}
	
	public void update() {
		if(task == null)
			return;
		current += speed;
		if(current >= 1)
			timeUp();
	}
	
	private void timeUp() {
		task.run();
		task = null;
		current = 1;
	}
}
