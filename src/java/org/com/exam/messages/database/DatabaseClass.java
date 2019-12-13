
package org.com.exam.messages.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.com.exam.messages.model.Student;
import org.com.exam.messages.util.NewHibernateUtil;

public class DatabaseClass {
    
//    private static Map<Integer, Simple> simple = new HashMap<>();
//
//    public static Map<Integer, Simple> getSimple() {
//        for(Simple s: doQuery()){
//            simple.put(s.getId(), s);
//        }
//        return simple;
//    }
    
    public static int doSave(Student stu){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        int n = (Integer) session.save(stu);
        session.getTransaction().commit();
        return n;
    }
    
    
    public static void doUpdate(Student stu){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(stu);
        session.getTransaction().commit();
    }
    
    public static void doDelete(Student stu){
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(stu);
        session.getTransaction().commit();
    }
    public static List<Student> doQuery(){
        List<Student> list = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        list = session.createQuery("from Student").list();
        return list;
    }
    
}
