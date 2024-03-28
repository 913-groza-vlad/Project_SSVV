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

    public void test1() {
        int result = service.saveStudent("13", "NicVlad", 933);
        assertEquals(1, result);
        service.deleteStudent("13");
    }

    public void test2() {
        assertThrows(ValidationException.class, () -> this.service.saveStudent(null, "Vlad", 933));
    }

    public void test3() {
        assertThrows(ValidationException.class, () -> this.service.saveStudent("", "Vlad", 933));
    }

    public void test4() {
        int result = service.saveStudent("2", "NicVlad", 935);
        assertEquals(1, result);
        service.deleteStudent("2");
    }

    public void test5() {
        assertThrows(ValidationException.class, () -> this.service.saveStudent("16", null, 933));
    }

    public void test6() {
        assertThrows(ValidationException.class, () -> this.service.saveStudent("15", "", 933));
    }

    public void test7() {
        int result = service.saveStudent("3", "NicVlad", 933);
        assertEquals(1, result);
        service.deleteStudent("3");
    }

    public void test8() {
        int result = this.service.saveStudent("14", "NicVlad", Integer.MAX_VALUE);
        assertEquals(1, result);
        this.service.deleteStudent("14");
    }


    public void test9() {
        assertThrows(ValidationException.class, () -> this.service.saveStudent("117", "NicVlad", -933));
    }

    public void test10() {
        assertThrows(ValidationException.class, () -> this.service.saveStudent("13", "NicVlad", Integer.MAX_VALUE + 1));
    }

    public void test11() {
        int result =  this.service.saveStudent("16", "Vlad", 0);
        assertEquals(1, result);
        this.service.deleteStudent("16");
    }


    public void test12() {
        int result = this.service.saveStudent("15", "NicVlad", Integer.MAX_VALUE - 1);
        assertEquals(1, result);
        this.service.deleteStudent("15");
    }

    public void test13() {
        int result = this.service.saveStudent("13", "NicVlad", 935);
        assertEquals(1, result);
        this.service.deleteStudent("13");
    }

    public void test14() {
        assertThrows(ValidationException.class, () -> this.service.saveStudent("", "", -933));
    }

    public void test15() {
        int result = service.saveStudent("13", "NicVlad", 935);
        assertEquals(1, result);
        result = service.saveStudent("13", "NicVlad", 935);
        assertEquals(0, result);
        service.deleteStudent("13");
    }
}
