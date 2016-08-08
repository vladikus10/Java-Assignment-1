package AS5;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless
public class ExamList {
    @PersistenceContext
    private EntityManager em;
    
    public List<Exam> getExams() {
        return em
            .createQuery("SELECT e FROM Exam e", Exam.class)
            .getResultList();
    }
    
    public List<Student> getStudents(int id){
        //return exams.get(index).getStudents();
        return em
            .createQuery("SELECT e FROM Exam e WHERE e.id = :id", Exam.class)
            .setParameter("id", id)
            .getSingleResult()
            .getStudents();
    }
    
    public Exam getOpennedExam(int id){
        try{
            return em
                .createQuery("SELECT e FROM Exam e WHERE e.id = :id", Exam.class)
                .setParameter("id", id)
                .getSingleResult();
        }
        catch(IllegalStateException e){
            System.out.println(e);
            return null;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e);
            return null;
        }
    }
    
    public synchronized void addExam(Exam e) {
        em.persist(e);
    }

    public synchronized void removeExam(Exam e) {
        em.remove(em.merge(e));
    }
    
    public synchronized void addStudent(Student s, int id) {
        boolean contains = false;
        Exam e = em
                .createQuery("SELECT e FROM Exam e WHERE e.id = :id", Exam.class)
                .setParameter("id", id)
                .getSingleResult();
        List<Student> studentList = e.getStudents();
        for(int i = 0 ; i < studentList.size() ; i++){
            if(studentList.get(i).getStudentId().equals(s.getStudentId())){
                contains = true;
                break;
            }
        }
        if(contains == false){
            s.setExam(e);
            e.getStudents().add(s);
            em.persist(s);
        }
    }

    public synchronized void removeStudent(String studentId, int id) {
        Exam e = em
                .createQuery("SELECT e FROM Exam e WHERE e.id = :id", Exam.class)
                .setParameter("id", id)
                .getSingleResult();
        List<Student> studentList = e.getStudents();
        for(int i = 0 ; i < studentList.size() ; i++){
            if(studentList.get(i).getStudentId().equals(studentId)){
                em.remove(em.merge(studentList.get(i)));
                studentList.remove(i); 
                break;
            }
        }
    }
}
