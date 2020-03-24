package com.redhat.event;

import java.util.concurrent.CompletableFuture;

import org.web3j.abi.EventValues;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * @author littleredhat
 */
public interface EventInterface {

	/**
	 * ת������
	 * 
	 * @param from
	 *            ��
	 * @param to
	 *            ��
	 * @param value
	 *            ��ֵ
	 * @return
	 */
	public CompletableFuture<TransactionReceipt> transferFunc(String from, String to, int value);

	/**
	 * �����¼�
	 * 
	 * @param transactionReceipt
	 * @return
	 */
	public EventValues processTransferEvent(TransactionReceipt transactionReceipt);
}
