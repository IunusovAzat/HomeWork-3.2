package ru.hogwarts.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public  class FacultyServiceImp implements FacultyService {

    @Autowired
    private final FacultyRepository facultyRepository;
    @Autowired
    private final StudentRepository studentRepository;

    public FacultyServiceImp(FacultyRepository facultyRepository, StudentService studentService, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    @Override
    public Collection<Faculty> getAllByColor(String color) {
        return getAll().
                stream().
                filter(it -> it.getColor().equals(color)).
                collect(Collectors.toList());
    }

    @Override
    public List<Faculty> sortFacultyByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    @Override
    public List<Faculty> findByNameIgnoreCaseAndColorIgnoreCase(String name, String color) {
        return facultyRepository.findByNameIgnoreCaseAndColorIgnoreCase(name, color);
    }

    @Override
    public Collection<Student> getStudents(Long id) {
        return studentRepository.findAllByFaculty_Id(id);
    }
}
