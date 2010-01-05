package nju.edu.lc3.util;

public class BitUtil {

	public BitUtil() {
	}

	/**
	 * 将指定byte数组以16进制的形式打印到控制台
	 * 
	 * @param hint
	 *            String
	 * @param b
	 *            byte[]
	 * @return void
	 */

	public static String toBinString(int value) {
		String temp = Integer.toBinaryString(value);
		for (; temp.length() < 16; temp = "0" + temp);

		return temp;
	}
	
	public static int setBits(int source, int from, int length,int value)
	{
		
		char[] tmp1 = intToBitArray(source,16);
		char[] tmp2 = intToBitArray(value,length);
		for(int i = 0;i<length;i++)
		{
			tmp1[from+i] = tmp2[i];
		}
		int result = bitArrayToInt(tmp1,0,16,true);
		
		return result;
	}

	public static String toHexString(int value) {
		String temp = Integer.toHexString(value);
		if(temp.length()>4)
		{
			temp = temp.substring(temp.length()-4);
		}
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
	
	public static char[] intToBitArray(int source,int length)
	{
		char[] result = new char[length];
		String temp = Integer.toBinaryString(source);
		for(int i=0;i<length;i++)
		{
			temp = "0"+temp;
		}
		for(int i =1 ;i<=length;i++)
		{
			result[length-i] = temp.charAt(temp.length()-i);
		}
		
		
		return result;
	}

	public static int bitArrayToInt(char[] bit, int start, int length,boolean isSigned) {
		if(!isSigned){
			char[] temp = new char[length];
			for (int i = start; i < start+length; i++) {
				temp[i-start] = bit[i];
			}
	
			int value = 0;
			for (int i = 0; i < temp.length; i++) {
				value += value(temp[i]) * Math.pow(2, temp.length - 1 - i);
			}
			return value;
		}
		else
		{
			char[] temp = new char[length];
			for (int i = start; i < start+length; i++) {
				temp[i-start] = bit[i];
			}
			int value = 0;
			if(temp[0]=='0')
			{
				for (int i = 1; i < temp.length; i++) {
					value += value(temp[i]) * Math.pow(2, temp.length - 1 - i);
				}
			}
			else
			{
				for(int i = 1; i<temp.length;i++)
				{
					value -= opp_value(temp[i]) * Math.pow(2, temp.length - 1 - i);
				}
				value-=1;
			}
			return value;
		}
	}
	
	public static void main(String[] args)
	{
		char[] bit1 = {'0','0','1','1'};
		char[] bit2 = {'1','0','1','1'};
		System.out.println(BitUtil.bitArrayToInt(bit1, 0, 4, false));
		System.out.println(BitUtil.bitArrayToInt(bit1, 0, 4, true));
		System.out.println(BitUtil.bitArrayToInt(bit2, 0, 4, false));
		System.out.println(BitUtil.bitArrayToInt(bit2, 0, 4, true));
	}
	
	private static int opp_value(char i)
	{
		if (i == '1') {
			return 0;
		} else {
			return 1;
		}
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
	 * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
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
	 * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF,
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
