package ru.guteam.customer_service.utils;

public enum Status {

    ACTIVE("ACTIVE"), OUT_OF_DATE("OUT_OF_DATE"), MISSED("MISSED");

    final String value;

    Status(String value) {
        this.value = value;
    }

        public String getValue() {
        return value;
    }
}
