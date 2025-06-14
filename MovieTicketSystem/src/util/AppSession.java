// File: src/util/AppSession.java
package util;

import model.Member;

public class AppSession {
    private static Member currentMember;

    public static void login(Member member) {
        currentMember = member;
    }

    public static void logout() {
        currentMember = null;
    }

    public static Member getCurrentMember() {
        return currentMember;
    }

    public static boolean isLoggedIn() {
        return currentMember != null;
    }

    public static boolean isAdmin() {
        return currentMember != null && currentMember.getRole().equals("admin");
    }
}
