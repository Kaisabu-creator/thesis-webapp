package com.awesome.thesis.profiles.profil;

public class Profil {
    private Long id;
    private String name;

    public Profil(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
