package brickBreaker;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sounds {

	public void startShortSound(String path) {

		File file = new File(path);
		try {

			if (file.exists()) {
				AudioInputStream ais = AudioSystem.getAudioInputStream(file);
				Clip clip = AudioSystem.getClip();
				clip.open(ais);
				clip.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Path exist: " + file.exists());
		}

	}

	public void startLoopSound(String path) {

		File file = new File(path);
		try {

			if (file.exists()) {
				AudioInputStream ais = AudioSystem.getAudioInputStream(file);
				Clip clip = AudioSystem.getClip();
				clip.open(ais);
				clip.start();
				
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Path exist: " + file.exists());
		}

	}

}
