package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
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

        ArrayList<Word> familyWords=new ArrayList<>();
        familyWords.add(new Word("father","әpә",R.drawable.family_father,R.raw.family_father));
        familyWords.add(new Word("mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        familyWords.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        familyWords.add(new Word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        familyWords.add(new Word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        familyWords.add(new Word("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        familyWords.add(new Word("older sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        familyWords.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        familyWords.add(new Word("grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        familyWords.add(new Word("grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));

        WordAdapter familyAdapter=new WordAdapter(this,familyWords,R.color.category_family);
        ListView familyListView = (ListView) findViewById(R.id.list);
        familyListView.setAdapter(familyAdapter);

        familyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word currentWord=(Word)parent.getAdapter().getItem(position);
               // Word word=familyWords.get(position);
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
