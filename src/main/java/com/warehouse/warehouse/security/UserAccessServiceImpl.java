package com.warehouse.warehouse.security;

import com.warehouse.warehouse.security.dto.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.impl.DefaultClaims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class UserAccessServiceImpl {

    private static final Logger LOG = LoggerFactory.getLogger(UserAccessServiceImpl.class);

    private final KeyStoreServiceImpl keyStoreService;
    private final Map<UserId, UserData> users;

    @Autowired
    public UserAccessServiceImpl(KeyStoreServiceImpl keyStoreService) {
        this.keyStoreService = keyStoreService;
        this.users = new ConcurrentHashMap<>();
        this.users.put(UserId.from("admin"), new UserData(UserId.from("admin"), Password.from("pass"), "ROLE_ADMIN"));
        this.users.put(UserId.from("user"), new UserData(UserId.from("user"), Password.from("pass"), "ROLE_USER"));

    }

    public Optional<UserData> login(LoginRequest loginRequest) {
        UserId userId = loginRequest.getUserId();
        UserData userData = users.get(userId);
        if (userData != null && userData.verifyPassword(loginRequest.getPassword())) {
            Key key = keyStoreService.createUserKey(userId);
            long nowDate = LocalDate.now().toEpochSecond(LocalTime.now(), ZoneOffset.ofHours(0)) * 1000;
            long expirationDate = (nowDate + 3600 * 24) * 1000;
            List<String> roles = userData.getRoles().stream().map(RoleId::getId).collect(Collectors.toList());
            String jwToken = Jwts.builder()
                    .setSubject(userId.getId())
                    .signWith(key)
                    .setExpiration(new Date(expirationDate))
                    .setIssuer("issuer")
                    .setIssuedAt(new Date(nowDate))
                    .claim("roles", roles)
                    .compact();
            UserData userDataWithJwToken = UserData.from(userData, JWToken.from(jwToken));
            users.put(userId, userDataWithJwToken);
            return Optional.of(userDataWithJwToken);
        } else {
            LOG.warn("login failed !");
        }
        return Optional.empty();
    }

    public Optional<Jws<Claims>> validate(JWToken jwToken) {
        try {
            String jwTokenWithoutSignature = JWTUtils.removeSignature(jwToken.getToken());
            Jwt jwt = Jwts.parserBuilder().build().parse(jwTokenWithoutSignature);
            DefaultClaims body = (DefaultClaims) jwt.getBody();
            String subject = body.get(Claims.SUBJECT, String.class);
            UserId userId = UserId.from(subject);
            UserData userData = users.get(userId);
            if (userData != null) {
                Optional<Key> userKey = keyStoreService.getUserKey(userId);
                if (userKey.isPresent()) {
                    Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(userKey.get()).build().parseClaimsJws(jwToken.getToken());
                    return Optional.of(claimsJws);
                } else {
                    LOG.warn("key for user {} not found !", userId.getId());
                }
            } else {
                LOG.warn("user data for {} not found !", userId.getId());
            }
        } catch (Exception e) {
            LOG.error("token validation failed !", e);
        }
        return Optional.empty();
    }

    public boolean logout(JWToken jwToken) {
        try {
            String jwTokenWithoutSignature = JWTUtils.removeSignature(jwToken.getToken());
            Jwt jwt = Jwts.parserBuilder().build().parse(jwTokenWithoutSignature);
            DefaultClaims body = (DefaultClaims) jwt.getBody();
            String subject = body.get(Claims.SUBJECT, String.class);
            UserId userId = UserId.from(subject);
            UserData userData = users.get(userId);
            if (userData != null) {
                Optional<Key> userKey = keyStoreService.getUserKey(userId);
                if (userKey.isPresent()) {
                    try {
                        Jwts.parserBuilder().setSigningKey(userKey.get()).build().parseClaimsJws(jwToken.getToken());
                        keyStoreService.removeUserKey(userId);
                        UserData userDataNoJwt = UserData.cloneAndRemoveJwToken(userData);
                        users.put(userId, userDataNoJwt);
                        LOG.info("user {} logout.", userId.getId());
                        return true;
                    } catch (Exception e) {
                        LOG.warn("JWT verification failed for {} not found !", userId.getId());
                    }
                } else {
                    LOG.warn("key for user {} not found !", userId.getId());
                }
            } else {
                LOG.warn("user data for {} not found !", userId.getId());
            }
        } catch (Exception e) {
            LOG.error("token validation failed !", e);
        }
        return false;
    }

}

