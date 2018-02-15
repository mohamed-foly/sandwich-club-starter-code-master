package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try{
            JSONObject jsonObject = new JSONObject(json);
            String mainName = jsonObject.getString("mainName");

            List<String> alsoKnownAs = null;
            JSONArray alsoKnownAsJson = jsonObject.getJSONArray("alsoKnownAs");
            int alsoKnownAsLength = alsoKnownAsJson.length();
            if (alsoKnownAsLength > 0) {
                for (int i = 0; i < alsoKnownAsLength; i++) {
                    alsoKnownAs.add(alsoKnownAsJson.getString(i)) ;
                }
            }

            String placeOfOrigin=jsonObject.getString("placeOfOrigin");
            String description=jsonObject.getString("description");
            String image=jsonObject.getString("image");
            List<String> ingredients = null;
            JSONArray ingredientsJson = jsonObject.getJSONArray("ingredients");
            int ingredientsLength = ingredientsJson.length();
            if (ingredientsLength > 0) {
                for (int i = 0; i < ingredientsLength; i++) {
                    ingredients.add(ingredientsJson.getString(i));
                }
            }
            Sandwich sandwich = new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);
            return sandwich;
        }catch (Exception ex){
            return null;
        }
    }
}
