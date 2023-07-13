package com.swd392.reservationrestautantapp.ApiService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.swd392.reservationrestautantapp.model.ReservationDTO;
import com.swd392.reservationrestautantapp.model.Reservation;
import com.swd392.reservationrestautantapp.model.ResponseObject;
import com.swd392.reservationrestautantapp.model.UserSystem;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    //ghi log req and response bằng okhttp3 - ở dưới có hướng dẫn add gradle
    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient.Builder okBuilder = new OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)          // kết hợp vs 1 dòng code ở dưới +
            .connectTimeout(30, TimeUnit.SECONDS)       // dùng để khi connect mà quá 30s ko có result trả về thì nó báo lỗi
            .retryOnConnectionFailure(true)                     //có cố kết nối lại khi nó lỗi ko
            .addInterceptor(loggingInterceptor);

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    //tạo retrofit từ interface class + link api
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.11:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okBuilder.build())
            .build()
            .create(ApiService.class);

    @GET("api/Customer")
    Call<ResponseObject<List<UserSystem>>> getAllUser();

    @POST("api/Reservation?discount=0")
    Call<ResponseObject<Object>> booking(@Body ReservationDTO reservationDTO);
  
    @GET("api/Reservation/history")
    Call<ResponseObject<List<Reservation>>> getReservationById(@Query("Userid") int uid);
  
    @POST("api/User/Registration")
    Call<ResponseObject<List<UserSystem>>> createUser(@Body UserSystem userSystem);

    @POST("api/User/GetUserData")
    Call<ResponseObject<UserSystem>> getUserData(@Query("phone") String phone);

    @FormUrlEncoded
    @POST("api/User/UpdateProfile")
    Call<ResponseObject> updateProfile(
            @Field("fullName") String fullName,
            @Field("phone") String phone,
            @Field("password") String password
    );
}
