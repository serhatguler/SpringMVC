package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller //@RestController
@RequestMapping("/students") //http://localhost:8080/SpringMvc2/students
//class level:tum methodlar icin gecerli
//method level: sadece o method icin gecerli
public class StudentController {

    @Autowired
    private StudentService service;

    //controller requeste gore model and view(data+view name) ya da String olarak sadece view name doner
    @GetMapping("/hi")//http://localhost:8080/SpringMvc2/students/hi
    public ModelAndView sayHi(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("message","hi");
        mav.addObject("messageBody","I am a student management system");
        mav.setViewName("hi");//hi.jsp
        return mav;
    }

    //view resolver mav icindeki datalari view name verilen dosyayi bulup icine bind eder.

    //1-Student creation
    //http://localhost:8080/SpringMvc2/students/new
    //kullanicidan bilgileri almak icin form gosterelim
    @GetMapping("/new")
    public String sendStudentForm(@Valid @ModelAttribute("student")Student student){
        return "studentForm";
    }
    //http://localhost:8080/SpringMvc2/students/new
    //@ModelAttribute view katmani ile controller arasindaki data teansferini saglar.



    //student DB ye kaydedince all studentlari listeleyelim.
    //http://localhost:8080/SpringMvc2/students/new/saveStudent+POST
//    @PostMapping("/saveStudent")
//    public String saveStudent(@ModelAttribute Student student){
//        //service de student i kaydet
//        service.saveStudent(student);
//        return "redirect:/students"; ////http://localhost:8080/SpringMvc2/students bu istege yonlendir
//    }


    @PostMapping("/saveStudent")
    public String sendStudentForm(@Valid @ModelAttribute("student")Student student, BindingResult bindingResult){

        if (bindingResult.hasErrors()){

            return "studentForm";

        }

        //service de student i kaydet
        service.saveStudent(student);
        return "redirect:/students"; ////http://localhost:8080/SpringMvc2/students bu istege yonlendir
    }





    //http://localhost:8080/SpringMvc2/students/+GET
    //2-list all student
    @GetMapping
    public ModelAndView listAllStudents(){
        List<Student> studentList= service.getAllStudent();
        ModelAndView mav = new ModelAndView();
        mav.addObject("students",studentList);
        mav.setViewName("students");
        return mav;
    }



    //3-student update
    //http://localhost:8080/SpringMvc2/students/update?id=1
    //Query param da (?id=1) update etmek icin @RequestParam(key degeri yani id=1) yazilir.
    //1st way:
//    @GetMapping("/update")
//    public String showUpdateForm(@RequestParam("id")Long id, Model model){
//        Student foundStudent = service.getStudentById(id);
//        model.addAttribute("student",foundStudent);
//        return "studentForm";
//    }


    //2nd way
    @GetMapping("/update")
    public ModelAndView showUpdateForm(@RequestParam("id")Long id){
        Student foundStudent = service.getStudentById(id);
        ModelAndView mav = new ModelAndView();
        mav.addObject("student",foundStudent);
        mav.setViewName("studentForm");
        return mav;
    }





    //4-delete student
    //http://localhost:8080/SpringMvc2/students/delete/4
    //silme isleminden sonra tum kayitlari listeleyelim
    @GetMapping("/delete/{id}") //buradaki id @PathVariable("id") ye yazilir.
    public String deleteStudents(@PathVariable("id")Long id){
        service.deleteStudent(id);
        return "redirect:/students";
    }

    //@ExceptionHandler belirtilen exception sinifi icin bir metod belirlememizi saglar
    // bu metodla yakalanan exception icin ozel bir islem icerir.(notFound u icinde mesaj ile gosterme)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleException(Exception ex){
        ModelAndView mav= new ModelAndView();
        mav.addObject("message",ex.getMessage());
        mav.setViewName("notFound");
        return mav;
    }
















}
