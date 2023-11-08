package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.security.PublicKey;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo(@PathVariable Long id) {
        Faculty faculty = facultyService.findFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find")
    public Collection<Faculty> getAllByColor(@RequestParam String color) {
        return facultyService.getAllByColor(color);
    }

    @GetMapping("/sort")
    public List<Faculty> getFacultySortedList(@RequestParam(value = "color") String color) {
        return facultyService.sortFacultyByColor(color);
    }

    @GetMapping("/sortBy")
    public List<Faculty> getFacultySortedByNameAndColorList(@RequestParam(value = "name") String name,
                                                            @RequestParam(value = "color") String color) {
        return facultyService.findByNameIgnoreCaseAndColorIgnoreCase(name, color);
    }
    @GetMapping("/{id}/student")
    public Collection<Student> getStudents(@PathVariable Long id){
        return facultyService.getStudents(id);
    }

    @GetMapping("/longest-name")
    public String getLongestName(){
        return facultyService.getLongestName();
    }
}
