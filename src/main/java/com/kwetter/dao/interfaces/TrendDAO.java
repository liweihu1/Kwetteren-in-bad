package com.kwetter.dao.interfaces;

import com.kwetter.domain.Trend;

import java.util.UUID;

public interface TrendDAO {
    Trend add(Trend trend);
    void delete(Trend trend);
    Trend update(Trend trend);
    Trend findById(UUID id);
    void clearData();
}
