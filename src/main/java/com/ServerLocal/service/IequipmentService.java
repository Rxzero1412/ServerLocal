package com.ServerLocal.service;


import com.ServerLocal.model.equipment;

import java.util.List;

public interface IequipmentService {
    public List<equipment> selectequipment();
    public boolean delequipment(String id);
    public boolean addequipment(equipment e);
}
