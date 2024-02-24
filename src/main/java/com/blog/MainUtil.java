//package com.blog;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class MainUtil {
//    public static void main(String [] args){
////        Employee emp1=new Employee();
////        emp1.setCity("bbsr");
////        emp1.setName("Abhi");
////        emp1.setSalery(45000);
////        Employee emp2=new Employee();
////        emp2.setCity("kjr");
////        emp2.setName("rakesh");
////        emp2.setSalery(5000);
////        Employee emp3=new Employee();
////        emp3.setCity("shiv");
////        emp3.setName("mbj");
////        emp3.setSalery(15000);
////        Employee emp4=new Employee();
////        emp4.setCity("bbsr");
////        emp4.setName("sakti");
////        emp4.setSalery(25000);
////       List<Employee>data=Arrays.asList(emp1,emp2,emp3,emp4);
//////        List<Employee>newdata=data.stream().filter(e->e.getSalery()==45000).collect(Collectors.toList());
//////        System.out.println(newdata);
//////        for(Employee employee:newdata){
//////            System.out.println(employee.getName());
//////            System.out.println(employee.getCity());
//////            System.out.println(employee.getSalery());
//////        }
////        List<Employee>newdata=data.stream().filter(e->e.getCity().equalsIgnoreCase("bbsr")).collect(Collectors.toList());
////        for(Employee employee:newdata){
////            System.out.println(employee.getName());
////            System.out.println(employee.getCity());
////            System.out.println(employee.getSalery());
////        }
//
//        PasswordEncoder PasswordEncoder=new BCryptPasswordEncoder();
//        System.out.println(PasswordEncoder.encode("testting"));
//
//
//    }
//}
