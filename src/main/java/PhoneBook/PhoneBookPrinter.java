package PhoneBook;

import java.util.List;

public class PhoneBookPrinter {
    public static void groupListPrint(PhoneBook phoneBook) {
        if (phoneBook.getGroupList().size() > 0) {
            System.out.println("\nСписок имеющихся групп:");
            for (String groupName : phoneBook.getGroupList()) {
                System.out.println(groupName);
            }
        } else {
            System.out.println("\nСписок групп пуст!!!");
        }
    }

    public static void contactListPrinter(PhoneBook phoneBook, String group) {
        List<Contact> contactList = phoneBook.findContactByGroup(group);
        System.out.println("\u001B[33m" + " Группа: " + group + ", контактов в группе: " + contactList.size() + "\u001B[0m");
        for (Contact contact : contactList) {
            System.out.println("Имя: " + contact.getName() + " Номер: " + contact.getPhone());
        }
    }

    public static void contactPrintByNumber(PhoneBook phoneBook, Contact contact) {
        System.out.println("Номер найден\n" + contact.toString());
        System.out.println("Добавлен в группу(ы): " + phoneBook.findGroupByNumber(contact.getPhone().toString()));
    }

    public void printAllContactInAllGroup(PhoneBook phoneBook) {
        System.out.println("\nСписок всех групп и контактов:");
        for (String groupName : phoneBook.getGroupList()) {
            contactListPrinter(phoneBook, groupName);
        }
    }
}
