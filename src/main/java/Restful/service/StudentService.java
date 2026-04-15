package Restful.service;

import Restful.Model.Student;
import Restful.dto.StudentSearchRequest;
import Restful.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public void create(Student s) {
        validate(s);
        logger.info("Create student {}", s.getFullName());
        repo.save(s);
    }
    public List<Student> getAll() {
        return repo.findAll(); // ✅ đúng
    }
    public void update(Student s) {
        if (!repo.exists(s.getId())) {
            throw new RuntimeException("Student not found");
        }
        validate(s);
        repo.update(s);
    }

    public void delete(int id) {
        if (!repo.exists(id)) {
            throw new RuntimeException("Student not found");
        }
        repo.delete(id);
    }

    public List<Student> search(StudentSearchRequest req) {
        List<Student> list = repo.findAll();

        return list.stream()
                .filter(s -> req.getName() == null || s.getFullName().toLowerCase().contains(req.getName().toLowerCase()))
                .filter(s -> req.getGender() == null || s.getGender().equalsIgnoreCase(req.getGender()))
                .filter(s -> req.getHometown() == null || s.getHometown().toLowerCase().contains(req.getHometown().toLowerCase()))
                .filter(s -> req.getClassName() == null || s.getClassName().equalsIgnoreCase(req.getClassName()))
                .filter(s -> req.getMajor() == null || s.getMajor().equalsIgnoreCase(req.getMajor()))
                .filter(s -> req.getMinMark() == null || s.getAverageMark() >= req.getMinMark())
                .filter(s -> req.getMaxMark() == null || s.getAverageMark() <= req.getMaxMark())
                .filter(s -> req.getFromDate() == null || !s.getBirthday().isBefore(req.getFromDate()))
                .filter(s -> req.getToDate() == null || !s.getBirthday().isAfter(req.getToDate()))
                .skip((long) req.getPage() * req.getSize())
                .limit(req.getSize())
                .collect(Collectors.toList());
    }

    public List<Student> birthdayToday() {
        return repo.birthdayToday();
    }

    private void validate(Student s) {

        if (s.getFullName() == null || s.getFullName().isEmpty()) {
            throw new RuntimeException("Name is required");
        }

        if (s.getFullName().length() > 50) {
            throw new RuntimeException("Name too long");
        }

        if (s.getBirthday() == null || s.getBirthday().isAfter(LocalDate.now())) {
            throw new RuntimeException("Invalid birthday");
        }

        if (s.getAverageMark() < 0 || s.getAverageMark() > 10) {
            throw new RuntimeException("Invalid mark");
        }
    }
}