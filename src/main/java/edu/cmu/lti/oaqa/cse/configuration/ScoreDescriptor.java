package edu.cmu.lti.oaqa.cse.configuration;

public class ScoreDescriptor {

	private double cost, benefit;

	public ScoreDescriptor(double cost, double benefit) {
		this.cost = cost;
		this.benefit = benefit;
	}

	public double getCost() {
		return cost;
	}
	
	public double getBenefit() {
		return benefit;
	}

}
