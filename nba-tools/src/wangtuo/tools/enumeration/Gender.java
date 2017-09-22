package wangtuo.tools.enumeration;

public enum Gender {
	MALE(0), FEMALE(1);
	
	private int gender;

	private Gender(int value) {
		gender = value;
	}

	public int getGender() {
		return gender;
	}
}
