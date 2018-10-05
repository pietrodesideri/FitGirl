package com.wit.android.fitgirl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by user on 3/2/2018.
 */

public class TipsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public Context context;
    private LayoutInflater inflater;
    List<Tips> data= Collections.emptyList();
    Tips current;

    // create constructor to innitilize context and data sent from MainActivity
    public TipsAdapter(Context context, List<Tips> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=inflater.inflate(R.layout.item_container, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {

        // Get current position of item in recyclerview to bind data and assign values from list
        final MyHolder myHolder= (MyHolder) holder;
        final Tips current=data.get(position);
        myHolder.article_title.setText(current.article_title);
        myHolder.article_author.setText(current.article_author);
       // myHolder.article_story.setText(current.article_story);


    }
    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder
    {

        TextView article_title;
        TextView article_author;
        //TextView article_story;



        // create constructor to get widget reference
        public MyHolder(View itemView)
        {
            super(itemView);
            article_title= itemView.findViewById(R.id.article_title);

            article_author = itemView.findViewById(R.id.author_name);

           // article_story = itemView.findViewById(R.id.article_story);

        }

    }

}
