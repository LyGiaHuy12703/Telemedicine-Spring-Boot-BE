package org.telemedicine.serverside.utils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.telemedicine.serverside.exception.AppException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CodeUtil<T> {
    //lớp lưu trữ mã và thời gian
    private static class CodeEntry<T> {
        private T payload;
        private LocalDateTime expiresAt;
        public CodeEntry(final T payload, final LocalDateTime expiresAt) {
            this.payload = payload;
            this.expiresAt = expiresAt;
        }
        public T getPayload() {
            return payload;
        }
        public LocalDateTime getExpiresAt() {
            return expiresAt;
        }
    }

    private final Map<String, CodeEntry<T>> verificationCodes = new ConcurrentHashMap<>();

    //lưu mã và thời gian sống
    public void save(String code, T payload, long timeLiveConfig) {
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(timeLiveConfig);
        verificationCodes.put(code, new CodeEntry<>(payload, expiresAt));
    }

    //lấy mã nếu còn hiệu lực
    public T get(String code) {
        CodeEntry<T> entry = verificationCodes.get(code);
        if (entry == null || entry.getExpiresAt().isBefore(LocalDateTime.now())) {
            //nếu mã hết hạn hoặc không toonf tại, xóa mã và tra về null
            verificationCodes.remove(code);
            throw new AppException(HttpStatus.NOT_FOUND, "Code not found");
        }
        return entry.getPayload();
    }

    //xóa mã sau khi đã sử dụng
    public void remove(String code) {
        verificationCodes.remove(code);
    }
}
