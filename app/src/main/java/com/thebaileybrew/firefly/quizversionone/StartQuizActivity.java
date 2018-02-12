package com.thebaileybrew.firefly.quizversionone;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    int currentScore;

    //Question One Radio Buttons
    RadioGroup fragmentQuestionOne;
    RadioButton questionOneAnswerOne;
    RadioButton questionOneAnswerTwo;
    RadioButton questionOneAnswerThree;
    RadioButton questionOneAnswerFour;
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
                displayCurrentScore(currentScore);
                Log.i(String.valueOf(currentScore), "Current Score is: ");
                break;
            case R.id.question_one_a2:
                //Incorrect
                Toast.makeText(getApplicationContext(), "River is the special stowaway.", Toast.LENGTH_LONG).show();
                Log.i(String.valueOf(currentScore), "Current Score is: ");
                break;
            case R.id.question_one_a3:
                //Incorrect
                Toast.makeText(getApplicationContext(), "Jayne is a hired gun/mercenary.", Toast.LENGTH_LONG).show();
                Log.i(String.valueOf(currentScore), "Current Score is: ");
                break;
            case R.id.question_one_a4:
                //Incorrect
                Toast.makeText(getApplicationContext(), "Zoe is the First Mate.", Toast.LENGTH_LONG).show();
                Log.i(String.valueOf(currentScore), "Current Score is: ");
                break;
            default:
                //No Selection
                Toast.makeText(getApplicationContext(), "Please make a selection", Toast.LENGTH_LONG).show();
                Log.i(String.valueOf(currentScore), "Current Score is: ");
                break;
        }
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
                case "FIRE FLY":
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
        currentScoreTV.setText(String.valueOf(score));
    }
}

