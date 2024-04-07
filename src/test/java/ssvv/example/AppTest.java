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
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme_test.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3, studentValidator, temaValidator);

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

    public void testAddAssignment1() {
        int result = service.saveTema("1", "Lab3", 4, 2);
        assertEquals(1, result);

        service.deleteTema("1");
    }

    public void testAddAssignment2() {
        assertThrows(ValidationException.class, () -> this.service.saveTema("2", "Lab4", 5, 7));
    }

    public void testAddAssignment3() {
        try {
            service.saveTema("", "Lab3", 4, 2);
            assert false;
        }
        catch (ValidationException ve) {
            assertEquals("ID invalid! \n", ve.getMessage());
            assert true;
        }
    }

    public void testAddAssignment4() {
        try {
            service.saveTema(null, "Lab3", 4, 2);
            assert false;
        }
        catch (ValidationException ve) {
            assertEquals("ID invalid! \n", ve.getMessage());
            assert true;
        }
    }

    public void testAddAssignment5() {
        try {
            service.saveTema("4", "", 4, 2);
            assert false;
        }
        catch (ValidationException ve) {
            assertEquals("Descriere invalida! \n", ve.getMessage());
            assert true;
        }
    }

    public void testAddAssignment6() {
        try {
            service.saveTema("4", null, 4, 2);
            assert false;
        }
        catch (ValidationException ve) {
            assertEquals("Descriere invalida! \n", ve.getMessage());
            assert true;
        }
    }

    public void testAddAssignment7() {
        try {
            service.saveTema("4", "Lab3", 0, 2);
            assert false;
        }
        catch (ValidationException ve) {
            assertEquals("Deadline invalid! \n", ve.getMessage());
            assert true;
        }
    }

    public void testAddAssignment8() {
        try {
            service.saveTema("4", "Lab3", 15, 2);
            assert false;
        }
        catch (ValidationException ve) {
            assertEquals("Deadline invalid! \n", ve.getMessage());
            assert true;
        }
    }

    public void testAddAssignment9() {
        try {
            service.saveTema("4", "Lab3", 4, 0);
            assert false;
        }
        catch (ValidationException ve) {
            assertEquals("Data de primire invalida! \n", ve.getMessage());
            assert true;
        }
    }

    public void testAddAssignment10() {
        assertThrows(ValidationException.class, () -> this.service.saveTema("4", "Lab3", 5, 15));
    }


    public void testAddAssignment11() {
        int result = service.saveTema("10", "Lab3", 4, 2);
        assertEquals(1, result);
        result = service.saveTema("10", "Lab3", 10, 7);
        assertEquals(0, result);
        service.deleteTema("10");
    }
}
