package com.thebaileybrew.firefly.quizversionone;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

import static android.view.View.GONE;

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

    //Question Three Display Views
    SwitchCompat questionThreeAnswerOne;
    SwitchCompat questionThreeAnswerTwo;
    SwitchCompat questionThreeAnswerThree;
    SwitchCompat questionThreeAnswerFour;
    TextView questionThreeTextView;
    LinearLayout questionThreeLinear;
    TextView questionThreeExtraCredit;
    TextView questionThreeECTextView;
    RadioGroup questionThreeRadioGroup;
    RadioGroup fragmentQuestionThree;
    RadioButton questionThreeAnswerOneEC;
    RadioButton questionThreeAnswerTwoEC;
    RadioButton questionThreeAnswerThreeEC;
    RadioButton questionThreeAnswerFourEC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_quiz_start);

        this.initializePaging();

        ViewPager pager = (ViewPager)super.findViewById(R.id.viewPager);
        if (pager.getCurrentItem() == 2) {
            //Creates the onClickListener for Question Three only if Fragment Three is currently active
            questionThreeAnswerOne = findViewById(R.id.question_three_a1);
            questionThreeAnswerTwo = findViewById(R.id.question_three_a2);
            questionThreeAnswerThree = findViewById(R.id.question_three_a3);
            questionThreeAnswerFour = findViewById(R.id.question_three_a4);

        }

    }


    public void initializePaging() {
        List<Fragment> fragments = new Vector<>();
        fragments.add(Fragment.instantiate(this, FragmentOne.class.getName()));
        fragments.add(Fragment.instantiate(this, FragmentTwo.class.getName()));
        fragments.add(Fragment.instantiate(this, FragmentThree.class.getName()));
        fragments.add(Fragment.instantiate(this, FragmentFour.class.getName()));
        mPagerAdapter = new com.thebaileybrew.firefly.quizversionone.PagerAdapter(super.getSupportFragmentManager(), fragments);
        //
        ViewPager pager = (ViewPager) super.findViewById(R.id.viewPager);
        pager.setAdapter(this.mPagerAdapter);
    }








    @Override
    public void onBackPressed() {
        final ViewPager pager = (ViewPager)super.findViewById(R.id.viewPager);
        if (pager.getCurrentItem() == 0) {
            //Defines what to do if the user presses the back button and is currently on the first fragment page
            //This calls finish() on the activity and returns
            super.onBackPressed();
        } else {
            //Otherwise, current page will rotate one back
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
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
                currentScore += 10;
                break;
            case R.id.question_one_a2:
                //Incorrect
                Toast.makeText(getApplicationContext(), "Incorrect. River Tam is a special stowaway.", Toast.LENGTH_LONG).show();
                currentScore -= 5;
                break;
            case R.id.question_one_a3:
                //Incorrect
                Toast.makeText(getApplicationContext(), "Incorrect. Jayne Cobb is a hired gun/mercenary.", Toast.LENGTH_LONG).show();
                currentScore -= 5;
                break;
            case R.id.question_one_a4:
                //Incorrect
                Toast.makeText(getApplicationContext(), "Incorrect. Zoe Washborne is the first mate.", Toast.LENGTH_LONG).show();
                currentScore -=5;
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
                pager.setCurrentItem(pager.getCurrentItem()+1,true);
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
                    currentScore += 10;
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "FIREFLY":
                    //Correct
                    currentScore += 10;
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "Fire Fly":
                    //Correct
                    currentScore += 10;
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "Fire fly":
                    //Correct
                    currentScore += 10;
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "FIRE FLY":
                    //Correct
                    currentScore += 10;
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "fire fly":
                    //Correct
                    currentScore += 10;
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "firefly":
                    //Correct
                    currentScore += 10;
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
        //Update the Score Display
        displayCurrentScore(currentScore);
        //Fade the Submit Button
        questionTwoSubmit = findViewById(R.id.submit_answer_question_two);
        questionTwoSubmit.setClickable(false);
        questionTwoSubmit.setText("Answer Submitted");
        questionTwoSubmit.setTextColor(getResources().getColor(R.color.grayFadeD));
        //Animate the progress bar
        questionProgress = findViewById(R.id.progressBar);
        ProgressBarAnimation anim = new ProgressBarAnimation(questionProgress, 600, 1200);
        anim.setDuration(3000);
        questionProgress.startAnimation(anim);
        //Auto-advance the Page Viewer
        final ViewPager pager = (ViewPager)super.findViewById(R.id.viewPager);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pager.setCurrentItem(pager.getCurrentItem()+1, true);
            }
        }, 3000);
    }

    public void onClickSubmitQuestionThree (View view) {
        questionThreeAnswerOne = findViewById(R.id.question_three_a1);
        questionThreeAnswerTwo = findViewById(R.id.question_three_a2);
        questionThreeAnswerThree = findViewById(R.id.question_three_a3);
        questionThreeAnswerFour = findViewById(R.id.question_three_a4);

        SwitchCompat.OnCheckedChangeListener switchListenerQuestionThree = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                switch (compoundButton.getId()) {
                    case R.id.question_three_a1:
                        Toast.makeText(getApplicationContext(), "Correct Lux is one of Jayne's guns", Toast.LENGTH_LONG).show();
                        currentScore += 5;
                        questionThreeAnswerOne.setClickable(false);
                        break;
                    case R.id.question_three_a2:
                        Toast.makeText(getApplicationContext(), "Incorrect Lucille is not one of Jayne's guns", Toast.LENGTH_LONG).show();
                        currentScore -= 5;
                        questionThreeAnswerTwo.setClickable(false);
                        break;
                    case R.id.question_three_a3:
                        Toast.makeText(getApplicationContext(), "Correct Vera is one of Jayne's guns", Toast.LENGTH_LONG).show();
                        currentScore += 5;
                        questionThreeAnswerThree.setClickable(false);
                        break;
                    case R.id.question_three_a4:
                        Toast.makeText(getApplicationContext(), "Incorrect Adriana is not one of Jayne's guns", Toast.LENGTH_LONG).show();
                        currentScore -= 5;
                        questionThreeAnswerFour.setClickable(false);
                        break;
                }

            }

        };
        questionThreeAnswerOne.setOnCheckedChangeListener(switchListenerQuestionThree);
        questionThreeAnswerTwo.setOnCheckedChangeListener(switchListenerQuestionThree);
        questionThreeAnswerThree.setOnCheckedChangeListener(switchListenerQuestionThree);
        questionThreeAnswerFour.setOnCheckedChangeListener(switchListenerQuestionThree);



        if (questionThreeAnswerOne.isChecked() && questionThreeAnswerFour.isChecked()) {
            Toast.makeText(getApplicationContext(), "Correct Lux and Vera are Jayne's guns.", Toast.LENGTH_LONG).show();
            questionThreeTextView = findViewById(R.id.question_three_text);
            questionThreeTextView.setVisibility(GONE);
            questionThreeLinear = findViewById(R.id.question_three_linear_switch_display);
            questionThreeLinear.setVisibility(GONE);
            questionThreeExtraCredit = findViewById(R.id.question_three_extra_credit);
            questionThreeExtraCredit.setVisibility(View.VISIBLE);
            questionThreeECTextView = findViewById(R.id.question_three_extra_credit_text);
            questionThreeECTextView.setVisibility(View.VISIBLE);
            questionThreeRadioGroup = findViewById(R.id.questionThreeExtraCreditRadioGroup);
            questionThreeRadioGroup.setVisibility(View.VISIBLE);
        }
        //Update the Score Display
        displayCurrentScore(currentScore);
        //Animate the progress bar
        questionProgress = findViewById(R.id.progressBar);
        ProgressBarAnimation anim = new ProgressBarAnimation(questionProgress, 1800, 2400);
        anim.setDuration(3000);
        questionProgress.startAnimation(anim);
        //Validate that correct answers are chosen


    }

    public void onClickSubmitQuestionThreeExtraCredit(View view) {
        fragmentQuestionThree = findViewById(R.id.questionThreeExtraCreditRadioGroup);
        questionThreeAnswerOneEC = findViewById(R.id.question_threeEC_a1);
        questionThreeAnswerTwoEC = findViewById(R.id.question_threeEC_a2);
        questionThreeAnswerThreeEC = findViewById(R.id.question_threeEC_a3);
        questionThreeAnswerFourEC = findViewById(R.id.question_threeEC_a4);

        switch (fragmentQuestionOne.getCheckedRadioButtonId()) {
            case R.id.question_one_a1:
                //Correct
                Toast.makeText(getApplicationContext(), "Correct. Malcolm Reynolds is the captain.", Toast.LENGTH_LONG).show();
                currentScore += 10;
                break;
            case R.id.question_one_a2:
                //Incorrect
                Toast.makeText(getApplicationContext(), "Incorrect. River Tam is a special stowaway.", Toast.LENGTH_LONG).show();
                currentScore -= 5;
                break;
            case R.id.question_one_a3:
                //Incorrect
                Toast.makeText(getApplicationContext(), "Incorrect. Jayne Cobb is a hired gun/mercenary.", Toast.LENGTH_LONG).show();
                currentScore -= 5;
                break;
            case R.id.question_one_a4:
                //Incorrect
                Toast.makeText(getApplicationContext(), "Incorrect. Zoe Washborne is the first mate.", Toast.LENGTH_LONG).show();
                currentScore -= 5;
                break;
            default:
                //No Selection
                Toast.makeText(getApplicationContext(), "Please make a selection", Toast.LENGTH_LONG).show();
                break;
        }

        //Auto-advance the Page Viewer
        final ViewPager pager = (ViewPager) super.findViewById(R.id.viewPager);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pager.setCurrentItem(pager.getCurrentItem() + 1, true);
            }
        }, 3000);
    }





    public void displayCurrentScore (int score) {
        TextView currentScoreTV = findViewById(R.id.currentScoreDisplay);
        currentScoreTV.setText(String.valueOf(score));
    }
}

