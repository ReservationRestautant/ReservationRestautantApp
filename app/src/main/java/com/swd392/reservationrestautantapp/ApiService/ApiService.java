package com.swd392.reservationrestautantapp.ApiService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.swd392.reservationrestautantapp.model.DataLogin;
import com.swd392.reservationrestautantapp.model.ReservationDTO;
import com.swd392.reservationrestautantapp.model.Reservation;
import com.swd392.reservationrestautantapp.model.ReservationHistory;
import com.swd392.reservationrestautantapp.model.ResponseObject;
import com.swd392.reservationrestautantapp.model.Spam;
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
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
            .baseUrl("http://172.18.208.1:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okBuilder.build())
            .build()
            .create(ApiService.class);

    @GET("api/Customer")
    Call<ResponseObject<List<UserSystem>>> getAllUser(@Header("Authorization") String token);

    @POST("api/Reservation?discount=0")
    Call<ResponseObject<Object>> booking(@Body ReservationDTO reservationDTO);
  
    @GET("api/Reservation/history")
    //api/Reservation/history?Userid=2
    Call<ResponseObject<List<ReservationHistory>>> getReservationById(@Header("Authorization") String token, @Query("Userid") int Userid);

    @GET("api/Reservation/detail")
    //api/Reservation/history?Userid=2
    Call<ResponseObject<ReservationHistory>> getdetailReservationById(@Header("Authorization") String token, @Query("id") int id);
  
    @POST("api/Customer")
    Call<ResponseObject<List<UserSystem>>> createUser(@Body UserSystem userSystem);

    @POST("api/auth/login")
    Call<ResponseObject<DataLogin>> login(@Query("phone") String phone,
                                          @Query("password") String password);

    @GET("api/Spam/detail")
    Call<ResponseObject<Spam>> spam(@Header("Authorization") String token, @Query("phone") String phone);

    @GET("api/Customer/search")
    Call<ResponseObject<UserSystem>> detail(@Header("Authorization") String token, @Query("phone") String phone);

    @PUT("api/Customer?action=Update")
    Call<ResponseObject<UserSystem>> updateProfile(@Header("Authorization") String token, @Body UserSystem userSystem);
}
