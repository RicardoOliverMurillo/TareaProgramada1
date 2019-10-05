package services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import sun.audio.*;


public class AudioManipulation {
	public void playAudio() throws IOException  {
		FileInputStream f = new FileInputStream("description.wav");
		AudioStream audio = new AudioStream(f);
		AudioPlayer.player.start(audio);
		System.out.println("audio is playing");
	}
}
