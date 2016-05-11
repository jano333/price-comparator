package sk.hudak.jef.ssl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author hudak
 */
public class JefSslManager {

    private static final Logger logger = LoggerFactory.getLogger(JefSslManager.class);

    private static JefSslManager instance;

    private X509TrustManager amcCustomTrustManager;
    private X509KeyManager amcCustomKeyManager;
    private HostnameVerifier amcCustomHostnameVerifier;

    private JefSslManager() {
        // no instance
    }

    public static JefSslManager getInstance() {
        if (JefSslManager.instance == null) {
            JefSslManager.instance = new JefSslManager();
        }
        return JefSslManager.instance;
    }

    public SSLContext init() throws RuntimeException {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");

            TrustManager[] trustManagers = new TrustManager[]{getAmcCustomTrustManager()};
            KeyManager[] keymanagers = new KeyManager[]{getAmcCustomKeyManager()};

            sslContext.init(keymanagers, trustManagers, null);

            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

            HttpsURLConnection.setDefaultHostnameVerifier(getAmcCustomHostnameVerifier());

            return sslContext;

        } catch (Exception e) {
            throw new RuntimeException("SSL inicialization error.", e);
        }
    }

//    /**
//     * pozri:
//     * http://cxf.apache.org/docs/client-http-transport-including-ssl-support
//     * .html
//     *
//     * @param httpConduit
//     * @throws RuntimeException
//     */
//    public void initCxf(HTTPConduit httpConduit) throws RuntimeException {
//        logger.debug("inicializing SSL for CXF");
//
//        try {
//            TLSClientParameters tlsParams = new TLSClientParameters();
//            tlsParams.setDisableCNCheck(true);
//
//            TrustManager[] trustManagers = new TrustManager[]{getAmcCustomTrustManager()};
//            tlsParams.setTrustManagers(trustManagers);
//
//            KeyManager[] keymanagers = new KeyManager[]{getAmcCustomKeyManager()};
//            tlsParams.setKeyManagers(keymanagers);
//
//            httpConduit.setTlsClientParameters(tlsParams);
//
//        } catch (Exception e) {
//            throw new RuntimeException("SSL for cxf inicialization error.", e);
//        }
//    }

	/*@Deprecated
    public void initMock() throws AmcBusinessException {
		logger.warn("using mock trust manage, key manager and host name verifier");

		try {
			SSLContext sslContext = SSLContext.getInstance("SSL");

			KeyManager[] keymanagers = new KeyManager[] { new TestCustomTomcatKeyManager() };
			TrustManager[] trustManagers = new TrustManager[] { new MockTrustManager() };
			sslContext.init(keymanagers, trustManagers, null);

			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

			HttpsURLConnection.setDefaultHostnameVerifier(new MockHostnameVerifier());

		} catch (Exception e) {
			throw new AmcBusinessException("SSL inicialization error.", e);
		}
	}*/

    private X509TrustManager getAmcCustomTrustManager() throws Exception {
        if (amcCustomTrustManager == null) {
            amcCustomTrustManager = new JefCustomTrustManager();
        }
        return amcCustomTrustManager;
    }

    private X509KeyManager getAmcCustomKeyManager() throws Exception {
        if (amcCustomKeyManager == null) {
            amcCustomKeyManager = new JefCustomKeyManager();
        }
        return amcCustomKeyManager;
    }

    private HostnameVerifier getAmcCustomHostnameVerifier() {
        if (amcCustomHostnameVerifier == null) {
            amcCustomHostnameVerifier = new JefCustomHostnameVerifier();
        }
        return amcCustomHostnameVerifier;
    }


    private static class MockTrustManager implements X509TrustManager {

        private static final Logger logger = LoggerFactory.getLogger(MockTrustManager.class);

        public MockTrustManager() {
            logger.warn("using mock trust manager");
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            logger.warn("checkClientTrusted()");
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            logger.warn("checkServerTrusted()");
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            logger.warn("getAcceptedIssuers()");
            return null;
        }
    }

    private static class MockHostnameVerifier implements HostnameVerifier {

        private static final Logger logger = LoggerFactory.getLogger(MockHostnameVerifier.class);

        public MockHostnameVerifier() {
            logger.warn("using mock host name verifier");
        }

        @Override
        public boolean verify(String hostname, SSLSession session) {
            logger.warn("verify");
            return true;
        }
    }

    @SuppressWarnings("unused")
    private static class MockKeyManager implements X509KeyManager {

        private static final Logger logger = LoggerFactory.getLogger(MockKeyManager.class);

        public MockKeyManager() {
            logger.warn("using mock key manager");
        }

        @Override
        public String chooseClientAlias(String[] keyType, Principal[] issuers, Socket socket) {
            logger.warn("chooseClientAlias");
            return null;
        }

        @Override
        public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
            logger.warn("chooseServerAlias");
            return null;
        }

        @Override
        public X509Certificate[] getCertificateChain(String alias) {
            logger.warn("getCertificateChain");
            return null;
        }

        @Override
        public String[] getClientAliases(String keyType, Principal[] issuers) {
            logger.warn("getClientAliases");
            return null;
        }

        @Override
        public PrivateKey getPrivateKey(String alias) {
            logger.warn("getPrivateKey");
            return null;
        }

        @Override
        public String[] getServerAliases(String keyType, Principal[] issuers) {
            logger.warn("getServerAliases");
            return null;
        }
    }
}
