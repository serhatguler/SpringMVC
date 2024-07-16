package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository repository;

    @Override
    public void saveStudent(Student student) {
        repository.save(student);
    }

    @Override
    public List<Student> getAllStudent() {

        return repository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        Student student = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student not found "+id));
        return student;

        //get(); NoSuchElementException


    }

    @Override
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        repository.delete(student.getId());
    }
}
