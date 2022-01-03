package com.bauth.auth.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.bauth.auth.model.enums.PasswordResetStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "password_reset")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordReset {
    @Id
    @NotNull
    @Column(name = "username", unique = true)
    private String username;
    @NotNull
    private String code;
    private PasswordResetStatus status;
    //@Column(updatable=false, insertable=false)
    private LocalDateTime createdAt;
    //@Column(insertable=false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        status = PasswordResetStatus.Requested;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
