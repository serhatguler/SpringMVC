package com.tpe.repository;

import com.tpe.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    void save(Student student);
    List<Student> findAll();
    void delete(Long id);
    Optional<Student> findById(Long id);//NullPointer Exception bu id ile student bulamazsak
    //optional null yerine bos bir optional objesi doner ve kullaniciya meaj gonderebiliriz




}
