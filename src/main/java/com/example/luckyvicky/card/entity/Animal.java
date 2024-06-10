package com.example.luckyvicky.card.entity;

import com.example.luckyvicky.common.exception.ErrorCode;
import com.example.luckyvicky.common.exception.LuckyVickyException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.DecoratingClassLoader;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Animal {
    POLAR_BEAR("북극곰", "IST",
            "북극곰은 기후 변화와 해빙 감소로 인해 서석지가 파괴되고 있어요.",
            "살 곳이 사라져가는 북극곰을 위해 안 쓰는 전자제품을 즉각적으로 끄는 건 어때요?"
                        + " 자차 대신 대중교통을 이용하거나 걷는 것도 도움이 될 거에요!"),
    PANDA("판다", "INF",
            "많은 벌채로 인한 대나무 숲의 감소 때문에 판다의 서식지가 위험받고 있어요.",
                        "판다가 좋아하는 대나무 숲을 살리기 위해 종이 사용을 줄여봐요!"
                        + " 생활 속 혼합 쓰레기의 분리수거를 철저히 지키는 것도 판다에게 도움이 될 거에요."),
    SEA_TURTLE("바다거북", "ISF",
            "바다거북은 바다로 유입되는 플라스틱 쓰레기때문에 생명의 위협을 느끼고 있어요.",
                        "바다거북이 플라스틱을 먹이로 착각해 먹지 않도록 일회용 플라스틱 사용을 줄여봐요."
                                    + " 배달, 포장 시 일회용품을 거절하거나 텀블러 사용을 50회 이상 늘린다면 바다거북에게 큰 도움이 된답니다!"),
    OTTER("수달", "ESF",
            "많은 화학물질과 산업 폐기물, 플라스틱 쓰레기는 수질을 오염시켜 수달이 살 수 없게 만들어요.",
            "수달이 좋아하는 물을 아껴주세요. 양치 시 양치컵을 사용하거나 샴푸 대신 샴푸바를 활용해보아요!"),
    MOUNTAIN_GORILLA("마운틴 고릴라", "INT",
            "마운틴 고릴라는 하루 5~6시간을 먹는 데 사용해요. 하지만 기후 위기로 비가 내리지 않게 되면서 마운틴 고릴라의 먹이인 식물이 사라져가고 있어요.",
            "음식물 쓰레기를 줄이면 온실가스 배출량도 함께 줄어들어요. 마운틴 고릴라의 서식지에 비를 내려 줄 수 있을 거에요!"),
    CORAL("산호", "IST",
            "지구 온난화로 해수 온도가 상승하면서 산호들이 열 스트레스를 겪고 있어요.",
            "스팸 메일을 꾸준히 지우는 것만으로도 전 세계 냉각기 사용이 줄어들어요! 산호초가 빛을 잃지 않도록 도와봐요."),
    TIGER("벵갈 호랑이", "ENT",
            "벵갈 호랑이의 서식지인 습지가 해수면 상승으로 인해 물에 잠겨가고 있어요.",
            "새 물건도 좋지만 리사이클링 물품 또는 업사이클리이을 애용해보는 건 어떨까요?"),
    CHEETAH("아프리카 치타", "EST",
            "극심한 폭염으로 수컷 치타의 남성 호르몬 수치가 낮아지면서 치타는 더 이상 아이를 가질 수 없어요.",
            "재사용이 가능한 용기를 적극 사용하고 무포장 제품을 선택해 구매해봐요! 아기 치타가 태어나는 데 도움이 될 거에요."),
    ELEPHANT("아프리카 코끼리", "ENF",
            "코끼리의 서식지는 사람들의 유해한 산업활동으로 인해 점점 더 파괴되어 가고 있어요.",
            "채식을 늘리는 것은 온실가스 배출량을 줄이는 데 큰 도움이 돼요. 추가로 지역 농산물 소비를 늘린다면 탄소 발자국을 줄일수도 있을거에요! ");

    @JsonValue
    private final String name;
    private final String mbti;
    private final String description;   //멸종 위기 이유 설명
    private final String recommend; //추천 습관

    private static final Map<String, Animal> animalMap = Stream.of(values())
            .collect(Collectors.toMap(Animal::getName, Function.identity()));

    @JsonCreator
    public static Animal resolve(String name) {
        return Optional.ofNullable(animalMap.get(name))
                .orElseThrow(() -> new LuckyVickyException(ErrorCode.VALUE_NOT_IN_OPTION));
    }
}
