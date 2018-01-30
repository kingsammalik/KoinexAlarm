package com.samapps.sachinmalik.koinexalarm.Alarm;

import com.google.gson.annotations.SerializedName;
public class Prices{

	@SerializedName("BTC")
	private String bTC;

	@SerializedName("BCH")
	private String bCH;

	@SerializedName("XRP")
	private String xRP;

	@SerializedName("ETH")
	private String eTH;

	@SerializedName("OMG")
	private double oMG;

	@SerializedName("LTC")
	private String lTC;

	@SerializedName("GNT")
	private double gNT;

	@SerializedName("MIOTA")
	private double mIOTA;

	public void setBTC(String bTC){
		this.bTC = bTC;
	}

	public String getBTC(){
		return bTC;
	}

	public void setBCH(String bCH){
		this.bCH = bCH;
	}

	public String getBCH(){
		return bCH;
	}

	public void setXRP(String xRP){
		this.xRP = xRP;
	}

	public String getXRP(){
		return xRP;
	}

	public void setETH(String eTH){
		this.eTH = eTH;
	}

	public String getETH(){
		return eTH;
	}

	public void setOMG(double oMG){
		this.oMG = oMG;
	}

	public double getOMG(){
		return oMG;
	}

	public void setLTC(String lTC){
		this.lTC = lTC;
	}

	public String getLTC(){
		return lTC;
	}

	public void setGNT(double gNT){
		this.gNT = gNT;
	}

	public double getGNT(){
		return gNT;
	}

	public void setMIOTA(double mIOTA){
		this.mIOTA = mIOTA;
	}

	public double getMIOTA(){
		return mIOTA;
	}

	@Override
 	public String toString(){
		return 
			"Prices{" + 
			"bTC = '" + bTC + '\'' + 
			",bCH = '" + bCH + '\'' + 
			",xRP = '" + xRP + '\'' + 
			",eTH = '" + eTH + '\'' + 
			",oMG = '" + oMG + '\'' + 
			",lTC = '" + lTC + '\'' + 
			",gNT = '" + gNT + '\'' + 
			",mIOTA = '" + mIOTA + '\'' + 
			"}";
		}
}