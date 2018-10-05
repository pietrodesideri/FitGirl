package com.wit.android.fitgirl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class details extends AppCompatActivity {
EditText editHeight,editWeight;
Button b_calculate;
TextView rslt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        editHeight=(EditText)findViewById(R.id.edheight);
        editWeight=(EditText)findViewById(R.id.edweight);
        b_calculate= (Button) findViewById(R.id.bcalculate);

        b_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatebmi(v);
            }
        });
        rslt=(TextView)findViewById(R.id.result);

    }


    public void calculatebmi(View view) {
        float height =Float.parseFloat(editHeight.getText().toString());
        float weight =Float.parseFloat(editWeight.getText().toString());
        float bmi=weight/(height*height);
        String result=(Float.valueOf(bmi)).toString();

        rslt.setText(result);
        Toast.makeText(details.this, new StringBuilder().append(Float.valueOf(bmi)).toString(),Toast.LENGTH_SHORT).show();
    }
}


