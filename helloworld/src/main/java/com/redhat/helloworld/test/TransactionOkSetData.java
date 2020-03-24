package com.redhat.helloworld.test;

import com.redhat.helloworld.contract.Ok;

import com.redhat.helloworld.util.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.core.RemoteCall;

import java.util.concurrent.CompletableFuture;


public class TransactionOkSetData {
    private static Logger logger = LoggerFactory.getLogger(TransactionOkSetData.class);
    public static void main(String[] args) throws Exception {
        // 获取凭证
        Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);
        System.out.println("getCredentialsAddress : " + credentials.getAddress());
        // defaults to http://localhost:8545/
        Web3j web3j = Web3j.build(new HttpService(" http://127.0.0.1:8545"));

        // 加载合约
        Ok contract = Ok.load(Consts.OK_ADDR, web3j, credentials,
                Consts.GAS_PRICE, Consts.GAS_LIMIT);
        System.out.println("getContractAddress : " + contract.getContractAddress());
        //同步查询
        ////////// 同步请求方式 //////////
        // set
        TransactionReceipt transactionReceipt = contract.setData("key1","value2").send();
        System.out.println("waiting..."); // 进入阻塞
        System.out.println("set : " + transactionReceipt.getTransactionHash());
        // get
//       TransactionReceipt result = contract.getData("key1").send();
//        System.out.println("waiting..."); // 进入阻塞
//        System.out.println("get : " + result);

        ////////// 异步请求方式 //////////
        // set
        CompletableFuture<TransactionReceipt> transactionReceiptAsync = contract.setData("key1","value1").sendAsync();
        logger.info("交易回执："+ transactionReceiptAsync.get().getTransactionHash());
       //   get
        CompletableFuture<String> resultAsync = contract.getData("key1").sendAsync();
        logger.info("链上查询："+ resultAsync.get());

    }
}
