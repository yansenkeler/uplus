package com.ejb.uplus.api;

import com.ejb.uplus.bean.LoginReturnEntity;
import com.ejb.uplus.bean.SimpleReturnEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by John on 11/11/2016.
 */

public interface ApiStore
{
    String BASE_URL = "http://192.168.1.192:3000/api/m/";

    @FormUrlEncoded
    @POST("user/sendVcode")
    Observable<SimpleReturnEntity> sendIcode(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("user/register")
    Observable<SimpleReturnEntity> register(@Field("vcode") String vcode, @Field("loginId") String loginId, @Field("password") String password);

    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginReturnEntity> login(@Field("loginId") String loginId, @Field("password") String password);
}
