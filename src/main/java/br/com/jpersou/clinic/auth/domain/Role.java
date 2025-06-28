package br.com.jpersou.clinic.auth.domain;

public enum Role {
    ADMIN("ROLE_ADMIN"),
    DOCTOR("ROLE_MEDICO"),
    NURSE("ROLE_NURSE");

    private final String role;

    Role(String role) {
        this.role = role;
    }
}
