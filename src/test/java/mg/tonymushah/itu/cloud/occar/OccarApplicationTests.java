package mg.tonymushah.itu.cloud.occar;

import java.security.Key;
import java.security.KeyPair;
import java.time.Instant;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.Jwts;

@SpringBootTest
class OccarApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void jwtTest(){
		KeyPair key = Jwts.SIG.RS256.keyPair().build();
		String token = Jwts.builder().id("tony").subject("userId").expiration(new Date(Instant.now().plusSeconds(15 * 60).getEpochSecond() * 1000)).signWith(key.getPublic()).compact();
		
		System.out.println(Jwts.parser().decryptWith(key.getPrivate()).build().parse(token));

	}
}
