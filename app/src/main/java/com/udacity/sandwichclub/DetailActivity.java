package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = 0;

        if (intent != null) {
            position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        }

        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
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

    private void populateUI(Sandwich sandwich) {
        TextView also_known = findViewById(R.id.also_known_tv);
        TextView also_known_label = findViewById(R.id.also_known_label_tv);
        String alsoKnown_val = ListToString(sandwich.getAlsoKnownAs());
        InitiateTextView(alsoKnown_val,also_known,also_known_label);


        TextView ingredients = findViewById(R.id.ingredients_tv);
        TextView ingredients_label = findViewById(R.id.ingredients_label_tv);
        String ingredients_val = ListToString(sandwich.getIngredients());
        InitiateTextView(ingredients_val,ingredients,ingredients_label);

        TextView origin = findViewById(R.id.origin_tv);
        TextView origin_label = findViewById(R.id.origin_label_tv);
        String origin_Val = sandwich.getPlaceOfOrigin();
        InitiateTextView(origin_Val,origin,origin_label);


        TextView description = findViewById(R.id.description_tv);
        TextView description_label = findViewById(R.id.description_label_tv);
        String description_val = sandwich.getDescription();
        InitiateTextView(description_val,description,description_label);
    }

    @NonNull
    private String ListToString(List<String> list){
        StringBuilder temp = new StringBuilder();
        for (String s :list){
            temp.append(s).append("\n");
        }
        return temp.toString();
    }
    private void InitiateTextView(String value , TextView tv, TextView label){
        if (value.isEmpty()){
            tv.setVisibility(View.GONE);
            label.setVisibility(View.GONE);
        }else{
            tv.setText(value);
        }
    }
}
