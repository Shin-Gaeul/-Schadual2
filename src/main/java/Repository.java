package com.example.repository;

import com.example.model.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 일정 생성 메서드
     * @param schedule 생성할 일정 정보
     * @return 생성된 일정의 ID
     */
    public int insert(Schedule schedule) {
        String sql = "INSERT INTO schedule (todo, author, password, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?)";

        // `update` 메서드는 생성된 행의 개수를 반환하므로, 이를 기반으로 ID를 가져오려면 추가 로직이 필요합니다.
        jdbcTemplate.update(sql,
                schedule.getTodo(),
                schedule.getAuthor(),
                schedule.getPassword(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );

        // MySQL의 LAST_INSERT_ID()를 사용하여 생성된 ID를 가져옴
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }
}
