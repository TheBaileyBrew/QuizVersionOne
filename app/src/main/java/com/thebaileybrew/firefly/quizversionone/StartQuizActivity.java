package com.thebaileybrew.firefly.quizversionone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
        findViewById(R.id.submit_answer_question_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentQuestionOne = findViewById(R.id.questionOneRadioGroup);
                questionOneAnswerOne = findViewById(R.id.question_one_a1);
                questionOneAnswerTwo = findViewById(R.id.question_one_a2);
                questionOneAnswerThree = findViewById(R.id.question_one_a3);
                questionOneAnswerFour = findViewById(R.id.question_one_a4);

                switch (fragmentQuestionOne.getCheckedRadioButtonId()) {
                    case R.id.question_one_a1:
                        //Correct
                        Toast.makeText(getApplicationContext(), "Correct. Malcolm Reynolds is the captain", Toast.LENGTH_LONG).show();
                        currentScore += 1;
                        Log.i(String.valueOf(currentScore), "Current Score is: ");
                        break;
                    case R.id.question_one_a2:
                        //Incorrect
                        Toast.makeText(getApplicationContext(), "Nope. That's wrong.", Toast.LENGTH_LONG).show();
                        Log.i(String.valueOf(currentScore), "Current Score is: ");
                        break;
                    case R.id.question_one_a3:
                        //Incorrect
                        Toast.makeText(getApplicationContext(), "Not quite.", Toast.LENGTH_LONG).show();
                        Log.i(String.valueOf(currentScore), "Current Score is: ");
                        break;
                    case R.id.question_one_a4:
                        //Incorrect
                        Toast.makeText(getApplicationContext(), "Nice Try.", Toast.LENGTH_LONG).show();
                        Log.i(String.valueOf(currentScore), "Current Score is: ");
                        break;
                    default:
                        //No Selection
                        Toast.makeText(getApplicationContext(), "Please make a selection", Toast.LENGTH_LONG).show();
                        Log.i(String.valueOf(currentScore), "Current Score is: ");
                        break;
                }


            }
        });

    }

    public void onClickSubmitQuestionTwo(View view) {

    }
}

