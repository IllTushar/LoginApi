package com.example.sampleretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sampleretrofit.Api.ApiServices;
import com.example.sampleretrofit.Api.RetrofitClient;
import com.example.sampleretrofit.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
EditText email,password;
Button login;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    processdata(email.getText().toString(),password.getText().toString());
                }
                catch(Exception e){
                    Log.d("##Error",e.getMessage());
                }
            }
        });
    }
    public void processdata(String Email, String Password){
        ApiServices apiServices = RetrofitClient.getRetrofit().create(ApiServices.class);
        Call<ResponseModel>call = apiServices.getData(Email,Password);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()){
                    Log.d("##check",response.body().getToken());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failled", Toast.LENGTH_SHORT).show();
            }
        });
    }
}