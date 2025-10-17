package it.back.back_app.admin.dto;

import java.time.LocalDateTime;

public interface AdminUserProjection {
    String getUserId();
    String getName();
    String getPhone();
    String getEmail();
    String getAddress();
    String getAddressDetail();
    int getCache();
    String getRole();
    LocalDateTime getCreateDate();
    LocalDateTime getUpdateDate();
}
