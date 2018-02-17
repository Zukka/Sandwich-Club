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

        System.out.println(sandwichJSonObject);
        JSONObject sandwichObject = sandwichJSonObject.getJSONObject("name");
        String sandwichName = sandwichObject.getString("mainName");
        if (!sandwichName.isEmpty())
            sandwich.setMainName(sandwichName);

        String sandwichAlsoKnownAs = sandwichObject.getString("alsoKnownAs");
        List<String> sandwichAlsoKnownAsList = new ArrayList<>(Arrays.asList(sandwichAlsoKnownAs.split(",")));
        sandwich.setAlsoKnownAs(sandwichAlsoKnownAsList);

        String sandwichDescription = sandwichJSonObject.getString("description");
        if (!sandwichDescription.isEmpty()) {
            sandwich.setDescription(sandwichDescription);
        } else {
            sandwich.setDescription(String.valueOf(R.string.emptyJSonValue));
        }
        String sandwichImage = sandwichJSonObject.getString("image");
        if (!sandwichImage.isEmpty())
            sandwich.setImage(sandwichImage);

        String sandwichPlaceOfOrigin = sandwichJSonObject.getString("placeOfOrigin");
        if (!sandwichPlaceOfOrigin.isEmpty()) {
            sandwich.setPlaceOfOrigin(sandwichPlaceOfOrigin);
        } else {
            sandwich.setPlaceOfOrigin(String.valueOf(R.string.emptyJSonValue));
        }

        String sandwichIngredients = sandwichJSonObject.getString("ingredients");
        List<String> sandwichIngredientsList = new ArrayList<>(Arrays.asList(sandwichIngredients.split(",")));
        sandwich.setIngredients(sandwichIngredientsList);
        return sandwich;
    }

}
