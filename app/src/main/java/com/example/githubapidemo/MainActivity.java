package com.example.githubapidemo;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.model.AccessToken;
import com.example.rest.UserEndPoint;

public class MainActivity extends AppCompatActivity {
    EditText editTextUsername;
    private String clientId = "ae128aff6aecfa9a6c6f";
    private String clientSecret = "cea4a542ce0882ce3096c78c9982e37f85f82865";
    private String redirectUri = "githubhome://callback";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextInputUsername);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize" + "?client_id=" + clientId + "&scope=repo&redirect_uri=" + redirectUri));
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Uri uri = getIntent().getData();
        if(uri != null && uri.toString().startsWith(redirectUri)){
            String code = uri.getQueryParameter("code");


            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl("https://github.com/")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = builder.build();

            UserEndPoint client = retrofit.create(UserEndPoint.class);

            Call<AccessToken> accessTokenCall = client.getAccessToken(
                    clientId,
                    clientSecret,
                    code
            );
            accessTokenCall.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                    Toast.makeText(MainActivity.this, "Response!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this,response.body().getAccessToken() , Toast.LENGTH_SHORT).show();
                    Log.i("TOKEN",response.body().getAccessToken() );
                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();

                }
            });


        }
    }

    public void logInUser(View view){
        Intent intent = new Intent(MainActivity.this, UserActivity.class);
        intent.putExtra("username", editTextUsername.getText().toString());
        startActivity(intent);

    }

}