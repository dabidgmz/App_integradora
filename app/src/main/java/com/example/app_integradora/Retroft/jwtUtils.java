package com.example.app_integradora.Retroft;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class jwtUtils {
    private static final String SECRET_KEY = "2PStm6q3pEysghNxNH7n6TUPj8HTDXwgg9kpZcE7dpQf2nytGPyAJtn6uHEspqgF";

    public static DecodedJWT decode(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        }catch (Exception e){
            return null;
        }
    }
}