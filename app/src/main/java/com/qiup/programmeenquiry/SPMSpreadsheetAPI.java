package com.qiup.programmeenquiry;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SPMSpreadsheetAPI
{
    @POST("1FAIpQLSf0dmJj4ReBJJ_WjO3RyEKFRy21xVWRLv3-aiX7MYAYsv3g-g/formResponse")

    @FormUrlEncoded
    Call<Void> postToSpreadsheets(
            @Field("entry.543131875") String[] Subjects,
            @Field("entry.2065412412") String[] Grades
    );
}
