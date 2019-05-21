package com.e.herosapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ClientApi.HeroesApi;
import model.Heroes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import url.Url;

public class GetHeroActivity extends AppCompatActivity {

    private TextView tvdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_hero);

        tvdata = findViewById(R.id.tvData);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        HeroesApi heroesApi = retrofit.create(HeroesApi.class);

        Call<List<Heroes>> heroCall = heroesApi.getHeroes();

        heroCall.enqueue(new Callback<List<Heroes>>() {
            @Override
            public void onResponse(Call<List<Heroes>> call, Response<List<Heroes>> response) {
                if (!response.isSuccessful()){
                    tvdata.setText("Code: "+ response.code());
                    return;
                }

                List<Heroes> heroesList = response.body();
                for (Heroes heroes: heroesList ){

                    String content = "";
                    content += "ID: " + heroes.getId() + "\n";
                    content += "Name: "+heroes.getName() + "\n";
                    content += "ID: " + heroes.getDesc() + "\n";


                tvdata.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Heroes>> call, Throwable t) {
                tvdata.setText("Error " + t.getMessage());
            }
        });
    }
}
