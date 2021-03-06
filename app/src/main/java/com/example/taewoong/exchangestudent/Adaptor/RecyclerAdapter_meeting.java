package com.example.taewoong.exchangestudent.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taewoong.exchangestudent.Activity.MeetingInfoActivity_Reg;
import com.example.taewoong.exchangestudent.Fragment.item;
import com.example.taewoong.exchangestudent.R;

import java.util.List;

/**
 * Created by Taewoong on 2017-11-17.
 */

public class RecyclerAdapter_meeting extends RecyclerView.Adapter<RecyclerAdapter_meeting.ViewHolder> {
    Context context;
    List<item> items;
    int item_layout;

    public RecyclerAdapter_meeting(Context context, List<item> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public RecyclerAdapter_meeting.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter_meeting.ViewHolder holder, int position) {
        final item item = items.get(position);
        Drawable drawable = ContextCompat.getDrawable(context, item.getImage());
        holder.image.setBackground(drawable);
        holder.title.setText(item.getTitle());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String group;
                String meeting;
                int start;
                int end;
                start = item.getTitle().indexOf('(');
                end = item.getTitle().indexOf(')');
                group = item.getTitle().substring(start+1,end);
                meeting = item.getTitle().substring(0,start);
                Intent intent = new Intent(context, MeetingInfoActivity_Reg.class);
                intent.putExtra("Meeting_title",meeting);
                intent.putExtra("Group_title",group);
                context.startActivity(intent);
            }
        });
    }

    public void refresh(){
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView title;
        CardView cardview;
        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            cardview = itemView.findViewById(R.id.cardview);
        }
    }
}
