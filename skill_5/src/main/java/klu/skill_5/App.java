package klu.skill_5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Appconfig.class);

        Student student = context.getBean(Student.class);

        student.display();

        context.close();  
    }
}