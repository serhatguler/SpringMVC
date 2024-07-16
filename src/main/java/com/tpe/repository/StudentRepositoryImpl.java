package com.tpe.repository;

import com.tpe.domain.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements StudentRepository{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void save(Student student) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(student);//DB de kayit varsa guncelleme yapar. yoksa kaydeder.

        tx.commit();
        session.close();

    }

    @Override
    public List<Student> findAll() {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        List<Student> studentList= session.createQuery("FROM Student",Student.class).getResultList();

        tx.commit();
        session.close();

        return studentList;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

       Student student = session.load(Student.class,id);
       session.delete(student);

        tx.commit();
        session.close();

    }

    @Override
    public Optional<Student> findById(Long id) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Student student =session.get(Student.class,id);//Olmayan id de student =null olur->NullPointerException
        Optional<Student> optional = Optional.ofNullable(student);//student null ise bos bir optional objsei doner

        tx.commit();
        session.close();



        return optional;
    }
}
