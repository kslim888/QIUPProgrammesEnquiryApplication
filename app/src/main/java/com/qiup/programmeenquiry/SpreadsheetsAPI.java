package com.qiup.programmeenquiry;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SpreadsheetsAPI
{

    @POST("1FAIpQLSd70K4tb93owJ7e8uyYXaZXAMOrlwrWUWnTG91SALNfwmDObQ/formResponse")

    @FormUrlEncoded
    Call<Void> postToSpreadsheets(
            @Field("entry.619355424") String Name,
            @Field("entry.1332327210") String IC,
            @Field("entry.1931534680") String contactNumber,
            @Field("entry.1048499227") String Email,
            @Field("entry.1244563596") String Remark
    );
}
