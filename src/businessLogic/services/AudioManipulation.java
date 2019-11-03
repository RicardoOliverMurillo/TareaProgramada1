package businessLogic.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import sun.audio.*;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani
 * Class AudioManipulation to reproduce audio files
 *
 */
public class AudioManipulation {
	/**
	 * method that reproduce the wav file
	 * @throws IOException
	 */
	public void playAudio() throws IOException  {
		FileInputStream f = new FileInputStream("description.wav");
		AudioStream audio = new AudioStream(f);
		AudioPlayer.player.start(audio);
		System.out.println("audio is playing");
	}
}
