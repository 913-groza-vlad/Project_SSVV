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
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti_test.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void test1() {
        Iterable<Student> students = service.findAllStudents();
        int size = 0;
        for (Student student : students) {
            size++;
        }

        int result = service.saveStudent("3", "Andrei", 935);

        assertEquals(0, result);
        // assertEquals(size + 1, service.findAllStudents().spliterator().getExactSizeIfKnown());
    }

    public void test2() {
        int result = service.saveStudent("4", "Dan", 935);
        assertEquals(1, result);
    }
}
