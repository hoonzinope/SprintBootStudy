package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args);
	}

	@Bean
	Hibernate5Module hibernate5Module() {
		//return new Hibernate5Module(); // 지연로딩의 경우, API로 엔티티 호출시 LAZY LOADING일때 외부노출을 막는다 (무한 루프 방지)

		Hibernate5Module hibernate5Module = new Hibernate5Module();
		//강제 지연 로딩 설정 (지연로딩 인 column도 가져오게끔!)
//		hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING,true); -> 쓰지 않는게 좋음!
		return hibernate5Module;
	}
}
