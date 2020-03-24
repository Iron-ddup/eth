package com.redhat.helloworld.test;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.Arrays;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.RawTransaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import com.redhat.helloworld.util.Consts;

/**
 * @author littleredhat
 */
public class FilterTest {
	// ��������
	private static int blockNumber = 0;
	// ��������
	private static int transactionNumber = 0;
	// defaults to http://localhost:8545/
	private static Web3j web3j = Web3j.build(new HttpService());

	public static void main(String[] args) throws Exception {
		/***** ��ʼ���� *****/
		BlockFilters();
		TransactionFilters();

		/***** ��ʼ���� *****/
		Transaction();
	}

	// To receive all new blocks as they are added to the blockchain
	private static void BlockFilters() {
		web3j.blockObservable(false).subscribe(block -> {
			System.out.println("������ : " + ++blockNumber);
			LocalDateTime timestamp = Instant.ofEpochSecond(block.getBlock().getTimestamp().longValueExact())
					.atZone(ZoneId.of("UTC")).toLocalDateTime();
			System.out.println("timestamp : " + timestamp);
			System.out.println("transactionCount : " + block.getBlock().getTransactions().size());
			System.out.println("hash : " + block.getBlock().getHash());
			System.out.println("parentHash : " + block.getBlock().getParentHash());
		});
	}

	// To receive all new transactions as they are added to the blockchain
	private static void TransactionFilters() {
		web3j.transactionObservable().subscribe(tx -> {
			System.out.println("������ : " + ++transactionNumber);
			System.out.println("getInput : " + tx.getInput());
			System.out.println("getFrom : " + tx.getFrom());
			System.out.println("getTo : " + tx.getTo());
		});
	}

	private static void Transaction() throws Exception {
		// load the credentials
		Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);

		// get the next available nonce
		EthGetTransactionCount ethGetTransactionCount = web3j
				.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
		BigInteger nonce = ethGetTransactionCount.getTransactionCount();

		/*
		 * String name ��������
		 * List<Type> inputParameters ��ڲ���
		 * List<TypeReference<?>> outputParameters ���ڲ���
		 */
		Function function = new Function("set", Arrays.asList(new Uint256(BigInteger.valueOf(10000))),
				Arrays.<TypeReference<?>>asList());

		// encode the function
		String encodedFunction = FunctionEncoder.encode(function);

		/*
		 * BigInteger nonce �������
		 * BigInteger gasPrice �۸�
		 * BigInteger gasLimit ����
		 * String to ��Լ��ַ String data ���뺯��
		 */
		RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, BigInteger.valueOf(Consts.GAS_PRICE),
				BigInteger.valueOf(Consts.GAS_LIMIT), Consts.HELLOWORLD_CONTRACT_ADDRESS, encodedFunction);
		// sign our transaction
		byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
		String hexValue = Numeric.toHexString(signedMessage);

		// send our transaction
		EthSendTransaction transactionResponse = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
		System.out.println(transactionResponse.getResult());
	}
}
