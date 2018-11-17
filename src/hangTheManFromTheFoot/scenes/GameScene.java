package hangTheManFromTheFoot.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import hangTheManFromTheFoot.entity.KeyboardKey;
import hangTheManFromTheFoot.events.Event;
import hangTheManFromTheFoot.events.EventDispatcher;
import hangTheManFromTheFoot.events.eventTypes.MouseMovedEvent;
import hangTheManFromTheFoot.events.eventTypes.MousePressedEvent;
import hangTheManFromTheFoot.events.eventTypes.MouseReleasedEvent;
import hangTheManFromTheFoot.input.MouseInput;
import hangTheManFromTheFoot.main.Game;
import hangTheManFromTheFoot.ui.HangmanButton;
import hangTheManFromTheFoot.ui.UIManager;
import hangTheManFromTheFoot.utils.StaticResourceLoader;

public class GameScene extends Scene{

	private int numberAttemptsLeft;
	private int letterNeedsToBeFound;
	private float alpha = 1.0f;
	private float footballImageX;
	private float footballImageY;
	private float footballImageVelX;
	private float footballImageVelY;
	private String secretWord;
	private boolean bufferClosed = false;
	private boolean secretWordCreated = false;
	private boolean gameOver = false;
	private boolean gameFinished = false;
	
	private Map<String, ArrayList<String>> words;
	private Random random = new Random();
	
	private BufferedImage letterPlaceholder;
	private BufferedImage gameSceneBackground;
	private BufferedImage buttonImage;
	private BufferedImage footballImage;
	private BufferedImage[] hangingManImages;
	
	private UIManager uiManager;
	private HangmanButton playAgainButton;
	private HangmanButton goToMenuButton;
	
	private KeyboardKey[] keyboardKeys;
	private boolean secretKeyCheck[];

	public GameScene(Game game, SceneController sceneController) {
		super(game, sceneController);
		
		numberAttemptsLeft = 6;
		words = new HashMap<String, ArrayList<String>>();
		readFromTextFile("res/words.txt");
		secretWord = getSecretWord();
		letterNeedsToBeFound = secretWord.length();
		secretKeyCheck = new boolean[secretWord.length()];
		secretWordCreated = true;
		
		uiManager = new UIManager();
		
		letterPlaceholder = StaticResourceLoader.letterPlaceHolder;
		gameSceneBackground = StaticResourceLoader.gameSceneBackground;
		buttonImage = StaticResourceLoader.menuItemBackground;
		hangingManImages = StaticResourceLoader.hangingManImages;
		footballImage = StaticResourceLoader.football;
		
		footballImageX = 50;
		footballImageY = 500;
		footballImageVelX = 0;
		footballImageVelY = 0;
		
		keyboardKeys = new KeyboardKey[26];
		initKeyboardKeys();
	}
	
	@Override
	public void update() {
		for(int i = 0; i < keyboardKeys.length; i++) {
			keyboardKeys[i].update();
		}
		uiManager.update();
		enterFadeInUpdate();
		updateFootball();
	}

	@Override
	public void render(Graphics g) {
		// Background
		g.drawImage(gameSceneBackground, 0, 0, Game.WIDTH, Game.HEIGHT, null);
		uiManager.render(g);
		if(gameOver) {
			g.setColor(Color.RED.brighter());
			g.setFont(new Font("Verdana", Font.BOLD, 72));
			g.drawString("GAME OVER", 370, 300);
		}else if(gameFinished) {
			g.setColor(Color.GREEN.brighter());
			g.setFont(new Font("Verdana", Font.BOLD, 72));
			g.drawString("YOU WON", 420, 300);
		}
		
		g.drawImage(footballImage, (int)footballImageX, (int)footballImageY, 64, 64, null);
		g.drawImage(hangingManImages[(hangingManImages.length - 1) - numberAttemptsLeft], -20, 70, 512, 512, null);
		
		g.setColor(new Color(0x3d7f7f));
		g.setFont(new Font("Verdana", Font.BOLD, 72));
		g.drawString("TOPIC", 850, 150);
		
		g.setFont(new Font("Verdana", Font.BOLD, 32));
		if(secretWordCreated) {
			g.setColor(Color.BLACK);
			for(int j = 0; j < secretWord.length(); j++) {
				g.drawImage(letterPlaceholder, 120 * (j + 1), 500, 64, 8, null);
				if(secretKeyCheck[j]) g.drawString(String.valueOf(secretWord.charAt(j)), 120 * (j + 1) + 20, 500);
			}
		}
		
		for(int i = 0; i < keyboardKeys.length; i++) {
			keyboardKeys[i].render(g);
		}
		
		enterFadeInRender(g);
	}
	
