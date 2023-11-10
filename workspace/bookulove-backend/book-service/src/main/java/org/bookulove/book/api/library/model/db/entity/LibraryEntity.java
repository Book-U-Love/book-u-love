package org.bookulove.book.api.library.model.db.entity;

import jakarta.persistence.CascadeType;
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

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LibraryEntity {

    @Id
    private Long userId;

    @NotNull
    private String name;

    @NotNull
    private double lat;     // 위도

    @NotNull
    private double lng;     // 경도

    @OneToMany(mappedBy = "buid", cascade = ALL, orphanRemoval = true)
    private List<BookLibraryRelation> bookLibraryRelation = new ArrayList<>();

    @Builder
    public LibraryEntity(Long userId, String name, double lat, double lng, List<BookLibraryRelation> bookLibraryRelation) {
        this.userId = userId;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.bookLibraryRelation = bookLibraryRelation;
    }

    public static LibraryEntity of(Long userId, String name, double lat, double lng){
        return LibraryEntity.builder()
                .userId(userId)
                .name(name)
                .lat(lat)
                .lng(lng)
                .build();
    }
}
