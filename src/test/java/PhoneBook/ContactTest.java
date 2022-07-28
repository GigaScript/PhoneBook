package PhoneBook;

import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Objects;


class ContactTest {
    private final Contact contact1 = new Contact("Юрий", "+79504340553");
    private final Contact contact2 = new Contact("Юрий", "+79504340553");
    private final Contact contact3 = new Contact("Юрий2", "+795043405532");
    private static long suiteStartTime;
    private long testStartTime;

    @BeforeAll
    public static void initSuite() {
        System.out.println("Running StringTest");
        suiteStartTime = System.nanoTime();
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("StringTest complete: " + (System.nanoTime() - suiteStartTime));
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Starting new test");
        testStartTime = System.nanoTime();
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("Test complete:" + (System.nanoTime() - testStartTime));
    }

    @Test
    void getName() {
        String actual = contact1.getName();
        String expected = "Юрий";
        assertEquals(expected, actual);
        //===hamcrest
        assertThat(actual, equalTo(expected));
    }

    @Test
    void getPhone() {
        String actual = contact1.getPhone();
        String expected = "+79504340553";
        assertEquals(expected, actual);
        //===hamcrest
        assertThat(actual, equalTo(expected));
    }

    @Test
    void testHashCode() {
        int actual = contact1.hashCode();
        int expected = Objects.hash("Юрий", "+79504340553");
        assertEquals(expected, actual);
        //===hamcrest
        assertThat(actual, equalTo(expected));
    }

    @Test
    void testToString() {
        String actual = contact1.toString();
        String expected = "Имя: Юрий Номер: +79504340553";
        assertEquals(expected, actual);
        //===hamcrest
        assertThat(actual, equalTo(expected));
    }

    @Test
    void testEquals() {
        assertEquals(contact1, contact2);
        //===hamcrest
        assertThat(contact1, equalTo(contact2));
    }
    @Test
    void  testHasPhoneField() {
        assertThat(contact1, hasProperty("phone"));
    }
    @Test
    void testIsCorrectPhoneValueForContact1() {
        assertThat(contact1, hasProperty("phone", equalTo("+79504340553")));
    }
    @Test
    void testNotNullPhoneField() {
        assertThat(contact1.getPhone(), notNullValue());
    }
    @Test
    void testIsContactClass() {
        assertThat(contact1, instanceOf(Contact.class));
    }
    @Test
    void testNotEqual () {
        assertThat(contact1, is(not(equalTo(contact3))));
    }

}