	private void updateFootball() {
		footballImageX += footballImageVelX;
		footballImageY += footballImageVelY;
		footballImageY++;						// Gravity
		if(footballImageVelX > 0) {
			footballImageVelX--;
		}
		if(footballImageVelX < 0) {
			footballImageVelX = 0;
		}
		if(footballImageVelY > 0) {
			footballImageVelY--;
		}
		if(footballImageVelY < 0) {
			footballImageVelY = 0;
		}
		if(footballImageY > 500) {
			footballImageY = 500;
		}
		if(footballImageY < 0) {
			footballImageY = 0;
		}
	}
	
	private void initKeyboardKeys() {
		for(int i = 0; i < this.keyboardKeys.length; i++) {
			KeyboardKey tempKey = new KeyboardKey(KeyboardKey.englishAlphabetLetters[i], (i + 1) * 40, 550 + (i % 2) * 75);
			this.keyboardKeys[i] = tempKey;
		}
	}
	
	private boolean checkLetterInsideSecretWord(String letter) {
		if(secretWord.contains(letter)) {
			for(int index = secretWord.indexOf(letter); index >= 0; index = secretWord.indexOf(letter, index + 1)) {
				secretKeyCheck[index] = true;
				letterNeedsToBeFound--;
			}
			return true;
		}
		return false;
	}
	
	public String getSecretWord() {
		int numOfCategories = words.keySet().size();
		int randomCategory = random.nextInt(numOfCategories);
		Set<String> keys = words.keySet();
		String[] keysArray = keys.toArray(new String[keys.size()]);
		String key = keysArray[randomCategory];
		int numOfElementsInCategory = words.get(key).size();
		int randomElement = random.nextInt(numOfElementsInCategory);
		System.out.println("Secret word has chosen from category: \'" + keysArray[randomCategory] + "\' and it is \"" + words.get(key).get(randomElement) + "\"");
		return words.get(key).get(randomElement).toUpperCase();
	}
	
	private void enterFadeInRender(Graphics g) {
		g.setColor(new Color(0, 0, 0, alpha));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
	}
	
	private void enterFadeInUpdate() {
		if(alpha > 0.000001f) {
			alpha -= 0.01f;
		}else {
			alpha = 0f;
		}
	}
	
