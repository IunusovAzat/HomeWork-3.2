package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);
    List<Student> findByAgeBetween(int ageMin, int ageMax);
    List<Student> findAllByFaculty_Id(Long id);

    @Query(value = "select count(*) from student", nativeQuery = true)
    int getCountOfStudents();

     @Query(value = "select avg(age) from student", nativeQuery = true)
    int getAveregeAge();

     @Query(value = "select * from student order by id desc limit 5", nativeQuery = true)
     Collection<Student> getFiveStudentsOrderedById();



}
