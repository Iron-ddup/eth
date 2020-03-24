package com.redhat.helloworld.sample;

import java.util.concurrent.Future;

import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * @author littleredhat
 */
public interface HelloWorldInterface {

	/**
	 * ��ȡ
	 * 
	 * @return
	 */
	public Future<Uint256> get();

	/**
	 * ����
	 * 
	 * @param x
	 *            ����
	 * @return
	 */
	public Future<TransactionReceipt> set(int x);
}
