package com.example.investment_api.member.exception.exceptions.auth;

import com.example.investment_api.member.exception.exceptions.MemberErrorCode;
import com.example.investment_api.member.exception.exceptions.MemberException;

public class InvalidLoginRequestException extends MemberException {
    public InvalidLoginRequestException() {
        super(MemberErrorCode.INVALID_LOGIN_REQUEST);
    }
}
