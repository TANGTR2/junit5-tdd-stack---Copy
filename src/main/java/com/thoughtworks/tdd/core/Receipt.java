package com.thoughtworks.tdd.core;

import java.util.Objects;
import java.util.UUID;

public class Receipt {
    private String uuid = UUID.randomUUID().toString();

    public String getReceiptId() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Objects.equals(uuid, receipt.uuid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(uuid);
    }
}
