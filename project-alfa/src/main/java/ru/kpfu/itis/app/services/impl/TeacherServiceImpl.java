package ru.kpfu.itis.app.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.app.forms.TeacherAddingForm;
import ru.kpfu.itis.app.model.Teacher;
import ru.kpfu.itis.app.repositories.TeachersRepository;
import ru.kpfu.itis.app.services.TeacherService;

import java.util.List;

/**
 * Created by Robert Gareev
 * 11-601 ITIS KPFU
 * 24.03.2018
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeachersRepository teachersRepository;

    @Override
    public List<Teacher> getAll() {
        return teachersRepository.findAll();
    }

    @Override
    public void add(TeacherAddingForm teacherAddingForm) {
        teachersRepository.save(Teacher.builder()
                .name(teacherAddingForm.getName())
                .build());
    }

    @Override
    public void delete(Long id) {
        teachersRepository.delete(id);
    }
}
