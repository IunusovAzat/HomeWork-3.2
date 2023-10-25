package ru.hogwarts.school.service;


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

    @Override
    public Student addStudent(Student student) {
       return studentRepository.save(student);
    }
    @Override
    public Student findStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }
    @Override
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }
    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Collection<Student> getAllByAge( int age){
        return getAll().
                stream().
                filter(it->it.getAge()==age).
                collect(Collectors.toList());
    }
    @Override
    public List<Student> sortStudentByAge(int age) {
        return studentRepository.findByAge(age);
    }
    @Override
    public List<Student> findByAgeBetween(int ageMin, int ageMax) {
        return studentRepository.findByAgeBetween(ageMin, ageMax);
    }

    @Override
    public Collection<Student> getStudent(Long id) {
        return studentRepository.findAllByFaculty_Id(id);
    }

    @Override
    public Faculty findStudentByFacultyId(Long id) {
        return studentRepository.findById(id).map(Student::getFaculty).orElse(null);
    }

}
