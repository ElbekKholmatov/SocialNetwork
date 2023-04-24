package com.instagram.instagram.domains;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "verification_code")
public class VerificationCode extends Auditable<Long> {

    private String code;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp default now()+interval '2 minutes'")
    private Date expiryDate;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Builder(builderMethodName = "childBuilder")
    public VerificationCode(Long id, String code, Date expiryDate, String username, String email) {
        super(id, null, null, null, null, false);
        this.code = code;
        this.expiryDate = expiryDate;
        this.username = username;
        this.email = email;
    }

}
