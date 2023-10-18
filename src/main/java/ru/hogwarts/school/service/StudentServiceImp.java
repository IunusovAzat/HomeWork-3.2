package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;


import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class StudentServiceImp implements StudentService {

    private final HashMap<Long, Student> students = new HashMap<>();
    private long count = 0;

    @Override
    public Student addStudent(Student student) {
        student.setId(count++);
        students.put(student.getId(), student);
        return students.get(student.getId());
    }
    @Override
    public Student findStudent(Long id) {
        return students.get(id);
    }
    @Override
    public Student editStudent(Student student) {
        if (!students.containsKey(student.getId())) {
            return null;
        }
        students.put(student.getId(), student);
        return students.get(student.getId());
    }
    @Override
    public void deleteStudent(Long id) {
        students.remove(id);
    }

    @Override
    public Collection<Student> getAll() {
        return students.values();
    }

    @Override
    public Collection<Student> getAllByAge( int age){
        return getAll().
                stream().
                filter(it->it.getAge()==age).
                collect(Collectors.toList());
    }

}
