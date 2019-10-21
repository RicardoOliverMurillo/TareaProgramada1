package services;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.IdentifiedLanguages;
import com.ibm.watson.language_translator.v3.model.IdentifyOptions;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;
import com.ibm.watson.tone_analyzer.v3.ToneAnalyzer;

/**
* 
* @author Marco Gómez, Ricardo Oliver, Anjelica Tristani
* Class TranslateText to translate an specific text 
*
*/
public class TranslateText {
	private String API_KEY = "VS2IXj95M3_3XsZVpGhnF2ujJ3AzlQHaTjNLVfjMbJkr";
	private String URL = "https://gateway.watsonplatform.net/language-translator/api";
	
	private LanguageTranslator authentication() {
		IamOptions options = new IamOptions.Builder()
				  .apiKey(API_KEY)
				  .build();
		LanguageTranslator languageTranslator = new LanguageTranslator("2018-05-01", options);
		languageTranslator.setEndPoint(URL);
		return languageTranslator;
	}
	/**
	 * method that translate the text requested
	 * @param text that is going to be translate 
	 * @return result with the text translated
	 */
	public String translate_text(String text, String from_language, String to_language) {
		LanguageTranslator languageTranslator = authentication();

		TranslateOptions translateOptions = new TranslateOptions.Builder()
		  .addText(text)
		  .modelId(from_language+"-"+to_language)
		  .build();

		TranslationResult result = languageTranslator.translate(translateOptions)
		  .execute().getResult();

		return result.getTranslations().get(0).getTranslationOutput();
	}
	
	public String getLanguage(String txt) {
		LanguageTranslator languageTranslator = authentication();

		IdentifyOptions identifyOptions = new IdentifyOptions.Builder()
		  .text(txt)
		  .build();

		IdentifiedLanguages languages = languageTranslator.identify(identifyOptions)
		  .execute().getResult();

		return languages.getLanguages().get(0).getLanguage();
	}
}
