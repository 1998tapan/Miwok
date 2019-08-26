package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    MediaPlayer.OnCompletionListener mOnCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        //String [] words={"one","two","three","four","five","six","seven","eight","nine","ten"};
        ArrayList<Word> words=new ArrayList<Word>();
        words.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two","otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four","oyyiisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five","massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six","temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine","wo'e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten","na'aacha",R.drawable.number_ten,R.raw.number_ten));

        WordAdapter itemsAdapter = new WordAdapter(this,words,R.color.category_numbers);
        ListView listView= (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word currentWord=(Word)parent.getAdapter().getItem(position);
                mMediaPlayer=MediaPlayer.create(getApplicationContext(),currentWord.getAudioResource());
                mMediaPlayer.start();
                //we created this global mOnCompletionListener object to avoid instantiating an object
                //everytime a list is clicked, thus saving memory
                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                //Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        /*LinearLayout rootView= (LinearLayout) findViewById(R.id.numberRootView);
        for(int index=0;index<words.size();index++){
            TextView wordView=new TextView(this);
            wordView.setText(words.get(index));
            assert rootView != null;
            rootView.addView(wordView);
        }*/

        /*int index=0;
        while(index < words.size()){
            TextView wordView=new TextView(this);
            wordView.setText(words.get(index));
            rootView.addView(wordView);
            index++;
        }*/
        /* Alternate method
        * String [] wordsArray={"one","two","three","four","five","six","seven","eight","nine","ten"};
        * ArrayList<String> words=new ArrayList<>();
        * LinearLayout rootView = findViewById(R.id.numberRootView);
        * TextView wordView = new TextView(this);
        * rootView.addView(wordView);
        * while(String str:wordsArray){
        *       words.add(str);
        *       wordView.append(words.get(index)+"\n";
        * }
        */

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
