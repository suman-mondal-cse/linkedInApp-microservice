package com.suman.linkedin.user_service.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

//    Hash a password for the first time.
    public static String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

//    Check that a plain text password matches a previously hashed one.
    public static boolean checkPassword(String plainTextPassword, String hashPassword){
        return BCrypt.checkpw(plainTextPassword, hashPassword);
    }
}
