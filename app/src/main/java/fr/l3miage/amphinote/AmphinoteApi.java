package fr.l3miage.amphinote;

import java.util.List;

import fr.l3miage.amphinote.model.UserModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AmphinoteApi  {

        @GET("UserRest.php")
        Call<List<UserModel>> getUserdata();
    }

