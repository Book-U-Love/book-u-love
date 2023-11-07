package org.bookulove.user.adapter.out.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bookulove.common.entity.BaseTimeEntity;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Table(name = "user_evaluation")
@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class UserEvalEntity extends BaseTimeEntity {

    @Id
    @Column(name = "eval_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull(message = "평가 점수는 필수값입니다.")
    private int grade;

    @NotEmpty(message = "평가 내용은 필수값입니다.")
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @NotNull(message = "평가자는 필수값입니다.")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "reviewer_id")
    private UserEntity reviewer;

    @NotNull(message = "평가대상은 필수값입니다.")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "reviewee_id")
    private UserEntity reviewee;

    @Builder
    public UserEvalEntity(Long id, int grade, String content, UserEntity reviewer, UserEntity reviewee) {
        this.id = id;
        this.grade = grade;
        this.content = content;
        this.reviewer = reviewer;
        this.reviewee = reviewee;
    }

    public static UserEvalEntity of(int grade, String content, UserEntity reviewer, UserEntity reviewee){
        return UserEvalEntity.builder()
                .grade(grade)
                .content(content)
                .reviewer(reviewer)
                .reviewer(reviewee)
                .build();
    }
}
