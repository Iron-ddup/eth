package com.redhat.helloworld.contract;

import com.redhat.helloworld.util.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

public class OkMain {
    private static Logger logger = LoggerFactory.getLogger(OkMain.class);
    private static int Stringlength=1024*27;
    public static void main(String[] args) throws Exception {

        // 获取凭证
        Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);
        System.out.println("getCredentialsAddress : " + credentials.getAddress());

        // defaults to http://localhost:8545/
        Web3j web3j = Web3j.build(new HttpService(" http://127.0.0.1:8545"));
        // 部署合约
//         Ok contract = Ok.deploy(web3j, credentials,
//                 Consts.GAS_PRICE, Consts.GAS_LIMIT).send();
//         System.out.println("getContractAddress : " + contract.getContractAddress());

          // 加载合约
        Ok contract = Ok.load(Consts.OK_ADDR, web3j, credentials,
                Consts.GAS_PRICE, Consts.GAS_LIMIT);
        System.out.println("getContractAddress : " + contract.getContractAddress());
        ////////// 异步请求方式 //////////
        String value=gen(Stringlength);
        logger.info("输入参数长度"+value.length()+":"+value);
        // set
        CompletableFuture<TransactionReceipt> transactionReceiptAsync = contract.set(value).sendAsync();
        logger.info("交易hash："+ transactionReceiptAsync.get().getTransactionHash());
        // get
        CompletableFuture<String> resultAsync = contract.get().sendAsync();
        logger.info("链上查询："+ resultAsync.get());
        //交易花费
//        Request<?, EthBlock> ethBlock=web3j.ethGetBlockByHash(transactionReceiptAsync.get().getTransactionHash(),true);
//        logger.info("gas花费："+ethBlock.send().getBlock().getGasUsed());
    }


        public static String gen(int length) {
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            length -= 20;
            char[] ss = new char[length];
            int[] flag = {0,0,0}; //A-Z, a-z, 0-9
            int i=0;
            while(flag[0]==0 || flag[1]==0 || flag[2]==0 || i<length) {
                i = i%length;
                int f = (int) (Math.random()*3%3);
                if(f==0)
                    ss[i] = (char) ('A'+Math.random()*26);
                else if(f==1)
                    ss[i] = (char) ('a'+Math.random()*26);
                else
                    ss[i] = (char) ('0'+Math.random()*10);
                flag[f]=1;
                i++;
            }
            String sss=new String(ss);
            sss=dateFormat.format(date)+sss;
            return  sss;
        }


}
