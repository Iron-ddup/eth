package com.redhat.helloworld.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;


/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.6.0.
 */
public class Ok extends Contract {
    private static final String BINARY = "60606040526105f6806100126000396000f360606040526000357c0100000000000000000000000000000000000000000000000000000000900480634ed3885e1461005d5780636d4ce63c146100b85780638ca3c55314610138578063ae55c888146101da57610058565b610002565b34610002576100b66004808035906020019082018035906020019191908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509090919050506102a1565b005b34610002576100ca6004805050610352565b60405180806020018281038252838181518152602001915080519060200190808383829060006004602084601f0104600302600f01f150905090810190601f16801561012a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34610002576101d86004808035906020019082018035906020019191908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050909091908035906020019082018035906020019191908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505090909190505061040e565b005b34610002576102336004808035906020019082018035906020019191908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509090919050506104fc565b60405180806020018281038252838181518152602001915080519060200190808383829060006004602084601f0104600302600f01f150905090810190601f1680156102935780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b8060006000509080519060200190828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102f057805160ff1916838001178555610321565b82800160010185558215610321579182015b82811115610320578251826000505591602001919060010190610302565b5b50905061034c919061032e565b80821115610348576000818150600090555060010161032e565b5090565b50505b50565b602060405190810160405280600081526020015060006000508054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103ff5780601f106103d4576101008083540402835291602001916103ff565b820191906000526020600020905b8154815290600101906020018083116103e257829003601f168201915b5050505050905061040b565b90565b80600160005083604051808280519060200190808383829060006004602084601f0104600302600f01f15090500191505090815260200160405180910390206000509080519060200190828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061049957805160ff19168380011785556104ca565b828001600101855582156104ca579182015b828111156104c95782518260005055916020019190600101906104ab565b5b5090506104f591906104d7565b808211156104f157600081815060009055506001016104d7565b5090565b50505b5050565b6020604051908101604052806000815260200150600160005082604051808280519060200190808383829060006004602084601f0104600302600f01f15090500191505090815260200160405180910390206000508054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105e55780601f106105ba576101008083540402835291602001916105e5565b820191906000526020600020905b8154815290600101906020018083116105c857829003601f168201915b505050505090506105f1565b91905056";

    public static final String FUNC_SET = "set";

    public static final String FUNC_SETDATA = "setData";

    public static final String FUNC_GET = "get";

    public static final String FUNC_GETDATA = "getData";

    @Deprecated
    protected Ok(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }



    @Deprecated
    protected Ok(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }



    public RemoteCall<TransactionReceipt> set(String x) {
        final Function function = new Function(
                FUNC_SET,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(x)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setData(String a, String b) {
        final Function function = new Function(
                FUNC_SETDATA,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(a),
                        new org.web3j.abi.datatypes.Utf8String(b)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> get() {
        final Function function = new Function(FUNC_GET,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> getData(String a) {
        final Function function = new Function(FUNC_GETDATA,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(a)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }



    @Deprecated
    public static RemoteCall<Ok> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Ok.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }



    @Deprecated
    public static RemoteCall<Ok> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Ok.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static Ok load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Ok(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Ok load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Ok(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }


}
