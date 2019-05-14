package com.peepeep.transport.interfaces;


import com.peepeep.transport.servicerequest.responsemodels.ForgetPassword;
import com.peepeep.transport.servicerequest.responsemodels.LoginDataset;
import com.peepeep.transport.servicerequest.responsemodels.RegistrationDataset;
import com.peepeep.transport.servicerequest.retrofitrequestparams.Retrofit_RequestParams;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ramesh.eerla on 1/12/2017.
 */

public interface PPRequestInterface {
    /**
     * Created by ramesh.eerla on 1/12/2017.
     *
     * @Path("version") :version number like(v2/mobile)
     * @Path("methodname") : webapi method name
     * @Body : parameters for the request
     */

   @POST("{methodname}")
    Call<LoginDataset> user_login(@Path("methodname") String methodname, @Query("userName") String userName,@Query("password") String password);

    @POST("{methodname}")
    Call<ForgetPassword> user_forgetPassword(@Path("methodname") String methodname, @Query("userName") String userName);

    @POST("{methodname}")
    Call<RegistrationDataset> peeppRegistration(@Path("methodname") String methodname, @Body Retrofit_RequestParams.RegistrationParams task);

   /*
    @POST("{methodname}")
    Call<Object> searchrooms(@Path("methodname") String methodname, @Body RequestParams.SearchTypes task);

    @POST("{methodname}")
    Call<Object> getMy_Properties(@Path("methodname") String methodname, @Body RequestParams.MyProperties task);

    @POST("{methodname}")
    Call<List<Dataset>> searchtypes(@Path("methodname") String methodname, @Body RequestParams.SearchValues task);

    @POST("{methodname}")
    Call<LoginDataset> login(@Path("methodname") String version, @Body RequestParams.LoginDetails task);

    @GET("{methodname}")
    Call<List<Countriesmodel>> getarea(@Path("methodname") String methodname);

    @GET("{methodname}")
    Call<List<Countriesmodel>> getareadata(@Path("methodname") String methodname, @QueryMap Map<String, String> options);


    @POST("{methodname}")
    Call<RetrofitResponse> submitRoom(@Path("methodname") String methodname, @Body RequestParams.Room_POST task);

    @POST("{methodname}")
    Call<RetrofitResponse> submitRommate(@Path("methodname") String methodname, @Body RequestParams.Roommate_POST task);

    @POST("{methodname}")
    Call<RetrofitResponse> submitHome(@Path("methodname") String methodname, @Body RequestParams.Home_POST task);

    @POST("{methodname}")
    Call<RetrofitResponse> submitPayinggust(@Path("methodname") String methodname, @Body RequestParams.PayingGust_POST task);

    @POST("{methodname}")
    Call<RetrofitResponse> submitHostel(@Path("methodname") String methodname, @Body RequestParams.Hostel_POST task);

    @POST("{methodname}")
    Call<RetrofitResponse> updateuserdata(@Path("methodname") String methodname, @Body RequestParams.PersonalProfile task);

    @POST("{methodname}")
    Call<RetrofitResponse> updatePassword(@Path("methodname") String methodname, @Body RequestParams.ChangePassword task);

    @POST("{methodname}")
    Call<RetrofitResponse> sendmail(@Path("methodname") String methodname, @Body RequestParams.SendMail task);

    @POST("{methodname}")
    Call<RetrofitResponse> bookmars(@Path("methodname") String methodname, @Body RequestParams.BookMarkset task);

    @GET("place/nearbysearch/json?")
    Call<PlacesPOJO.Root> doPlaces(@Query(value = "type", encoded = true) String type, @Query(value = "location", encoded = true) String location, @Query(value = "name", encoded = true) String name, @Query(value = "opennow", encoded = true) boolean opennow, @Query(value = "rankby", encoded = true) String rankby, @Query(value = "key", encoded = true) String key);


    @GET("distancematrix/json") // origins/destinations:  LatLng as string
    Call<ResultDistanceMatrix> getDistance(@Query("key") String key, @Query("origins") String origins, @Query("destinations") String destinations);
*/
}
