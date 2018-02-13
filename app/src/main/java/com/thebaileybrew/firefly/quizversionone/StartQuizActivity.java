package com.thebaileybrew.firefly.quizversionone;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

/**
 * Created by grumnb on 2/8/2018.
 */

public class StartQuizActivity extends FragmentActivity {

    private android.support.v4.view.PagerAdapter mPagerAdapter;

    int currentScore = 0;
    Button questionOneSubmit;
    Button questionTwoSubmit;
    Button questionThreeSubmit;
    Button questionFourSubmit;
    ProgressBar questionProgress;

    //Question One Radio Buttons
    RadioGroup fragmentQuestionOne;
    RadioButton questionOneAnswerOne;
    RadioButton questionOneAnswerTwo;
    RadioButton questionOneAnswerThree;
    RadioButton questionOneAnswerFour;

    //Question Two Edit Text Field
    EditText editTextQuestionTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_quiz_start);

        this.initializePaging();

    }


    private void initializePaging() {
        List<Fragment> fragments = new Vector<>();
        fragments.add(Fragment.instantiate(this, FragmentOne.class.getName()));
        fragments.add(Fragment.instantiate(this, FragmentTwo.class.getName()));
        fragments.add(Fragment.instantiate(this, FragmentThree.class.getName()));
        fragments.add(Fragment.instantiate(this, FragmentFour.class.getName()));
        this.mPagerAdapter  = new com.thebaileybrew.firefly.quizversionone.PagerAdapter(super.getSupportFragmentManager(), fragments);
        //
        ViewPager pager = (ViewPager)super.findViewById(R.id.viewPager);
        pager.setAdapter(this.mPagerAdapter);
    }

    public class ProgressBarAnimation extends Animation {
        private ProgressBar progressBar;
        private float from;
        private float to;

        public ProgressBarAnimation(ProgressBar progressBar, float from, float to) {
            super();
            this.progressBar = progressBar;
            this.from = from;
            this.to = to;
        }
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            float value = from + (to - from) * interpolatedTime;
            progressBar.setProgress((int) value);
        }
    }

    public void onClickSubmitQuestionOne(View view) {
        fragmentQuestionOne = findViewById(R.id.questionOneRadioGroup);
        questionOneAnswerOne = findViewById(R.id.question_one_a1);
        questionOneAnswerTwo = findViewById(R.id.question_one_a2);
        questionOneAnswerThree = findViewById(R.id.question_one_a3);
        questionOneAnswerFour = findViewById(R.id.question_one_a4);

        switch (fragmentQuestionOne.getCheckedRadioButtonId()) {
            case R.id.question_one_a1:
                //Correct
                Toast.makeText(getApplicationContext(), "Correct. Malcolm Reynolds is the captain.", Toast.LENGTH_LONG).show();
                currentScore += 1;
                break;
            case R.id.question_one_a2:
                //Incorrect
                Toast.makeText(getApplicationContext(), "Incorrect. River Tam is a special stowaway.", Toast.LENGTH_LONG).show();
                break;
            case R.id.question_one_a3:
                //Incorrect
                Toast.makeText(getApplicationContext(), "Incorrect. Jayne Cobb is a hired gun/mercenary.", Toast.LENGTH_LONG).show();
                break;
            case R.id.question_one_a4:
                //Incorrect
                Toast.makeText(getApplicationContext(), "Incorrect. Zoe Washborne is the first mate.", Toast.LENGTH_LONG).show();
                break;
            default:
                //No Selection
                Toast.makeText(getApplicationContext(), "Please make a selection", Toast.LENGTH_LONG).show();
                break;
        }
        //Update the Score Display
        displayCurrentScore(currentScore);
        //Fade the Submit Button
        questionOneSubmit = findViewById(R.id.submit_answer_question_one);
        questionOneSubmit.setClickable(false);
        questionOneSubmit.setText("Answer Submitted");
        questionOneSubmit.setTextColor(getResources().getColor(R.color.grayFadeD));
        //Animate the Progress Bar
        questionProgress = findViewById(R.id.progressBar);
        ProgressBarAnimation anim = new ProgressBarAnimation(questionProgress, 0, 600);
        anim.setDuration(3000);
        questionProgress.startAnimation(anim);
        //Auto-advance the Page Viewer
        final ViewPager pager = (ViewPager)super.findViewById(R.id.viewPager);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pager.setCurrentItem(1,true);
            }
        }, 3000);

    }


    public void onClickSubmitQuestionTwo (View view) {
        editTextQuestionTwo = findViewById(R.id.question_two_answerEditText);
        String questionTwoAnswer = editTextQuestionTwo.getText().toString();
        Log.i(questionTwoAnswer, "User entered text is:");
        if (questionTwoAnswer.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter a ship classification", Toast.LENGTH_LONG).show();
        } else {
            switch (questionTwoAnswer) {
                case "Firefly":
                    //Correct
                    currentScore += 1;
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "FIREFLY":
                    //Correct
                    currentScore += 1;
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "Fire Fly":
                    //Correct
                    currentScore += 1;
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "Fire fly":
                    //Correct
                    currentScore += 1;
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "FIRE FLY":
                    //Correct
                    currentScore += 1;
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "fire fly":
                    //Correct
                    currentScore += 1;
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "firefly":
                    //Correct
                    currentScore += 1;
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "FireFly":
                    //Correct
                    currentScore += 1;
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "The correct answer is Firefly", Toast.LENGTH_LONG).show();
                    break;
            }
        }

        questionTwoSubmit = findViewById(R.id.submit_answer_question_two);
        questionTwoSubmit.setClickable(false);
        questionTwoSubmit.setText("Answer Submitted");
        questionTwoSubmit.setTextColor(getResources().getColor(R.color.grayFadeD));

        questionProgress = findViewById(R.id.progressBar);
        ProgressBarAnimation anim = new ProgressBarAnimation(questionProgress, 600, 1200);
        anim.setDuration(3000);
        questionProgress.startAnimation(anim);

        final ViewPager pager = (ViewPager)super.findViewById(R.id.viewPager);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pager.setCurrentItem(2,true);
            }
        }, 3000);
    }




    public void displayCurrentScore (int score) {
        TextView currentScoreTV = findViewById(R.id.currentScoreDisplay);
        score = score * 10;
        currentScoreTV.setText(String.valueOf(score));
    }
}

