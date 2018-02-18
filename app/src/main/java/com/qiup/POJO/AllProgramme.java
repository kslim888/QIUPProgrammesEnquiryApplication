package com.qiup.POJO;

import com.google.gson.annotations.SerializedName;

public class AllProgramme {

	// Foundation
	@SerializedName("FIS")
	private FIS fis;

	@SerializedName("FIB")
	private FIB fib;

	@SerializedName("FIA")
	private FIA fia;

	// Diploma
	@SerializedName("DAC")
	private DAC dac;

	@SerializedName("DBM")
	private DBM dbm;

	@SerializedName("DHM")
	private DHM dhm;

	@SerializedName("DCE")
	private DCE dce;

	@SerializedName("DIT")
	private DIT dit;

	@SerializedName("DIS")
	private DIS dis;

	@SerializedName("DET")
	private DET det;

	@SerializedName("DME")
	private DME dme;

	// Degree
	@SerializedName("BHT")
	private BHT bht;

	@SerializedName("BCE")
	private BCE bce;

	@SerializedName("BSNE")
	private BSNE bsne;

	// Foundation
	public FIS getFis() {
		return fis;
	}

	public FIB getFib() {
		return fib;
	}

	public FIA getFia() {
		return fia;
	}

	// Diploma
	public DME getDme() { return dme; }

	public DET getDet() {
		return det;
	}

	public DAC getDac() {
		return dac;
	}

	public DBM getDbm() {
		return dbm;
	}

	public DHM getDhm() {
		return dhm;
	}

	public DCE getDce() {
		return dce;
	}

	public DIT getDit() {
		return dit;
	}

	public DIS getDis() {
		return dis;
	}

	// Degree

	public BHT getBht() { return bht; }

	public BCE getBce() { return bce; }

	public BSNE getBsne() { return bsne; }
}