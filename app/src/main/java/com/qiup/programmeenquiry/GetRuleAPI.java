package com.qiup.programmeenquiry;

import com.qiup.POJO.RulePojo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetRuleAPI {
    @GET("/v0/b/main-stack-194212.appspot.com/o/AllProgramme.json?alt=media&token=9d03debb-1568-4868-8b91-8f006da908b7")
    Call<RulePojo> getRule();
}
