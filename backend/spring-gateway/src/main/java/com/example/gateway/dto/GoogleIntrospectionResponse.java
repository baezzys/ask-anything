package com.example.gateway.dto;

import lombok.*;

@NoArgsConstructor
@Data
public class GoogleIntrospectionResponse{
    String azp;
    String aud;
    String sub;
    String scope;

    String exp;

    String expires_in;
    String email;
    String email_verified;

    String access_type;

    public String getScope() {
        return this.scope;
    }

    public String getAzp() {
        return azp;
    }

    public String getAud() {
        return aud;
    }

    public String getSub() {
        return sub;
    }

    public String getExp() {
        return exp;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail_verified() {
        return email_verified;
    }

    public String getAccess_type() {
        return access_type;
    }
}