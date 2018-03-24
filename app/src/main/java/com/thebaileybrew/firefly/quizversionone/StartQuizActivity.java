package com.thebaileybrew.firefly.quizversionone;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.CheckBox;
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

import com.gigamole.navigationtabstrip.NavigationTabStrip;

import java.util.List;
import java.util.Vector;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by grumnb on 2/8/2018.
 */

public class StartQuizActivity extends FragmentActivity {
    private final static String TAG = "QuizActivity";

    //Boolean values for questions submitted
    Boolean questionOne;
    Boolean questionTwo;
    Boolean questionThree;
    Boolean questionFour;
    Boolean questionFive;
    Boolean questionSix;
    Boolean questionSeven;

    //Define Buttons & Radio Groups
    Button questionOneSubmit;
    Button questionTwoSubmit;
    Button questionFourSubmit;
    Button questionFiveSubmit;
    Button questionSixSubmit;
    Button submitChoicesQuestionThree;
    Button showCheckedTextViewsQuestionThree;
    Button questionThreeECSubmit;
    RadioGroup fragmentQuestionOne;
    RadioGroup questionThreeRadioGroup;
    RadioGroup fragmentQuestionThree;
    RadioGroup fragmentQuestionFour;
    RadioGroup fragmentQuestionFive;
    RadioButton questionOneAnswerOne;
    RadioButton questionOneAnswerTwo;
    RadioButton questionOneAnswerThree;
    RadioButton questionOneAnswerFour;
    RadioButton questionThreeAnswerOneEC;
    RadioButton questionThreeAnswerTwoEC;
    RadioButton questionThreeAnswerThreeEC;
    RadioButton questionThreeAnswerFourEC;
    RadioButton questionFourAnswerOne;
    RadioButton questionFourAnswerTwo;
    RadioButton questionFourAnswerThree;
    RadioButton questionFourAnswerFour;
    RadioButton questionFiveAnswerOne;
    RadioButton questionFiveAnswerTwo;
    RadioButton questionFiveAnswerThree;
    RadioButton questionFiveAnswerFour;


    //Define integer values
    int currentScore = 0;
    int correctTextViews = 0;
    int selectedTextViews = 0;
    int correctCB = 0;
    int selectedCB = 0;

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

    //Define Checkboxes
    CheckBox questionSixOptionOne;
    CheckBox questionSixOptionTwo;
    CheckBox questionSixOptionThree;
    CheckBox questionSixOptionFour;
    CheckBox questionSixOptionFive;
    CheckBox questionSixOptionSix;

    //Define Layouts
    LinearLayout questionThreeLinear;
    LinearLayout linearLayoutQuestionThreeChoices;

    //Miscelaneous Variable Definitions
    private android.support.v4.view.PagerAdapter mPagerAdapter;
    NavigationTabStrip navigationTab;
    ViewPager pager;
    ProgressBar questionProgress;

