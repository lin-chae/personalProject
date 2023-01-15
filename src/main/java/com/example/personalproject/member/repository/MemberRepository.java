package com.example.personalproject.member.repository;

import com.example.personalproject.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

	Optional<Member> findByEmail(String email);

	Optional<Member> findByEmailAuthenticationKey(String EmailAuthenticationKey);


	Optional<Member> findByResetPasswordKey(String uuid);
}
