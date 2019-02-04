package com.kwetter.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Tweet {
    private UUID id;
    private String message;
    private Date dateCreated;
    private List<User> mentions;
    private List<User> heartedBy;
    private List<Trend> trends;
}
