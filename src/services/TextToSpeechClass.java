package services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.text_to_speech.v1.util.WaveUtils;

public class TextToSpeechClass {
	
	private String API_KEY = "mLzhrxWMLPrJKuppEqzbFACo2MPFt5xEiZVTGSzq3MUX";
	private String URL = "https://stream.watsonplatform.net/text-to-speech/api";
	
	public void create_Audio(String txt) {
		IamOptions options = new IamOptions.Builder()
				  .apiKey(API_KEY)
				  .build();

				TextToSpeech textToSpeech = new TextToSpeech(options);
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

