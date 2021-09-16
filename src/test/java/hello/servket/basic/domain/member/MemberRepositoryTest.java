package hello.servket.basic.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {
    private MemberRepository memberRepository=MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    public void save () throws Exception{
        //given
        Member member = new Member("hello", 20);
        //when
        Member saveMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(saveMember.getId());
        //then
        assertThat(findMember).isEqualTo(saveMember);
    }

    @Test
    public void findAll() throws Exception{
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}