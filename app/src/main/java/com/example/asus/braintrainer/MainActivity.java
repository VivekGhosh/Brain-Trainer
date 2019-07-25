package com.example.asus.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button button1,button2,button3,button4;
    Button playAgainButton;
    TextView resultTextView, sumTextView, scoreTextView, timerTextView;
    RelativeLayout gameRelativeLayout;


    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;


    public void playAgain(View view)
    {
        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        scoreTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish()
            {
                timerTextView.setText("0s");
                resultTextView.setText("Your score: " + Integer.toString(score) + "/"
                            + Integer.toString(numberOfQuestions));

                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }


    public void generateQuestion()
    {
        answers.clear();

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        int incorrectAnswer;

        for(int i=0;i<4;i++)
        {
            if(i == locationOfCorrectAnswer)
            {
                answers.add(a+b);
            }
            else
            {
                incorrectAnswer = rand.nextInt(41);

                while(incorrectAnswer == (a+b))
                    incorrectAnswer = rand.nextInt(41);

                answers.add(incorrectAnswer);
            }
        }

        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
            score++;
            resultTextView.setText("Correct!");
        }
        else
        {
            resultTextView.setText("Wrong!");
        }

        numberOfQuestions++;

        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));

        generateQuestion();

    }

    public void start(View view)
    {
        startButton.setVisibility(View.INVISIBLE);

        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);

        sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameRelativeLayout = findViewById(R.id.gameRelativeLayout);

    }
}
