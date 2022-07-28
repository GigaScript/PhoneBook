package PhoneBook;

import java.util.Scanner;

public class Main {
    public static PhoneBook phoneBook = new PhoneBook();
    public static PhoneBookPrinter phoneBookPrinter = new PhoneBookPrinter();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        phoneBook.addContact("Все контакты", new Contact("Юрий", "+79504340553"));
        phoneBook.addContact("Все контакты", new Contact("Вася", "89504340553"));
        phoneBook.addContact("Коллеги", new Contact("Вася", "89504340553"));
        phoneBook.addContact("Все контакты", new Contact("Юрий", "+79504340553"));

        while (true) {
            System.out.println("\n1. Создание группы контактов" +
                    "\n2. Создание контакта" +
                    "\n3. Добавление контакта в разные группы" +
                    "\n4. Поиск контактов по группе" +
                    "\n5. Поиск контакта по номеру" +
                    "\n6. Список контактов по группам" +
                    "\n0. Выход");
            String input = scanner.nextLine();
            try {
                switch (input) {
                    case "0":
                        System.out.println("Работа программы завершена");
                        return;
                    case "1":
                        phoneBookPrinter.groupListPrint(phoneBook);
                        System.out.print("\nВведите имя новой группы >>");
                        String group = scanner.nextLine();
                        phoneBook.addGroup(group);
                        break;
                    case "2":
                        phoneBookPrinter.contactListPrinter(phoneBook, "Все контакты");
                        System.out.print("\nВведите имя и телефон через пробел >> ");
                        String[] namePhoneArr = scanner.nextLine().split(" ");
                        if (namePhoneArr.length == 2) {
                            phoneBook.addContact("Все контакты", new Contact(namePhoneArr[0], namePhoneArr[1]));
                        } else {
                            System.out.println("Введено не корректное число параметров");
                        }
                        break;
                    case "3":
                        phoneBookPrinter.contactListPrinter(phoneBook, "Все контакты");
                        System.out.print("\nВведите телефон контакта >> ");
                        Contact contact = phoneBook.findContactByNumber(scanner.nextLine());
                        if (contact == null) {
                            System.out.println("Номер не найден\nКонтакт не добавлен в группу");
                            break;
                        }
                        phoneBookPrinter.groupListPrint(phoneBook);
                        System.out.print("\nВведите названия группы >> ");
                        String groupNameToAddContact = scanner.nextLine();
                        if (phoneBook.findContactByGroup(groupNameToAddContact) == null) {
                            System.out.println("Группы не существует. Контакт не добавлен в группу");
                            break;
                        }
                        phoneBook.addContact(groupNameToAddContact, contact);
                        break;
                    case "4":
                        phoneBookPrinter.groupListPrint(phoneBook);
                        System.out.print("\nВведите имя группы, для поиска контактов >> ");
                        phoneBookPrinter.contactListPrinter(phoneBook,scanner.nextLine());
                        break;
                    case "5":
                        System.out.print("\nВведите номер телефона, для поиска >> ");
                        Contact numberForFind = phoneBook.findContactByNumber(scanner.nextLine());
                        if (numberForFind == null) {
                            System.out.println("Номер не найден\nКонтакт не добавлен в группу");
                            break;
                        }
                        phoneBookPrinter.contactPrintByNumber(phoneBook,numberForFind);
                        break;
                    case "6":
                        phoneBookPrinter.printAllContactInAllGroup(phoneBook);
                        break;
                    default:
                        System.out.println("\u001B[31m" + "Не верный пункт меню." + "\u001B[0m");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Ошибка: не существующий номер");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: нужно ввести цифры а не текст");
            }
        }
    }
}