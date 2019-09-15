package ru.topjava.graduation.util.exception;

public class UserVoteForThisDayAlreadyExistsException extends RuntimeException {
    public UserVoteForThisDayAlreadyExistsException(String message) {
        super(message);
    }
}
