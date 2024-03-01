package com.p4zd4n.bibliothecachudyana.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {

    public static String encodePassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
