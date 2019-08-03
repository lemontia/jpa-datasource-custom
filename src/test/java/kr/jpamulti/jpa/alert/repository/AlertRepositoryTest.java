package kr.jpamulti.jpa.alert.repository;

import kr.jpamulti.config.AlertDataSourceConfig;
import kr.jpamulti.jpa.alert.entity.Alert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(AlertDataSourceConfig.class)
public class AlertRepositoryTest {

    @Autowired
    private AlertRepository alertRepository;

    @Test
    public void crud(){
        Alert alert = new Alert();
        alert.setId(1l);
        alert.setContent("테스트입니다");

        alertRepository.save(alert);

        Long count = alertRepository.count();

        System.out.println("========================== 카운트: " + count);

        assertThat(count).isEqualTo(1);
    }
}
