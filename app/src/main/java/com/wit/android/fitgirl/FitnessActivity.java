package com.wit.android.fitgirl;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FitnessActivity extends AppCompatActivity
{

    TextView navTitle;
    ImageView navLeft;
    LinearLayout click;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);

        navTitle = findViewById(R.id.navTitle);
        navTitle.setText("Fitness Exercises");

        navLeft = findViewById(R.id.navLeft);
        navLeft.setImageResource(R.drawable.back);

        click = findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                back();
            }
        });


    }

    public void back(){
        Intent parentIntent = NavUtils.getParentActivityIntent(this);
        parentIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(parentIntent);
        finish();
    }
}
