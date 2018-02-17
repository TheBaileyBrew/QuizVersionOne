package com.thebaileybrew.firefly.quizversionone;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import java.util.List;
import java.util.Vector;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by grumnb on 2/8/2018.
 */

public class StartQuizActivity extends FragmentActivity {
    //Define Buttons & Radio Groups
    Button questionOneSubmit;
    Button questionTwoSubmit;
    Button questionThreeSubmit;
    Button questionFourSubmit;
    Button submitChoicesQuestionThree;
    Button showCheckedTextViewsQuestionThree;
    RadioGroup fragmentQuestionOne;
    RadioGroup questionThreeRadioGroup;
    RadioGroup fragmentQuestionThree;
    RadioButton questionOneAnswerOne;
    RadioButton questionOneAnswerTwo;
    RadioButton questionOneAnswerThree;
    RadioButton questionOneAnswerFour;
    RadioButton questionThreeAnswerOneEC;
    RadioButton questionThreeAnswerTwoEC;
    RadioButton questionThreeAnswerThreeEC;
    RadioButton questionThreeAnswerFourEC;

    //Define integer values
    int currentScore = 0;
    int correctTextViews = 0;
    int selectedTextViews = 0;

    //Define float values
    float currentProgress;
    float desiredProgress;

    //Define TextViews
    EditText editTextQuestionTwo;
    TextView questionThreeExtraCredit;
    TextView questionThreeTextView;
    TextView questionThreeECTextView;
    CheckedTextView questionThreeOptionA;
    CheckedTextView questionThreeOptionB;
    CheckedTextView questionThreeOptionC;
    CheckedTextView questionThreeOptionD;
    CheckedTextView questionThreeOptionE;
    CheckedTextView questionThreeOptionF;

    //Define Layouts
    LinearLayout questionThreeLinear;
    LinearLayout linearLayoutQuestionThreeChoices;

    //Miscelaneous Variable Definitions
    private android.support.v4.view.PagerAdapter mPagerAdapter;
    ViewPager pager;
    ProgressBar questionProgress;

