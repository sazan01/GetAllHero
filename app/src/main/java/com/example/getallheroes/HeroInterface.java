package com.example.getallheroes;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HeroInterface {
    @FormUrlEncoded
    @POST("heroes")
    Call<Void> addHeroes(@Field("name") String name, @Field("desc") String desc);

    @GET("heroes")
    Call<List<HeroInterface>> getHeroes();

    @FormUrlEncoded
    @POST("heroes")
    Call<Void> addHero(@FieldMap Map<String,String> map);
}
