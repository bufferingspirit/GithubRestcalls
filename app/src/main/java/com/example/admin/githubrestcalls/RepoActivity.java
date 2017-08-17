package com.example.admin.githubrestcalls;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.admin.githubrestcalls.Profile.Profile;
import com.example.admin.githubrestcalls.Repos.Owner;
import com.example.admin.githubrestcalls.Repos.Repo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static java.lang.Thread.sleep;

public class RepoActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.github.com/users/bufferingspirit/repos";
    private static final String TAG = "RepoActivity";

    final OkHttpClient client = new OkHttpClient();

    final Request request = new Request.Builder()
            .url(BASE_URL)
            .build();


    String resultResponse = "";

    ArrayList<Repo> repoList = new ArrayList<Repo>();
    RecyclerView rView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    RepoAdapter repoAdapter;
    ArrayList<Repo> tempList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);

        rView = (RecyclerView) findViewById(R.id.rView);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        rView.setLayoutManager(layoutManager);
        rView.setItemAnimator(itemAnimator);

        repoAdapter = new RepoAdapter(repoList);
        rView.setAdapter(repoAdapter);


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                resultResponse = response.body().string();

                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<Repo>>() {}.getType();
                tempList = gson.fromJson(resultResponse, listType);
                System.out.println(tempList);
            }
        });



        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < tempList.size(); i++){
            repoList.add(tempList.get(i));
            Log.d(TAG, "onCreate: " + tempList.get(i).getName());
        }
        repoAdapter.notifyDataSetChanged();
    }

}