    //Video Playing Question Four
    Button playVideo;
    MediaController myVideoController;
    VideoView questionFourVideoViewer;
    String videoPath;
    Uri uriPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_quiz_start);
        //Find View By Definitions
        fragmentQuestionOne = findViewById(R.id.questionOneRadioGroup);
        questionOneAnswerOne = findViewById(R.id.question_one_a1);
        questionOneAnswerTwo = findViewById(R.id.question_one_a2);
        questionOneAnswerThree = findViewById(R.id.question_one_a3);
        questionOneAnswerFour = findViewById(R.id.question_one_a4);
        questionOneSubmit = findViewById(R.id.submit_answer_question_one);
        editTextQuestionTwo = findViewById(R.id.question_two_answerEditText);
        questionTwoSubmit = findViewById(R.id.submit_answer_question_two);
        questionThreeTextView = findViewById(R.id.question_three_text);
        questionThreeExtraCredit = findViewById(R.id.question_three_extra_credit);
        questionThreeECTextView = findViewById(R.id.question_three_extra_credit_text);
        questionThreeRadioGroup = findViewById(R.id.questionThreeExtraCreditRadioGroup);
        questionThreeOptionA = findViewById(R.id.question_three_optionA);
        questionThreeOptionB = findViewById(R.id.question_three_optionB);
        questionThreeOptionC = findViewById(R.id.question_three_optionC);
        questionThreeOptionD = findViewById(R.id.question_three_optionD);
        questionThreeOptionE = findViewById(R.id.question_three_optionE);
        questionThreeOptionF = findViewById(R.id.question_three_optionF);
        linearLayoutQuestionThreeChoices = findViewById(R.id.question_three_linear_switch_display);
        submitChoicesQuestionThree = findViewById(R.id.submit_answer_question_three);
        showCheckedTextViewsQuestionThree = findViewById(R.id.question_three_show_checked_text_views);
        playVideo = findViewById(R.id.start_fragment_four_video);
        questionFourVideoViewer = findViewById(R.id.video_view_fragment_four);
        questionProgress = findViewById(R.id.progressBar);

        this.initializePaging();
    }

    public void initializePaging() {
        //Creates the record list of fragments in the quiz
        List<Fragment> fragments = new Vector<>();
        fragments.add(Fragment.instantiate(this, FragmentOne.class.getName()));
        fragments.add(Fragment.instantiate(this, FragmentTwo.class.getName()));
        fragments.add(Fragment.instantiate(this, FragmentThree.class.getName()));
        fragments.add(Fragment.instantiate(this, FragmentFour.class.getName()));
        fragments.add(Fragment.instantiate(this, FragmentFive.class.getName()));
        fragments.add(Fragment.instantiate(this, FragmentSix.class.getName()));
        fragments.add(Fragment.instantiate(this, FragmentSeven.class.getName()));
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
            //Otherwise, current fragment will rotate one back
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }

    public class ProgressBarAnimation extends Animation {
        //Creates the progress bar animation effect
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
        //switch statement checks for radio id selection, based on id reference in the radio group
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
                Toast.makeText(getApplicationContext(), "You made no selection.", Toast.LENGTH_LONG).show();
                break;
        }
        //Update the Score Display
        displayCurrentScore(currentScore);

        //Fade the Submit Button
        questionOneSubmit.setClickable(false);
        questionOneSubmit.setText("Answer Submitted");
        questionOneSubmit.setTextColor(getResources().getColor(R.color.grayFadeD));

        //Animate the Progress Bar
        currentProgress = questionProgress.getProgress();
        desiredProgress = currentProgress + 600;
        ProgressBarAnimation anim = new ProgressBarAnimation(questionProgress, currentProgress, desiredProgress);
        anim.setDuration(3000);
        questionProgress.startAnimation(anim);

        //Auto-advance the Page Viewer
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pager.setCurrentItem(pager.getCurrentItem()+1,true);
            }
        }, 3000);

    }

    public void onClickSubmitQuestionTwo (View view) {
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
        questionTwoSubmit.setClickable(false);
        questionTwoSubmit.setText("Answer Submitted");
        questionTwoSubmit.setTextColor(getResources().getColor(R.color.grayFadeD));
        //Animate the progress bar
        ProgressBarAnimation anim = new ProgressBarAnimation(questionProgress, 600, 1200);
        anim.setDuration(3000);
        questionProgress.startAnimation(anim);
        //Auto-advance the Page Viewer
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pager.setCurrentItem(pager.getCurrentItem()+1, true);
            }
        }, 3000);
    }

    public void onClickInitiateCheckedTextView (View view) {
        if (showCheckedTextViewsQuestionThree.getText().toString() == "Show Options") {
            linearLayoutQuestionThreeChoices.setVisibility(VISIBLE);
            submitChoicesQuestionThree.setVisibility(VISIBLE);
            showCheckedTextViewsQuestionThree.setText("Hide Options");
        } else {
            linearLayoutQuestionThreeChoices.setVisibility(GONE);
            submitChoicesQuestionThree.setVisibility(GONE);
            showCheckedTextViewsQuestionThree.setText("Show Options");
        }
        questionThreeOptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionThreeOptionA.isChecked()) {
                    //Sets the drawable Checkmark
                    questionThreeOptionA.setCheckMarkDrawable(0);
                    questionThreeOptionA.setChecked(false);
                    Log.i(String.valueOf(questionThreeOptionA.isChecked()), "Option is:");
                } else {
                    questionThreeOptionA.setCheckMarkDrawable(R.drawable.checked);
                    questionThreeOptionA.setChecked(true);
                    Log.i(String.valueOf(questionThreeOptionA.isChecked()), "Option is:");
                }
            }
        });
        questionThreeOptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionThreeOptionB.isChecked()) {
                    //Sets the drawable Checkmark
                    questionThreeOptionB.setCheckMarkDrawable(0);
                    questionThreeOptionB.setChecked(false);
                    Log.i(String.valueOf(questionThreeOptionB.isChecked()), "Option is:");
                } else {
                    questionThreeOptionB.setCheckMarkDrawable(R.drawable.checked);
                    questionThreeOptionB.setChecked(true);
                    Log.i(String.valueOf(questionThreeOptionB.isChecked()), "Option is:");
                }
            }
        });
        questionThreeOptionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionThreeOptionC.isChecked()) {
                    //Sets the drawable Checkmark
                    questionThreeOptionC.setCheckMarkDrawable(0);
                    questionThreeOptionC.setChecked(false);
                } else {
                    questionThreeOptionC.setCheckMarkDrawable(R.drawable.checked);
                    questionThreeOptionC.setChecked(true);
                }
            }
        });
        questionThreeOptionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionThreeOptionD.isChecked()) {
                    //Sets the drawable Checkmark
                    questionThreeOptionD.setCheckMarkDrawable(0);
                    questionThreeOptionD.setChecked(false);
                } else {
                    questionThreeOptionD.setCheckMarkDrawable(R.drawable.checked);
                    questionThreeOptionD.setChecked(true);
                }
            }
        });
        questionThreeOptionE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionThreeOptionE.isChecked()) {
                    //Sets the drawable Checkmark
                    questionThreeOptionE.setCheckMarkDrawable(0);
                    questionThreeOptionE.setChecked(false);
                } else {
                    questionThreeOptionE.setCheckMarkDrawable(R.drawable.checked);
                    questionThreeOptionE.setChecked(true);
                }
            }
        });
        questionThreeOptionF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionThreeOptionF.isChecked()) {
                    //Sets the drawable Checkmark
                    questionThreeOptionF.setCheckMarkDrawable(0);
                    questionThreeOptionF.setChecked(false);
                } else {
                    questionThreeOptionF.setCheckMarkDrawable(R.drawable.checked);
                    questionThreeOptionF.setChecked(true);
                }
            }
        });
    }

    public void onClickSubmitQuestionThree (View view) {
        Boolean A = questionThreeOptionA.isChecked();
        Boolean B = questionThreeOptionB.isChecked();
        Boolean C = questionThreeOptionC.isChecked();
        Boolean D = questionThreeOptionD.isChecked();
        Boolean E = questionThreeOptionE.isChecked();
        Boolean F = questionThreeOptionF.isChecked();
        switch (String.valueOf(A)) {
            case "true":
                //Correct
                correctTextViews += 1;
                selectedTextViews += 1;
                questionThreeOptionA.setCheckMarkDrawable(R.drawable.checked);
                break;
            default:
                //Not selected
                questionThreeOptionA.setCheckMarkDrawable(R.drawable.checked);
                break;
        }
        switch (String.valueOf(B)) {
            case "true":
                //Incorrect
                selectedTextViews += 1;
                questionThreeOptionB.setCheckMarkDrawable(R.drawable.checked_wrong);
                break;
            default:
                //Not selected
                break;
        }
        switch (String.valueOf(C)) {
            case "true":
                //Incorrect
                selectedTextViews += 1;
                questionThreeOptionC.setCheckMarkDrawable(R.drawable.checked_wrong);
                break;
            default:
                //Not selected
                break;
        }
        switch (String.valueOf(D)) {
            case "true":
                //Incorrect
                selectedTextViews += 1;
                questionThreeOptionD.setCheckMarkDrawable(R.drawable.checked_wrong);
                break;
            default:
                //Not selected
                break;
        }
        switch (String.valueOf(E)) {
            case "true":
                //Correct
                correctTextViews += 1;
                selectedTextViews += 1;
                questionThreeOptionE.setCheckMarkDrawable(R.drawable.checked);
                break;
            default:
                //Not selected
                questionThreeOptionE.setCheckMarkDrawable(R.drawable.checked);
                break;
        }
        switch (String.valueOf(F)) {
            case "true":
                //Incorrect
                selectedTextViews += 1;
                questionThreeOptionF.setCheckMarkDrawable(R.drawable.checked_wrong);
                break;
            default:
                //Not selected
                break;
        }

        if (correctTextViews == 2 && selectedTextViews == 2) {
            // Two correct answers plus zero incorrect
            Toast.makeText(this, "You've chosen correctly", Toast.LENGTH_SHORT).show();
            currentScore += 20;
        } else if (correctTextViews == 1 && selectedTextViews >= 2) {
            //One correct answer plus at least one incorrect
            Toast.makeText(this, "You've chosen one select answer", Toast.LENGTH_SHORT).show();
            currentScore += 10;
            currentScore = currentScore - (5*(selectedTextViews - 1));
        } else if (correctTextViews == 2 && selectedTextViews > 2) {
            //Two Correct answers plus at least one incorrect
            Toast.makeText(this, "You've chosen the correct answers plus a few wrong", Toast.LENGTH_SHORT).show();
            currentScore += 10;
            currentScore = currentScore - (5*(selectedTextViews - 1));
        } else if (correctTextViews == 0 && selectedTextViews >= 1) {
            //Zero correct answers plus at least one incorrect
            Toast.makeText(this, "You've selected zero correct answers", Toast.LENGTH_SHORT).show();
            currentScore = currentScore - (5*(selectedTextViews - 1));
        } else if (correctTextViews == 0 && selectedTextViews == 0) {
            //Zero correct answers selected plus zero incorrect
            Toast.makeText(this, "You've made no selection", Toast.LENGTH_SHORT).show();
        }

        submitChoicesQuestionThree.setVisibility(GONE);
        showCheckedTextViewsQuestionThree.setVisibility(GONE);

        //Update the Score Display
        displayCurrentScore(currentScore);
        //Animate the progress bar
        ProgressBarAnimation anim = new ProgressBarAnimation(questionProgress, 1800, 2400);
        anim.setDuration(3000);
        questionProgress.startAnimation(anim);

        if (correctTextViews >= 1) {
            //Advance to Extra Credit Question
        } else {
            //Advance to Fragment Four
            //Auto-advance the Page Viewer
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pager.setCurrentItem(pager.getCurrentItem()+1,true);
                }
            }, 3000);
        }

    }

    public void advanceToExtraCredit (View view) {
        //Set Question to GONE
        submitChoicesQuestionThree.setVisibility(GONE);
        showCheckedTextViewsQuestionThree.setVisibility(GONE);
        questionThreeTextView.setVisibility(GONE);
        questionThreeLinear.setVisibility(GONE);
        //Set Extra Credit to VISIBLE
        questionThreeExtraCredit.setVisibility(VISIBLE);
        questionThreeECTextView.setVisibility(VISIBLE);
        questionThreeRadioGroup.setVisibility(VISIBLE);
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
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pager.setCurrentItem(pager.getCurrentItem() + 1, true);
            }
        }, 3000);
    }

    public void onClickStartThemeVideo(View view) {
        videoPath = "android.resource://" + getPackageName()+ "/" + R.raw.fireflyopening;
        uriPath = Uri.parse(videoPath);
        myVideoController = new MediaController(this);
        //Prepare the video
        setupMedia();
        setupListeners();
    }

    private void setupMedia() {
       questionFourVideoViewer.setMediaController(myVideoController);
       questionFourVideoViewer.setVideoURI(uriPath);
    }

    private void setupListeners() {
    }

    public void onClickSubmitQuestionFour (View view) {
    }

    public void displayCurrentScore (int score) {
        TextView currentScoreTV = findViewById(R.id.currentScoreDisplay);
        currentScoreTV.setText(String.valueOf(score));
    }
}


