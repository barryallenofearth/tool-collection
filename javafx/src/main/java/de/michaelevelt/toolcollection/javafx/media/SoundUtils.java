package de.michaelevelt.toolcollection.javafx.media;

import javafx.application.Platform;
import javafx.scene.media.AudioClip;

import java.io.File;

public class SoundUtils {

	public static void playAuswertungSound(String fileName) {
		File soundFile = new File(fileName);
		if (soundFile.isFile()) {
			Platform.runLater(() -> {
				AudioClip mediaPlayer = new AudioClip(soundFile.toURI().toString());
				mediaPlayer.play();
			});
		}
	}
}
