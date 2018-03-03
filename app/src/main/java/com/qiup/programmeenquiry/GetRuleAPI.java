package com.qiup.programmeenquiry;

import com.qiup.POJO.RulePojo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetRuleAPI {
    @GET("/AllProgramme.json")
    Call<RulePojo> getRule();
}