	private void readFromTextFile(String path) {
		File file = new File(path);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line;
			String tempKey = "";
			ArrayList<String> tempList = new ArrayList<String>();
	
			while((line=br.readLine()) != null) {
				if(line.startsWith("#")) {
					tempList = new ArrayList<String>();
					tempKey = line.substring(1);
					while(!(line=br.readLine()).startsWith("!")) {
						tempList.add(line);
					}
					words.put(tempKey, tempList);
					tempKey = "";
				}
			}
			
			br.close();
			bufferClosed = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, ArrayList<String>> getWords() {
		return words;
	}
	
	public boolean isBufferClosed() {
		return bufferClosed;
	}

	private void checkIfKeyPressed() {
		for(int i = 0; i < keyboardKeys.length; i++) {
			if(!keyboardKeys[i].isKeyPressed() && keyboardKeys[i].intersects(MouseInput.getX(), MouseInput.getY(), 1, 1)) {
				keyboardKeys[i].setKeyPressed();
				boolean found = checkLetterInsideSecretWord(keyboardKeys[i].getKeyLetter());
				if(!found) numberAttemptsLeft--;
				if(numberAttemptsLeft == 0) {
					secretWordCreated = false;
					gameOver = true;
					
					handleButtonCreation();
				}
				if(letterNeedsToBeFound == 0){
					secretWordCreated = false;
					gameFinished = true;
					
					handleButtonCreation();
				}
			}
		}
	}
	
	private void handleButtonCreation() {
		playAgainButton = new HangmanButton(420, 350, buttonImage.getWidth() + 50, buttonImage.getHeight(), buttonImage, true);
		playAgainButton.setButtonText("PLAY AGAIN");
		playAgainButton.setTextX(playAgainButton.getX() + playAgainButton.getWidth() / 3);
		playAgainButton.setTextY(playAgainButton.getY() + playAgainButton.getHeight() / 2);
		goToMenuButton = new HangmanButton(420, 450, buttonImage.getWidth() + 50, buttonImage.getHeight(), buttonImage, true);
		goToMenuButton.setButtonText("GO BACK TO MENU");
		goToMenuButton.setTextX(goToMenuButton.getX() + goToMenuButton.getWidth() / 4);
		goToMenuButton.setTextY(goToMenuButton.getY() + goToMenuButton.getHeight() / 2);
		uiManager.addComponent(playAgainButton);
		uiManager.addComponent(goToMenuButton);
	}
	
	@Override
	public void onEvent(Event event) {
		EventDispatcher dispatcher = new EventDispatcher(event);
		dispatcher.dispatch(Event.Type.MOUSE_PRESSED, (Event e) -> onMousePressed((MousePressedEvent) e));
		dispatcher.dispatch(Event.Type.MOUSE_RELEASED, (Event e) -> onMouseReleased((MouseReleasedEvent) e));
		dispatcher.dispatch(Event.Type.MOUSE_MOVED, (Event e) -> onMouseMoved((MouseMovedEvent) e));
	}

	public boolean onMousePressed(MousePressedEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			if(!gameOver) {
				checkIfKeyPressed();
			}
			if((gameOver || gameFinished) && goToMenuButton != null) {
				if(goToMenuButton.checkCollision(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))) {
					resetGame();
					game.setScene(game.getMenuSceneIndex());
				}
			}
			if((gameOver || gameFinished) && playAgainButton != null) {
				if(playAgainButton.checkCollision(new Rectangle(MouseInput.getX(), MouseInput.getY(), 1, 1))) {
					resetGame();
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean onMouseReleased(MouseReleasedEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			return true;
		}
		return false;
	}
	
	public boolean onMouseMoved(MouseMovedEvent e) {
		for(int i = 0; i < keyboardKeys.length; i++) {
			if(keyboardKeys[i].intersects(MouseInput.getX(), MouseInput.getY(), 1, 1)) {
				keyboardKeys[i].updateBackgroundColor();
			}else {
				keyboardKeys[i].backToOriginalColor();
			}
		}
		return false;
	}
	
	private void resetGame() {
		gameOver = false;
		gameFinished = false;
		uiManager.removeComponent(playAgainButton);
		uiManager.removeComponent(goToMenuButton);
		playAgainButton = null;
		goToMenuButton = null;
		numberAttemptsLeft = 6;
		secretWord = getSecretWord();
		letterNeedsToBeFound = secretWord.length();
		secretKeyCheck = new boolean[secretWord.length()];
		secretWordCreated = true;
		initKeyboardKeys();
	}
	
	/*
	 * I might need this:
	 * 
	 * new Thread() {
			public void run() {
				while(!game.isBufferClosed()) {			// Wait until it reads all the txt file.
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
	 */
}
