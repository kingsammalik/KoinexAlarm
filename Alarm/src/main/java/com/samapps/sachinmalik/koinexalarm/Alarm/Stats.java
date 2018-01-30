package com.samapps.sachinmalik.koinexalarm.Alarm;

import com.google.gson.annotations.SerializedName;

public class Stats{

	@SerializedName("BTC")
	private BTC bTC;

	@SerializedName("BCH")
	private BCH bCH;

	@SerializedName("XRP")
	private XRP xRP;

	@SerializedName("ETH")
	private ETH eTH;

	@SerializedName("LTC")
	private LTC lTC;

	public void setBTC(BTC bTC){
		this.bTC = bTC;
	}

	public BTC getBTC(){
		return bTC;
	}

	public void setBCH(BCH bCH){
		this.bCH = bCH;
	}

	public BCH getBCH(){
		return bCH;
	}

	public void setXRP(XRP xRP){
		this.xRP = xRP;
	}

	public XRP getXRP(){
		return xRP;
	}

	public void setETH(ETH eTH){
		this.eTH = eTH;
	}

	public ETH getETH(){
		return eTH;
	}

	public void setLTC(LTC lTC){
		this.lTC = lTC;
	}

	public LTC getLTC(){
		return lTC;
	}

	@Override
 	public String toString(){
		return 
			"Stats{" + 
			"bTC = '" + bTC + '\'' + 
			",bCH = '" + bCH + '\'' + 
			",xRP = '" + xRP + '\'' + 
			",eTH = '" + eTH + '\'' + 
			",lTC = '" + lTC + '\'' + 
			"}";
		}
}