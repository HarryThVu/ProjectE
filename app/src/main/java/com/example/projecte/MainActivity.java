package com.example.projecte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * MainActivity
 *
 * This is where the StrategoGameState will be tested.
 *
 * @author Anne Marie Blank,
 * @author Harry Vu,
 * @author Vincent Truong,
 * @author Kathryn Weidman
 * @version 3/11/2022
 */

public class MainActivity extends AppCompatActivity {

    /**
     * This is basically the main method that the tester button and the info needed for running the test.
     *
     * @param savedInstanceState   Required for this to class to extend AppCompatActivity.
     *
     * @return void                Doesn't return anything.
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //It sets up the textView and the Button.
        //This all the test that will be run to then be displayed in the textView
        Button testerBut = (Button) findViewById(R.id.TestButton);
        TextView testString = (TextView) findViewById(R.id.TestDescrip);
        testerBut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //Clears textView of all previous string/text and creates the printString variable.
                //The printString variable will be used to save all the test results and later be appended
                //onto the textView.
                String printString ="";
                testString.setText(printString);

                //Creates the first instance and a deep copy for it.
                StrategoGameState firstInstance = new StrategoGameState();
                StrategoGameState firstCopy = new StrategoGameState(firstInstance);

                //Where the StrategoGameState methods will be tested and where the test results will
                //be append to the printString.
                firstInstance.placePiece(0, firstInstance.getUnit(0,1), 1,1);
                firstInstance.selectPiece(0,firstInstance.getUnit(0,1) );
                firstInstance.clearSelection(0);
                firstInstance.movePiece(0,firstInstance.getUnit(0,1),1);
                printString += firstInstance.whatsUp;

                //Creates the secondInstance and it's deep copy.
                StrategoGameState secondInstance = new StrategoGameState();
                StrategoGameState secondCopy = new StrategoGameState(secondInstance);

                //It will compare the two deep copies and see if they are the same as each other.
                if(firstCopy.getTurn() == secondCopy.getTurn()) {
                    printString += "\n The 2 deep copies are identical.";
                }
                else {
                    printString += "\n The 2 deep copies are not identical.";
                }


                //The two deep copies toString will be appended to the printString.
                printString += "\n " + firstCopy.toString();
                printString += "\n " + secondCopy.toString();

                //The printString will be set onto the textView and displayed for the user.
                testString.setText(printString);
            }
        });

    }
}