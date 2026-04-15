package Restful.repository;

import Restful.Model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class StudentRepository {

    private final JdbcTemplate jdbc;

    public StudentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void save(Student s) {
        String sql = """
            INSERT INTO student(full_name, birthday, class_name, major, hometown, gender, average_mark)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        jdbc.update(sql,
                s.getFullName(),
                Date.valueOf(s.getBirthday()),
                s.getClassName(),
                s.getMajor(),
                s.getHometown(),
                s.getGender(),
                s.getAverageMark());
    }

    public void update(Student s) {
        String sql = """
            UPDATE student
            SET full_name=?, birthday=?, class_name=?, major=?, hometown=?, gender=?, average_mark=?
            WHERE id=?
        """;

        jdbc.update(sql,
                s.getFullName(),
                Date.valueOf(s.getBirthday()),
                s.getClassName(),
                s.getMajor(),
                s.getHometown(),
                s.getGender(),
                s.getAverageMark(),
                s.getId());
    }

    public void delete(int id) {
        jdbc.update("DELETE FROM student WHERE id=?", id);
    }

    public boolean exists(int id) {
        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM student WHERE id=?",
                Integer.class,
                id
        );
        return count != null && count > 0;
    }

    public List<Student> findAll() {
        String sql = "SELECT * FROM student";

        return jdbc.query(sql, (rs, rowNum) -> {
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setFullName(rs.getString("full_name"));
            s.setBirthday(rs.getDate("birthday").toLocalDate());
            s.setClassName(rs.getString("class_name"));
            s.setMajor(rs.getString("major"));
            s.setHometown(rs.getString("hometown"));
            s.setGender(rs.getString("gender"));
            s.setAverageMark(rs.getDouble("average_mark"));
            return s;
        });
    }

    public List<Student> birthdayToday() {
        String sql = """
            SELECT * FROM student
            WHERE EXTRACT(DAY FROM birthday) = EXTRACT(DAY FROM CURRENT_DATE)
            AND EXTRACT(MONTH FROM birthday) = EXTRACT(MONTH FROM CURRENT_DATE)
        """;

        return jdbc.query(sql, (rs, rowNum) -> {
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setFullName(rs.getString("full_name"));
            s.setBirthday(rs.getDate("birthday").toLocalDate());
            return s;
        });
    }
}