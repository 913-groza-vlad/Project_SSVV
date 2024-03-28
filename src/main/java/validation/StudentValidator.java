package validation;
import domain.Student;

public class StudentValidator implements Validator<Student> {
    public void validate(Student student) throws ValidationException {
        if (student.getID() == null || student.getID().isEmpty()) {
            throw new ValidationException("ID invalid! \n");
        }
        if (student.getNume() == null || student.getNume().isEmpty()) {
            throw new ValidationException("Nume invalid! \n");
        }
        if (student.getGrupa() < 0) {
            throw new ValidationException("Grupa invalida! \n");
        }
    }
}

