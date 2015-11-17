package com.eventwebapp.repositories;

import com.eventwebapp.entities.rso.RsoMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by xavier on 11/6/15.
 */
public interface RsoMemberRepo extends JpaRepository<RsoMember, Long> {
//    @Query("SELECT r FROM RsoMember r WHERE r.id_rso = :rsoId")
    List<RsoMember> findByMember(@Param("member") Long member);

    @Query("SELECT r FROM RsoMember r WHERE r.member = :member AND r.affiliated_rso = :rso")
    RsoMember findByMemberAndRso(@Param("member") Long member, @Param("rso") Long rso);
}
