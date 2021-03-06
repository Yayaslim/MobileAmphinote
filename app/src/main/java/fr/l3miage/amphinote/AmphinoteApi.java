package fr.l3miage.amphinote;

import java.util.List;
import java.util.Map;

import fr.l3miage.amphinote.model.NoteModel;
import fr.l3miage.amphinote.model.UserModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

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
        @Multipart
        @POST("NoteRest.php")
        Call<NoteModel> setNote(@Part ("titre") RequestBody titre,
                                @Part ("description")RequestBody description,
                                @Part MultipartBody.Part photo,
                                @Part ("userid") Integer userid,
                                @Part ("matiere") RequestBody matiere
                                );
        @FormUrlEncoded
        @POST("NoteRest.php")
        Call<Integer> setLike(@Field("aime") Integer aime,
                              @Field("userid") Integer userid,
                              @Field("noteid") Integer noteid);
        @GET("NoteRest.Php")
        Call<Integer> getLike(@Query("userid") Integer userid,
                              @Query("noteid") Integer noteid);

        @GET("NoteRest.php")
        Call<List<NoteModel>> getNote(@QueryMap Map<String,Object> query);

        @PATCH("UserRest.php/{id}")
        Call<UserModel> changeUser(@Path("id") Integer id,
                                   @Body String body);
    }

