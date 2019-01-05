package com.ServerLocal.util;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by 83541 on 2018/12/8.
 */
public class com extends Thread implements SerialPortEventListener {
    public List<String> listPorts(){
        List<String> relist=new ArrayList<>();
        Enumeration enumeration= CommPortIdentifier.getPortIdentifiers();
        CommPortIdentifier portId;
        while(enumeration.hasMoreElements()){
            portId=(CommPortIdentifier)enumeration.nextElement();
            if(portId.getPortType()==CommPortIdentifier.PORT_SERIAL){
                System.out.println("port name :"+portId.getName());
                relist.add(portId.getName());
            }
        }
        return relist;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {

    }
}
