package com.kwetter.dao.memory;

import com.kwetter.dao.interfaces.TrendDAO;
import com.kwetter.domain.Trend;

import javax.enterprise.inject.Alternative;
import java.util.UUID;

@Alternative
public class TrendDAOMEMImpl implements TrendDAO {
    @Override
    public Trend add(Trend trend) {
        return null;
    }

    @Override
    public void delete(Trend trend) {

    }

    @Override
    public Trend update(Trend trend) {
        return null;
    }

    @Override
    public Trend findById(UUID id) {
        return null;
    }

    @Override
    public void clearData() {

    }
}
