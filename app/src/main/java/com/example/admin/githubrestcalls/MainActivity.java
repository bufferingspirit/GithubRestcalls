package com.example.admin.githubrestcalls;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.admin.githubrestcalls.Profile.Profile;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.github.com/users/bufferingspirit";
    private static final String TAG = "MainActivity";

    final OkHttpClient client = new OkHttpClient();

    final Request request = new Request.Builder()
            .url(BASE_URL)
            .build();


    String resultResponse = "";

    Profile profileData;

    TextView tvName, tvLogin, tvProfileURL, tvRepos, tvJoinDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = (TextView) findViewById(R.id.tvName);
        tvLogin = (TextView) findViewById(R.id.tvLogin);
        tvProfileURL = (TextView) findViewById(R.id.tvProfileURL);
        tvRepos = (TextView) findViewById(R.id.tvNumRepos);
        tvJoinDate = (TextView) findViewById(R.id.tvJoinDate);



        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                resultResponse = response.body().string();

                Gson gson = new Gson();
                profileData = gson.fromJson(resultResponse, Profile.class);
            }
        });

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tvName.setText("Name: " + profileData.getName().toString());
        tvLogin.setText("Login: " + profileData.getLogin().toString());
        tvProfileURL.setText("Profile URL: " + profileData.getHtmlUrl().toString());
        tvRepos.setText("Number of Repositories" + profileData.getPublicRepos().toString());
        tvJoinDate.setText("Join Date: " + profileData.getCreatedAt().toString());

    }

    public void openRepos(View view){
        Intent intent = new Intent(this, RepoActivity.class);
        startActivity(intent);
    }

}
