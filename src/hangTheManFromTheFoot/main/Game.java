package hangTheManFromTheFoot.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import hangTheManFromTheFoot.events.Event;
import hangTheManFromTheFoot.events.EventListener;
import hangTheManFromTheFoot.input.KeyboardInput;
import hangTheManFromTheFoot.input.MouseInput;
import hangTheManFromTheFoot.scenes.GameScene;
import hangTheManFromTheFoot.scenes.MenuScene;
import hangTheManFromTheFoot.scenes.SceneController;

public class Game extends Canvas implements Runnable, EventListener{
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final String TITLE = "Hang The Man From The Foot v0.1";
	
	private boolean running = false;
	
	private Thread thread;
	private JFrame frame;
	
	private KeyboardInput keyboardInput;
	private MouseInput mouseInput;
	
	private SceneController sceneController;
	private MenuScene menuScene;
	private int menuSceneIndex;
	private GameScene gameScene;
	private int gameSceneIndex;
	
	public Game() {
		
	}
	
	private void init() {
		initWindow();
		
		sceneController = new SceneController();
		
		menuScene = new MenuScene(this, sceneController);
		menuSceneIndex = sceneController.getNumberOfScenes();
		sceneController.addScene(menuScene);
		
		gameScene = new GameScene(this, sceneController);
		gameSceneIndex = sceneController.getNumberOfScenes();
		sceneController.addScene(gameScene);
		
		sceneController.setScene(menuSceneIndex);
		
		keyboardInput = new KeyboardInput();
		mouseInput = new MouseInput(this);
		
		addKeyListener(keyboardInput);
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
	}
	
	private void initWindow() {
		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	@Override
	public void run() {
		init();
		
		requestFocus();
		
		long lastTime = System.nanoTime();
		double amountOfUpdates = 60.0;
		double ns = 1000000000 / amountOfUpdates;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				delta--;
				frames++;
			}
			render();
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS : " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	public void start() {
		if(running) {
			return;
		}
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		if(!running) {
			return;
		}
		
		running = false;
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void update() {
		keyboardInput.update();
		sceneController.update();
		
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		//Clear the Screen
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		sceneController.render(g);
		
		g.dispose();
		bs.show();
	}
	
	@Override
	public void onEvent(Event event) {
		sceneController.getCurrentScene().onEvent(event);
	}
	
	public int getMenuSceneIndex() {
		return menuSceneIndex;
	}
	
	public int getGameSceneIndex() {
		return gameSceneIndex;
	}
}
