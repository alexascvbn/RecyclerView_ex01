package com.example.recyclerview_ex01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private ArrayList<Person> people;
    ItemClicked activity;

    public interface ItemClicked {
        void onItemClicked(int index);
    }

    public PersonAdapter(Context context, ArrayList<Person> list) {
        people = list;
        activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPref;
        TextView tvName, tvSurname;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            ivPref = itemView.findViewById(R.id.ivPref);
            tvName = itemView.findViewById(R.id.tvName);
            tvSurname = itemView.findViewById(R.id.tvSurname);

            itemView.setOnClickListener(v -> activity.onItemClicked(people.indexOf((Person) v.getTag())));
        }
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull PersonAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(people.get(position));

        holder.tvName.setText(people.get(position).getName());
        holder.tvSurname.setText(people.get(position).getSurname());

        if (people.get(position).getPreference().equals("bus")) {
            holder.ivPref.setImageResource(R.drawable.bus);
        } else {
            holder.ivPref.setImageResource(R.drawable.plane);
        }
    }

    @Override
    public int getItemCount() {
        return people.size();
    }
}
