package org.bookulove.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bookyoulove.entity.BaseTimeEntity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Table(name = "User")
@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class UserEntity extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Size(max = 128)
    @NotNull
    private String password;

    @Size(max = 16)
    @NotNull
    private String nickname;

    @Size(max = 16)
    @NotNull
    private String phone;

    @NotNull
    private boolean allowNoti;


}
