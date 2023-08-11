package utils.utility;

import java.util.regex.Pattern;

public class Regex {
	public static boolean isValidMobileNumber(String mobileNumber) {
		Pattern ptn = Pattern.compile("[0-9]{10}");
		return ptn.matcher(mobileNumber).find();
	}

	public static boolean isValidEmailId(String email) {
		Pattern ptn = Pattern.compile("^[a-zA-Z0-9_.]+@[a-zA-Z]+(?:\\.[a-zA-Z]+)*$");
		return ptn.matcher(email).find();
	}

	public static boolean isInteger(String integer) {
		Pattern ptn = Pattern.compile("^-?\\d+$");
		return ptn.matcher(String.valueOf(integer)).find();
	}

	public static boolean isNumber(String number) {
		Pattern ptn = Pattern.compile("^-?\\d+(\\.\\d*)?$");
		return ptn.matcher(String.valueOf(number)).find();
	}

	public static boolean isDouble(String doubleNumber) {
		Pattern ptn = Pattern.compile("^-?\\d+\\.\\d+$");
		return ptn.matcher(String.valueOf(doubleNumber)).find();
	}

	public static boolean isNonZeroInteger(String integer) {
		Pattern ptn = Pattern.compile("^[1-9]\\d*$");
		return ptn.matcher(String.valueOf(integer)).find();
	}

	public static boolean isNonZeroNumber(String doubleNumber) {
		Pattern ptn = Pattern.compile("^[+]?\\d+(\\.\\d+)?$");
		return ptn.matcher(String.valueOf(doubleNumber)).find();
	}

	public static boolean isNonZeroDoubleNumber(String doubleNumber) {
		Pattern ptn = Pattern.compile("^(?!0\\.0$)(?!\\d+$)\\d*\\.\\d+$");
		return ptn.matcher(String.valueOf(doubleNumber)).find();
	}

	public static boolean isContainsSpecialCharacter(String doubleNumber) {
		Pattern ptn = Pattern.compile("[!\"#$%&'()*+,-./:;<=>?@[\\\\]^_`{|}~\\s]");
		return ptn.matcher(String.valueOf(doubleNumber)).find();
	}

	public static boolean isAlphabet(String charSeq) {
		Pattern ptn = Pattern.compile("^[a-zA-Z]+$");
		return ptn.matcher(String.valueOf(charSeq)).find();
	}

	public static boolean isAlphaNumeric(String charSeq) {
		Pattern ptn = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$");
		return ptn.matcher(String.valueOf(charSeq)).find();
	}

	public static boolean isAlphaSymbolic(String charSeq) {
		Pattern ptn = Pattern.compile(
				"^(?=.*[a-zA-Z])(?=.*[-!@#$%^&*()_+{}\\[\\]:;<>,.?~\\/\\s])[a-zA-Z\\d!@#$%^&*()_+{}\\[\\]:;<>,.?~\\/\\s]+$");
		return ptn.matcher(String.valueOf(charSeq)).find();
	}

}
