package jpabook.jpashop;

import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    /*
    @Test
    public void testMember() throws Exception{

        Member member = new Member();
        member.setUsername("hoonzi");
        //save
        Long saveId = memberRepository.save(member);
        //find
        Member findMember = memberRepository.find(saveId);
        //validation
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
//        Assertions.assertThat(findMember).isEqualTo(member); // em 에 transaction을 걸었기 때문에 영속성 컨테스트에서 내려감
    }
    */
}