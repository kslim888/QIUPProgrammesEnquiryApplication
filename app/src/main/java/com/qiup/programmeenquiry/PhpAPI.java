package com.qiup.programmeenquiry;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PhpAPI
{
    @POST("test.php")

    @FormUrlEncoded
    Call<Void> postToPHP(
            @Field("subject") String[] subject,
            @Field("grade") String[] grade
    );
}
