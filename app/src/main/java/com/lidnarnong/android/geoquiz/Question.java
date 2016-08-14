package com.lidnarnong.android.geoquiz;

/**
 * Created by 林丹荣 on 2016/7/16.
 */
public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public Question(int textResId,boolean answerTrue){
        mTextResId=textResId;
        mAnswerTrue=mAnswerTrue;

    }
}
