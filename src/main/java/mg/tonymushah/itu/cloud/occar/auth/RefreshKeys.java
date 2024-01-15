package mg.tonymushah.itu.cloud.occar.auth;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RefreshKeys {
    @Value("${tokens.refresh.private-key}")
    private String privateKey;
    @Value("${tokens.refresh.public-key}")
    private String publicKey;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public RefreshKeys() {
    }

    public RefreshKeys(@Value("tokens.refresh.private-key") String privateKey,
            @Value("tokens.refresh.public-key") String publicKey) {
        this.setPrivateKey(privateKey);
        this.setPublicKey(publicKey);
    }

    public KeyPair keyPair() throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        System.out.println(this.getPublicKey());
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(this.getPublicKey().replace("\\n", "\n").getBytes());
        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(this.getPrivateKey().replace("\\n", "\n").getBytes());

        return new KeyPair(keyFactory.generatePublic(publicKeySpec), keyFactory.generatePrivate(privateKeySpec));
    }
}
