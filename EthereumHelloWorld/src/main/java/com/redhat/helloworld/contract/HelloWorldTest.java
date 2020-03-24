package com.redhat.helloworld.contract;

import java.util.concurrent.Future;

import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.redhat.helloworld.util.Consts;
import com.redhat.helloworld.util.Util;

/**
 * @author littleredhat
 */
public class HelloWorldTest {

	public static void main(String[] args) throws Exception {
		// ��ȡƾ֤
		Credentials credentials = Util.GetCredentials();
		System.out.println("contractAddress : " + credentials.getAddress());

		// ��ȡ��Լ
		HelloWorldContract contract = Util.GetHelloWorldContract(credentials, Consts.HELLOWORLD_CONTRACT_ADDRESS);
		System.out.println("contractAddress : " + contract.getContractAddress());

		// set
		Future<TransactionReceipt> transferReceipt = contract.set(10000);
		System.out.println("waiting...");
		System.out.println("set : " + transferReceipt.get().getTransactionHash());

		// get
		Future<Uint256> result = contract.get();
		System.out.println("waiting...");
		System.out.println("get : " + result.get().getValue().intValue());
	}
}
