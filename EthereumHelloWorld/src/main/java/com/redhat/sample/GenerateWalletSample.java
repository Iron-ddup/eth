package com.redhat.sample;

import java.io.File;

import org.web3j.crypto.WalletUtils;

/**
 * @author littleredhat
 * @description ����Ǯ��
 */
public class GenerateWalletSample {
	// Ǯ������
	private static String password = "123456";
	// Ǯ��·��
	private static String destinationDirectory = "F:\\chain\\geth-win64\\chain\\keystore";

	public static void main(String[] args) throws Exception {
		// To create a new wallet file
		String fileName = WalletUtils.generateNewWalletFile(password, new File(destinationDirectory), true);
		System.out.println(fileName);
	}
}
