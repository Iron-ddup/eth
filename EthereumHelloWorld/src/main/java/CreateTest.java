import java.io.File;

import org.web3j.crypto.WalletUtils;

/**
 * @author littleredhat
 * @description ����Ǯ��
 */
public class CreateTest {
	// Ǯ������
	private static String password = "123456";
	// Ǯ��·��
	private static String path = "F:\\chain\\geth-win64\\chain\\keystore";

	public static void main(String[] args) throws Exception {
		// To create a new wallet file
		String fileName = WalletUtils.generateNewWalletFile(password, new File(path), true);
		System.out.println(fileName);
	}
}
