package com.wit.android.fitgirl;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    EditText editHeight,editWeight, etAge;
    Button b_calculate;
    TextView rslt, navTitle, result_message;
    ImageView refresh, navLeft, happy;
    LinearLayout click;
    RelativeLayout fragment_holder;
    FrameLayout content_frame;

    RelativeLayout obese_tips, overweight_tips, underweight_tips, normal_tips;
    TextView obese, overweight, underweight, normal, wait;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        editHeight= findViewById(R.id.et_height);
        editWeight= findViewById(R.id.et_weight);
        etAge= findViewById(R.id.et_age);
        navTitle= findViewById(R.id.navTitle);
        click = findViewById(R.id.click);
        result_message = findViewById(R.id.result_message);
        happy = findViewById(R.id.happy);
        content_frame = findViewById(R.id.content_frame);

        obese_tips= findViewById(R.id.obese_tips);
        overweight_tips= findViewById(R.id.overweight_tips);
        underweight_tips= findViewById(R.id.underweight_tips);
        normal_tips= findViewById(R.id.normal_tips);
        obese= findViewById(R.id.obese);
        overweight= findViewById(R.id.overweight);
        underweight= findViewById(R.id.underweight);
        normal= findViewById(R.id.normal);
        wait = findViewById(R.id.wait);



        fragment_holder = findViewById(R.id.fragment_holder);

        navLeft = findViewById(R.id.navLeft);
        navLeft.setImageResource(R.drawable.hamburger);

        navTitle.setText(R.string.fit__girl);

        refresh = findViewById(R.id.refresh);

        happy.setBackgroundResource(R.drawable.smiley_face);
        result_message.setText("Hey there, fill in your details for more details on your weight :)");

        refresh.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                editHeight.setText("");
                editWeight.setText("");
                etAge.setText("");

            }
        });



        b_calculate= findViewById(R.id.calculate);

        b_calculate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                try
                {
                    String height = editHeight.getText().toString().trim();
                    String weight = editWeight.getText().toString().trim();
                    String age = etAge.getText().toString().trim();

                    if (age.equals("")) {
                        Toast.makeText(getApplicationContext(), "Age required", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (height.equals("")) {
                        Toast.makeText(getApplicationContext(), "Height required", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (weight.equals("")) {
                        Toast.makeText(getApplicationContext(), "Weight required", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    calculatebmi(v);

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });


        rslt=(TextView)findViewById(R.id.calculated_bmi);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        BottomNavigationView navigation = findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        click.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                drawer.openDrawer(GravityCompat.START);
            }
        });

    }

    public void calculatebmi(View view)
    {
        float height =Float.parseFloat(editHeight.getText().toString());
        float weight =Float.parseFloat(editWeight.getText().toString());
        float bmi=weight/(height*height);

        String result=(Float.valueOf(bmi)).toString();

        rslt.setText(result);
        Toast.makeText(HomeActivity.this, new StringBuilder().append(Float.valueOf(bmi)).toString(),Toast.LENGTH_SHORT).show();

//        Underweight = <18.5
//        Normal weight = 18.5–24.9
//        Overweight = 25–29.9
//        Obesity = BMI of 30 or greater

        if (bmi < 18.5)
        {
            happy.setBackgroundResource(R.drawable.thinking_outline);
            result_message.setText("Hey you, according to experts, you're underweight.");

            underweight_tips.setVisibility(View.VISIBLE);
            overweight_tips.setVisibility(View.GONE);
            obese_tips.setVisibility(View.GONE);
            normal_tips.setVisibility(View.GONE);
            wait.setVisibility(View.GONE);

        }
        else if ((bmi > (18.5)) && (bmi < 24.9))
        {
            happy.setBackgroundResource(R.drawable.colored_smiley);
            result_message.setText("Hey you, according to experts, you're of normal weight.");

            wait.setVisibility(View.GONE);
            underweight_tips.setVisibility(View.GONE);
            overweight_tips.setVisibility(View.GONE);
            obese_tips.setVisibility(View.GONE);
            normal_tips.setVisibility(View.VISIBLE);
        }
        else if (bmi > (25) && bmi <= 29.9)
        {
            happy.setBackgroundResource(R.drawable.thinking);
            result_message.setText("Hey you, according to experts, you're overweight.");

            underweight_tips.setVisibility(View.GONE);
            overweight_tips.setVisibility(View.VISIBLE);
            obese_tips.setVisibility(View.GONE);
            normal_tips.setVisibility(View.GONE);
            wait.setVisibility(View.GONE);
        }
        else if (bmi > 30)
        {
            happy.setBackgroundResource(R.drawable.sadey_covered);
            result_message.setText("Hey you, according to experts, you're obese. ");

            underweight_tips.setVisibility(View.GONE);
            overweight_tips.setVisibility(View.GONE);
            obese_tips.setVisibility(View.VISIBLE);
            normal_tips.setVisibility(View.GONE);
            wait.setVisibility(View.GONE);
        }

    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.bmiItem:

                    return true;
                case R.id.tips:

//                    Intent i = new Intent (HomeActivity.this, Tips_Fragment.class);
//                    startActivity(i);

                    Tips_Fragment tips_fragment = Tips_Fragment.create();

                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_holder, tips_fragment).commit();

                    return true;


            }
            return false;
        }
    };

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.health_tips)
        {
            Intent intent = new Intent(this, HealthTipsActivity.class);
            startActivity(intent);
//
//            HealthTipsFragment generalFragment = HealthTipsFragment.create();
//
//            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, generalFragment).commit();

//            loadFragment(new HealthTipsFragment());

        }
        else if (id == R.id.healthy_recipes)
        {
            Intent intent = new Intent(this, RecipesActivity.class);
            startActivity(intent);

//            RecipesFragment recipesFragment = RecipesFragment.create();
//
//            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, recipesFragment).commit();

        }
        else if (id == R.id.exercise)
        {
            Intent intent = new Intent(this, FitnessActivity.class);
            startActivity(intent);

//            FitnessFragment fitnessFragment = FitnessFragment.create();
//
//            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fitnessFragment).commit();

        }
        else if (id == R.id.about)
        {
            Intent intent = new Intent(this, AboutFitGirlActivity.class);
            startActivity(intent);

//            AboutFitGirlFragment aboutFitGirlFragment = AboutFitGirlFragment.create();
//
//            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, aboutFitGirlFragment).commit();

        }
        else if (id == R.id.share)
        {Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: 'Link to app in playstore inserted here'");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
