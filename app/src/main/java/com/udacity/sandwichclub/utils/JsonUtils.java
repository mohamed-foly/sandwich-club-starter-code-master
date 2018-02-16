package com.udacity.sandwichclub.utils;

import android.util.Log;
import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try{

            JSONObject jsonObject = new JSONObject(json);
            JSONObject name = jsonObject.getJSONObject("name");
            String mainName = name.getString("mainName");

            List<String> alsoKnownAs = JsonToList(name.getJSONArray("alsoKnownAs"));

            String placeOfOrigin=jsonObject.getString("placeOfOrigin");
            String description=jsonObject.getString("description");
            String image=jsonObject.getString("image");
            List<String> ingredients =JsonToList( jsonObject.getJSONArray("ingredients"));
            return new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);
        }catch (Exception ex){
            Log.e("Data","Exception : "+ex.getMessage());
            return null;
        }
    }
  private static ArrayList<String> JsonToList(JSONArray jsonArray) throws JSONException {
        ArrayList<String> temp = new ArrayList<>();
        int ArrayLength = jsonArray.length();
        if (ArrayLength > 0) {
            for (int i = 0; i < ArrayLength; i++) {
                temp.add(jsonArray.getString(i));
            }
        }
        return temp;

    }
}
