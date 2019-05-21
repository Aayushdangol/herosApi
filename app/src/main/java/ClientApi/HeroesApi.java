package ClientApi;


import java.util.List;

import model.Heroes;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HeroesApi {


    //body bata gare ko
    @POST("heroes")
    Call<Void> registerHeroes(@Body Heroes heroes);


    @GET("heroes")
    Call<List<Heroes>> getHeroes();

    //field ko
    @FormUrlEncoded
    @POST("heroes")
    Call<Void> addHeroes(@Field("name") String name, @Field("desc") String desc);


}
