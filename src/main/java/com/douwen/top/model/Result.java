package com.douwen.top.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nonnull;
import org.springframework.lang.Nullable;

import java.util.function.Function;

public class Result<T> {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Result.class);
    @JsonIgnore
    private final int httpStatusHint;
    private final boolean success;
    private final String code;
    private final String message;
    private final T data;
    private final long timestamp;

    public static <T> Result<T> success() {
        return success(200, null);
    }

    @Nonnull
    public static <T> Result<T> success(@Nullable T data) {
        return success(200, data);
    }

    @Nonnull
    public static <T> Result<T> success(int httpStatusHint, @Nullable T data) {
        return success(httpStatusHint, null, null, data);
    }

    @Nonnull
    public static <T> Result<T> success(@Nullable String code, @Nullable String message, @Nullable T data) {
        return success(200, code, message, data);
    }

    @Nonnull
    public static <T> Result<T> success(int httpStatusHint, @Nullable String code, @Nullable String message, @Nullable T data) {
        if ( // NOPMD
                httpStatusHint > 0 && (httpStatusHint < 200 || httpStatusHint >= 400)) {
            log.warn("Response succeed, but http status hint is not 2xx or 3xx: {}", httpStatusHint);
        }
        return new Result<>(httpStatusHint, true, code, message, data, now());
    }

    @Nonnull
    public static <T> Result<T> failed(String code, String message) {
        return failed(code, message, null);
    }

    @Nonnull
    public static <T> Result<T> failed(int httpStatusHint, String code, String message) {
        return failed(httpStatusHint, code, message, null);
    }

    @Nonnull
    public static <T> Result<T> failed(String code, String message, @Nullable T data) {
        return failed(500, code, message, data);
    }

    @Nonnull
    public static <T> Result<T> failed(int httpStatusHint, String code, String message, @Nullable T data) {
        return new Result<>(httpStatusHint, false, code, message, data, now());
    }

    private static long now() {
        return System.currentTimeMillis();
    }

    /**
     * This getter is for backwards-compatibility.
     *
     * @return code
     */
    @Nullable
    public String getMessageCode() {
        return this.code;
    }

    @JsonIgnore
    public int getHttpStatusHint() {
        return httpStatusHint;
    }

    @JsonIgnore
    public boolean isFailed() {
        return !isSuccess();
    }

    @JsonIgnore
    public boolean isSuccessWithStatus(int status) {
        return isSuccess() && this.httpStatusHint == status;
    }

    @JsonIgnore
    public boolean isFailedWithStatus(int status) {
        return isFailed() && this.httpStatusHint == status;
    }

    @Nonnull
    public <D> Result<D> cleanData() {
        return transferData(d -> null);
    }

    @Nonnull
    public <D> Result<D> transferData(@Nonnull Function<T, D> action) {
        return new Result<>(httpStatusHint, success, code, message, action.apply(data), timestamp);
    }

    @Nonnull
    public <D> Result<D> transferDataIfPresent(@Nonnull Function<T, D> action) {
        return transferData(d -> d == null ? null : action.apply(d));
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Result)) return false;
        final Result<?> other = (Result<?>) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getHttpStatusHint() != other.getHttpStatusHint()) return false;
        if (this.isSuccess() != other.isSuccess()) return false;
        if (this.getTimestamp() != other.getTimestamp()) return false;
        final Object this$code = this.getCode();
        final Object other$code = other.getCode();
        if (this$code == null ? other$code != null : !this$code.equals(other$code)) return false;
        final Object this$message = this.getMessage();
        final Object other$message = other.getMessage();
        if (this$message == null ? other$message != null : !this$message.equals(other$message)) return false;
        final Object this$data = this.getData();
        final Object other$data = other.getData();
        if (this$data == null ? other$data != null : !this$data.equals(other$data)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Result;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getHttpStatusHint();
        result = result * PRIME + (this.isSuccess() ? 79 : 97);
        final long $timestamp = this.getTimestamp();
        result = result * PRIME + (int) ($timestamp >>> 32 ^ $timestamp);
        final Object $code = this.getCode();
        result = result * PRIME + ($code == null ? 43 : $code.hashCode());
        final Object $message = this.getMessage();
        result = result * PRIME + ($message == null ? 43 : $message.hashCode());
        final Object $data = this.getData();
        result = result * PRIME + ($data == null ? 43 : $data.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Result(httpStatusHint=" + this.getHttpStatusHint() + ", success=" + this.isSuccess() + ", code=" + this.getCode() + ", message=" + this.getMessage() + ", data=" + this.getData() + ", timestamp=" + this.getTimestamp() + ")";
    }

    private Result(final int httpStatusHint, final boolean success, final String code, final String message, final T data, final long timestamp) {
        this.httpStatusHint = httpStatusHint;
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = timestamp;
    }
}
