package gameManagers;

public class GameLoop implements Runnable {

	private Thread thread;

	private boolean isRunning = false;
	private double frameCap = 1.0 / 60.0;

	public GameLoop() {
		// TODO Auto-generated constructor stub

		// missing game class to update
	}
	
	public void start() {

		if (isRunning)
			return;

		thread = new Thread(this);

		thread.run();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		isRunning = true;

		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;
		boolean render = false;
		int frames = 0;
		double frameTime = 0;

		while (isRunning) {

			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;

			lastTime = firstTime;

			unprocessedTime += passedTime;
			frameTime += passedTime;

			while (unprocessedTime >= frameCap) {

				// update game here

				unprocessedTime -= frameCap;

				render = true;

				if (frameTime >= 1) {
					frameTime = 0;
					System.out.println(frames);
					frames = 0;
				}

			}

			if (render) {

				// render game
				// update window
				// clean screen
				
				frames++;

			} else {

				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

	public void stop() {

		if (!isRunning)
			return;

		isRunning = false;
	}

}
