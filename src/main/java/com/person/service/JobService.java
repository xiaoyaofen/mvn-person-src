package com.person.service;

import com.person.bean.Menu;
import com.person.bean.Params;
import com.person.bean.Station;

import java.util.List;
import java.util.Map;

public interface JobService {
    public Map<String, List<Params>> findJobMenu();

    public List<Station> findStation(String value);

    public Station findOneStation(String id);
}
