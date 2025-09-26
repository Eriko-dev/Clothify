package com.example.clothify.security;

import com.example.clothify.entity.User;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    private final String secretKey = "CV++3595WkUMp+jkq9r4LU/GmKm9qR5pbEIbhuRrv/+uMT9gnRd5eKUUCwf7ZcfwIqKPmDSpwaT8oCsVokoMi11nORfF/IRE0jOcrEnCBH8=";

    // ===================== GENERATE TOKEN =====================
    public String generateAccessToken(User user) {
        return generateToken(user, 30, ChronoUnit.HOURS);
    }

    public String generateRefreshToken(User user) {
        return generateToken(user, 30, ChronoUnit.DAYS);
    }

    private String generateToken(User user, long amountToAdd, ChronoUnit unit) {
        try {
            Date issueTime = new Date();
            Date expirationTime = Date.from(issueTime.toInstant().plus(amountToAdd, unit));

            JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

            List<String> roles = user.getRoles().stream()
                    .map(r -> r.getName())
                    .toList();

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getEmail())
                    .claim("roles", roles)
                    .issueTime(issueTime)
                    .expirationTime(expirationTime)
                    .build();

            Payload payload = new Payload(claimsSet.toJSONObject());
            JWSObject jwsObject = new JWSObject(header, payload);

            JWSSigner signer = new MACSigner(secretKey.getBytes());
            jwsObject.sign(signer);

            return jwsObject.serialize();

        } catch (JOSEException e) {
            throw new RuntimeException("Error while generating JWT", e);
        }
    }

    // ===================== VALIDATE TOKEN =====================
    public boolean validateToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(secretKey.getBytes());

            // verify signature
            if (!signedJWT.verify(verifier)) return false;

            // check expiration
            Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();
            return expiration.after(new Date());

        } catch (Exception e) {
            return false;
        }
    }

    // ===================== EXTRACT EMAIL =====================
    public String extractEmail(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (ParseException e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }

    // ===================== EXTRACT ROLES =====================
    public List<String> extractRoles(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            Object rolesObj = signedJWT.getJWTClaimsSet().getClaim("roles");

            if (rolesObj instanceof List<?>) {
                //noinspection unchecked
                return (List<String>) rolesObj;
            }
            return List.of();
        } catch (ParseException e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }
}
