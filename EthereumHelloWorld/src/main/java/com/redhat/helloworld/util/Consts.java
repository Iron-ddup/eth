package com.redhat.helloworld.util;

import java.math.BigInteger;

/**
 * @author littleredhat
 */
public class Consts {
	// Gas�۸�
	public static BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
	// Gas����
	public static BigInteger GAS_LIMIT = BigInteger.valueOf(4_300_000L);
	// ETHER��̫��
	public static BigInteger ETHER = new BigInteger("1000000000000000000");
	// Ǯ������
	public static String PASSWORD = "123456";
	// Ǯ��·��
	public static String PATH = "F:\\chain\\geth-win64\\chain\\keystore\\UTC--2017-05-03T17-48-46.721084800Z--6c97ea3f4f71669412aab8b7f705e253ce14064c";
	// Ǯ��Ŀ¼
	public static String DIRECTORY = "F:\\chain\\geth-win64\\chain\\keystore";
	// HelloWorld���ܺ�Լ��ַ
	public static String HELLOWORLD_CONTRACT_ADDRESS = "0x5a4dc569C7B395130c58A9B0C183fEf6c4957AA9";
}
