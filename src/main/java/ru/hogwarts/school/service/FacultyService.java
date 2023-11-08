package ru.hogwarts.school.service;


import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);
    Faculty findFaculty(Long id);
    Faculty editFaculty(Faculty faculty);
    void deleteFaculty(Long id);
    Collection<Faculty> getAll();
    Collection<Faculty> getAllByColor(String color);
    List<Faculty> sortFacultyByColor(String color);
    List<Faculty> findByNameIgnoreCaseAndColorIgnoreCase(String name, String color);
    Collection<Student> getStudents(Long id);
    Collection<Student> getFacultyStudents(Long id);
    String getLongestName();
}
