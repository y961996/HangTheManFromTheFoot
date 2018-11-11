package hangTheManFromTheFoot.utils;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundUtils {
	
	private Clip clip;
	
	public Clip loadSound(String path) {
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(path)));
		}catch(LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		return clip;
	}
	
	public void playOnce(Clip clip) {
		clip.start();
	}
	
	public void playLoop(Clip clip) {
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	// Temp
	public void playFile(File file) {
		try {
			final Clip clip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
			
			clip.addLineListener(new LineListener() {
				@Override
				public void update(LineEvent event) {
					if(event.getType() == LineEvent.Type.STOP) {
						clip.close();
					}
				}
			});
			
			clip.open(AudioSystem.getAudioInputStream(file));
			clip.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
	}
}
