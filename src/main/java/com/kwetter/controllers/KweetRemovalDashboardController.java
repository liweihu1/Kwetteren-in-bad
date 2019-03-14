package com.kwetter.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.UUID;

@SessionScoped
@ManagedBean(name = "KweetRemovalDashboardController")
public class KweetRemovalDashboardController {

    @ManagedProperty(value = "#{param.id}")
    private UUID selectedUserID;
}
