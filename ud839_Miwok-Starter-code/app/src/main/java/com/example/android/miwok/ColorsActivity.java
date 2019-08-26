package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    MediaPlayer.OnCompletionListener mOnCompletionListener= new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> colorWords=new ArrayList<>();
        colorWords.add(new Word("red","weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        colorWords.add(new Word("mustard yellow","ṭopiisә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        colorWords.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        colorWords.add(new Word("gray","ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
        colorWords.add(new Word("brown","ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
        colorWords.add(new Word("black","kululli",R.drawable.color_black,R.raw.color_black));
        colorWords.add(new Word("white","kelelli",R.drawable.color_white,R.raw.color_white));
        colorWords.add(new Word("dusty yellow","chiwiiṭә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));

        WordAdapter colorsAdapter=new WordAdapter(this,colorWords,R.color.category_colors);
        ListView  colorListView = (ListView) findViewById(R.id.list);
        colorListView.setAdapter(colorsAdapter);

        colorListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word currentWord=(Word)parent.getAdapter().getItem(position);
                mMediaPlayer= MediaPlayer.create(getApplicationContext(),currentWord.getAudioResource());
                mMediaPlayer.start();
                //we created this global mOnCompletionListener object to avoid instantiating an object
                //everytime a list is clicked, thus saving memory
                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);

                //Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }
    }
}
