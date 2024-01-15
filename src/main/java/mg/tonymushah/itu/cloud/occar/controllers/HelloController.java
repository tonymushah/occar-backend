package mg.tonymushah.itu.cloud.occar.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.security.InvalidKeyException;
import mg.tonymushah.itu.cloud.occar.auth.AuthConfiguration;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired AuthConfiguration configuration;

    @GetMapping(path = {"/", ""})
    public String index(@RequestParam(name = "name", defaultValue = "Tony") String name) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException{  
        return configuration.getSessionJwtBuilder().subject(name).expiration(new Date(Instant.now().plusSeconds(60 * 5).getEpochSecond() * 100)).compact();
    }
    @GetMapping(path = {"/jwt", "/jwt/"})
    public String subData(@RequestParam(name = "data") String data) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return configuration.getSessionJwtParser().parse(data).toString();
    }
}
