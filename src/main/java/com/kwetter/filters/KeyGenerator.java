package com.kwetter.filters;

import com.kwetter.filters.interfaces.IKeyGenerator;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class KeyGenerator implements IKeyGenerator {

    @Override
    public Key generateKey() {
        String keyString = "Kwetteren-in-bad";
        return new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
    }
}
