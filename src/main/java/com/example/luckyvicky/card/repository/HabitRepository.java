package com.example.luckyvicky.card.repository;

import com.example.luckyvicky.card.entity.Habit;
import com.example.luckyvicky.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findAllByMember(Member member);

    List<Habit> findAllByMemberAndStatus(Member member, int status);
}
