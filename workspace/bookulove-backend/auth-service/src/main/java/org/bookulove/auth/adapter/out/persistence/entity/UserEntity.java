package org.bookulove.auth.adapter.out.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Table(name = "user")
@Entity
@Getter
@NoArgsConstructor(access = PRIVATE)
public class UserEntity extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Size(max = 32, message = "로그인 아이디는 32글자 이하입니다.")
    @NotNull(message = "로그인 아이디는 필수값입니다.")
    @Column(unique = true)
    private String loginId;

    @NotNull(message = "패스워드는 필수값입니다.")
    private String password;

    @NotNull(message = "핸드폰번호는 필수값입니다.")
    private String phoneNumber;

    @Size(max = 16, message = "닉네임은 16글자 이하입니다.")
    @NotNull(message = "닉네임은 필수값입니다.")
    private String nickname;

    @NotNull(message = "알람설정은 필수값입니다.")
    private boolean allowNoti;

    @Builder
    public UserEntity(Long id, String loginId, String password, String phoneNumber, String nickname, boolean allowNoti) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.allowNoti = allowNoti;
    }

    public static UserEntity of(String id, String password, String phoneNumber, String nickname){
        return UserEntity.builder()
                .loginId(id)
                .password(password)
                .phoneNumber(phoneNumber)
                .nickname(nickname)
                .allowNoti(true)
                .build();
    }
}
