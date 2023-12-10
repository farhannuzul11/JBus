package com.farhanNuzulNJBusAF.controller;

/**
 * Represents a generic response containing success status, a message, and a payload.
 *
 * <p>
 * This class is used to encapsulate the result of operations, providing information about the success
 * of the operation, a descriptive message, and an optional payload.
 * </p>
 *
 * <p>
 * The success field indicates whether the operation was successful or not. The message field
 * contains a human-readable description of the operation result. The payload field may hold additional
 * information related to the operation.
 * </p>
 *
 * @param <T> The type of the payload.
 *
 * @version 1.0
 */
public class BaseResponse<T> {
    public boolean success;
    public String message;
    public T payload;

    /**
     * Constructs a new BaseResponse with the specified success status, message, and payload.
     *
     * @param success Indicates whether the operation was successful.
     * @param message A descriptive message providing information about the operation result.
     * @param payload An optional payload containing additional information related to the operation.
     */
    public BaseResponse(boolean success, String message, T payload) {
        this.success = success;
        this.message = message;
        this.payload = payload;
    }
}
