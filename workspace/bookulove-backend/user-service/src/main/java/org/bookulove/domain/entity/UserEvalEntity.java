package org.bookulove.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bookyoulove.entity.BaseTimeEntity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Table(name = "user_evaluation")
@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class UserEvalEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;


}
