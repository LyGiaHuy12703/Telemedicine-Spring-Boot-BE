package org.telemedicine.serverside.dto.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Builder
public class ApiResponse<T> {
    @Builder.Default
    boolean success = true;
    String message;
    String code;
    T data;
    List<ValidationError> errors; //thêm trường lỗi

    //thêm phương thức để tạo phản hồi lỗi
    public static <T> ApiResponse<T> error(String code, List<ValidationError> errors) {
        return ApiResponse.<T>builder()
                .success(false)
                .code(code)
                .errors(errors)
                .build();
    }

}
