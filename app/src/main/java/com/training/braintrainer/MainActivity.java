package com.training.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GridLayout startGame;
    LinearLayout game;
    TextView timer;
    TextView calculation;
    TextView score;
    TextView state;
    Button play_again;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    int right_score = 0;
    int score_number = 0;
    int timer_counter = 30;
    int answer = 0;
    int value1 = 0;
    int value2 = 0;
    String question;
    Random rand = new Random();
    ArrayList<Integer> answers = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGame = findViewById(R.id.startGame);
        game = findViewById(R.id.game);
        timer = findViewById(R.id.timer);
        calculation = findViewById(R.id.calculation);
        score = findViewById(R.id.score);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        state = findViewById(R.id.state);
        play_again = findViewById(R.id.play_again);
    }

    public void setQuestion() {
        value1 = rand.nextInt(51);
        value2 = rand.nextInt(51);
        answer = value1 + value2;
        question = value1 + " + " + value2;
        calculation.setText(question);
    }

    public void answer(View view) {
        Button buttonPressed = (Button) view;
        int check_answer = Integer.valueOf(buttonPressed.getText().toString());
        if(check_answer == answer) {
            right_score++;
            state.setText("Correct!");
        } else {
            state.setText("Wrong..");
        }
        score_number++;
        score.setText(right_score + " / " +score_number);
        setQuestion();
        setAnswers();
        setTextButton();
    }
    public void setAnswers() {
        int locationRightAnswer = rand.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if(locationRightAnswer == i) {
                answers.add(answer);
            } else {
                value1 = rand.nextInt(51);
                value2 = rand.nextInt(51);
                answers.add(value1 + value2);
            }
        }
    }

    public void setTextButton() {
        button0.setText(String.valueOf(answers.get(0)));
        button1.setText(String.valueOf(answers.get(1)));
        button2.setText(String.valueOf(answers.get(2)));
        button3.setText(String.valueOf(answers.get(3)));
        answers.clear();
    }

    public void start(View view) {
        startGame.setVisibility(View.INVISIBLE);
        game.setVisibility(View.VISIBLE);
        setTimer();
        setQuestion();
        setAnswers();
        setTextButton();
    }

    public void setTimer()
    {
        new CountDownTimer((1000 * timer_counter), 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000) + "s");
                timer_counter--;
            }

            @Override
            public void onFinish() {
                state.setText("Done");
                play_again.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void playAgain(View view) {
        timer_counter = 30;
        setTimer();
        state.setText("");
        play_again.setVisibility(View.INVISIBLE);
        right_score = 0;
        score_number = 0;
        score.setText(right_score + " / " +score_number);
    }
}
