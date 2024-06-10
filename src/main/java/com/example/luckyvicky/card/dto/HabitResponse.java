package com.example.luckyvicky.card.dto;

import com.example.luckyvicky.card.entity.Animal;
import com.example.luckyvicky.card.entity.Color;
import com.example.luckyvicky.card.entity.Habit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitResponse {
    private Long id;
    private Long member_id;
    private String title;
    private String description;
    private int goal_count;
    private int count;
    private Animal animal;
    private Color color;
    private int status;

    public static  HabitResponse of(Habit habit){
        return HabitResponse.builder()
                .id(habit.getId())
                .member_id(habit.getMember().getId())
                .title(habit.getTitle())
                .description(habit.getDescription())
                .goal_count(habit.getGoal_count())
                .count(habit.getCount())
                .animal(habit.getAnimal())
                .color(habit.getColor())
                .status(habit.getStatus())
                .build();
    }
}
