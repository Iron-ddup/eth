package com.redhat.event;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;

/**
 * @author littleredhat
 */
public class EventContract extends Contract implements EventInterface {

	protected EventContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		super(contractAddress, web3j, credentials, gasPrice, gasLimit);
	}

	public CompletableFuture<TransactionReceipt> transferFunc(String from, String to, int value) {
		Function function = new Function("TransferFunc",
				Arrays.asList(new Address(from), new Address(to), new Uint256(BigInteger.valueOf(value))),
				Arrays.<TypeReference<?>>asList());
		return executeTransactionAsync(function);
	}

	public EventValues processTransferEvent(TransactionReceipt transactionReceipt) {
		/*
		 * String name ��������
		 * List<TypeReference<?>> indexedParameters �������Ĳ���
		 * List<TypeReference<?>> nonIndexedParameters ���������Ĳ���
		 */
		Event event = new Event("Transfer", Arrays.<TypeReference<?>>asList(
				new TypeReference<Address>() {}, new TypeReference<Address>() {}),
				Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
		return extractEventParameters(event, transactionReceipt).get(0);
	}
}
