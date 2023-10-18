package ru.hogwarts.school.service;


import ru.hogwarts.school.model.Faculty;

public interface FacultyService {
    Faculty addFaculty(Faculty faculty);
    Faculty findFaculty(Long id);
    Faculty editFaculty(Long id, Faculty faculty);
    void deleteFaculty(Long id);
}
