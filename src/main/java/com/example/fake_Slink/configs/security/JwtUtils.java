package com.example.fake_Slink.configs.security;

import com.example.fake_Slink.dtos.requests.IntrospectRequest;
import com.example.fake_Slink.dtos.responses.IntrospectResponse;
import com.example.fake_Slink.models.Student;
import com.example.fake_Slink.models.Teacher;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secretKey}")
    private String secretKey;

    public String generateToken(Object person) {
        String idNum;
        int id;
        if(person instanceof Student) {
            Student student = (Student) person;
            idNum = student.getIdNum();
            id = student.getId();
        } else if (person instanceof Teacher) {
            Teacher teacher = (Teacher) person;
            idNum = teacher.getIdNum();
            id = teacher.getId();
        } else {
            throw new RuntimeException("Khong tim thay loai nguoi dung nay.");
        }

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(idNum)
                .issueTime(new Date(System.currentTimeMillis()))
                .expirationTime(new Date(System.currentTimeMillis() + expiration))
                .claim("id", id)
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(secretKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        String token = request.getToken();

        JWSVerifier verifier = new MACVerifier(secretKey.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        boolean verified = signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verified && expirationTime.after(new Date()))
                .build();
    }
}
