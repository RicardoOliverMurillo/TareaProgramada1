package services;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;

public class TranslateText {
	private String API_KEY = "VS2IXj95M3_3XsZVpGhnF2ujJ3AzlQHaTjNLVfjMbJkr";
	private String URL = "https://gateway.watsonplatform.net/language-translator/api";
	
	public String translate_text(String text) {
		IamOptions options = new IamOptions.Builder()
				  .apiKey(API_KEY)
				  .build();

				LanguageTranslator languageTranslator = new LanguageTranslator("2018-05-01", options);
				languageTranslator.setEndPoint(URL);

				TranslateOptions translateOptions = new TranslateOptions.Builder()
				  .addText(text)
				  .modelId("es-en")
				  .build();

				TranslationResult result = languageTranslator.translate(translateOptions)
				  .execute().getResult();

				return result.getTranslations().get(0).getTranslationOutput();
	}
}
