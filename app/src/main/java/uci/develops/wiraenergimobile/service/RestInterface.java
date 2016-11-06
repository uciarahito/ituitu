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
import uci.develops.wiraenergimobile.response.CustomerResponse;
import uci.develops.wiraenergimobile.response.ListRoleResponse;
import uci.develops.wiraenergimobile.response.LoginResponse;
import uci.develops.wiraenergimobile.response.RegisterResponse;
import uci.develops.wiraenergimobile.response.RequestListCustomerResponse;
import uci.develops.wiraenergimobile.response.UserResponse;

/**
 * Created by user on 10/22/2016.
 */
public interface RestInterface {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> Login(@Field("email") String email, @Field("password") String password, @Field("registration_key") String registration_key);

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
                                              @Field("tax") int tax, @Field("email") String email, @Field("website") String website, @Field("note") String note, @Field("postcode") String postcode);

    @FormUrlEncoded
    @PUT("customer/{decode}")
    Call<ApproveResponse> sendDataContactInfo(@Header("Authorization") String token, @Path("decode") String decode, @Field("name1") String name1, @Field("name2") String name2,
                                              @Field("name3") String name3, @Field("phone1") String phone1, @Field("phone2") String phone2, @Field("phone3") String phone3,
                                              @Field("mobile1") String mobile1, @Field("mobile2") String mobile2, @Field("mobile3") String mobile3, @Field("email1") String email1,
                                              @Field("email2") String email2, @Field("email3") String email3, @Field("jabatan1") String jabatan1, @Field("jabatan2") String jabatan2,
                                              @Field("jabatan3") String jabatan3);

    @FormUrlEncoded
    @PUT("customer/{decode}")
    Call<ApproveResponse> sendDataShippingInfo(@Header("Authorization") String token, @Path("decode") String decode, @Field("shipping_pic") String shipping_pic, @Field("shipping_address") String shipping_address,
                                               @Field("shipping_city") String shipping_city, @Field("shipping_province") String shipping_province, @Field("shipping_postcode") String shipping_postcode,
                                               @Field("shipping_eta") String shipping_eta, @Field("shipping_map") String shipping_map, @Field("shipping_phone") String shipping_phone, @Field("shipping_mobile") String shipping_mobile,
                                               @Field("shipping_email") String shipping_email, @Field("shipping_fax") String shipping_fax, @Field("shipping_tax") String shipping_tax, @Field("shipping_note") String shipping_note,
                                               @Field("approve") int approve, @Field("active") int active);

    @GET("customer/{decode}")
    Call<CustomerResponse> getCustomer(@Header("Authorization") String token, @Path("decode") String decode);

    @FormUrlEncoded
    @PUT("customer/{decode}")
    Call<ApproveResponse> addFeedback(@Header("Authorization") String token, @Path("decode") String decode, @Field("note") String note, @Field("approve") int approve, @Field("active") int active);

    @GET("user/{id_user}")
    Call<UserResponse> getUser(@Header("Authorization") String token, @Path("id_user") int id_user);

    @FormUrlEncoded
    @PUT("customer/{decode}")
    Call<ApproveResponse> customerSubmit(@Header("Authorization") String token, @Path("decode") String decode, @Field("approve") int approve);
}