    //Video Playing Question Four
    Button onClickPlay;
    Button onClickStop;
    MediaController myVideoController;
    VideoView questionFourVideoViewer;
    String onClickText;
    String videoPath;
    Uri uriPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_quiz_start);

        //Declare all questions unanswered at activity start
        questionOne = false;
        questionTwo = false;
        questionThree = false;
        questionFour = false;
        questionFive = false;
        questionSix = false;
        questionSeven = false;

        //initialize the progress bar to be called by any FRAGMENT page
        questionProgress = findViewById(R.id.progressBar);
        this.initializePaging();

        //initialize the navigation tab to partner with the ViewPager
        navigationTab = findViewById(R.id.navigation_question_tab);
        navigationTab.setTabIndex(0);
    }

    /*
    The initializePaging method is called to define the ViewPager Fragments
    Each fragment is instantiated separately and managed by the PagerAdapter and NavigationTab
    */
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
        /*
        New fragments must be added and instantiated here.
        For each new fragment, a new reference must be added to the Questions String-Array
        The new String-Array reference adds a selection to the NavigationTab
        Initial creation of the Pager Adapter to support Fragments
        */

        //initialize the ViewPager and set the adapter to use the fragments that are instantiated
        mPagerAdapter = new com.thebaileybrew.firefly.quizversionone.PagerAdapter(super.getSupportFragmentManager(), fragments);
        ViewPager pager = (ViewPager) super.findViewById(R.id.viewPager);
        pager.setAdapter(this.mPagerAdapter);

        //Adds the OnPageChangeListener to the ViewPager
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            public void onPageSelected(int position) {
                navigationTab.setTabIndex(position);
                //Checks for the current position and updates submit buttons if question has been answered
                switch (position) {
                    case 0:
                        //Fragment One
                        if (questionOne) {
                        }
                        break;
                    case 1:
                        //Fragment Two
                        if (questionTwo) {
                        }
                        break;
                    case 2:
                        //Fragment Three
                        if (questionThree) {
                        }
                        break;
                    case 3:
                        //Fragment Four
                        if (questionFour) {
                        }
                        break;
                    case 4:
                        //Fragment Five
                        if (questionFive) {
                        }
                        break;
                    case 5:
                        //Fragment Six
                        if (questionSix) {
                        }
                        break;
                    case 6:
                        //Fragment Seven
                        if (questionSeven) {
                        }
                        break;
                }
            }
        });
    }

    //This method animates the Progress Bar and auto-advances the ViewPager and Navigation Tab
    public void updateViewPagerNavigationAndAnimation() {
        //Animate the progress bar
        currentProgress = questionProgress.getProgress();
        desiredProgress = currentProgress + 600;
        ProgressBarAnimation anim = new ProgressBarAnimation(questionProgress, currentProgress, desiredProgress);
        anim.setDuration(2500);
        questionProgress.startAnimation(anim);

        //Auto-advance the Page Viewer
        final ViewPager pager = (ViewPager) super.findViewById(R.id.viewPager);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                pager.setCurrentItem(pager.getCurrentItem()+1, true);
                navigationTab.setTabIndex(navigationTab.getTabIndex()+1);
            }
        }, 2500);
    }

    //This method sets the action taken when the back button is pressed
    @Override
    public void onBackPressed() {
        final ViewPager pager = (ViewPager)super.findViewById(R.id.viewPager);
        if (pager.getCurrentItem() == 0) {
            //If current page is the first question the finish() method is called on the activity
            super.onBackPressed();
        } else {
            //If current fragment is any other page, then fragment is switched one previous
            //Can be called numerous times depending on what fragment is available
            pager.setCurrentItem(pager.getCurrentItem() - 1);
            navigationTab.setTabIndex(navigationTab.getTabIndex() - 1);
        }
    }

    // Commenting out the Day/Night Mode switch for publication purposes
    // public void onClickSwitchDayNightMode(View view) {
    //    LinearLayout mainDisplay = findViewById(R.id.standard_display_activity_start);
    //    if (mainDisplay.getTag() == "Night") {
    //        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    //        mainDisplay.setBackgroundColor(getResources().getColor(R.color.colorPrimaryWhite));
    //        mainDisplay.setTag("Day");
    //    } else {
    //        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    //        mainDisplay.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    //        mainDisplay.setTag("Night");
    //    }
    // }

    //This method defines the animation process for the progress bar
    public class ProgressBarAnimation extends Animation {
        private ProgressBar progressBar;
        private float from;
        private float to;
        //Initialize the progress bar animation to transition (from - to)
        public ProgressBarAnimation(ProgressBar progressBar, float from, float to) {
            super();
            this.progressBar = progressBar;
            this.from = from;
            this.to = to;
        }
        //Applies the given transition across a predefined time to give the progress bar the feel of movement
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            float value = from + (to - from) * interpolatedTime;
            progressBar.setProgress((int) value);
        }
    }


    //Processing Details for FRAGMENT ONE - onClick Method for SUBMIT
    public void onClickSubmitQuestionOne(View view) {
        //Defines the location of question one radio group/buttons and defines the String counterpart to the Radio Buttons
        fragmentQuestionOne = findViewById(R.id.questionOneRadioGroup);
        questionOneAnswerOne = findViewById(R.id.question_one_a1);
        String questionOneAnswerOneStringValue = questionOneAnswerOne.getText().toString();
        questionOneAnswerTwo = findViewById(R.id.question_one_a2);
        String questionOneAnswerTwoStringValue = questionOneAnswerTwo.getText().toString();
        questionOneAnswerThree = findViewById(R.id.question_one_a3);
        String questionOneAnswerThreeStringValue = questionOneAnswerThree.getText().toString();
        questionOneAnswerFour = findViewById(R.id.question_one_a4);
        String questionOneAnswerFourStringValue = questionOneAnswerFour.getText().toString();
        questionOneSubmit = findViewById(R.id.submit_answer_question_one);
        /*
        This switch statement verifies which radio button is selected
        Each case that returns will apply scoring
        and change resource colors of the selected and correct radio button text
        */
        switch (fragmentQuestionOne.getCheckedRadioButtonId()) {
            case R.id.question_one_a1:
                Toast.makeText(getApplicationContext(), "Correct. " + questionOneAnswerOneStringValue + " is the captain.", Toast.LENGTH_LONG).show();
                questionOneAnswerOne.setTextColor(getResources().getColor(R.color.colorAccent));
                currentScore = currentScore + 10;
                break;
            case R.id.question_one_a2:
                Toast.makeText(getApplicationContext(), "Incorrect. " + questionOneAnswerTwoStringValue + " is a special stowaway.", Toast.LENGTH_LONG).show();
                questionOneAnswerOne.setTextColor(getResources().getColor(R.color.colorAccent));
                questionOneAnswerTwo.setTextColor(getResources().getColor(R.color.incorrectRed));
                currentScore = currentScore - 5;
                break;
            case R.id.question_one_a3:
                Toast.makeText(getApplicationContext(), "Incorrect. " + questionOneAnswerThreeStringValue + " is a hired gun/mercenary.", Toast.LENGTH_LONG).show();
                questionOneAnswerOne.setTextColor(getResources().getColor(R.color.colorAccent));
                questionOneAnswerThree.setTextColor(getResources().getColor(R.color.incorrectRed));
                currentScore = currentScore - 5;
                break;
            case R.id.question_one_a4:
                Toast.makeText(getApplicationContext(), "Incorrect. " + questionOneAnswerFourStringValue + " is the first mate.", Toast.LENGTH_LONG).show();
                questionOneAnswerOne.setTextColor(getResources().getColor(R.color.colorAccent));
                questionOneAnswerFour.setTextColor(getResources().getColor(R.color.incorrectRed));
                currentScore = currentScore - 5;
                break;
            default:
                /*
                If the user makes no selection, but presses the submit button
                Then a Toast will be display and no points submitted or subtracted
                */
                Toast.makeText(getApplicationContext(), "You made no selection.", Toast.LENGTH_LONG).show();
                break;
        }
        //Method call to update the score display
        displayCurrentScore(currentScore);

        //Fade the submit button and set to unclickable
        questionOneSubmit.setClickable(false);
        questionOneSubmit.setText("Answer Submitted");
        questionOneSubmit.setTextColor(getResources().getColor(R.color.grayFadeD));

        /*
        Calls the method to update Nav Tab, ViewPager and Progress Bar
        Set the Boolean for Question 1 to true (answered)
        */
        questionOne = true;
        updateViewPagerNavigationAndAnimation();
    }


    //Processing Details for FRAGMENT TWO - onClick Method for SUBMIT
    public void onClickSubmitQuestionTwo (View view) {
        editTextQuestionTwo = findViewById(R.id.question_two_answerEditText);
        questionTwoSubmit = findViewById(R.id.submit_answer_question_two);
        String questionTwoAnswer = editTextQuestionTwo.getText().toString();
        Log.i(questionTwoAnswer, "User entered text is:");
        /*
        Checks to see if text was entered.
        If text is empty then display a a toast message and allow user to try again
        */
        if (questionTwoAnswer.equals("")) {
            Toast.makeText(getApplicationContext(), "Please enter a ship classification", Toast.LENGTH_LONG).show();
            return;
        } else {
            /*
            Switch case validates possible answers for the Edit Text
            **Personal Note** I took the liberty of adding a few potential entry styles
            */
            switch (questionTwoAnswer) {
                case "Firefly":
                    //Correct
                    currentScore = currentScore + 10;
                    editTextQuestionTwo.setTextColor(getResources().getColor(R.color.colorAccent));
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "FIREFLY":
                    //Correct
                    currentScore = currentScore + 10;
                    editTextQuestionTwo.setTextColor(getResources().getColor(R.color.colorAccent));
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "Fire Fly":
                    //Correct
                    currentScore = currentScore + 10;
                    editTextQuestionTwo.setTextColor(getResources().getColor(R.color.colorAccent));
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "Fire fly":
                    //Correct
                    currentScore = currentScore + 10;
                    editTextQuestionTwo.setTextColor(getResources().getColor(R.color.colorAccent));
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "FIRE FLY":
                    //Correct
                    currentScore = currentScore + 10;
                    editTextQuestionTwo.setTextColor(getResources().getColor(R.color.colorAccent));
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "fire fly":
                    //Correct
                    currentScore = currentScore + 10;
                    editTextQuestionTwo.setTextColor(getResources().getColor(R.color.colorAccent));
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "firefly":
                    //Correct
                    currentScore = currentScore + 10;
                    editTextQuestionTwo.setTextColor(getResources().getColor(R.color.colorAccent));
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                case "FireFly":
                    //Correct
                    currentScore = currentScore + 10;
                    editTextQuestionTwo.setTextColor(getResources().getColor(R.color.colorAccent));
                    Toast.makeText(getApplicationContext(), "Correct, " + questionTwoAnswer + " is the classification", Toast.LENGTH_LONG).show();
                    break;
                default:
                    currentScore = currentScore - 10;
                    editTextQuestionTwo.setTextColor(getResources().getColor(R.color.incorrectRed));
                    Toast.makeText(getApplicationContext(), "The correct answer is Firefly", Toast.LENGTH_LONG).show();
                    break;
            }
        }
        //Update the Score Display
        displayCurrentScore(currentScore);

        //Fade the submit button and set to unclickable
        questionTwoSubmit.setClickable(false);
        questionTwoSubmit.setText("Answer Submitted");
        questionTwoSubmit.setTextColor(getResources().getColor(R.color.grayFadeD));

        /*
        Calls the method to update Nav Tab, ViewPager and Progress Bar
        Set the Boolean for Question 1 to true (answered)
        */
        questionTwo = true;
        updateViewPagerNavigationAndAnimation();
    }


    //Processing Details for FRAGMENT THREE - onClick Method
    public void onClickInitiateCheckedTextView (View view) {
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
        String CheckedTextViewString = showCheckedTextViewsQuestionThree.getText().toString();


        if (CheckedTextViewString == "Show Options") {
            linearLayoutQuestionThreeChoices.setVisibility(VISIBLE);
            submitChoicesQuestionThree.setVisibility(VISIBLE);
            showCheckedTextViewsQuestionThree.setText("Hide Options");
        } else {
            linearLayoutQuestionThreeChoices.setVisibility(GONE);
            submitChoicesQuestionThree.setVisibility(GONE);
            showCheckedTextViewsQuestionThree.setText("Show Options");
        }

        /*
        Sets the onClickListeners for checkboxes
        Validates each checkbox and adds/removes the customer checkmark drawable
        */
        questionThreeOptionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionThreeOptionA.isChecked()) {
                    //Removes the checkmark if already checked on 2nd click
                    questionThreeOptionA.setCheckMarkDrawable(0);
                    questionThreeOptionA.setChecked(false);
                } else {
                    //Adds the checkmark if not checked
                    questionThreeOptionA.setCheckMarkDrawable(R.drawable.checked);
                    questionThreeOptionA.setChecked(true);
                }
            }
        });
        questionThreeOptionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionThreeOptionB.isChecked()) {
                    //Removes the checkmark if already checked on 2nd click
                    questionThreeOptionB.setCheckMarkDrawable(0);
                    questionThreeOptionB.setChecked(false);
                } else {
                    //Adds the checkmark if not checked
                    questionThreeOptionB.setCheckMarkDrawable(R.drawable.checked);
                    questionThreeOptionB.setChecked(true);
                }
            }
        });
        questionThreeOptionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionThreeOptionC.isChecked()) {
                    //Removes the checkmark if already checked on 2nd click
                    questionThreeOptionC.setCheckMarkDrawable(0);
                    questionThreeOptionC.setChecked(false);
                } else {
                    //Adds the checkmark if not checked
                    questionThreeOptionC.setCheckMarkDrawable(R.drawable.checked);
                    questionThreeOptionC.setChecked(true);
                }
            }
        });
        questionThreeOptionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionThreeOptionD.isChecked()) {
                    //Removes the checkmark if already checked on 2nd click
                    questionThreeOptionD.setCheckMarkDrawable(0);
                    questionThreeOptionD.setChecked(false);
                } else {
                    //Adds the checkmark if not checked
                    questionThreeOptionD.setCheckMarkDrawable(R.drawable.checked);
                    questionThreeOptionD.setChecked(true);
                }
            }
        });
        questionThreeOptionE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questionThreeOptionE.isChecked()) {
                    //Removes the checkmark if already checked on 2nd click
                    questionThreeOptionE.setCheckMarkDrawable(0);
                    questionThreeOptionE.setChecked(false);
                } else {
                    //Adds the checkmark if not checked
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

    //This method calculates the number of correct/incorrect checkboxes from Question Three
    public void onClickSubmitQuestionThree (View view) {
        questionThree = true;
        Boolean A = questionThreeOptionA.isChecked();
        Boolean B = questionThreeOptionB.isChecked();
        Boolean C = questionThreeOptionC.isChecked();
        Boolean D = questionThreeOptionD.isChecked();
        Boolean E = questionThreeOptionE.isChecked();
        Boolean F = questionThreeOptionF.isChecked();
        switch (String.valueOf(A)) {
            case "true":
                //Correct
                correctTextViews = correctTextViews + 1;
                selectedTextViews = selectedTextViews + 1;
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
                selectedTextViews = selectedTextViews + 1;
                questionThreeOptionB.setCheckMarkDrawable(R.drawable.checked_wrong);
                break;
            default:
                //Not selected
                break;
        }
        switch (String.valueOf(C)) {
            case "true":
                //Incorrect
                selectedTextViews = selectedTextViews + 1;
                questionThreeOptionC.setCheckMarkDrawable(R.drawable.checked_wrong);
                break;
            default:
                //Not selected
                break;
        }
        switch (String.valueOf(D)) {
            case "true":
                //Incorrect
                selectedTextViews = selectedTextViews + 1;
                questionThreeOptionD.setCheckMarkDrawable(R.drawable.checked_wrong);
                break;
            default:
                //Not selected
                break;
        }
        switch (String.valueOf(E)) {
            case "true":
                //Correct
                correctTextViews = correctTextViews + 1;
                selectedTextViews = selectedTextViews + 1;
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
                selectedTextViews = selectedTextViews + 1;
                questionThreeOptionF.setCheckMarkDrawable(R.drawable.checked_wrong);
                break;
            default:
                //Not selected
                break;
        }

        //This series of If/Else statements determines how many were answered correctly, versus incorrectly
        if (correctTextViews == 2 && selectedTextViews == 2) {
            // Two correct answers plus zero incorrect
            Toast.makeText(this, "You've chosen correctly", Toast.LENGTH_SHORT).show();
            currentScore += 20;
        } else if (correctTextViews == 1 && selectedTextViews == 1) {
            //One correct answer plus zero incorrect
            Toast.makeText(this, "You've chosen one select answer", Toast.LENGTH_SHORT).show();
            currentScore += 10;
            currentScore = currentScore - 5;
        } else if (correctTextViews == 0 && selectedTextViews == 0) {
            //Zero correct answers selected plus zero incorrect
            Toast.makeText(this, "You've made no selections", Toast.LENGTH_SHORT).show();
            currentScore = currentScore - 10;
        } else if (correctTextViews == 1 && selectedTextViews >= 2) {
            //One correct answer plus at least one incorrect
            Toast.makeText(this, "You've chosen one select answer", Toast.LENGTH_SHORT).show();
            currentScore += 10;
            currentScore = currentScore - (5*(selectedTextViews - 1));
        } else if (correctTextViews == 2 && selectedTextViews > 2) {
            //Two Correct answers plus at least one incorrect
            Toast.makeText(this, "You've chosen the correct answers plus a few wrong", Toast.LENGTH_SHORT).show();
            currentScore += 20;
            currentScore = currentScore - (5*(selectedTextViews - 2));
        } else if (correctTextViews == 0 && selectedTextViews >= 1) {
            //Zero correct answers plus at least one incorrect
            Toast.makeText(this, "You've selected zero correct answers", Toast.LENGTH_SHORT).show();
            currentScore = currentScore - (5 * (selectedTextViews));
        }

        submitChoicesQuestionThree.setVisibility(GONE);
        showCheckedTextViewsQuestionThree.setVisibility(GONE);

        //Update the Score Display
        displayCurrentScore(currentScore);

        //Animate the progress bar
        currentProgress = questionProgress.getProgress();
        desiredProgress = currentProgress + 600;
        ProgressBarAnimation anim = new ProgressBarAnimation(questionProgress, currentProgress, desiredProgress);
        anim.setDuration(3000);
        questionProgress.startAnimation(anim);

        if (correctTextViews == 2 && selectedTextViews == 2) {
            /*
            Advances the user to an extra credit question IF and ONLY IF the two correct answers are chosen
            Sets checkboxes and question items to GONE, allowing space for the extra credit question to be displayed
            */
            submitChoicesQuestionThree.setVisibility(GONE);
            showCheckedTextViewsQuestionThree.setVisibility(GONE);
            questionThreeTextView.setVisibility(GONE);
            questionThreeLinear.setVisibility(GONE);
            //Set Extra Credit Questions to VISIBLE
            questionThreeExtraCredit.setVisibility(VISIBLE);
            questionThreeECTextView.setVisibility(VISIBLE);
            questionThreeRadioGroup.setVisibility(VISIBLE);
        } else {
            //Advances the user to question four (bypasses the extra credit)
            final ViewPager pager = (ViewPager) super.findViewById(R.id.viewPager);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pager.setCurrentItem(pager.getCurrentItem()+1,true);
                }
            }, 3000);
        }
    }

    //Processing Details for FRAGMENT THREE EXTRA CREDIT - onClick Method
    public void onClickSubmitQuestionThreeExtra(View view) {
        fragmentQuestionThree = findViewById(R.id.questionThreeExtraCreditRadioGroup);
        questionThreeAnswerOneEC = findViewById(R.id.question_threeEC_a1);
        String questionThreeAnswerOneText = questionThreeAnswerOneEC.getText().toString();
        questionThreeAnswerTwoEC = findViewById(R.id.question_threeEC_a2);
        String questionThreeAnswerTwoText = questionThreeAnswerTwoEC.getText().toString();
        questionThreeAnswerThreeEC = findViewById(R.id.question_threeEC_a3);
        String questionThreeAnswerThreeText = questionThreeAnswerThreeEC.getText().toString();
        questionThreeAnswerFourEC = findViewById(R.id.question_threeEC_a4);
        String questionThreeAnswerFourText = questionThreeAnswerFourEC.getText().toString();

        switch (fragmentQuestionThree.getCheckedRadioButtonId()) {
            case R.id.question_threeEC_a1:
                //Correct
                questionThreeAnswerOneEC.setTextColor(getResources().getColor(R.color.incorrectRed));
                questionThreeAnswerTwoEC.setTextColor(getResources().getColor(R.color.colorAccent));
                Toast.makeText(getApplicationContext(), "Incorrect. " + questionThreeAnswerOneText+ " is not the correct Eastwood character.", Toast.LENGTH_SHORT).show();
                currentScore = currentScore - 10;
                break;
            case R.id.question_threeEC_a2:
                //Incorrect
                questionThreeAnswerTwoEC.setTextColor(getResources().getColor(R.color.colorAccent));
                Toast.makeText(getApplicationContext(), "Correct. "+ questionThreeAnswerTwoText + " is the correct Eastwood character.", Toast.LENGTH_SHORT).show();
                currentScore = currentScore + 10;
                break;
            case R.id.question_threeEC_a3:
                //Incorrect
                questionThreeAnswerThreeEC.setTextColor(getResources().getColor(R.color.incorrectRed));
                questionThreeAnswerTwoEC.setTextColor(getResources().getColor(R.color.colorAccent));
                Toast.makeText(getApplicationContext(), "Incorrect. "+ questionThreeAnswerThreeText + " is not the correct Eastwood character.", Toast.LENGTH_SHORT).show();
                currentScore -= 5;
                break;
            case R.id.question_threeEC_a4:
                //Incorrect
                questionThreeAnswerFourEC.setTextColor(getResources().getColor(R.color.incorrectRed));
                questionThreeAnswerTwoEC.setTextColor(getResources().getColor(R.color.colorAccent));
                Toast.makeText(getApplicationContext(), "Incorrect. " + questionThreeAnswerFourText + " is not the correct Eastwood character.", Toast.LENGTH_SHORT).show();
                currentScore -= 5;
                break;
            default:
                //No Selection
                questionThreeAnswerTwoEC.setTextColor(getResources().getColor(R.color.colorAccent));
                Toast.makeText(getApplicationContext(), "No answer is better than a wrong answer I guess.", Toast.LENGTH_LONG).show();
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


    //Processing Details for FRAGMENT FOUR - onClick Method
    public void onClickSubmitQuestionFour (View view) {
        //Defines the location of question one radio group/buttons and defines the String counterpart to the Radio Buttons
        fragmentQuestionFour = findViewById(R.id.questionFourRadioGroup);
        questionFourAnswerOne = findViewById(R.id.question_four_a1);
        String questionFourAnswerOneStringValue = questionFourAnswerOne.getText().toString();
        questionFourAnswerTwo = findViewById(R.id.question_four_a2);
        String questionFourAnswerTwoStringValue = questionFourAnswerTwo.getText().toString();
        questionFourAnswerThree = findViewById(R.id.question_four_a3);
        String questionFourAnswerThreeStringValue = questionFourAnswerThree.getText().toString();
        questionFourAnswerFour = findViewById(R.id.question_four_a4);
        String questionFourAnswerFourStringValue = questionFourAnswerFour.getText().toString();
        questionFourSubmit = findViewById(R.id.submit_answer_question_four);
        //switch statement to check which radio button is selected
        //each case return will apply scoring and change resource color of the selected and correct radio button text
        switch (fragmentQuestionFour.getCheckedRadioButtonId()) {
            case R.id.question_four_a1:
                Toast.makeText(getApplicationContext(), "Correct. " + questionFourAnswerOneStringValue + " is not the song name.", Toast.LENGTH_LONG).show();
                questionFourAnswerThree.setTextColor(getResources().getColor(R.color.colorAccent));
                questionFourAnswerOne.setTextColor(getResources().getColor(R.color.incorrectRed));
                currentScore = currentScore - 5;
                break;
            case R.id.question_four_a2:
                Toast.makeText(getApplicationContext(), "Incorrect. " + questionFourAnswerTwoStringValue + " is not the song name.", Toast.LENGTH_LONG).show();
                questionFourAnswerThree.setTextColor(getResources().getColor(R.color.colorAccent));
                questionFourAnswerTwo.setTextColor(getResources().getColor(R.color.incorrectRed));
                currentScore = currentScore - 5;
                break;
            case R.id.question_four_a3:
                Toast.makeText(getApplicationContext(), "Correct. " + questionFourAnswerThreeStringValue + " is the song name.", Toast.LENGTH_LONG).show();
                questionFourAnswerThree.setTextColor(getResources().getColor(R.color.colorAccent));
                currentScore = currentScore + 10;
                break;
            case R.id.question_four_a4:
                Toast.makeText(getApplicationContext(), "Incorrect. " + questionFourAnswerFourStringValue + " is not the song name.", Toast.LENGTH_LONG).show();
                questionFourAnswerThree.setTextColor(getResources().getColor(R.color.colorAccent));
                questionFourAnswerFour.setTextColor(getResources().getColor(R.color.incorrectRed));
                currentScore = currentScore - 5;
                break;
            default:
                //No Selection
                Toast.makeText(getApplicationContext(), "You made no selection.", Toast.LENGTH_LONG).show();
                break;
        }
        //Update the Score Display
        displayCurrentScore(currentScore);

        //Fade the Submit Button
        questionFourSubmit.setClickable(false);
        questionFourSubmit.setText("Answer Submitted");
        questionFourSubmit.setTextColor(getResources().getColor(R.color.grayFadeD));

        /*
        Calls the method to update Nav Tab, ViewPager and Progress Bar
        Set the Boolean for Question 1 to true (answered)
        */
        questionFour = true;
        updateViewPagerNavigationAndAnimation();
    }


    //Processing Details for FRAGMENT FIVE - onClick Method
    public void onClickSubmitQuestionFive (View view) {
        //Defines the location of question one radio group/buttons and defines the String counterpart to the Radio Buttons
        fragmentQuestionFive = findViewById(R.id.questionFiveRadioGroup);
        questionFiveAnswerOne = findViewById(R.id.question_five_a1);
        String questionFiveAnswerOneStringValue = questionFiveAnswerOne.getText().toString();
        questionFiveAnswerTwo = findViewById(R.id.question_five_a2);
        String questionFiveAnswerTwoStringValue = questionFiveAnswerTwo.getText().toString();
        questionFiveAnswerThree = findViewById(R.id.question_five_a3);
        String questionFiveAnswerThreeStringValue = questionFiveAnswerThree.getText().toString();
        questionFourAnswerFour = findViewById(R.id.question_five_a4);
        String questionFiveAnswerFourStringValue = questionFiveAnswerFour.getText().toString();
        questionFiveSubmit = findViewById(R.id.submit_answer_question_five);
        //switch statement to check which radio button is selected
        //each case return will apply scoring and change resource color of the selected and correct radio button text
        switch (fragmentQuestionFive.getCheckedRadioButtonId()) {
            case R.id.question_five_a1:
                Toast.makeText(getApplicationContext(), "Correct. " + questionFiveAnswerOneStringValue + " is not the song name.", Toast.LENGTH_LONG).show();
                questionFiveAnswerThree.setTextColor(getResources().getColor(R.color.colorAccent));
                questionFiveAnswerOne.setTextColor(getResources().getColor(R.color.incorrectRed));
                currentScore = currentScore - 5;
                break;
            case R.id.question_five_a2:
                Toast.makeText(getApplicationContext(), "Incorrect. " + questionFiveAnswerTwoStringValue + " is not the song name.", Toast.LENGTH_LONG).show();
                questionFiveAnswerThree.setTextColor(getResources().getColor(R.color.colorAccent));
                questionFiveAnswerTwo.setTextColor(getResources().getColor(R.color.incorrectRed));
                currentScore = currentScore - 5;
                break;
            case R.id.question_four_a3:
                Toast.makeText(getApplicationContext(), "Correct. " + questionFiveAnswerThreeStringValue + " is the song name.", Toast.LENGTH_LONG).show();
                questionFiveAnswerThree.setTextColor(getResources().getColor(R.color.colorAccent));
                currentScore = currentScore + 10;
                break;
            case R.id.question_five_a4:
                Toast.makeText(getApplicationContext(), "Incorrect. " + questionFiveAnswerFourStringValue + " is not the song name.", Toast.LENGTH_LONG).show();
                questionFiveAnswerThree.setTextColor(getResources().getColor(R.color.colorAccent));
                questionFiveAnswerFour.setTextColor(getResources().getColor(R.color.incorrectRed));
                currentScore = currentScore - 5;
                break;
            default:
                //No Selection
                Toast.makeText(getApplicationContext(), "You made no selection.", Toast.LENGTH_LONG).show();
                break;
        }
        //Update the Score Display
        displayCurrentScore(currentScore);

        //Fade the Submit Button
        questionFiveSubmit.setClickable(false);
        questionFiveSubmit.setText("Answer Submitted");
        questionFiveSubmit.setTextColor(getResources().getColor(R.color.grayFadeD));

        /*
        Calls the method to update Nav Tab, ViewPager and Progress Bar
        Set the Boolean for Question 1 to true (answered)
        */
        questionFive = true;
        updateViewPagerNavigationAndAnimation();
    }


    //Processing Details for FRAGMENT SIX - onClick Method
    public void onClickSubmitQuestionSix (View view) {
        //Defines the true/false for checkboxes in Fragment Six
        questionSixOptionOne = findViewById(R.id.question_six_checkbox_one);
        questionSixOptionTwo = findViewById(R.id.question_six_checkbox_two);
        questionSixOptionThree = findViewById(R.id.question_six_checkbox_three);
        questionSixOptionFour = findViewById(R.id.question_six_checkbox_four);
        questionSixOptionFive = findViewById(R.id.question_six_checkbox_five);
        questionSixOptionSix = findViewById(R.id.question_six_checkbox_six);
        questionSixSubmit = findViewById(R.id.submit_answer_question_six);
        Boolean A = questionSixOptionOne.isChecked();
        Boolean B = questionSixOptionTwo.isChecked();
        Boolean C = questionSixOptionThree.isChecked();
        Boolean D = questionSixOptionFour.isChecked();
        Boolean E = questionSixOptionFive.isChecked();
        Boolean F = questionSixOptionSix.isChecked();
        switch (String.valueOf(A)) {
            case "true":
                //Correct
                correctCB = correctCB + 1;
                selectedCB = selectedCB + 1;
                break;
            default:
                //Not selected
                break;
        }
        switch (String.valueOf(B)) {
            case "true":
                //Incorrect
                selectedCB = selectedCB + 1;
                //Answer is FALSE because : There were Star Wars references. Whedon and Fillion were big fans.
                break;
            default:
                //Not selected
                break;
        }
        switch (String.valueOf(C)) {
            case "true":
                //Incorrect
                selectedCB = selectedCB + 1;
                //Answer is FALSE because : Neil Patrick Harris actually auditioned for the role of Dr. Simon Tam.
                break;
            default:
                //Not selected
                break;
        }
        switch (String.valueOf(D)) {
            case "true":
                //Correct
                correctCB = correctCB + 1;
                selectedCB = selectedCB + 1;
                break;
            default:
                //Not selected
                break;
        }
        switch (String.valueOf(E)) {
            case "true":
                //Correct
                correctCB = correctCB + 1;
                selectedCB = selectedCB + 1;
                break;
            default:
                //Not selected
                break;
        }
        switch (String.valueOf(F)) {
            case "true":
                //Incorrect
                selectedCB = selectedCB + 1;
                //Answer is FALSE because : Firefly was supposed to have a cast of five (not seven).
                break;
            default:
                //Not selected
                break;
        }

        //POSSIBLE COMBINATIONS SHOULD BE
        // 3C & 3S, 2C & 2S, 1C & 1S, 0C & 0S, 3C & >3S, 2C & >2S, 1C & >1S, 0C & >0S
        // Need to update the if/else if statements to display why the false answers are false and update the color schemes for true/false
        if (correctCB == 3 && selectedCB == 3) {
            // Two correct answers plus zero incorrect
            Toast.makeText(this, "You've chosen correctly", Toast.LENGTH_SHORT).show();
            //ADD NEW FIELDS TO CHANGE CHECK BOX TEXT TO SHOW WHY THE FALSE ANSWERS WERE FALSE
            currentScore += 20;

        } else if (correctTextViews == 2 && selectedTextViews == 2) {
            //One correct answer plus zero incorrect
            Toast.makeText(this, "You've chosen one select answer", Toast.LENGTH_SHORT).show();
            currentScore += 10;
            currentScore = currentScore - 5;
        } else if (correctTextViews == 0 && selectedTextViews == 0) {
            //Zero correct answers selected plus zero incorrect
            Toast.makeText(this, "You've made no selections", Toast.LENGTH_SHORT).show();
            currentScore = currentScore - 10;
        } else if (correctTextViews == 1 && selectedTextViews > 1) {
            //One correct answer plus at least one incorrect
            Toast.makeText(this, "You've chosen one select answer", Toast.LENGTH_SHORT).show();
            currentScore += 10;
            currentScore = currentScore - (5*(selectedTextViews - 1));
        } else if (correctTextViews == 2 && selectedTextViews > 2) {
            //Two Correct answers plus at least one incorrect
            Toast.makeText(this, "You've chosen the correct answers plus a few wrong", Toast.LENGTH_SHORT).show();
            currentScore += 20;
            currentScore = currentScore - (5*(selectedTextViews - 2));
        } else if (correctTextViews == 0 && selectedTextViews >= 1) {
            //Zero correct answers plus at least one incorrect
            Toast.makeText(this, "You've selected zero correct answers", Toast.LENGTH_SHORT).show();
            currentScore = currentScore - (5 * (selectedTextViews));
        }
        //Update the Score Display
        displayCurrentScore(currentScore);

        //Fade the Submit Button
        questionSixSubmit.setClickable(false);
        questionSixSubmit.setText("Answer Submitted");
        questionFiveSubmit.setTextColor(getResources().getColor(R.color.grayFadeD));

        /*
        Calls the method to update Nav Tab, ViewPager and Progress Bar
        Set the Boolean for Question 1 to true (answered)
        */
        questionSix = true;
        updateViewPagerNavigationAndAnimation();
    }

    //This method prepares the video in question four
    public String onClickStartThemeVideo(View view) {
        onClickPlay = findViewById(R.id.start_fragment_four_video);
        onClickText = onClickPlay.getText().toString();
        questionFourVideoViewer = findViewById(R.id.video_view_fragment_four);
        if (onClickPlay.getText().toString().equals("Pause")) {
            questionFourVideoViewer.resume();
        } else {
            videoPath = "android.resource://" + getPackageName() + "/" + R.raw.fireflyopening;
            uriPath = Uri.parse(videoPath);
            myVideoController = new MediaController(this);
            //Prepare the video
            setupMedia();
            setupListeners();
        }
        return onClickText;
    }

    //This method sets the media player for question four
    private void setupMedia() {
        onClickPlay = findViewById(R.id.start_fragment_four_video);
        onClickPlay.setText("Pause");
        questionFourVideoViewer.setMediaController(myVideoController);
        questionFourVideoViewer.setVideoURI(uriPath);
        questionFourVideoViewer.start();
    }

    //This method sets the listeners for the play/stop buttons on the video in question four
    private void setupListeners() {
        onClickStop = findViewById(R.id.stop_fragment_four_video);
        final Button onClickPlay = findViewById(R.id.start_fragment_four_video);
        onClickStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickPlay.setText("Play");
                questionFourVideoViewer.stopPlayback();
            }
        });

    }

    //Update and display the current score
    public void displayCurrentScore (int score) {
        TextView currentScoreTV = findViewById(R.id.currentScoreDisplay);
        currentScoreTV.setText(String.valueOf(score));
    }

    //These methods define the actions taken when the Navigation Tab is clicked
    public void onClickQuestionOne (View view) {
        ViewPager pager = findViewById(R.id.viewPager);
        navigationTab.setTabIndex(0);
        pager.setCurrentItem(0,true);
    }
    public void onClickQuestionTwo (View view) {
        ViewPager pager = findViewById(R.id.viewPager);
        navigationTab.setTabIndex(1);
        pager.setCurrentItem(1,true);
    }
    public void onClickQuestionThree (View view) {
        ViewPager pager = findViewById(R.id.viewPager);
        navigationTab.setTabIndex(2);
        pager.setCurrentItem(2,true);
    }
    public void onClickQuestionFour (View view) {
        ViewPager pager = findViewById(R.id.viewPager);
        navigationTab.setTabIndex(3);
        pager.setCurrentItem(3,true);
    }
    public void onClickQuestionFive (View view) {
        ViewPager pager = findViewById(R.id.viewPager);
        navigationTab.setTabIndex(4);
        pager.setCurrentItem(4,true);
    }
    public void onClickQuestionSix (View view) {
        ViewPager pager = findViewById(R.id.viewPager);
        navigationTab.setTabIndex(5);
        pager.setCurrentItem(5,true);
    }
    public void onClickQuestionSeven (View view) {
        ViewPager pager = findViewById(R.id.viewPager);
        navigationTab.setTabIndex(6);
        pager.setCurrentItem(6,true);
    }
}
