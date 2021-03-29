package com.warehouse.warehouse.security;

import com.warehouse.warehouse.security.dto.UserId;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.security.*;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class KeyStoreServiceImpl{

    private final KeyStore keystore;
    private final Key caKey;
    private final Map<UserId, KeyPair> keyCache;
    private final KeyPairGenerator keyPairGenerator;

    public KeyStoreServiceImpl() throws KeyStoreInitializationException {
        try {
            keystore = KeyStore.getInstance("JKS");
            InputStream is = KeyStoreServiceImpl.class.getResourceAsStream("/keystore.jks");
            keystore.load(is, "secret".toCharArray());
            caKey = keystore.getKey("organization", "secret".toCharArray());
            keyCache = new ConcurrentHashMap<>();
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            keyPairGenerator.initialize(2048, secureRandom);
        } catch (Exception e) {
            throw new KeyStoreInitializationException(e);
        }
    }


    public Key getCertificationAuthorityKey() {
        return caKey;
    }


    public Key createUserKey(UserId userId) {
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        keyCache.put(userId, keyPair);
        return keyPair.getPrivate();
    }


    public Optional<Key> getUserKey(UserId userId) {
        KeyPair pair = keyCache.get(userId);
        if (pair != null) {
            return Optional.of(keyCache.get(userId).getPrivate());
        } else {
            return Optional.empty();
        }
    }


    public boolean removeUserKey(UserId userId) {
        return keyCache.remove(userId) != null;
    }

}

