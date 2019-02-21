package com.kwetter.dto;

import com.kwetter.domain.Trend;
import com.kwetter.domain.Kweet;

import java.util.ArrayList;
import java.util.List;

public class TrendDTO {
    private String id;
    private String name;
    private List<KweetDTO> Kweets;

    public TrendDTO(){

    }

    public TrendDTO(Trend trend){
        this.id = trend.getId().toString();
        this.name = trend.getName();
        this.Kweets = new ArrayList<>();
        for (Kweet t : trend.getKweets()){
            this.Kweets.add(new KweetDTO(t));
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<KweetDTO> getKweets() {
        return Kweets;
    }

    public void setKweets(List<KweetDTO> Kweets) {
        this.Kweets = Kweets;
    }
}
