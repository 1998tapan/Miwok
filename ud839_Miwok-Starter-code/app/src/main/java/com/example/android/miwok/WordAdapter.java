package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceID;

    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceID){
        super(context,0,words);
        mColorResourceID=colorResourceID;
    }

   // @androidx.annotation.NonNull
    @NotNull
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView==null){
           listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
       }
       Word currentWord=getItem(position);

        View textContainer=listItemView.findViewById(R.id.textContainer);
        int color=ContextCompat.getColor(getContext(),mColorResourceID);

        textContainer.setBackgroundColor(color);

        TextView defaultTranslationTextView= listItemView.findViewById(R.id.defaultWord);
        defaultTranslationTextView.setText(currentWord.getDefaultTranslation());

        TextView miwokTranslationTextView = listItemView.findViewById(R.id.miwokWord);
        miwokTranslationTextView.setText(currentWord.getMiwokTranslation());

        ImageView imgView = listItemView.findViewById(R.id.img);

        if(currentWord.hasImage()) {
            imgView.setImageResource(currentWord.getImageResource());
            imgView.setVisibility(View.VISIBLE);
        }
        else{
            imgView.setVisibility(View.GONE);
        }
        return listItemView;
    }
}
