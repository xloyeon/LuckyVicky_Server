package com.example.luckyvicky.card.entity;

import com.example.luckyvicky.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "habit")
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 2000)
    private String description;

    @ColumnDefault("3")
    @Column(name = "goal_count")
    private int goal_count;  //습관 설정 횟수

    @ColumnDefault("0")
    @Column(name = "count")
    private int count;  //습관 달성 횟수

    @Enumerated(EnumType.STRING)
    private Animal animal;

    @Enumerated(EnumType.STRING)
    private Color color;

    private int status; //진행중 : 0, 완료:1

    @Builder
    public Habit(Member member, String title, String description,
                 int goal_count, int count, Animal animal, Color color){
        this.member = member;
        this.title = title;
        this.description = description;
        this.goal_count = goal_count;
        this.count = count;
        this.animal = animal;
        this.color = color;
        this.status = 0;
    }

    public void updateHabit(String title,
                            String description,
                            int count,
                            Color color){
        this.title = title;
        this.description = description;
        this.count = count;
        this.color = color;
    }
}
