package com.clgdante.d3paraxp.database;

public class DBElement {
	private int plvl;
	private long totalExp;

	public DBElement(int plvl, long totalExp) {
		this.plvl = plvl;
		this.totalExp = totalExp;
	}

	public int getPlvl() {
		return plvl;
	}

	public long getTotalExp() {
		return totalExp;
	}
}
