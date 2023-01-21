package com.example.pigeon.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.pigeon.Adapters.RateAdapter;
import com.example.pigeon.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RateActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    FloatingActionButton fbAdd;

    JSONArray jsonArray = new JSONArray();
    RateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

        //Assign variable
        recyclerView = findViewById(R.id.recycler_view);
        fbAdd = findViewById(R.id.fb_add);

        //Set layout manager
        recyclerView.setLayoutManager(new GridLayoutManager(
                this, 3
        ));
        //Initialize adapter
        adapter = new RateAdapter(this, jsonArray);
        //Set adapter
        recyclerView.setAdapter(adapter);

        fbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Initialize dialog
                Dialog dialog = new Dialog(RateActivity.this);
                //Set background transparent
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(
                        Color.TRANSPARENT
                ));
                //Set View
                dialog.setContentView(R.layout.dialog);
                //Display dialog
                dialog.show();

                //Initialize and assign variable
                RatingBar ratingBar = dialog.findViewById(R.id.rating_bar);
                TextView tvRating = dialog.findViewById(R.id.tv_rating);
                Button btSubmit = dialog.findViewById(R.id.bt_submit);

                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                        //Set selected rating on text view
                        tvRating.setText(String.format("(%s)", v));
                    }
                });

                //Set listener on submit button
                btSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Get rating from rating bar
                        String sRating = String.valueOf(ratingBar.getRating());
                        try {
                            //Add value in json array
                            jsonArray.put(new JSONObject().put("rating",sRating));
                            //Set adapter
                            recyclerView.setAdapter(adapter);
                            //Dismiss dialog
                            dialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

            }
        });



    }
}
