package org.bookulove.user.adapter.out.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bookulove.common.entity.BaseTimeEntity;

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

    @Size(max = 16)
    @NotNull
    private String loginId;

    @NotNull(message = "패스워드는 필수값입니다.")
    private String password;

    @Size(max = 16)
    @NotNull
    private String nickname;

    @NotNull
    private boolean allowNoti;

    @Builder
    public UserEntity(Long id, String loginId, String password, String nickname, boolean allowNoti) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
        this.allowNoti = allowNoti;
    }

    public static UserEntity of(String id, String password, String nickname) {
        return UserEntity.builder()
                .loginId(id)
                .password(password)
                .nickname(nickname)
                .allowNoti(true)
                .build();
    }

    public void updateUser(String password, String nickname) {
        if (password != null) {
            this.password = password;
        }
        if (nickname != null) {
            this.nickname = nickname;
        }
    }
}
