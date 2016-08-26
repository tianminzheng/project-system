package com.tianyalan.core.domain.model.project;

import com.tianyalan.common.domain.model.ValueObject;

public class ProjectPriority extends ValueObject {

	private int benefit;
	private int cost;
	private int risk;
	
	public ProjectPriority() {		
	}
	
	public ProjectPriority(int benefit, int cost, int risk) {
		this();
		
		this.benefit = benefit;
		this.cost = cost;
		this.risk = risk;
	}
	
	public int getBenefit() {
		return benefit;
	}
	public int getCost() {
		return cost;
	}
	public int getRisk() {
		return risk;
	}
}
