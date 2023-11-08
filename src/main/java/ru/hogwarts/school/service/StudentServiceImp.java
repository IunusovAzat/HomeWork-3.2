package ru.hogwarts.school.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImp implements StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentServiceImp(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    private final Logger logger = LoggerFactory.getLogger(StudentServiceImp.class);

    @Override
    public Student addStudent(Student student) {
        logger.info("Was invoked method for addStudent");
       return studentRepository.save(student);
    }
    @Override
    public Student findStudent(Long id) {
        logger.info("Was invoked method for findStudent");
        return studentRepository.findById(id).orElse(null);
    }
    @Override
    public Student editStudent(Student student) {
        logger.info("Was invoked method for editStudent");
        return studentRepository.save(student);
    }
    @Override
    public void deleteStudent(Long id) {
        logger.info("Was invoked method for deleteStudent");
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> getAll() {
        logger.info("Was invoked method for getAll");
        return studentRepository.findAll();
    }

    @Override
    public Collection<Student> getAllByAge( int age){
        logger.info("Was invoked method for getAllByAge");
        return getAll().
                stream().
                filter(it->it.getAge()==age).
                collect(Collectors.toList());
    }
    @Override
    public List<Student> sortStudentByAge(int age) {
        logger.info("Was invoked method for sortStudentByAge");
        return studentRepository.findByAge(age);
    }
    @Override
    public List<Student> findByAgeBetween(int ageMin, int ageMax) {
        logger.info("Was invoked method for findByAgeBetween");
        return studentRepository.findByAgeBetween(ageMin, ageMax);
    }

    @Override
    public List<Student> getStudent(Long id) {
        logger.info("Was invoked method for getStudent");
        return studentRepository.findAllByFaculty_Id(id);
    }

    @Override
    public Faculty getFacultyByStudentId(Long id) {
        logger.info("Was invoked method for getFacultyByStudentId");
        return studentRepository.findById(id).map(Student::getFaculty).orElse(null);
    }

    @Override
    public int getCountOfStudents() {
        logger.info("Was invoked method for getCountOfStudents");
        return studentRepository.getCountOfStudents();
    }

    @Override
    public int getAveregeAge() {
        logger.info("Was invoked method for getAveregeAge");
        return studentRepository.getAveregeAge();
    }

    @Override
    public Collection<Student> getFiveStudentsOrderedById() {
        logger.info("Was invoked method for getFiveStudentsOrderedById");
        return studentRepository.getFiveStudentsOrderedById();
    }

    @Override
    public Collection<String> getStudentsStartsWithA() {
        return studentRepository.findAll().
                stream().
                map(Student::getName).
                map(String::toUpperCase).
                filter(it -> it.startsWith("A")).
                sorted().
                collect(Collectors.toList());
    }

    @Override
    public double getAverageAgeViaStreams() {
        return studentRepository.findAll().
                stream().
                mapToInt(Student::getAge).
                average().
                orElse(0.0);
    }


}
