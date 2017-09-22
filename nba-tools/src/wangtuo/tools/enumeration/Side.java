package wangtuo.tools.enumeration;

public enum Side {
	LEFT(0), RIGHT(1);

	private int side;

	private Side(int value) {
		side = value;
	}

	public int getSide() {
		return side;
	}
}
