package old.safenar.util;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JSONParserTest {

    static class Person {
        String name;
        int age;
        String address;
        String phone;
        String email;

        Person(String name, int age, String address, String phone, String email) {
            this.name = name;
            this.age = age;
            this.address = address;
            this.phone = phone;
            this.email = email;
        }

        Person() {}

        @Override
        public String toString() {
            return "Person ["
                    + "name=" + name +
                    ", age=" + age +
                    ", address=" + address +
                    ", phone=" + phone +
                    ", email=" + email +
                    "]";
        }

    }
    @Test
    void testParse() {
        String json = """
                {
                    "name": "John",
                    "age": 30,
                    "address": "New York",
                    "phone": "123-456-7890",
                    "email": "john@example.com"
                }
                """;
        JSONParser<Person> parser = new JSONParser<>(json, Person.class).parse();
        Person person = parser.getAsJsonObject();
        assertEquals("John", person.name);
        assertEquals(30, person.age);
        assertEquals("New York", person.address);
        assertEquals("123-456-7890", person.phone);
        assertEquals("john@example.com", person.email);
    }

}