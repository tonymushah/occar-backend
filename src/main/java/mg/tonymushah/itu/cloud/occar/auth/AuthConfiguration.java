package mg.tonymushah.itu.cloud.occar.auth;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.InvalidKeyException;

@Configuration
public class AuthConfiguration {
    @Autowired
    private SessionKeys sessionKeys;
    @Autowired
    private RefreshKeys refreshKeys;

    public SessionKeys getSessionKeys() {
        return sessionKeys;
    }

    public RefreshKeys getRefreshKeys() {
        return refreshKeys;
    }

    public KeyPair getSessionKeyPair() throws NoSuchAlgorithmException, InvalidKeySpecException {
        return this.getSessionKeys().keyPair();
    }

    public KeyPair getRefreshKeyPair() throws NoSuchAlgorithmException, InvalidKeySpecException {
        return this.getRefreshKeys().keyPair();
    }

    public JwtBuilder getSessionJwtBuilder()
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.builder().signWith(this.getSessionKeyPair().getPublic());
    }

    public JwtBuilder getRefreshJwtBuilder()
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.builder().signWith(this.getRefreshKeyPair().getPublic());
    }

    public JwtParserBuilder getRefreshJwtParserBuilder() throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.parser().decryptWith(this.getRefreshKeyPair().getPrivate());
    }

    public JwtParser getRefreshJwtParser() throws NoSuchAlgorithmException, InvalidKeySpecException {
        return this.getRefreshJwtParserBuilder().build();
    }

    public JwtParserBuilder getSessionJwtParserBuilder() throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Jwts.parser().decryptWith(this.getSessionKeyPair().getPrivate());
    }

    public JwtParser getSessionJwtParser() throws NoSuchAlgorithmException, InvalidKeySpecException {
        return this.getSessionJwtParserBuilder().build();
    }
}
