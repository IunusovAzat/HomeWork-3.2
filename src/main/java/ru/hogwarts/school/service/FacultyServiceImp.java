package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(FacultyServiceImp.class);

    @Override
    public Faculty addFaculty(Faculty faculty) {
        logger.info("Was invoked method for addFaculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty findFaculty(Long id) {
        logger.info("Was invoked method for findFaculty");
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method for editFaculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long id) {
        logger.info("Was invoked method for deleteFaculty");
        facultyRepository.deleteById(id);
    }

    @Override
    public Collection<Faculty> getAll() {
        logger.info("Was invoked method for getAll");
        return facultyRepository.findAll();
    }

    @Override
    public Collection<Faculty> getAllByColor(String color) {
        logger.info("Was invoked method for getAllByColor");
        return getAll().
                stream().
                filter(it -> it.getColor().equals(color)).
                collect(Collectors.toList());
    }

    @Override
    public List<Faculty> sortFacultyByColor(String color) {
        logger.info("Was invoked method for sortFacultyByColor");
        return facultyRepository.findByColor(color);
    }

    @Override
    public List<Faculty> findByNameIgnoreCaseAndColorIgnoreCase(String name, String color) {
        logger.info("Was invoked method for findByNameIgnoreCaseAndColorIgnoreCase");
        return facultyRepository.findByNameIgnoreCaseAndColorIgnoreCase(name, color);
    }

    @Override
    public Collection<Student> getStudents(Long id) {
        logger.info("Was invoked method for getStudents");
        return studentRepository.findAllByFaculty_Id(id);
    }

    @Override
    public Collection<Student> getFacultyStudents(Long id) {
        logger.info("Was invoked method for getFacultyStudents");
        return facultyRepository.findById(id).map(Faculty::getStudents).orElse(null);
    }
}
