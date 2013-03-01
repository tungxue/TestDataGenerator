/**
 * 
 */
package com.tungxue.datautil;

import java.math.BigInteger;

/**
 * @author tungxue
 * 
 */
public class BigIntegerGet {
	public static String getAdd(String Str1, String Str2) {
		String Str3 = new String();
		BigInteger BigInt1 = new BigInteger(Str1);
		BigInteger BigInt2 = new BigInteger(Str2);
		BigInt1 = BigInt1.add(BigInt2);
		Str3 = BigInt1.toString();
		return Str3;
	}

	public static String getSubtract(String Str1, String Str2) {
		String Str3 = new String();
		BigInteger BigInt1 = new BigInteger(Str1);
		BigInteger BigInt2 = new BigInteger(Str2);
		BigInt1 = BigInt1.subtract(BigInt2);
		Str3 = BigInt1.toString();
		return Str3;
	}

	public static String getMultiply(String Str1, String Str2) {
		String Str3 = new String();
		BigInteger BigInt1 = new BigInteger(Str1);
		BigInteger BigInt2 = new BigInteger(Str2);
		BigInt1 = BigInt1.multiply(BigInt2);
		Str3 = BigInt1.toString();
		return Str3;
	}

	public static String getDivide(String Str1, String Str2) {
		String Str3 = new String();
		BigInteger BigInt1 = new BigInteger(Str1);
		BigInteger BigInt2 = new BigInteger(Str2);
		BigInt1 = BigInt1.divide(BigInt2);
		Str3 = BigInt1.toString();
		return Str3;
	}

	public static String getRemainder(String Str1, String Str2) {// ％
		String Str3 = new String();
		BigInteger BigInt1 = new BigInteger(Str1);
		BigInteger BigInt2 = new BigInteger(Str2);
		BigInt1 = BigInt1.remainder(BigInt2);
		Str3 = BigInt1.toString();
		return Str3;
	}

	public static String getGcd(String Str1, String Str2) {
		String Str3 = new String();
		BigInteger BigInt1 = new BigInteger(Str1);
		BigInteger BigInt2 = new BigInteger(Str2);
		BigInt1 = BigInt1.gcd(BigInt2);
		Str3 = BigInt1.toString();
		return Str3;
	}

	public static String getPow(String Str1, String Str2) {
		String Str3 = new String();
		BigInteger BigInt1 = new BigInteger(Str1);
		int Int2 = Integer.valueOf(Str2);
		BigInt1 = BigInt1.pow(Int2);
		Str3 = BigInt1.toString();
		return Str3;
	}

	public static String getMod(String Str1, String Str2) {
		String Str3 = new String();
		BigInteger BigInt1 = new BigInteger(Str1);
		BigInteger BigInt2 = new BigInteger(Str2);
		BigInt1 = BigInt1.mod(BigInt2);
		Str3 = BigInt1.toString();
		return Str3;
	}

	public static String getModInverse(String Str1, String Str2) {
		String Str3 = new String();
		BigInteger BigInt1 = new BigInteger(Str1);
		BigInteger BigInt2 = new BigInteger(Str2);
		BigInt1 = BigInt1.modInverse(BigInt2);
		Str3 = BigInt1.toString();
		return Str3;
	}

	public static String getMax(String Str1, String Str2) {
		String Str3 = new String();
		BigInteger BigInt1 = new BigInteger(Str1);
		BigInteger BigInt2 = new BigInteger(Str2);
		BigInt1 = BigInt1.max(BigInt2);
		Str3 = BigInt1.toString();
		return Str3;
	}

	public static String getMin(String Str1, String Str2) {
		String Str3 = new String();
		BigInteger BigInt1 = new BigInteger(Str1);
		BigInteger BigInt2 = new BigInteger(Str2);
		BigInt1 = BigInt1.min(BigInt2);
		Str3 = BigInt1.toString();
		return Str3;
	}

	public static int getHashcode(String Str) {
		int hash = -1;
		BigInteger BigInt = new BigInteger(Str);
		hash = BigInt.hashCode();
		return hash;
	}

	public static boolean getIsProbablePrime(String Str, int certainty) {
		boolean flag = false;
		BigInteger BigInt = new BigInteger(Str);
		flag = BigInt.isProbablePrime(certainty);
		return flag;
	}
}
