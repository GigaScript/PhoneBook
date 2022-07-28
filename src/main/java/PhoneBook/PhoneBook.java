package PhoneBook;

import java.util.*;

public class PhoneBook {
    private Map<String, List<Contact>> phoneBook = new HashMap<>();

    public void addContact(String group, Contact contact) {
        addGroup(group);
        List list = phoneBook.get(group);
        if (!list.contains(contact)) {
            list.add(contact);
        }
    }

    public Set<String> getGroupList() {
        return phoneBook.keySet();
    }

    public boolean addGroup(String group) {
        if (!phoneBook.containsKey(group)) {
            List<Contact> list = new ArrayList<Contact>();
            phoneBook.put(group, list);
            return true;
        }
        return false;
    }

    public Contact findContactByNumber(String number) {
        for (Map.Entry<String, List<Contact>> maps : phoneBook.entrySet()) {
            for (Contact contact : maps.getValue()) {
                if (contact.getPhone().equals(number)) {
                    return contact;
                }
            }
        }
        return null;
    }

    public List<String> findGroupByNumber(String number) {
        List list = new ArrayList();
        for (Map.Entry<String, List<Contact>> maps : phoneBook.entrySet()) {
            for (Contact contact : maps.getValue()) {
                if (contact.getPhone().equals(number)) {
                    list.add(maps.getKey());
                }
            }
        }
        return list;
    }

    public List<Contact> findContactByGroup(String group) {
        if (phoneBook.containsKey(group)) {
            return phoneBook.get(group);
        }
        return null;
    }
}
