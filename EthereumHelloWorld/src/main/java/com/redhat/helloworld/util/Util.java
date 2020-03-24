package com.redhat.helloworld.util;

import java.io.IOException;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import com.redhat.helloworld.contract.HelloWorldContract;

/**
 * @author littleredhat
 */
public class Util {

	/**
	 * ��ȡ����Աƾ֤
	 * 
	 * @param contractAddress
	 *            ��Լ��ַ
	 * @return
	 * @throws Exception
	 */
	public static Credentials GetCredentials() {
		// ����Աƾ֤
		Credentials credentials = null;
		try {
			credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);
		} catch (IOException | CipherException e) {
			e.printStackTrace();
		}
		return credentials;
	}

	/**
	 * �����ڳ��Լ
	 * 
	 * @param credentials
	 *            Ǯ��ƾ֤
	 * @param contractAddress
	 *            ��Լ��ַ
	 * @return
	 * @throws Exception
	 */
	public static HelloWorldContract GetHelloWorldContract(Credentials credentials, String contractAddress) {
		// defaults to http://localhost:8545/
		Web3j web3j = Web3j.build(new HttpService());
		// ��ȡ��Լ
		HelloWorldContract contract = new HelloWorldContract(contractAddress, web3j, credentials, Consts.GAS_PRICE,
				Consts.GAS_LIMIT);
		return contract;
	}
}
