package edu.cmu.lti.oaqa.cse.configuration;

public class ScoreDescriptor implements Comparable<ScoreDescriptor> {

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

	@Override
	public int compareTo(ScoreDescriptor o) {
		double thisRatio = this.getBenefit()
				/ ((this.getCost() == 0) ? Double.MIN_VALUE : this.getCost());
		double thatRatio = o.getBenefit()
				/ ((o.getCost() == 0) ? Double.MIN_VALUE : this.getCost());
		if (thisRatio > thatRatio)
			return 1;
		if (thisRatio < thatRatio)
			return -1;
		return 0;
	}

}
