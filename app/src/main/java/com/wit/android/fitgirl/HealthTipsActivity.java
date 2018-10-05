package com.wit.android.fitgirl;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HealthTipsActivity extends AppCompatActivity
{

    TextView navTitle;
    ImageView navLeft;
    LinearLayout click;
    RecyclerView rv_tips;

    private List<Tips> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TipsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);

        navTitle = findViewById(R.id.navTitle);
        navTitle.setText("Health Tips");

        navLeft = findViewById(R.id.navLeft);
        navLeft.setImageResource(R.drawable.back);

        click = findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

       // rv_tips = findViewById(R.id.rv_tips);

      //  new AsyncFetch().execute();

    }

    public void back() {
        Intent parentIntent = NavUtils.getParentActivityIntent(this);
        parentIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(parentIntent);
        finish();
    }

    private class AsyncFetch extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(HealthTipsActivity.this);


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {

            Connection newConnection = new Connection();
            return newConnection.generateList();

        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();
            List<Tips> data = new ArrayList<>();

            pdLoading.dismiss();
            try {

                JSONArray jArray = new JSONArray(result);
                // Extract data from json and store into ArrayList as class objects
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    Tips tip = new Tips();
                    tip.article_title = json_data.getString("article_title");
                    tip.article_author = json_data.getString("author_name");

                    data.add(tip);
                }

                // Setup and Handover data to recyclerview
           //     recyclerView = (RecyclerView) findViewById(R.id.rv_tips);
                mAdapter = new TipsAdapter(getApplicationContext(), data);
                recyclerView.setAdapter(mAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(HealthTipsActivity.this));


            } catch (JSONException e) {
                Toast.makeText(HealthTipsActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }

    }
}
