package com.qiup.POJO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WhatSubjectRequired{

	@SerializedName("subject")
	private List<String> subject;

	public List<String> getSubject(){
		return subject;
	}

}