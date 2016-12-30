package uci.develops.wiraenergimobile.service;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import uci.develops.wiraenergimobile.response.ApproveResponse;
import uci.develops.wiraenergimobile.response.CustomerAddressResponse;
import uci.develops.wiraenergimobile.response.CustomerGroupResponse;
import uci.develops.wiraenergimobile.response.ListCustomerAddressResponse;
import uci.develops.wiraenergimobile.response.ListCustomerGroupResponse;
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

    @GET("customers/group")
    Call<ListCustomerGroupResponse> getAllCustomerGroup(@Header("Authorization") String token);

    @GET("customers/group/{decode_group}")
    Call<CustomerGroupResponse> getCustomerGroupByDecode(@Header("Authorization") String token, @Path("decode_group") String decode_group);

    @GET("roleuser")
    Call<ListRoleResponse> getAllRoles(@Header("Authorization") String token);

    @FormUrlEncoded
    @PUT("customer/{decode}")
    Call<ApproveResponse> requestCustomerAction(@Header("Authorization") String token, @Path("decode") String decode, @Field("approve") int approve, @Field("active") int active);

    @FormUrlEncoded
    @PUT("customer/{decode}")
    Call<ApproveResponse> sendDataCompanyInfo(@Header("Authorization") String token, @Path("decode") String decode, @Field("code") String code, @Field("first_name") String first_name, @Field("last_name") String last_name,
                                              @Field("address") String address, @Field("city") String city, @Field("province") String province, @Field("phone") String phone,
                                              @Field("mobile") String mobile, @Field("fax") String fax, @Field("term") String term, @Field("group") String group, @Field("valuta") String valuta, @Field("npwp") String npwp,
                                              @Field("tax") String tax, @Field("email") String email, @Field("website") String website, @Field("note") String note, @Field("postcode") String postcode);

    @FormUrlEncoded
    @PUT("customer/{decode}")
    Call<ApproveResponse> sendDataContactInfo(@Header("Authorization") String token, @Path("decode") String decode, @Field("name1") String name1, @Field("name2") String name2,
                                              @Field("name3") String name3, @Field("phone1") String phone1, @Field("phone2") String phone2, @Field("phone3") String phone3,
                                              @Field("mobile1") String mobile1, @Field("mobile2") String mobile2, @Field("mobile3") String mobile3, @Field("email1") String email1,
                                              @Field("email2") String email2, @Field("email3") String email3, @Field("jabatan1") String jabatan1, @Field("jabatan2") String jabatan2,
                                              @Field("jabatan3") String jabatan3);

    @FormUrlEncoded
    @PUT("customers/address/{decode}")
    Call<ApproveResponse> sendDataShippingInfo(@Header("Authorization") String token, @Path("decode") String decode, @Field("name") String name, @Field("address") String address,
                                               @Field("pic") String pic, @Field("map") String map, @Field("phone") String phone, @Field("mobile") String mobile);

    @FormUrlEncoded
    @PUT("customers/address/{decode}")
    Call<ApproveResponse> sendDataShippingInfoNew(@Header("Authorization") String token, @Path("decode") String decode, @FieldMap Map<String, String> param);

    @GET("customers/address/{decode}")
    Call<ListCustomerAddressResponse> getCustomerAddress(@Header("Authorization") String token, @Path("decode") String decode);

    @FormUrlEncoded
    @POST("customers/address/{decode}")
    Call<ApproveResponse> createCustomerAddress(@Header("Authorization") String token, @Path("decode") String decode, @FieldMap Map<String, String> param);

    @GET("customers/address/{decode}/{decode_customer_address}")
    Call<CustomerAddressResponse> getCustomerAddressByDecode(@Header("Authorization") String token, @Path("decode") String decode, @Path("decode_customer_address") String decode_customer_address);

    @DELETE("customers/address/{decode}/{decode_customer_address}")
    Call<ApproveResponse> deleteCustomerAddress(@Header("Authorization") String token, @Path("decode") String decode, @Path("decode_customer_address") String decode_customer_address);

    @FormUrlEncoded
    @PUT("customer/{decode}")
    Call<ApproveResponse> updateContactInfo1(@Header("Authorization") String token, @Path("decode") String decode, @Field("name1") String name1, @Field("phone1") String phone1,
                                             @Field("mobile1") String mobile1, @Field("email1") String email1, @Field("jabatan1") String jabatan1);

    @FormUrlEncoded
    @PUT("customer/{decode}")
    Call<ApproveResponse> updateDecodeCustomer(@Header("Authorization") String token, @Path("decode") String decode, @Field("decode") String customer_decode);

    @FormUrlEncoded
    @PUT("customer/{decode}")
    Call<ApproveResponse> updateContactInfo2(@Header("Authorization") String token, @Path("decode") String decode, @Field("name2") String name2, @Field("phone2") String phone2,
                                             @Field("mobile2") String mobile2, @Field("email2") String email2, @Field("jabatan2") String jabatan2);

    @FormUrlEncoded
    @PUT("customer/{decode}")
    Call<ApproveResponse> updateContactInfo3(@Header("Authorization") String token, @Path("decode") String decode, @Field("name3") String name3, @Field("phone3") String phone3,
                                             @Field("mobile3") String mobile3, @Field("email3") String email3, @Field("jabatan3") String jabatan3);

    @GET("customer/{decode}")
    Call<CustomerResponse> getCustomer(@Header("Authorization") String token, @Path("decode") String decode);

    @FormUrlEncoded
    @PUT("customer/{decode}")
    Call<ApproveResponse> addFeedback(@Header("Authorization") String token, @Path("decode") String decode, @Field("note") String note, @Field("approve") int approve, @Field("active") int active);

    @GET("user/{id_user}")
    Call<UserResponse> getUser(@Header("Authorization") String token, @Path("id_user") int id_user);

    @FormUrlEncoded
    @PUT("user/{id_user}")
    Call<ApproveResponse> updateCustomerDecode(@Header("Authorization") String token, @Path("id_user") int id_user, @FieldMap Map<String, String> param);

    @FormUrlEncoded
    @PUT("customer/{decode}")
    Call<ApproveResponse> customerSubmit(@Header("Authorization") String token, @Path("decode") String decode, @Field("approve") int approve);

    @FormUrlEncoded
    @PUT("customer/{decode}")
    Call<ApproveResponse> customerUpdateApproveActive(@Header("Authorization") String token, @Path("decode") String decode, @Field("approve") int approve, @Field("active") int active);

    @FormUrlEncoded
    @POST("customers/register/approve")
    Call<ApproveResponse> customerApproveActive(@Header("Authorization") String token, @Field("decode") String decode);

    @FormUrlEncoded
    @POST("customers/register/reject/0")
    Call<ApproveResponse> customerRejectDeleteCustomer(@Header("Authorization") String token, @Field("decode_new") String decode_new);

    @FormUrlEncoded
    @POST("customers/register/reject/1")
    Call<ApproveResponse> customerRejectUpdateUser(@Header("Authorization") String token, @Field("decode_old") String decode_old, @Field("decode_new") String decode_new);



}