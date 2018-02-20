package com.udacity.sandwichclub.utils;

import android.os.Debug;
import android.util.Log;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Sandwich sandwich = new Sandwich();
        JSONObject sandwichJSonObject = new JSONObject(json);

        JSONObject sandwichObject = sandwichJSonObject.getJSONObject(JsonConstants.name);
        String sandwichName = sandwichObject.optString(JsonConstants.mainName);
        if (!sandwichName.isEmpty())
            sandwich.setMainName(sandwichName);

        String sandwichAlsoKnownAs = sandwichObject.optString(JsonConstants.alsoKnowAs);
        List<String> sandwichAlsoKnownAsList = new ArrayList<>(Arrays.asList(sandwichAlsoKnownAs.split(",")));
        sandwich.setAlsoKnownAs(sandwichAlsoKnownAsList);

        String sandwichDescription = sandwichJSonObject.optString(JsonConstants.description);
        if (!sandwichDescription.isEmpty()) {
            sandwich.setDescription(sandwichDescription);
        } else {
            sandwich.setDescription(JsonConstants.emptyValue);
        }
        String sandwichImage = sandwichJSonObject.optString(JsonConstants.image);
        if (!sandwichImage.isEmpty())
            sandwich.setImage(sandwichImage);

        String sandwichPlaceOfOrigin = sandwichJSonObject.optString(JsonConstants.placeOfOrigin);
        if (!sandwichPlaceOfOrigin.isEmpty()) {
            sandwich.setPlaceOfOrigin(sandwichPlaceOfOrigin);
        } else {
            sandwich.setPlaceOfOrigin(JsonConstants.emptyValue);
        }

        String sandwichIngredients = sandwichJSonObject.optString(JsonConstants.ingredients);
        List<String> sandwichIngredientsList = new ArrayList<>(Arrays.asList(sandwichIngredients.split(",")));
        sandwich.setIngredients(sandwichIngredientsList);
        return sandwich;
    }

}
