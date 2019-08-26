package com.example.android.miwok;

public class Word {
    private static final int NO_IMG_PROVIDED=-1;
    private String mDefaultTranslation,mMiwokTranslation;
    private int mImageResource=NO_IMG_PROVIDED;
    private int mAudioResource;

    public Word(String defaultTranslation, String miwokTranslation, int imageResource,int audioResource){
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslation;
        mImageResource=imageResource;
        mAudioResource=audioResource;
    }

    public Word(String defaultTranslation, String miwokTranslation,int audioResource){
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslation;
        mAudioResource=audioResource;
    }

    public boolean hasImage(){
        return (mImageResource!=NO_IMG_PROVIDED);
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public void setDefaultTranslation(String defaultTranslation) {
        mDefaultTranslation = defaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public void setMiwokTranslation(String miwokTranslation) {
        mMiwokTranslation = miwokTranslation;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public void setImageResource(int imageResource) {
        mImageResource = imageResource;
    }

    public int getAudioResource(){
        return mAudioResource;
    }
}
