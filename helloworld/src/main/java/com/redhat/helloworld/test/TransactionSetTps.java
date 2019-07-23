package com.redhat.helloworld.test;

import com.redhat.helloworld.contract.HelloWorldContract;
import com.redhat.helloworld.util.Consts;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class TransactionSetTps {
    private static String toAddress = "0x769a38064a07d1585275219247ad3c615942c6d8";
    private  static int totalData=10;
    private  static Web3j web3j;

    public static void main(String[] args) throws IOException {
        // defaults to http://localhost:8545/
        web3j = Web3j.build(new HttpService());
        // get BlockNumber
        Request<?, EthBlockNumber> EthBlockNumberList = web3j.ethBlockNumber();
        BigInteger number=EthBlockNumberList.send().getBlockNumber();
        System.out.println("块高："+number);
        BigInteger tx=web3j.ethGetTransactionCount(toAddress, DefaultBlockParameter.valueOf("latest")).send().getTransactionCount();
        System.out.println("交易总数："+tx);
        LocalDateTime timestamp = Instant.ofEpochSecond(web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(number),true).send().getBlock().getTimestamp().longValueExact())
                .atZone(ZoneId.of("UTC")).toLocalDateTime();
        System.out.println("timestamp : " + timestamp);
        try {
           Transaction();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BigInteger lastNumber=EthBlockNumberList.send().getBlockNumber();
            System.out.println("交易后块高："+lastNumber);
            BigInteger tx2=web3j.ethGetTransactionCount(toAddress, DefaultBlockParameter.valueOf("latest")).send().getTransactionCount();
            System.out.println("交易后交易总数："+tx2);
            LocalDateTime timestamp2 = Instant.ofEpochSecond(web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(lastNumber),true).send().getBlock().getTimestamp().longValueExact())
                    .atZone(ZoneId.of("UTC")).toLocalDateTime();
            System.out.println("timestamp : " + timestamp2);
            System.out.println("产生新块的数量："+lastNumber.divide(number));
            System.out.println("产生交易的数量："+tx2.divide(tx));

            long t2=timestamp2.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            long t1=timestamp.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

            System.out.println("所需时间："+(t2-t1));
            if(t2-t1==0){
                System.out.println("输入有误！");
            }
            long dataNum =totalData*1000;
           System.out.println("tps："+((t2-t1)/dataNum));
        }

    }

    private static void Transaction() throws Exception {
        // defaults to http://localhost:8545/
      //  Web3j web3j = Web3j.build(new HttpService());

        // load the credentials
        Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);
        // 加载合约
        HelloWorldContract contract = HelloWorldContract.load(Consts.HELLOWORLD_ADDR, web3j, credentials,
                Consts.GAS_PRICE, Consts.GAS_LIMIT);
        System.out.println("getContractAddress : " + contract.getContractAddress());

        for(int i=0;i<=totalData;i++){
            // get the next available nonce
//            EthGetTransactionCount ethGetTransactionCount = web3j
//                    .ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
//            BigInteger nonce = ethGetTransactionCount.getTransactionCount();
//            /*
//             * String name 函数名字
//             * List<Type> inputParameters 入口参数
//             * List<TypeReference<?>> outputParameters 出口参数
//             */
//            Function function = new Function("set", Arrays.asList(new Uint256(BigInteger.valueOf(i))),
//                    Arrays.<TypeReference<?>>asList());
//
//            // encode the function
//            String encodedFunction = FunctionEncoder.encode(function);
//
//            /*
//             * BigInteger nonce 随机数
//             * BigInteger gasPrice gas价格
//             * BigInteger gasLimit gas上限
//             * String to 合约地址
//             * String data 数据
//             */
//            RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, Consts.GAS_PRICE, Consts.GAS_LIMIT,
//                    Consts.HELLOWORLD_ADDR, encodedFunction);
//
//            // sign our transaction
//            byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
//            String hexValue = Numeric.toHexString(signedMessage);
//
//            // send our transaction
//            EthSendTransaction transactionResponse = web3j.ethSendRawTransaction(hexValue).send();
//            System.out.println(transactionResponse.getResult());


            //异步请求
            CompletableFuture<TransactionReceipt> transactionReceiptAsync = contract.set(i).sendAsync();
            System.out.println("waiting..."); // 马上返回
            System.out.println("set : " + transactionReceiptAsync.get().getTransactionHash());
            Uint256 result = contract.get().send();
            System.out.println("第"+i+"笔");
            System.out.println("get : " + result.getValue().intValue());
        }
        System.out.println("完毕！");
    }
}
