package by.ustsinovich.tutormatic.auth.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TokenClaims {

    ROLES("roles"),

    TYPE("type");

    private final String value;

}
