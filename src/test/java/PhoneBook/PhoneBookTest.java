package PhoneBook;

import org.junit.jupiter.api.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PhoneBookTest {
    private static PhoneBook phoneBook = new PhoneBook();
    private static Contact contact1 = new Contact("Юрий", "+79504340553");
    private static Contact contact2 = new Contact("Юрий2", "+795043405532");
    private static String[] groupName = {"Все контакты", "Группа1", "Группа2"};
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
    void addContact() {
        phoneBook.addContact("Все контакты", contact1);
        phoneBook.addContact("Все контакты", contact2);
        List<Contact> phoneBookGroupList = phoneBook.findContactByGroup("Все контакты");
        assertEquals(2, phoneBookGroupList.size());
        //===hamcrest
        assertThat(phoneBookGroupList.size(), equalTo(2));
    }

    @Test
    void addGroup() {
        phoneBook.addGroup("Все контакты");
        for (int i = 0; i < groupName.length; i++) {
            assertFalse(phoneBook.addGroup(groupName[i]));
            //===hamcrest
            assertThat(false, equalTo(phoneBook.addGroup(groupName[i])));

        }
    }

    @Test
    void getGroupList() {
        phoneBook.addGroup("Группа1");
        phoneBook.addGroup("Группа2");
        int counter = groupName.length - 1;
        for (String groupNameFromBook : phoneBook.getGroupList()) {
            assertEquals(groupName[counter], groupNameFromBook);
            counter--;
        }
        int counter2 = groupName.length - 1;
        //===hamcrest
        Set<String> groupListFromBook = phoneBook.getGroupList();
        phoneBook.addContact("Все контакты", contact1);
        assertThat(groupListFromBook, hasItems(groupName));
    }


    @Test
    void findContactByNumber() {
        phoneBook.addContact("Все контакты", new Contact("Юрий", "+79504340553"));
        assertEquals(contact1, phoneBook.findContactByNumber("+79504340553"));
        //===hamcrest
        assertThat(phoneBook.findContactByNumber("+79504340553"), equalTo(contact1));
    }

    @Test
    void findGroupByNumber() {
        List<String> groupList = List.of("Все контакты", "Группа1");
        phoneBook.addContact("Все контакты", contact1);
        phoneBook.addContact("Группа1", contact1);
        List<String> groupListFromBook = phoneBook.findGroupByNumber("+79504340553");
        assertEquals(groupList, groupListFromBook);
        //===hamcrest
        assertThat(groupList, equalTo(groupListFromBook));


    }

    @Test
    void findContactByGroup() {
        phoneBook.addContact("Все контакты", contact1);
        phoneBook.addContact("Группа1", contact1);
        assertEquals(contact1, phoneBook.findContactByGroup("Группа1").get(0));
        //===hamcrest
        assertThat(phoneBook.findContactByGroup("Группа1").get(0), equalTo(contact1));
    }

}
