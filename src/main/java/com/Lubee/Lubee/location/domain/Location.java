package com.Lubee.Lubee.location.domain;

import com.Lubee.Lubee.enumset.Category;
import com.Lubee.Lubee.enumset.Spot;
import com.Lubee.Lubee.memory.domain.Memory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long location_id;

    // 좌표
    private Point coordinate;

    // 위치를 볼 수 있는 spot
    private Spot spot;

    // 위치 사진
    private String picture;

    // 위치 이름
    private String name;

    // 위치 주소
    private String parcelBaseAddress;

    // 위치 카테고리
    private Category category;

    // 몇번이나 방문한 곳인지
    private int count;

    @OneToMany(mappedBy = "location")
    private List<Memory> memories;
}