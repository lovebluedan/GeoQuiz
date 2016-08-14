package com.lidnarnong.android.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE="com.lindanrong.android.geoquiz.answer_is_true";//定义extra常量
    private static final String EXTRA_ANSER_SHOWN="com.bignerdran.android.geoquiz.answer_show";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    //定义newIntent 将Intent 回调数据定义好
    public static Intent newIntent(Context packageContext,boolean answerIsTrue)
    {

        Intent i=new Intent(packageContext,CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
        return i;
    }
    //解析结果Intent
    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSER_SHOWN,false);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue=getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);//获取传过来的值，后面的参数是为了接收不到有效键值而设置的。
        mAnswerTextView= (TextView) findViewById(R.id.answer_text_view);

        mShowAnswer= (Button) findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.flase_button);
                }
                setAnswerShowResult(true);
                
            }
        });
    }

    private void setAnswerShowResult(boolean isAnswerShown) {
        Intent data=new Intent();
        data.putExtra(EXTRA_ANSER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,data);
    }
}
