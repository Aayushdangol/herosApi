package com.e.herosapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ClientApi.HeroesApi;
import model.Heroes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {

    private final static String  BASE_URL="http://10.0.2.2:3000/";
    private Button btnReg;
    private EditText etName,etDes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        btnReg = findViewById(R.id.btnReg);
        etName = findViewById(R.id.etName);
        etDes = findViewById(R.id.etDesc);


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
                clear();
            }
        });
    }


    private void register(){
            String name = etName.getText().toString();
           String desc = etDes.getText().toString();



            Heroes heroes = new Heroes(name,desc);

            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();



             HeroesApi  heroesApi = retrofit.create(HeroesApi.class);

            Call<Void> voidCall = heroesApi.registerHeroes(heroes);


            voidCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(PostActivity.
                            this,"You have been successfully registered ",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(PostActivity.this,
                            "Error :" + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                }
            });

            Intent intent = new Intent(PostActivity.this,MainActivity.class);
            startActivity(intent);
        }


        private void clear(){
            etName.setText("");
            etDes.setText("");

        }




}
