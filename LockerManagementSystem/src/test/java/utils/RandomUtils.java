package utils;

import org.learning.lld.models.Buyer;
import org.learning.lld.models.Contact;

import java.util.UUID;

public class RandomUtils {
    public static String randomString() {
        return UUID.randomUUID().toString();
    }

    public static String randomEmail() {
        return randomString() + "@gmail.com";
    }

    public static String randomPhone() {
        return randomString();//todo
    }

    public static Buyer randomBuyer() {
        return new Buyer(new Contact(randomEmail(), randomPhone()), randomString());
    }
}
