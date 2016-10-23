package uci.develops.wiraenergimobile.service;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import uci.develops.wiraenergimobile.response.ListRoleResponse;
import uci.develops.wiraenergimobile.response.LoginResponse;
import uci.develops.wiraenergimobile.response.RegisterResponse;
import uci.develops.wiraenergimobile.response.RequestListCustomerResponse;

/**
 * Created by user on 10/22/2016.
 */
public interface RestInterface {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> Login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> Register(@Field("name") String name, @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @GET("customer")
    Call<RequestListCustomerResponse> getAllRequestCustomer(@Header("Authorization") String token);

    @FormUrlEncoded
    @GET("roles")
    Call<ListRoleResponse> getAllRoles(@Header("Authorization") String token);
}
