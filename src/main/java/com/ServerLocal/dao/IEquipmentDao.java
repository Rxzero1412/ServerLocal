package com.ServerLocal.dao;

import com.ServerLocal.model.equipment;

import java.util.List;

public interface IEquipmentDao {
    public List<equipment> selectequipment();
    public boolean delequipment(String id);
    public boolean addequipment(equipment e);
    public boolean updateequipment(equipment e);

}
