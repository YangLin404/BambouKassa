package be.linyang.kassa.Model.items;

public enum Tax {
	Low(0.12d),High(0.21d);

	private double percent;

	Tax(double percent) {
		this.percent = percent;
	}

	public double getPercent() {
		return percent;
	}
}
