package org.bookulove.book.api.library.model.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bookulove.book.api.book.model.db.entity.BookLibraryRelation;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Library {

    @Id
    private Long userId;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private double lat;     // 위도

    @NotNull
    private double lng;     // 경도

    @OneToMany(mappedBy = "buid")
    private List<BookLibraryRelation> bookLibraryRelation = new ArrayList<>();

    @Builder
    public Library(Long userId, @NotNull String name, String description, @NotNull double lat, @NotNull double lng) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.lat = lat;
        this.lng = lng;
    }

    @Builder
    public Library(Long userId, @NotNull String name, @NotNull double lat, @NotNull double lng) {
        this.userId = userId;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }
}
