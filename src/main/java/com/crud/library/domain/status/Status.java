package com.crud.library.domain.status;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {
    BORROWED("borrowed"),
    AVAILABLE("available"),
    LOST("lost");

    private String status;

    Status(String status){
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }
}
