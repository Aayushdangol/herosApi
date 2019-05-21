package com.e.herosapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;

import ClientApi.HeroesApi;
import model.Heroes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import url.Url;

public class AddHeroActivity extends AppCompatActivity {


    private EditText etName, etDesc;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hero);


        etName= findViewById(R.id.etHeroName);
        etDesc = findViewById(R.id.etHeroDesc);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }



    private void save(){
        String name = etName.getText().toString();
        String desc = etDesc.getText().toString();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();


        HeroesApi heroesApi = retrofit.create(HeroesApi.class);

        Call<Void> heroesCall = heroesApi.addHeroes(name,desc);

        heroesCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(AddHeroActivity.this, "Code" + response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(AddHeroActivity.this,"Successfully Added",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            Toast.makeText(AddHeroActivity.this,"Error"+ t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }





}
