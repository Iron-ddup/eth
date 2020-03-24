package com.redhat.event;

import java.math.BigInteger;
import java.util.concurrent.Future;

import org.web3j.abi.EventValues;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * @author littleredhat
 */
public interface CrowdFundingInterface {

	/**
	 * ���ͽ��
	 * 
	 * @param value
	 *            ���
	 * @return
	 */
	public TransactionReceipt fund(BigInteger value);

	/**
	 * ���ս��
	 * 
	 * @return
	 */
	public Future<TransactionReceipt> get();

	/**
	 * �ڳ�����¼�
	 * 
	 * @param transactionReceipt
	 *            ����ƾ��
	 * @return
	 */
	public EventValues processCrowdEndEvent(TransactionReceipt future);
}
