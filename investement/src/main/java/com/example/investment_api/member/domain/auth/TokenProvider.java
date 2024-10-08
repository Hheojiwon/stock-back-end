package com.example.investment_api.member.domain.auth;

public interface TokenProvider {

    String create(final Long id);

    Long extract(final String token);
}
