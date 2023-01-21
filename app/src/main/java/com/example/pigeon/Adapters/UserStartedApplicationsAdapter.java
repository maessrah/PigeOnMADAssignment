package com.example.pigeon.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeon.Model.Model;
import com.example.pigeon.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class UserStartedApplicationsAdapter extends FirebaseRecyclerAdapter<Model, UserStartedApplicationsAdapter.Viewholder> {

    public UserStartedApplicationsAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {

        super(options);

    }


    @Override
    protected void onBindViewHolder(@NonNull UserStartedApplicationsAdapter.Viewholder holder, @SuppressLint("RecyclerView") int position, @NonNull Model model) {

        Context context = holder.txtTitle.getContext();

        //for loading selected applications into recycler view
        holder.txtTitle.setText(model.getCompanyName());
        holder.txtDesc.setText(model.getJobTitle());
        String jobKey=model.getJobKey();
        String userID= GoogleSignIn.getLastSignedInAccount(holder.txtTitle.getContext()).getId();

        holder.completeWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.cancelWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.txtTitle.getContext());
                builder.setTitle("Are you sure to delete this job ads?");
                builder.setMessage("Deleted data can't be undoe");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference().child("JobsPosted").child(userID)
                                .child(jobKey).child(getRef(position).getKey()).removeValue();
                        FirebaseDatabase.getInstance().getReference().child("JobsPosted").child("JobsAds")
                                .child(getRef(position).getKey()).removeValue();

                        Toast.makeText(holder.txtTitle.getContext(),"Succesfully Deleted",Toast.LENGTH_SHORT).show();

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(holder.txtTitle.getContext(),"Cancelled",Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();

            }
        });



    }


    @NonNull
    @Override
    public UserStartedApplicationsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //the data objects are inflated into the xml file single_data_item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_started_template, parent, false);
        return new UserStartedApplicationsAdapter.Viewholder(view);
    }

    //we need view holder to hold each objet form recyclerview and to show it in recyclerview
    class Viewholder extends RecyclerView.ViewHolder {


        Button completeWork,cancelWork;
        TextView txtTitle;
        TextView txtDesc;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            //assigning the address of the materials
            completeWork=(Button)itemView.findViewById(R.id.completeWorkBtn);
            cancelWork=(Button) itemView.findViewById(R.id.cancelWorkBtn);
            txtTitle = (TextView) itemView.findViewById(R.id.Title);
            txtDesc = (TextView) itemView.findViewById(R.id.Desc);
        }
    }
}
