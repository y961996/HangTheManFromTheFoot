package hangTheManFromTheFoot.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import hangTheManFromTheFoot.events.Event;
import hangTheManFromTheFoot.events.EventListener;
import hangTheManFromTheFoot.input.KeyboardInput;
import hangTheManFromTheFoot.input.MouseInput;
import hangTheManFromTheFoot.scenes.GameScene;
import hangTheManFromTheFoot.scenes.MenuScene;
import hangTheManFromTheFoot.scenes.OptionsScene;
import hangTheManFromTheFoot.scenes.SceneController;
import hangTheManFromTheFoot.utils.SoundUtils;

public class Game extends Canvas implements Runnable, EventListener{
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final String TITLE = "Hang The Man From The Foot v1.0";
	public static final boolean DEBUG = false;
	
	public static Rectangle mouseRectangle =  new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1);
	
	private boolean running = false;
	
	private Thread thread;
	private JFrame frame;
	
	private KeyboardInput keyboardInput;
	private MouseInput mouseInput;
	
	private SceneController sceneController;
	private MenuScene menuScene;
	private int menuSceneIndex;
	private OptionsScene optionsScene;
	private int optionsSceneIndex;
	private GameScene gameScene;
	private int gameSceneIndex;
	
	private SoundUtils soundUtils;
	
	public Game() {
		
	}
	
	private void init() {
		initWindow();
		
		sceneController = new SceneController();
		
		menuScene = new MenuScene(this, sceneController);
		menuSceneIndex = sceneController.getNumberOfScenes();
		sceneController.addScene(menuScene);
		
		optionsScene = new OptionsScene(this, sceneController);
		optionsSceneIndex = sceneController.getNumberOfScenes();
		sceneController.addScene(optionsScene);
		
		gameScene = new GameScene(this, sceneController);
		gameSceneIndex = sceneController.getNumberOfScenes();
		sceneController.addScene(gameScene);
		
		sceneController.setScene(menuSceneIndex);
		
		keyboardInput = new KeyboardInput();
		mouseInput = new MouseInput(this);
		
		addKeyListener(keyboardInput);
		addMouseListener(mouseInput);
		addMouseMotionListener(mouseInput);
		
		soundUtils = new SoundUtils();
		soundUtils.startMusic("res/sounds/sound.wav");
		soundUtils.setVolume(0.5f);
	}
	
	private void initWindow() {
		initCursor();
		
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

	private void initCursor() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image cursor = toolkit.getImage("res/images/cursor.png");
		Point cursorHotSpot = new Point(0, 0);
		Cursor customCursor = toolkit.createCustomCursor(cursor, cursorHotSpot, "cursor");
		
		frame = new JFrame(TITLE);
		
		if(cursor != null) {
			frame.setCursor(customCursor);
		}
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
		@SuppressWarnings("unused")
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
				if(DEBUG) System.out.println("FPS : " + frames);
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
		mouseRectangle.x = MouseInput.getX();
		mouseRectangle.y = MouseInput.getY();
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
	
	public int getOptionsSceneIndex() {
		return optionsSceneIndex;
	}
	
	public void setScene(int index) {
		this.sceneController.setScene(index);
	}
	
	public SoundUtils getMenuSoundUtils() {
		return this.soundUtils;
	}
}
