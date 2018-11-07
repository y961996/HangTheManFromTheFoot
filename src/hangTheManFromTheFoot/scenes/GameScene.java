package hangTheManFromTheFoot.scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
import hangTheManFromTheFoot.events.EventListener;
import hangTheManFromTheFoot.events.eventTypes.MouseMovedEvent;
import hangTheManFromTheFoot.events.eventTypes.MousePressedEvent;
import hangTheManFromTheFoot.events.eventTypes.MouseReleasedEvent;
import hangTheManFromTheFoot.input.MouseInput;
import hangTheManFromTheFoot.main.Game;
import hangTheManFromTheFoot.utils.StaticResourceLoader;

public class GameScene extends Scene implements EventListener{

	private float alpha = 1.0f;
	private String secretWord;
	private boolean bufferClosed = false;
	private boolean secretWordCreated = false;
	
	private Map<String, ArrayList<String>> words;
	private BufferedImage letterPlaceholder;
	private Random random = new Random();
	
	private KeyboardKey[] keyboardKeys;
	private boolean secretKeyCheck[];

	public GameScene(Game game, SceneController sceneController) {
		super(game, sceneController);
		
		words = new HashMap<String, ArrayList<String>>();
		readFromTextFile("res/words.txt");
		letterPlaceholder = StaticResourceLoader.letterPlaceHolder;
		secretWord = getSecretWord();
		secretKeyCheck = new boolean[secretWord.length()];
		secretWordCreated = true;
		
		keyboardKeys = new KeyboardKey[26];
		initKeyboardKeys();
	}
	
	@Override
	public void update() {
		for(int i = 0; i < keyboardKeys.length; i++) {
			keyboardKeys[i].update();
		}
		
		enterFadeInUpdate();
	}

	@Override
	public void render(Graphics g) {
		if(secretWordCreated) {
			g.setColor(Color.CYAN);
			g.setFont(new Font("Verdana", Font.BOLD, 32));
			g.drawString(secretWord, 200, 100);
			
			g.setColor(Color.BLACK);
			for(int j = 0; j < secretWord.length(); j++) {
				g.drawImage(letterPlaceholder, 100 * (j + 1), 400, 64, 8, null);
				if(secretKeyCheck[j]) g.drawString(String.valueOf(secretWord.charAt(j)), 100 * (j + 1) + 20, 400);
			}
		}
		
		for(int i = 0; i < keyboardKeys.length; i++) {
			keyboardKeys[i].render(g);
		}
		
		enterFadeInRender(g);
	}
	
	private void initKeyboardKeys() {
		for(int i = 0; i < this.keyboardKeys.length; i++) {
			KeyboardKey tempKey = new KeyboardKey(KeyboardKey.englishAlphabetLetters[i], (i + 1) * 40, 500 + (i % 2) * 75);
			this.keyboardKeys[i] = tempKey;
		}
	}
	
	private void checkLetterInsideSecretWord(String letter) {
		if(secretWord.contains(letter)) {
			for(int index = secretWord.indexOf(letter); index >= 0; index = secretWord.indexOf(letter, index + 1)) {
				secretKeyCheck[index] = true;
			}
		}
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
				checkLetterInsideSecretWord(keyboardKeys[i].getKeyLetter());
			}
		}
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
			checkIfKeyPressed();
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
