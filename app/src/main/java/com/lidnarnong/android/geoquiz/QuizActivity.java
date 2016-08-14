package com.lidnarnong.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private Button mPrevButton;
    private Button mCheatButton;
    private boolean mIsCheater;
    private static final String TAG="QuizAcitivity";
    private static final String KEY_INDEX="index";//定义记住旋转屏幕前的下标
    private static final int REQUEST_CODE_CHEAT =0 ;
    //定义问题的数组
    private Question[] mQuestionBank=new Question[]{
            new Question(R.string.qustion_oceans,true),
            new Question(R.string.qustion_mideast,false),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true),
    };
        private int mCurrentIndex=0;//数组下标检索


    //封装updateQuestion()封装公共代码
    private void updateQuestion(){
        int question=mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }
    //定义判断对象答案
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue=mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId=0;
        if(mIsCheater){
            messageResId=R.string.judgement_toast;
        }else{

        if(userPressedTrue==answerIsTrue){
            messageResId=R.string.correct_toast;
        }else
        {
            messageResId=R.string.incorrect_toast;
        }
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //确定，取消，next 按钮 prev ,显示问题的控件
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (Button) findViewById(R.id.next_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mPrevButton = (Button) findViewById(R.id.prev_button);
        mCheatButton = (Button) findViewById(R.id.cheat_button);
        //作弊按钮
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start CheaActivity
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                //  Intent i=CheatActivity.newIntent(QuizActivity.this,answerIsTrue);
                boolean answerIstrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(i, REQUEST_CODE_CHEAT);
            }
        });

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);

            }

        });


        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });
        updateQuestion();
        //查询是否获得该数值
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }


        mPrevButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
    }
        //处理返回的结果

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }

            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    ;




    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }


    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG, "onResume() called");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.i(TAG,"onStop() called");

    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG," onDestory() called");
    }

}
