package com.example.projecte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrategoGameState firstInstance = new StrategoGameState();
        StrategoGameState firstCopy = new StrategoGameState(firstInstance);
        StrategoGameState secondCopy = new StrategoGameState(firstInstance);

        Button testerBut = (Button) findViewById(R.id.TestButton);


        testerBut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                firstInstance.placePiece(0,,0,0);

                firstInstance.selectPiece(0, );

                firstInstance.clearSelection(0);

                firstInstance.movePiece(0,,1);



            }
        });

    }
}