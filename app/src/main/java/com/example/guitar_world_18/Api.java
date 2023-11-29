package com.example.guitar_world_18;

    import java.util.List;
    import retrofit2.Call;
    import retrofit2.http.GET;
    import retrofit2.http.POST;
    import retrofit2.http.Header;
    import retrofit2.http.Path;
    import retrofit2.http.Body;

public interface Api {
        String BASE_URL = "http://10.0.2.2/guitar-world/api/";
        @GET("guitar")
        Call<List<Guitar>> getGuitars();
        @GET("guitar/{id}")
        Call<List<Guitar2>> getGuitarById(@Path("id") int guitarId);

        @GET("user/login")
        Call<User> loginWithGet(@Header("email") String email, @Header("password") String password);

        @POST("user/login")
        Call<User> loginWithPost(@Body User user);
    }

