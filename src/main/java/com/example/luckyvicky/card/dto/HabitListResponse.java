package com.example.luckyvicky.card.dto;

import com.example.luckyvicky.card.entity.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
public class HabitListResponse {
    List<HabitResponse> habitResponseList;
    public static HabitListResponse from(List<HabitResponse> habits){
        return HabitListResponse.builder()
                .habitResponseList(habits)
                .build();
    }
}
