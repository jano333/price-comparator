package sk.hudak.jef.ssl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public class JefCustomTrustManager implements X509TrustManager {

    private static final Logger logger = LoggerFactory.getLogger(JefCustomTrustManager.class);

    private X509TrustManager javaDefaultTrustManager;
    private X509Certificate serverCert;

    public JefCustomTrustManager() throws Exception {
        logger.debug("inicializing");
        this.javaDefaultTrustManager = (X509TrustManager) getJavaDefaultTrustManager();
    }

    private TrustManager getJavaDefaultTrustManager() throws Exception {
        TrustManagerFactory tmg = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmg.init((KeyStore) null);
        // beriem prvy
        return tmg.getTrustManagers()[0];
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        logger.debug("checkClientTrusted()");
        logger.debug("delegating to java default trust manager");
        javaDefaultTrustManager.checkClientTrusted(chain, authType);
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        logger.debug("checkServerTrusted() for hostname " + chain[0].getSubjectX500Principal().getName());
        this.serverCert = chain[0];
        // ak sa v subjecte certifikatu nachadza povoleny hostname, koncim
        // validaciu
        for (String allowedHostName : JefSslContants.ALLOWED_HOSTNAME) {
            if (this.serverCert.getSubjectX500Principal().getName().contains(allowedHostName)) {
                logger.debug("ignoring server cert");
                return;
            }
        }
        logger.debug("delegating checking to java default trust manager");
        this.javaDefaultTrustManager.checkServerTrusted(chain, authType);
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        logger.debug("getAcceptedIssuers()");
        List<X509Certificate> issuers = new ArrayList<>();
        issuers.add(this.serverCert);
        for (X509Certificate javaDefaultIssuer : this.javaDefaultTrustManager.getAcceptedIssuers()) {
            issuers.add(javaDefaultIssuer);
        }
        return issuers.toArray(new X509Certificate[issuers.size()]);
    }

}
