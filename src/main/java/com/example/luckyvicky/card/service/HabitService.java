package com.example.luckyvicky.card.service;

import com.example.luckyvicky.card.dto.CreateHabitRequest;
import com.example.luckyvicky.card.dto.HabitListResponse;
import com.example.luckyvicky.card.dto.HabitResponse;
import com.example.luckyvicky.card.dto.UpdateHabitRequest;
import com.example.luckyvicky.card.entity.Animal;
import com.example.luckyvicky.card.entity.Habit;
import com.example.luckyvicky.card.repository.HabitRepository;
import com.example.luckyvicky.common.dto.NormalResponse;
import com.example.luckyvicky.common.exception.ErrorCode;
import com.example.luckyvicky.common.exception.LuckyVickyException;
import com.example.luckyvicky.member.entity.Member;
import com.example.luckyvicky.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class HabitService {
    private final HabitRepository habitRepository;
    private final MemberRepository memberRepository;

    //나한테 없는 동물카드 중 랜덤뽑기
    public Animal getRandomAnimal(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new LuckyVickyException(ErrorCode.MEMBER_NOT_FOUND));

        List<Animal> animalList = List.of(Animal.values());

        habitRepository.findAllByMember(member).stream()
                .forEach(habit -> {
                    if(animalList.contains(habit.getAnimal()))
                        animalList.remove(habit.getAnimal());
                });


        Random random = new Random();
        Animal randomAnimal = animalList.get(random.nextInt(animalList.size()));

        return randomAnimal;
    }

    //습관 생성
    public Long createHabit(Long memberId, CreateHabitRequest request){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new LuckyVickyException(ErrorCode.MEMBER_NOT_FOUND));
        Habit habit = habitRepository.save(request.toEntity(member));

        return habit.getId();
    }

    public NormalResponse deleteHabit(Long memberId, Long id){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new LuckyVickyException(ErrorCode.MEMBER_NOT_FOUND));

        Habit habit = habitRepository.findById(id)
                .orElseThrow(()-> new LuckyVickyException(ErrorCode.VALUE_NOT_IN_OPTION));
        habitRepository.delete(habit);
        return NormalResponse.success();
    }

    public HabitResponse getHabit(Long memberId, Long habitId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new LuckyVickyException(ErrorCode.MEMBER_NOT_FOUND));

        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(()-> new LuckyVickyException(ErrorCode.VALUE_NOT_IN_OPTION));

        return HabitResponse.of(habit);

    }
    //지금 현재 진행 중인 습관들 보여주기
    public HabitListResponse getCurrentHabitList(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new LuckyVickyException(ErrorCode.MEMBER_NOT_FOUND));


        List<HabitResponse> habitResponses =  habitRepository.findAllByMemberAndStatus(member, 0)
                .stream()
                .sorted((h1, h2) -> h2.getId().compareTo(h1.getId()))
                .map(habit -> HabitResponse.of(habit))
                .collect(Collectors.toList());

        return HabitListResponse.from(habitResponses);
    }

    //update
    public Long updateHabit(Long memberId, Long id, UpdateHabitRequest request){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new LuckyVickyException(ErrorCode.MEMBER_NOT_FOUND));

        Habit habit = habitRepository.findById(id)
                .orElseThrow(()-> new LuckyVickyException(ErrorCode.VALUE_NOT_IN_OPTION));

        habit.updateHabit(
            request.getTitle(), request.getDescription(), request.getCount(), request.getColor()
        );
        habitRepository.save(habit);
        return habit.getId();
    }

    //완료한 습관들 보여주기
    public HabitListResponse getDoneHabitList(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new LuckyVickyException(ErrorCode.MEMBER_NOT_FOUND));


        List<HabitResponse> habitResponses = habitRepository.findAllByMemberAndStatus(member, 1)
                .stream()
                .sorted((h1, h2) -> h2.getId().compareTo(h1.getId()))
                .map(habit -> HabitResponse.of(habit))
                .collect(Collectors.toList());

        return HabitListResponse.from(habitResponses);
    }
}
