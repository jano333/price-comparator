package sk.hudak.jef.ssl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509KeyManager;
import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

// TODO pridat logy do jednotlivych metod
// TODO javadoc pre triedu
public class JefCustomKeyManager implements X509KeyManager {

	private static final Logger logger = LoggerFactory.getLogger(JefCustomKeyManager.class);

	private X509KeyManager javaDefaultKeyManager;

	public JefCustomKeyManager() throws Exception {
		logger.debug("inicializing");
		this.javaDefaultKeyManager = (X509KeyManager) getJavaDefaultKeyManager();
	}

	private static KeyManager getJavaDefaultKeyManager() throws Exception {
		KeyManagerFactory kmg = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		kmg.init(null, null);
		// beriem prvy
		return kmg.getKeyManagers()[0];
	}

	@Override
	public String chooseClientAlias(String[] keyType, Principal[] issuers, Socket socket) {
		return javaDefaultKeyManager.chooseClientAlias(keyType, issuers, socket);
	}

	@Override
	public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
		return javaDefaultKeyManager.chooseServerAlias(keyType, issuers, socket);
	}

	@Override
	public X509Certificate[] getCertificateChain(String alias) {
		return javaDefaultKeyManager.getCertificateChain(alias);
	}

	@Override
	public String[] getClientAliases(String keyType, Principal[] issuers) {
		return javaDefaultKeyManager.getClientAliases(keyType, issuers);
	}

	@Override
	public PrivateKey getPrivateKey(String alias) {
		return javaDefaultKeyManager.getPrivateKey(alias);
	}

	@Override
	public String[] getServerAliases(String keyType, Principal[] issuers) {
		return javaDefaultKeyManager.getServerAliases(keyType, issuers);
	}
}
