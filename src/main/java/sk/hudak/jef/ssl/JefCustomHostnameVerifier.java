package sk.hudak.jef.ssl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class JefCustomHostnameVerifier implements HostnameVerifier {

	private static final Logger logger = LoggerFactory.getLogger(JefCustomHostnameVerifier.class);

	private HostnameVerifier javaDefaultHostnameVerifier;

	/**
	 * Povoluje prechod pre {@link JefSslContants#ALLOWED_HOSTNAME}. Pre ostatne
	 * deleguje volanie do java default host name verifikatora.
	 */
	public JefCustomHostnameVerifier() {
		logger.debug("inicializing");
		this.javaDefaultHostnameVerifier = getJavaDefaultHostnameVerifier();
	}

	private HostnameVerifier getJavaDefaultHostnameVerifier() {
		return HttpsURLConnection.getDefaultHostnameVerifier();
	}

	@Override
	public boolean verify(String hostname, SSLSession session) {
		logger.debug("verify {}", hostname);
		if (JefSslContants.ALLOWED_HOSTNAME.contains(hostname)) {
			return true;
		}
		logger.debug("delegating verifying to java default host name");
		return javaDefaultHostnameVerifier.verify(hostname, session);
	}

}
