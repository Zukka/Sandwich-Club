package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    TextView sandwichDescription;
    TextView sandwichAlsoKnownAs;
    TextView sandwichPlaceOfOrigin;
    TextView sandwichIngredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        sandwichDescription = findViewById(R.id.description_tv);
        sandwichAlsoKnownAs = findViewById(R.id.also_known_tv);
        sandwichPlaceOfOrigin = findViewById(R.id.origin_tv);
        sandwichIngredients = findViewById(R.id.ingredients_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);

        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            Toast.makeText(this,  e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwichSelected) {
        sandwichDescription.setText((sandwichSelected.getDescription()));

        String stringForAlsoKnownAs = "";
        List<String> alsoKnownAsList = sandwichSelected.getAlsoKnownAs();
        for (String itemList : alsoKnownAsList)
        {
            if (itemList != "") {
                stringForAlsoKnownAs = stringForAlsoKnownAs + itemList + ",";
            }
        }
        String sandwichNames = stringForAlsoKnownAs.replace("\"","");
        sandwichAlsoKnownAs.setText(sandwichNames.substring(1,(sandwichNames.length()-2)));

        sandwichPlaceOfOrigin.setText(sandwichSelected.getPlaceOfOrigin());

        String stringForIngredients = "";
        List<String> ingredientsList = sandwichSelected.getIngredients();
        for (String itemList : ingredientsList)
        {
            if (itemList != "") {
                stringForIngredients = stringForIngredients + itemList + ",";
            }
        }
        String sandwichIngredientsParsed = stringForIngredients.replace("\"","");
        sandwichIngredients.setText(sandwichIngredientsParsed.substring(1,(sandwichIngredientsParsed.length()-2)));

    }
}
