import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef="auditorDateTimeProvider")
public class JpaAuditingConfig {

	// spring이 갖고 있는 auditorDateTimeProvider Bean객체 오버라이드
	@Bean(name="auditorDateTimeProvider")
	public DateTimeProvider auditorDateTimeProvider() {
		// return 타입이 optional이라 optional로 형변환함
		return () -> Optional.of(LocalDateTime.now());
	}

}