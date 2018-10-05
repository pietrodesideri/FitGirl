package com.wit.android.fitgirl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    EditText editHeight,editWeight, etAge;
    Button b_calculate;
    TextView rslt, navTitle;
    ImageView refresh, navLeft;
    LinearLayout click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        editHeight= findViewById(R.id.et_height);
        editWeight= findViewById(R.id.et_weight);
        etAge= findViewById(R.id.et_age);
        navTitle= findViewById(R.id.navTitle);
        click = findViewById(R.id.click);

        navLeft = findViewById(R.id.navLeft);
        navLeft.setImageResource(R.drawable.hamburger);

        navTitle.setText("Fit Girl");


        refresh = findViewById(R.id.refresh);

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



        b_calculate= (Button) findViewById(R.id.calculate);

        b_calculate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                try {
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

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                case R.id.photosItem:

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.health_tips)
        {
            Intent intent = new Intent(this, HealthTipsActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.healthy_recipes)
        {
            Intent intent = new Intent(this, RecipesActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.fitness_videos)
        {
            Intent intent = new Intent(this, FitnessActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.about)
        {
            Intent intent = new Intent(this, AboutFitGirlActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.share)
        {

        }
        else if (id == R.id.settings)
        {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
