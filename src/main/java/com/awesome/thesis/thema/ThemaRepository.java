package com.awesome.thesis.thema;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ThemaRepository {
    private final List<Thema> themen = new ArrayList<>();

    public void addThema(Thema thema) {
        themen.add(thema);
    }

    public void removeThema(Thema thema) {
        themen.remove(thema);
    }

    public List<Thema> getThemen() {
        return themen;
    }

}
