package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
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

        ArrayList<Word> phrasesWords=new ArrayList<>();
        phrasesWords.add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        phrasesWords.add(new Word("What is your name","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        phrasesWords.add(new Word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        phrasesWords.add(new Word("How are you feeling","michәksәs",R.raw.phrase_how_are_you_feeling));
        phrasesWords.add(new Word("I'm feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        phrasesWords.add(new Word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        phrasesWords.add(new Word("Yes, I'm coming","hәә’әәnәm",R.raw.phrase_yes_im_coming));
        phrasesWords.add(new Word("I'm coming","әәnәm",R.raw.phrase_im_coming));
        phrasesWords.add(new Word("Let's go","yoowutis",R.raw.phrase_lets_go));
        phrasesWords.add(new Word("Come here","әnni'nem",R.raw.phrase_come_here));


        WordAdapter phrasesAdapter=new WordAdapter(this,phrasesWords,R.color.category_phrases);
        ListView phraseListView = (ListView) findViewById(R.id.list);
        phraseListView.setAdapter(phrasesAdapter);

        phraseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
