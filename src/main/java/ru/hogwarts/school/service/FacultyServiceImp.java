package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;


import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public  class FacultyServiceImp implements FacultyService {

    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private Long count = 0L;

    @Override
    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(count++);
        faculties.put(faculty.getId(), faculty);
        return faculties.get(faculty.getId());
    }

    @Override
    public Faculty findFaculty(Long id) {
        return faculties.get(id);
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        if (!faculties.containsKey(faculty.getId())) {
            return null;
        }
        faculties.put(faculty.getId(), faculty);
        return faculties.get(faculty.getId());
    }

    @Override
    public void deleteFaculty(Long id) {
        faculties.remove(id);
    }

    @Override
    public Collection<Faculty> getAll() {
        return faculties.values();
    }

    @Override
    public Collection<Faculty> getAllByColor(String color) {
        return getAll().
                stream().
                filter(it -> it.getColor().equals(color)).
                collect(Collectors.toList());
    }
}
