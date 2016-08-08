package AS5;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ExamListBB {
    private final Exam exam = new Exam();
    private final Student student = new Student();
    @Inject
    private ExamList examList;
    
    public Exam getExam(){
        return exam;
    }
    
    public Student getStudent() {
        return student;
    }
    public String removeExam(Exam e) {
        examList.removeExam(e);
        return "teacher?faces-redirect=true";
    }
    
    public String addExam() {
        examList.addExam(exam);
        return "teacher?faces-redirect=true";
    }
    
    public String removeStudent(String studentId, int id) {
        examList.removeStudent(studentId, id);
        return "examStudent?faces-redirect=true&id=" + id;
    }
    
    public String addStudent(int id) {
        examList.addStudent(student, id);
        return "examStudent?faces-redirect=true&id=" + id;
    }
}
