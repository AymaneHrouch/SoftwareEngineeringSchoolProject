package marksManager.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Util {

	public static String toJson(Object object) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		String JSONObject = gson.toJson(object);
		return JSONObject;
	}

}
