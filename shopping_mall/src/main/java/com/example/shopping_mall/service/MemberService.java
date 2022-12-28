package com.example.shopping_mall.service;

import com.example.shopping_mall.domain.Member;
import com.example.shopping_mall.dto.JoinFormDto;
import com.example.shopping_mall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member save(Member member) {
        validateDuplicationMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicationMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());

        if(findMember != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if(member == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

}
