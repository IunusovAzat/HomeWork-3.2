package ru.hogwarts.school.service;


import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    Student findStudent(Long id);
    Student editStudent(Student student);
    void deleteStudent(Long id);
    Collection<Student> getAll();
    Collection<Student> getAllByAge(int age);
    List<Student> sortStudentByAge(int age);
    List<Student> findByAgeBetween(int ageMin, int ageMax);
    Collection<Student> getStudent(Long id);
    Faculty getFacultyByStudentId(Long id);
    int getCountOfStudents();
    int getAveregeAge();
    Collection<Student> getFiveStudentsOrderedById();
    Collection<String> getStudentsStartsWithA();
    double getAverageAgeViaStreams();

}

