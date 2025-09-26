package com.example.clothify.security;

import com.example.clothify.entity.User;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JwtService {
    private String secretKey ="CV++3595WkUMp+jkq9r4LU/GmKm9qR5pbEIbhuRrv/+uMT9gnRd5eKUUCwf7ZcfwIqKPmDSpwaT8oCsVokoMi11nORfF/IRE0jOcrEnCBH8=";


    public String generateAccessToken(User user) {
        Date issueTime = new Date();
        Date expiredTime = Date.from(issueTime.toInstant().plus(30, ChronoUnit.HOURS));


        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issueTime(issueTime)
                .expirationTime(expiredTime)
                .build();

        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            JWSSigner signer = new MACSigner(secretKey.getBytes());
            jwsObject.sign(signer);


        } catch (JOSEException e) {
            throw new RuntimeException("Error while generating JWT", e);
        }
        return jwsObject.serialize();
    }
    public String generateRefreshToken(User user) {
        Date issueTime = new Date();
        Date expiredTime = Date.from(issueTime.toInstant().plus(30, ChronoUnit.DAYS));


            JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getEmail())
                    .issueTime(issueTime)
                    .expirationTime(expiredTime)
                    .build();

            Payload payload = new Payload(claimsSet.toJSONObject());
            JWSObject jwsObject = new JWSObject(header, payload);

        try {
            JWSSigner signer = new MACSigner(secretKey.getBytes());
            jwsObject.sign(signer);


        } catch (JOSEException e) {
            throw new RuntimeException("Error while generating JWT", e);
        }
        return jwsObject.serialize();
    }
}