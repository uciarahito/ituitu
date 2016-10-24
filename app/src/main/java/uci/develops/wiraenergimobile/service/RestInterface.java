package uci.develops.wiraenergimobile.service;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import uci.develops.wiraenergimobile.response.ApproveResponse;
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

    @GET("customer")
    Call<RequestListCustomerResponse> getAllRequestCustomer(@Header("Authorization") String token);

    @GET("roleuser")
    Call<ListRoleResponse> getAllRoles(@Header("Authorization") String token);

    @FormUrlEncoded
    @PUT("customer/{decode}")
    Call<ApproveResponse> requestCustomerAction(@Header("Authorization") String token, @Path("decode") String decode, @Field("approve") int approve, @Field("active") int active);

    @FormUrlEncoded
    @PUT("customer/{decode}")
    Call<ApproveResponse> sendDataCompanyInfo(@Header("Authorization") String token, @Path("decode") String decode, @Field("first_name") String first_name, @Field("last_name") String last_name,
                                              @Field("address") String address, @Field("city") String city, @Field("province") String province, @Field("phone") String phone,
                                              @Field("mobile") String mobile, @Field("fax") String fax, @Field("term") String term, @Field("valuta") String valuta, @Field("npwp") String npwp,
                                              @Field("tax") int tax, @Field("email") String email, @Field("website") String website, @Field("note") String note);
}
