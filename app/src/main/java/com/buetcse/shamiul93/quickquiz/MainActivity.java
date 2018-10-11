package com.buetcse.shamiul93.quickquiz;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.*;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends Activity {


    Button mTrueButton;
    Button mFalseButton;
    ProgressBar progress;

    private int Score, Index;


    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null)
        {
            Score = savedInstanceState.getInt("scoreKey");
            Index = savedInstanceState.getInt("indexKey");

            showCurrentQuestion();
        }
        else
        {
            Score = 0 ;
            Index = 0 ;
        }


        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });


        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

    }

    void checkAnswer(boolean ans) {
        boolean Verdict;

        if (ans == mQuestionBank[Index].getAnswer()) {
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_LONG).show();
            Verdict = true;
            Score = (Score + 1) % mQuestionBank.length;
        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_toast, Toast.LENGTH_LONG).show();
            Verdict = false;
        }

        Index = (Index + 1) % (mQuestionBank.length);

        if (Index == 0) {

            TextView scoreView = findViewById(R.id.score);
            scoreView.setText("Score " + Score + "/" + mQuestionBank.length);

            progress = findViewById(R.id.progress_bar);
            progress.incrementProgressBy((int) Math.ceil(100.0 / mQuestionBank.length));

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setCancelable(false);
            alert.setMessage("You Scored " + Score + " Points!");
            alert.setTitle("Quiz Over");
            alert.setNegativeButton("Restart", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Score = 0;
                    Index = 0;
                    progress.setProgress(0);

                    showCurrentQuestion();
                }
            });

            alert.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            alert.show();
        } else {
            showNextQuestion();
        }


    }

    void showNextQuestion() {
        TextView textView = findViewById(R.id.question_text_view);
        textView.setText(mQuestionBank[Index].getQuestionId());

        TextView scoreView = findViewById(R.id.score);
        scoreView.setText("Score " + Score + "/" + mQuestionBank.length);

        progress = findViewById(R.id.progress_bar);
        progress.incrementProgressBy((int) Math.ceil(100.0 / mQuestionBank.length));

    }

    void showCurrentQuestion()
    {
        TextView textView = findViewById(R.id.question_text_view);
        textView.setText(mQuestionBank[Index].getQuestionId());

        TextView scoreView = findViewById(R.id.score);
        scoreView.setText("Score " + Score + "/" + mQuestionBank.length);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("scoreKey", Score);
        outState.putInt("indexKey", Index);
    }
}
