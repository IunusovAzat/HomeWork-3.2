package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public Collection<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        Student student = studentService.findStudent(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public Collection<Student> getAllByAge(@RequestParam int age) {
        return studentService.getAllByAge(age);
    }

    @GetMapping("/sort")
    public List<Student> getStudentSortedList(@RequestParam(value = "age") int age) {
        return studentService.sortStudentByAge(age);
    }

    @GetMapping("/sortBy")
    public List<Student> getStudentSortedInAgeRangeList(@RequestParam(value = "min") int min,
                                                        @RequestParam(value = "max") int max) {
        return studentService.findByAgeBetween(min, max);
    }

    @GetMapping("/{id}/fakulty")
    public Faculty getFacultyByStudent(@PathVariable Long id) {
        return studentService.getFacultyByStudentId(id);
    }

    @GetMapping("/count")
    public int getCountOfStudents() {
        return studentService.getCountOfStudents();
    }

    @GetMapping("/averege-age")
    public int getAveregeAge() {
        return studentService.getAveregeAge();
    }

    @GetMapping("/five-ordere-by-id")
    public Collection<Student> getFiveStudentsOrderedById() {
        return studentService.getFiveStudentsOrderedById();
    }

    @GetMapping("/starts-with-a")
    public Collection<String> getStudentsStartsWithA() {
        return studentService.getStudentsStartsWithA();
    }

    @GetMapping("/average-age-via-streams")
    public double getAverageAgeViaStreams(){
        return studentService.getAverageAgeViaStreams();
    }
}
