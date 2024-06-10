package com.example.luckyvicky.card.controller;

import com.example.luckyvicky.card.dto.CreateHabitRequest;
import com.example.luckyvicky.card.dto.HabitListResponse;
import com.example.luckyvicky.card.dto.HabitResponse;
import com.example.luckyvicky.card.dto.UpdateHabitRequest;
import com.example.luckyvicky.card.entity.Animal;
import com.example.luckyvicky.card.service.HabitService;
import com.example.luckyvicky.common.dto.NormalResponse;
import com.example.luckyvicky.member.KakaoTokenJsonData;
import com.example.luckyvicky.member.KakaoUserInfo;
import com.example.luckyvicky.member.dto.KakaoUserInfoResponse;
import com.example.luckyvicky.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/habit")
public class HabitController {
    private final HabitService habitService;

    //랜덤뽑기
    @PostMapping("/randomAnimal")
    public ResponseEntity<Animal> getRandomAnimal(@RequestParam Long id){
        return ResponseEntity.ok(habitService.getRandomAnimal(id));
    }

    //현재 진행 습관
    @GetMapping("/current")
    public ResponseEntity<HabitListResponse> getCurrentList(@RequestParam Long id){
        return ResponseEntity.ok(habitService.getCurrentHabitList(id));
    }

    //진행 완료 습관
    @GetMapping("/done")
    public ResponseEntity<HabitListResponse> getDoneList(@RequestParam Long id){
        return ResponseEntity.ok(habitService.getDoneHabitList(id));
    }

    //습관 상세 보기
    @GetMapping("/{habitId}")
    public ResponseEntity<HabitResponse> getHabit(@RequestParam Long id,
                                                  @PathVariable Long habitId){
        return ResponseEntity.ok(habitService.getHabit(id, habitId));
    }

    //습관 생성
    @PostMapping("")
    public ResponseEntity<Map<String, Object>> createHabit(@RequestParam Long id,
                                            @RequestBody CreateHabitRequest request){

        Map<String, Object> response = new HashMap<>();
        Long habitId = habitService.createHabit(id, request);
        response.put("habitId", habitId);

        return ResponseEntity.ok(response);
    }

    //습관 수정
    @PutMapping("/{habitId}")
    public ResponseEntity<Long> updateHabit(@RequestParam Long id,
                                            @PathVariable Long habitId,
                                            @RequestBody UpdateHabitRequest request){
        return ResponseEntity.ok(habitService.updateHabit(id, habitId, request));
    }

    //습관 삭제
    @DeleteMapping("/{habitId}")
    public ResponseEntity<NormalResponse> deleteHabit(@RequestParam Long id,
                                                      @PathVariable Long habitId){
        return ResponseEntity.ok(habitService.deleteHabit(id, habitId));
    }
}
