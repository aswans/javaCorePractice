package com.aswans.binary;
/**
 * @desc Java 二进制，八进制，十进制，十六进制转换 
 * @author zsj add 2018年11月24日 下午3:24:58
 */
public class BinaryTest {

	public static void main(String[] args) {
		  //java中0开头表示八进制
          byte a = 012;
          //ox开头的表示十六进制
          int b =  0xAF;
          //0b开头表示二进制
          byte mm = 0b11;
          System.out.println(a);
          System.out.println(b);
          System.out.println(mm);
          
          
          System.out.println("----------进制转换-------------");
          int c = 56;
          System.out.println("十进制转二进制："+Integer.toBinaryString(c));
          System.out.println("十进制转八进制："+Integer.toOctalString(c));
          System.out.println("十进制转十六进制："+Integer.toHexString(c));
          
          System.out.println("-----------------------------");
          System.out.println("二进制转十进制："+Integer.valueOf("111000", 2).toString());
          System.out.println("八进制转十进制："+Integer.valueOf("70", 8).toString());
          System.out.println("十六进制转十进制："+Integer.valueOf("38", 16).toString());
          
          System.out.println("-----------------------------");
          System.out.println("十六进制转二进制："+Integer.toBinaryString(0xa));
          System.out.println("十六进制转八进制："+Integer.toOctalString(0x12));
          
          System.out.println("八进制转二进制："+Integer.toBinaryString(03));
          System.out.println("八进制转十六进制："+Integer.toHexString(022));
          
          
          System.out.println("------------移位运算-----------");
          /*
           * <<：左移运算符，num << 3，相当于num乘以2的3次方    
           * <<：带符号右移运算符，正数右移高位补0，负数右移高位补1，例如：num >> 3，相当于num除以2的3次方
           * 43210      位数
			--------
			 1010                   十进制：10     原始数         number
			10100                   十进制：20     左移一位       number = number << 1;
			 1010                   十进制：10     右移一位       number = number >> 1;
           * 
           * >>>：无符号右移，忽略符号位，无论是正数还是负数，高位通通补0，空位都以0补齐
           */
          System.out.println(2<<3);
          System.out.println(2>>3);
          
          
          System.out.println("------------& 、|、^运算-----------");
          
          
          System.out.println(intToBinaryString(4));
          
	}
	
	
	/**
	 * The digits for every supported radix.
	 */
	private static final char[] DIGITS = {
	        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
	        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
	        'u', 'v', 'w', 'x', 'y', 'z'
	};
    /**
     * @desc jdk中十进制转二进制源码 
     * @author zsj add 2018年11月24日 下午5:31:15
     * @param i
     * @return
     */
	public static String intToBinaryString(int i) {
	    int bufLen = 32;  // 整型是4个字节32位
	    char[] buf = new char[bufLen];
	    int cursor = bufLen;

	    do {
	        buf[--cursor] = DIGITS[i & 1];
	    }  while ((i >>>= 1) != 0);

	    return new String(buf,cursor,bufLen-cursor);
	}

}
