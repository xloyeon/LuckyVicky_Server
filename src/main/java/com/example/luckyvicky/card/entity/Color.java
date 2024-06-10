package com.example.luckyvicky.card.entity;

import com.example.luckyvicky.common.exception.ErrorCode;
import com.example.luckyvicky.common.exception.LuckyVickyException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Color {
    GREEN(1),
    YELLOW(2),
    BLUE(3),
    ORANGE(4);

    private static final Map<Integer, Color> colorMap = Stream.of(values())
            .collect(Collectors.toMap(Color::getIdx, Function.identity()));

    @JsonValue
    private final int idx;

    @JsonCreator
    public static Color resolve(int idx){
        return Optional.ofNullable(colorMap.get(idx))
                .orElseThrow(() -> new LuckyVickyException(ErrorCode.VALUE_NOT_IN_OPTION));
    }


}
