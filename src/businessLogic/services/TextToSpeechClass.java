package businessLogic.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.text_to_speech.v1.util.WaveUtils;
import com.ibm.watson.tone_analyzer.v3.ToneAnalyzer;

import sun.audio.*;

public class TextToSpeechClass {
	
	private String API_KEY = "7SbNdfiH4MghUhq2n6rcOXQqRI2yPWwJBnc8i-hQukgD";
	private String URL = "https://stream.watsonplatform.net/text-to-speech/api";
	 
	/**
	 * Method that return the object with the authentication of the TextToSpeech
	 * @return TextToSpeech textToSpeech object
	 */
	private TextToSpeech authentication() {
		IamOptions options = new IamOptions.Builder()
				  .apiKey(API_KEY)
				  .build();

				TextToSpeech textToSpeech = new TextToSpeech(options);
		return textToSpeech;
	}
	/**
	 * Method that creates a file .wav file with the txt parameter 
	 * @param txt text that we want to create the audio 
	 */
	public void create_Audio(String txt) {

				TextToSpeech textToSpeech = authentication();
				textToSpeech.setEndPoint(URL);

				try {
				  SynthesizeOptions synthesizeOptions = new SynthesizeOptions.Builder()
				      .text(txt)
				      .accept("audio/wav")
				      .voice("en-US_AllisonVoice")
				      .build();

				  InputStream inputStream = textToSpeech.synthesize(synthesizeOptions).execute().getResult();
				  InputStream in = WaveUtils.reWriteWaveHeader(inputStream);

				  OutputStream out = new FileOutputStream("description.wav");
				  byte[] buffer = new byte[1024];
				  int length;
				  while ((length = in.read(buffer)) > 0) {
				    out.write(buffer, 0, length);
				  }

				  out.close();
				  in.close();
				  inputStream.close();
				} catch (IOException e) {
				  e.printStackTrace();
				}
	}

}

