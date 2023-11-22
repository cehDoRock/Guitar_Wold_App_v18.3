package com.example.guitar_world_18;

    import java.util.List;
    import retrofit2.Call;
    import retrofit2.http.GET;
    public interface Api {
        String BASE_URL = "https://simplifiedcoding.net/demos/";
        @GET("marvel")
        Call<List<Results>> getSuperHeroes();
    }

