// File: src/service/MemberService.java
package service;

import model.Member;
import util.AppSession;

import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class MemberService {
    private Map<String, Member> memberDatabase = new HashMap<>();

    public MemberService() {
        preloadAdminAccount();
    }

    /**
     * 預設建立一組 admin 管理員帳號
     */
    private void preloadAdminAccount() {
        String email = "admin";
        String password = "admin123";
        String encryptedPassword = encryptPassword(password);
        Member admin = new Member("0000", email, encryptedPassword, "2000-01-01", "admin");
        memberDatabase.put(email, admin);
    }

    /**
     * 註冊新會員（一般使用者）
     */
    public boolean register(String email, String password, String birthDate) {
        if (memberDatabase.containsKey(email)) {
            return false; // email 已存在
        }
        if (email.equalsIgnoreCase("admin")) {
            return false; // 禁止註冊為 admin
        }
        String encryptedPassword = encryptPassword(password);
        Member member = new Member(UUID.randomUUID().toString(), email, encryptedPassword, birthDate);
        memberDatabase.put(email, member);
        return true;
    }

    /**
     * 登入系統，成功會設為當前使用者
     */
    public Member login(String email, String password) {
        Member member = memberDatabase.get(email);
        if (member == null) return null;
        String encryptedInput = encryptPassword(password);
        if (!member.getPassword().equals(encryptedInput)) return null;
        AppSession.login(member);
        return member;
    }

    public Member getMemberByEmail(String email) {
        return memberDatabase.get(email);
    }

    public void logout() {
        AppSession.logout();
    }

    /**
     * 將密碼以 SHA-256 加密
     */
    private String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash)
                hexString.append(String.format("%02x", b));
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing failed", e);
        }
    }
}
