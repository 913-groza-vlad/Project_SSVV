package ssvv.example;

import domain.Nota;
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

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AssignmentTest extends TestCase {
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme_test.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3, studentValidator, temaValidator);

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AssignmentTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AssignmentTest.class );
    }

    /**
     * Rigourous Test :-)
     */

    public void test1() {
        int result = service.saveTema("1", "Lab3", 4, 2);
        assertEquals(1, result);

        service.deleteTema("1");
    }

    public void test2() {
        assertThrows(ValidationException.class, () -> this.service.saveTema("2", "Lab4", 5, 7));
    }

}
