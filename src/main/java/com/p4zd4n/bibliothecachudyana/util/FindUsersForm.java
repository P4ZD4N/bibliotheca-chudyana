package com.p4zd4n.bibliothecachudyana.util;

import jakarta.validation.constraints.Positive;

import java.util.List;

public class FindUsersForm {

    @Positive(message = "Pole 'ID' musi być liczbą dodatnią!")
    private Integer id;

    private String username;

    private List<String> authoritiesAsString;

    private Integer enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getAuthorities() {
        return authoritiesAsString;
    }

    public void setAuthorities(List<String> authoritiesAsString) {
        this.authoritiesAsString = authoritiesAsString;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}
