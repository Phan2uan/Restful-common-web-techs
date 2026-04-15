package Restful.controller;

import Restful.Model.Student;
import Restful.dto.StudentSearchRequest;
import Restful.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }
    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }
    @PostMapping
    public String create(@RequestBody Student s) {
        service.create(s);
        return "Created";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody Student s) {
        s.setId(id);
        service.update(s);
        return "Updated";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "Deleted";
    }

    @PostMapping("/search")
    public List<Student> search(@RequestBody StudentSearchRequest req) {
        return service.search(req);
    }
    @GetMapping("/birthday")
    public List<Student> birthday() {
        return service.birthdayToday();
    }
}