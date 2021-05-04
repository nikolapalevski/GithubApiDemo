package com.example.githubapidemo;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.Commit;
import com.example.model.Repo;
import com.example.rest.ApiClient;
import com.example.rest.UserEndPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    TextView textViewInfo;
    String currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        textViewInfo = findViewById(R.id.textViewInfo);
        currUser = getIntent().getExtras().getString("username");
        Toast.makeText(this, currUser, Toast.LENGTH_SHORT).show();
        loadCommitRetrofit();
        //loadRepos();
    }

    private void loadRepos() {
        final UserEndPoint apiService = ApiClient.getClient().create(UserEndPoint.class);
        Call<List<Repo>> call = apiService.getUserRepos(currUser);
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                for (int i = 0; i<response.body().size(); i++){
                    Toast.makeText(UserActivity.this, response.body().get(i).getName(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });
    }

    private void loadCommitRetrofit() {
        final UserEndPoint apiService = ApiClient.getClient().create(UserEndPoint.class);

        Call<List<Commit>> call = apiService.getDataForUser(currUser);
        call.enqueue(new Callback<List<Commit>>() {
            @Override
            public void onResponse(Call<List<Commit>> call, Response<List<Commit>> response) {
                //Toast.makeText(UserActivity.this, response.body().get(0).getCommit().getMessage(), Toast.LENGTH_SHORT).show();
                for (int i = 0; i<response.body().size(); i++){
                    Toast.makeText(UserActivity.this, response.body().get(i).getCommit().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Commit>> call, Throwable t) {

            }
        });
    }
}