package com.redhat.helloworld.contract;

import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author littleredhat
 */
public class HelloWorldContract extends Contract implements HelloWorldInterface {

    // https://remix.ethereum.org/ Compile - Details - WEB3DEPLOY - data
    private static final String BINARY = "606060405260a18060106000396000f360606040526000357c01000000000000000000000000000000000000000000000000000000009004806360fe47b11460435780636d4ce63c14605d57603f565b6002565b34600257605b60048080359060200190919050506082565b005b34600257606c60048050506090565b6040518082815260200191505060405180910390f35b806000600050819055505b50565b60006000600050549050609e565b9056";

    /**
     * HelloWorld合约
     *
     * @param contractAddress 合约地址
     * @param web3j           RPC请求
     * @param credentials     钱包凭证
     * @param gasPrice        GAS价格
     * @param gasLimit        GAS上限
     */
    private HelloWorldContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
                               BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    /**
     * 部署合约
     *
     * @param web3j       RPC请求
     * @param credentials 钱包凭证
     * @param gasPrice    GAS价格
     * @param gasLimit    GAS上限
     * @return
     */
    public static RemoteCall<HelloWorldContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        // 构造函数参数 NULL
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList());
        return deployRemoteCall(HelloWorldContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    /**
     * 加载合约
     *
     * @param contractAddress 合约地址
     * @param web3j           RPC请求
     * @param credentials     钱包凭证
     * @param gasPrice        GAS价格
     * @param gasLimit        GAS上限
     * @return
     */
    public static HelloWorldContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new HelloWorldContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    /**
     * get
     */
    public RemoteCall<Uint256> get() {
        Function function = new Function("get", Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function);
    }

//    public static final String FUNC_GET = "get";
//    public RemoteCall<BigInteger> get() {
//        final Function function = new Function(FUNC_GET,
//                Arrays.<Type>asList(),
//                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
//        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
//    }
    /**
     * set
     */
    public RemoteCall<TransactionReceipt> set(int x) {
        Function function = new Function("set", Arrays.<Type>asList(new Uint256(x)),
                Arrays.<TypeReference<?>>asList());
        return executeRemoteCallTransaction(function);
    }
}
