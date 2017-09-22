package wangtuo.tools.enumeration;

public enum GameType {
	PRECEDING_SEASON(0), REGULAR_SEASON(1), PLAYOFFS(2), FINALS(3);

	int type;

	private GameType(int value) {
		this.type = value;
	}

	public int getType() {
		return type;
	}
}
