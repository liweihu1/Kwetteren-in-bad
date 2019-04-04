package com.kwetter.filters;

import com.kwetter.domain.Role;
import com.kwetter.filters.interfaces.IKeyGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class KeyGenerator implements IKeyGenerator {

    @Override
    public Key generateKey() {
        String keyString = "Kwetteren-in-bad";
        return new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
    }
}
