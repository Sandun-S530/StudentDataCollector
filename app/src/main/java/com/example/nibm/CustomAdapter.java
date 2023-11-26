package com.example.nibm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final Activity activity;
    private Context context;
    private ArrayList id, index, name, age, gender, mNo, pmNo;

    CustomAdapter(Activity activity, Context context, ArrayList id, ArrayList index, ArrayList name, ArrayList age, ArrayList gender, ArrayList mNo,
                  ArrayList pmNo) {

        this.activity = activity;
        this.context = context;
        this.id = id;
        this.index = index;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.mNo = mNo;
        this.pmNo = pmNo;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.index_text.setText(String.valueOf(index.get(position)));
        holder.name_text.setText(String.valueOf(name.get(position)));
        holder.age_text.setText(String.valueOf(age.get(position)));
        holder.gender_text.setText(String.valueOf(gender.get(position)));
        holder.mNo_text.setText(String.valueOf(mNo.get(position)));
        holder.pmNo_text.setText(String.valueOf(pmNo.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id" , String.valueOf(id.get(position)));
                intent.putExtra("index", String.valueOf(index.get(position)));
                intent.putExtra("name", String.valueOf(name.get(position)));
                intent.putExtra("age", String.valueOf(age.get(position)));
                intent.putExtra("gender", String.valueOf(gender.get(position)));
                intent.putExtra("mobile", String.valueOf(mNo.get(position)));
                intent.putExtra("home", String.valueOf(pmNo.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });

    }


    @Override
    public int getItemCount() {

        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView index_text, name_text, age_text, gender_text, mNo_text, pmNo_text;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            index_text = itemView.findViewById(R.id.index_text);
            name_text = itemView.findViewById(R.id.name_text);
            age_text = itemView.findViewById(R.id.age_text);
            gender_text = itemView.findViewById(R.id.gender_text);
            mNo_text = itemView.findViewById(R.id.mNo_text);
            pmNo_text = itemView.findViewById(R.id.pmNo_text);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

}
