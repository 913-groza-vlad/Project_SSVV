package ssvv.example;

import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.*;

public class GradeTest extends TestCase {
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti_test.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme_test.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note_test.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3, studentValidator, temaValidator);

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public GradeTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( GradeTest.class );
    }

    /**
     * Rigourous Test :-)
     */

    public void test1() {
        int result = service.saveStudent("1", "Vlad", 933);
        assertEquals(1, result);

        service.deleteStudent("1");
    }

    public void test2() {
        int result = service.saveTema("1", "Lab3", 4, 2);
        assertEquals(1, result);

        service.deleteTema("1");
    }

    public void test3() {
        int result = service.saveNota("1", "1", 10, 4, "Good job!");
        assertEquals(-1, result);
    }

    public void test4() {
        int resultStudent = service.saveStudent("1", "Vlad", 933);
        assertEquals(1, resultStudent);
        int resultTema = service.saveTema("1", "Lab3", 4, 2);
        assertEquals(1, resultTema);
        int resultNota = service.saveNota("1", "1", 10, 4, "Good job!");
        assertEquals(1, resultNota);

        service.deleteTema("1");
        service.deleteStudent("1");
        service.deleteNota(new Pair("1", "1"));
    }

    public void testAddStudent() {
        try {
            service.saveStudent("", "Vlad", 933);
            assert false;
        }
        catch (Exception e) {
            assertEquals(e.getMessage(), "ID invalid! \n");
            assert true;
        }
    }

    public void testAddStudentAssignment() {
        int result = service.saveStudent("5", "Dan", 933);
        assertEquals(1, result);

        try {
            service.saveTema("2", "Lab4", 2, 4);
            assert false;
        }
        catch (ValidationException ve) {
            assertEquals("Deadline invalid! \n", ve.getMessage());
            assert true;
        }

        service.deleteStudent("5");
    }

    public void testAddStudentAssignmentGrade() {
        int result = service.saveStudent("5", "Dan", 933);
        assertEquals(1, result);

        int result2 = service.saveTema("4", "Lab4", 8, 6);
        assertEquals(1, result2);

        int result3 = service.saveNota("5", "4", 10, 9, "Good job!");
        assertEquals(1, result3);
        assertEquals(7.5, service.getNota("5", "4").getNota());

        service.deleteStudent("5");
        service.deleteTema("4");
        service.deleteNota(new Pair("5", "4"));
    }
}
