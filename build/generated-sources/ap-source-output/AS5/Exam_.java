package AS5;

import AS5.Student;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-11-25T16:32:09")
@StaticMetamodel(Exam.class)
public class Exam_ { 

    public static volatile SingularAttribute<Exam, String> courseName;
    public static volatile SingularAttribute<Exam, String> examDate;
    public static volatile SingularAttribute<Exam, String> courseCode;
    public static volatile SingularAttribute<Exam, String> examName;
    public static volatile ListAttribute<Exam, Student> studentList;
    public static volatile SingularAttribute<Exam, Long> id;
    public static volatile SingularAttribute<Exam, String> examPlace;
    public static volatile SingularAttribute<Exam, String> examTime;

}