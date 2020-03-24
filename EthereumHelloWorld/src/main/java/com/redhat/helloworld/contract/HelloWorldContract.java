package com.redhat.helloworld.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.Future;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;

/**
 * @author littleredhat
 */
public class HelloWorldContract extends Contract implements HelloWorldInterface {

	/**
	 * HelloWorld��Լ
	 * 
	 * @param contractAddress
	 *            ��Լ��ַ
	 * @param web3j
	 *            JSON-RPC����
	 * @param credentials
	 *            �����ߵ�ַ
	 * @param gasPrice
	 *            gas�۸�
	 * @param gasLimit
	 *            gas����
	 */
	public HelloWorldContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		super("", contractAddress, web3j, credentials, gasPrice, gasLimit);
	}

	public Future<Uint256> get() {
		Function function = new Function("get", Arrays.asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
				}));
		return executeCallSingleValueReturnAsync(function);
	}

	public Future<TransactionReceipt> set(int x) {
		Function function = new Function("set", Arrays.asList(new Uint256(BigInteger.valueOf(x))),
				Arrays.<TypeReference<?>>asList());
		return executeTransactionAsync(function);
	}
}
