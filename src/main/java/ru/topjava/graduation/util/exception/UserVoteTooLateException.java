package ru.topjava.graduation.util.exception;

public class UserVoteTooLateException extends RuntimeException {
    public UserVoteTooLateException(String message) {
        super(message);
    }
}
