package com.ServerLocal.controller;

import com.ServerLocal.model.equipment;

import java.util.List;

public class statics {
    public static List<equipment> cardreadcom;

    public static List<equipment> getCardreadcom() {
        return cardreadcom;
    }

    public static void setCardreadcom(List<equipment> cardreadcom) {
        statics.cardreadcom = cardreadcom;
    }
}
