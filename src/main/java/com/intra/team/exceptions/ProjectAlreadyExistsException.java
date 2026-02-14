package com.intra.team.exceptions;

public class ProjectAlreadyExistsException extends RuntimeException {
    public ProjectAlreadyExistsException(String msg) {
        super(msg);
    }
}
