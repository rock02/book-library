package br.com.alelo.enums;

import org.apache.commons.lang3.StringUtils;

public enum AgeGroupEnum {

    YOUNG,
    ADULT,
    OLD;

    public static AgeGroupEnum fromValue( String v ) {
        return StringUtils.isNotBlank( v ) ? valueOf( v ) : null;
    }
}
