// File: src/model/Member.java
package model;

public class Member {
    private String uid;
    private String email;
    private String password;
    private String birthDate;
    private String role; // "user" or "admin"

    // 建構子（預設為一般使用者）
    public Member(String uid, String email, String password, String birthDate) {
        this(uid, email, password, birthDate, "user");
    }

    // 建構子（可指定角色）
    public Member(String uid, String email, String password, String birthDate, String role) {
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.role = role;
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getRole() {
        return role;
    }

    public boolean isAdmin() {
        return "admin".equalsIgnoreCase(role);
    }
}
