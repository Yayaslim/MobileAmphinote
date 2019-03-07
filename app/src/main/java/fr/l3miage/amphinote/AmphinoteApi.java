package fr.l3miage.amphinote;

import java.util.List;
import java.util.Map;

import fr.l3miage.amphinote.model.UserModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AmphinoteApi  {

        @GET("UserRest.php")
        Call<UserModel> getUserdata(@Header("Authorization") String header,
                                    @Query("email") String email);
        @FormUrlEncoded
        @POST("UserRest.php")
        Call<UserModel> setUser(@Field("email") String email,
                                @Field("lastname") String lastname,
                                @Field("username") String username,
                                @Field("password") String password,
                                @Field("age") String age);
    }

