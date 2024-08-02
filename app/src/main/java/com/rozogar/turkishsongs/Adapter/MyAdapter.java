package com.rozogar.turkishsongs.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rozogar.turkishsongs.R;
import com.rozogar.turkishsongs.Activity.PlayActivity;

import java.util.ArrayList;
import Model.Music;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    private final ArrayList<Music> music;
    private Context context;

    public MyAdapter(ArrayList<Music> music, Context context) {
        this.music = music;
        this.context = context;
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView text;
        ImageView picture;
        Context context;
        ArrayList<Music> music;
        public MyViewHolder(View view, Context context, ArrayList<Music> music) {
            super(view);
            this.context = context;
            this.music = music;
            text = view.findViewById(R.id.text);
            picture = view.findViewById(R.id.picture);
            view.setOnClickListener(this);
        }

        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, PlayActivity.class);
            intent.putParcelableArrayListExtra("thisMusic", music);
            intent.putExtra("currentIndex", position);
            context.startActivity(intent);
        }
    }



    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view, context, music);
    }

        public void onBindViewHolder (MyViewHolder holder,int position){
            Music thismusic = music.get(position);
            holder.text.setText(thismusic.getName());
            holder.picture.setImageResource(thismusic.getImg());
        }

    public int getItemCount() {
        return music.size();
    }
}



