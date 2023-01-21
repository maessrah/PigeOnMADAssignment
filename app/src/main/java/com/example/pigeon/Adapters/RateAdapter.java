package com.example.pigeon.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeon.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.ViewHolder> {
    Activity activity;
    JSONArray jsonArray;

    //Create Constructor
    public RateAdapter(Activity activity, JSONArray jsonArray){
        this.activity = activity;
        this.jsonArray = jsonArray;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Initialize view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rate_popup,parent, false);
        //Return holder view
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            //Initialize json object
            JSONObject object = jsonArray.getJSONObject(position);
            //Get rating from json array
            String sRating = object.getString("rating");
            //Set rating on text view
            holder.tvRating.setText(sRating);
            //Set rating on rating bar
            holder.ratingBar.setRating(Float.parseFloat(sRating));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        //Pass json length
        return jsonArray.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize variable
        TextView tvRating;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign Variable
            tvRating = itemView.findViewById(R.id.tv_rating);
            ratingBar = itemView.findViewById(R.id.rating_bar);

        }
    }
}
