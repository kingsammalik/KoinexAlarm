package com.samapps.sachinmalik.koinexalarm.Alarm;

import com.google.gson.annotations.SerializedName;

public class TickerResponse{

	@SerializedName("stats")
	private Stats stats;

	@SerializedName("prices")
	private Prices prices;

	public void setStats(Stats stats){
		this.stats = stats;
	}

	public Stats getStats(){
		return stats;
	}

	public void setPrices(Prices prices){
		this.prices = prices;
	}

	public Prices getPrices(){
		return prices;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"stats = '" + stats + '\'' + 
			",prices = '" + prices + '\'' + 
			"}";
		}
}