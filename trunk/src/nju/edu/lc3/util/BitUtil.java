package nju.edu.lc3.util;

public class BitUtil {

	public BitUtil() {
	}

	/**
	 * ��ָ��byte������16���Ƶ���ʽ��ӡ������̨
	 * 
	 * @param hint
	 *            String
	 * @param b
	 *            byte[]
	 * @return void
	 */

	public static String toBinString(int value) {
		String temp = Integer.toBinaryString(value);
		for (; temp.length() < 16; temp = "0" + temp)
			;

		return temp;
	}

	public static String toHexString(int value) {
		String temp = Integer.toHexString(value);
		for (; temp.length() < 4; temp = "0" + temp)
			;
		temp = "0x" + temp;
		return temp;
	}

	public static int toInt(String hex) {
		int result;
		String subString = hex.substring(hex.indexOf('x') + 1);
		result = Integer.valueOf(subString, 16);
		return result;
	}

	public static int bitarrayToInt(char[] bit, int start, int length) {
		char[] temp = new char[length];
		for (int i = start; i < start+length; i++) {
			temp[i] = bit[i];
		}

		int value = 0;
		for (int i = 0; i < temp.length; i++) {
			value += value(temp[i]) * Math.pow(2, temp.length - 1 - i);
		}
		return value;
	}

	private static int value(char i) {
		if (i == '0') {
			return 0;
		} else {
			return 1;
		}
	}

	public static void printHexString(String hint, byte[] b) {
		System.out.print(hint);
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			System.out.print(hex.toUpperCase() + " ");
		}
		System.out.println("");
	}

	/**
	 * 
	 * @param b
	 *            byte[]
	 * @return String
	 */
	public static String Bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
		}
		return ret;
	}

	/**
	 * ������ASCII�ַ��ϳ�һ���ֽڣ� �磺"EF"--> 0xEF
	 * 
	 * @param src0
	 *            byte
	 * @param src1
	 *            byte
	 * @return byte
	 */
	public static byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 }))
				.byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 }))
				.byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	/**
	 * ��ָ���ַ���src����ÿ�����ַ��ָ�ת��Ϊ16������ʽ �磺"2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF,
	 * 0xD9}
	 * 
	 * @param src
	 *            String
	 * @return byte[]
	 */
	public static byte[] HexString2Bytes(String src) {
		byte[] ret = new byte[8];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < 8; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}

}