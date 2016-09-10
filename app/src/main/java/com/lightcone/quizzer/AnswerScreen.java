package com.lightcone.quizzer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class AnswerScreen extends AppCompatActivity {

    public static final String TAG = "WEBSTREAM";
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private static int numberRight;
    private int numberWrong;
    private int score;
    Context context;

    public int bkg;  // Holds int identifying background image

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answerscreen);

        context = getBaseContext();

        // Subject index for questions:
        //   0 Astronomy
        //   1 History
        //   2 Mathematics
        //   3 Geography
        //   4 Biology

        // Choose the background image
        switch(AstroQA.subjectIndex){

            case 0:
                bkg = R.drawable.ngc6302_dark;
                break;

            case 1:
                bkg = R.drawable.lincoln;
                break;

            case 2:
                bkg = R.drawable.fractal;
                break;

            case 3:
                bkg = R.drawable.india;
                break;

            case 4:
                bkg = R.drawable.helix;
                break;
        }

        // Deal with deprecated methods in setting the background image

        final int sdk = android.os.Build.VERSION.SDK_INT;
        LinearLayout layout =(LinearLayout)findViewById(R.id.LinearLayout1);
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            layout.setBackgroundDrawable( getResources().getDrawable(bkg) );
        } else {
            layout.setBackground( getResources().getDrawable(bkg) );
        }

        // Create a floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabnew);

        // Add a click listener to the floating action button that launches a snackbar
        // and sets a listener on it to deal with clicks on the action button of the
        // snackbar.

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processMenu();  // Pop up Spinner menu of quiz subjects

/*                Snackbar.make(view, R.string.snackText, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.snackButtonText, new View.OnClickListener() {
                            // Handle clicks on snackbar button
                            @Override
                            public void onClick(View v) {
                                Log.i("SNACK", "Snackbar button was clicked");
                                //Intent i = new Intent(context, MainActivity.class);
                                //startActivity(new Intent(context, MainActivity.class));
                                processMenu();  // Pop up Spinner menu of quiz subjects
                            }
                        }).show();*/
            }
        });

        numberRight = AstroQA.numberRight;
        numberWrong= AstroQA.numberWrong;
        score = (int)(100*AstroQA.score);
        String question = AstroQA.question;
        boolean isCorrect = AstroQA.isCorrect;
        String amplification = AstroQA.amplification;

        Log.i(TAG,"right="+numberRight+" wrong="+numberWrong+" score="+score+"%");
        Log.i(TAG,"question="+question);

        Button newButton = (Button) findViewById(R.id.nextQuestion_button);
        newButton.setOnClickListener(event_listener);

        Button resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(event_listener);

        // Question
        tv1 = (TextView)findViewById(R.id.TextView02);
        tv1.append(question);
        // Possible answers
        tv2 = (TextView)findViewById(R.id.TextView03);
        for(int i=0; i<5; i++){
            tv2.append(AstroQA.answer[i]+"\n");
        }

        // Correct or incorrect
        tv3 = (TextView)findViewById(R.id.TextView04);
        String ans = "Your answer "+AstroQA.answerArray[AstroQA.selectedButton]+" is ";
        if(isCorrect){
            ans += "CORRECT. ";
            ans += "\n\n"+amplification;
        } else {
            ans += "INCORRECT. ";
            ans += " The correct answer is " + AstroQA.answerArray[AstroQA.correctIndex] +".";
        }
        tv3.append(ans);

        // Score
        tv4 = (TextView)findViewById(R.id.TextView05);
        String s = "Right: "+numberRight+"   Wrong: "+numberWrong+"   Score: " + score +"%";
        tv4.append(s);
    }


    @Override
    protected void onPause(){
        super.onPause();
        // To prevent navigation back to previous question
        finish();
    }

    // Process button clicks
    private OnClickListener event_listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.nextQuestion_button:
                    AstroQA.selectedButton = -1;    // So warning is issued if no answer selected
                    Intent i = new Intent(AnswerScreen.this, AstroQA.class);  // New question
                    startActivity(i);
                    break;
                case R.id.reset_button:
                    resetScores();
                    break;
            }
        }
    };

    // Method to reset scores
    private void resetScores(){
        numberRight = 0;
        numberWrong = 0;
        score = 0;

        SharedPreferences.Editor edit = AstroQA.prefs.edit();
        edit.putInt("numberRight", numberRight);
        edit.putInt("numberWrong", numberWrong);
        edit.putInt("numberQuestions", numberRight+numberWrong);
        edit.putFloat("score", score);
        edit.putInt("qnumber",AstroQA.qnumber);
        edit.commit();

        String s = "Right: "+numberRight+"   Wrong: "+numberWrong+"   Score: " + score +"%";
        tv4.setText("");
        tv4.append(s);
    }

    // Open AlertDialog holding quiz subject options menu and process with anonymous inner class
    private void processMenu(){
        new AlertDialog.Builder(this).setTitle(R.string.new_subject)
                .setItems(R.array.quiz_array,
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialoginterface, int i){
                                AstroQA.subjectIndex = i;
                                startActivity(new Intent(context, AstroQA.class));
                                //doMenu(i);
                            }
                        }).show();
    }

/*    // Menu with list of quiz subject options
    private void doMenu(int index){
        AstroQA.subjectIndex = index;
        startActivity(new Intent(this, AstroQA.class));

    }*/
}

