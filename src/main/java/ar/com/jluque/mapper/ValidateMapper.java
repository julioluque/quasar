package ar.com.jluque.mapper;

public class ValidateMapper {

	public ValidateMapper() {
	}

	public static void amountSatellites(double[] distances) {
		if (distances == null || distances.length != 3) {
			throw new IllegalArgumentException("review amount of satelites");
		}
	}

	public static void amountMessages(String[][] messages) {
		if (messages == null || messages.length != 3) {
			throw new IllegalArgumentException("review amount of messages");
		}
	}
	

}
