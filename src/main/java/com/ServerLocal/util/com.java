package com.ServerLocal.util;

import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by 83541 on 2018/12/8.
 */
public class com extends Thread implements SerialPortEventListener {
    // 监听器,我的理解是独立开辟一个线程监听串口数据
    static CommPortIdentifier portIds; // 串口通信管理类
    static Enumeration<?> portList; // 有效连接上的端口的枚举
    static InputStream inputStream[]; // 从串口来的输入流
    static OutputStream outputStream[];// 向串口输出的流
    static SerialPort serialPort[]; // 串口的引用
    public static boolean runboolean=true;

    // 堵塞队列用来存放读到的数据
    private BlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>();
    static public List<String> list = new ArrayList<String>();
    private String s;
    private int charcount=0;
    private char[] rebuf = new char[34];
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
        new CommPort() {
            @Override
            public void enableReceiveFraming(int i) throws UnsupportedCommOperationException {

            }

            @Override
            public void disableReceiveFraming() {

            }

            @Override
            public boolean isReceiveFramingEnabled() {
                return false;
            }

            @Override
            public int getReceiveFramingByte() {
                return 0;
            }

            @Override
            public void disableReceiveTimeout() {

            }

            @Override
            public void enableReceiveTimeout(int i) throws UnsupportedCommOperationException {

            }

            @Override
            public boolean isReceiveTimeoutEnabled() {
                return false;
            }

            @Override
            public int getReceiveTimeout() {
                return 0;
            }

            @Override
            public void enableReceiveThreshold(int i) throws UnsupportedCommOperationException {

            }

            @Override
            public void disableReceiveThreshold() {

            }

            @Override
            public int getReceiveThreshold() {
                return 0;
            }

            @Override
            public boolean isReceiveThresholdEnabled() {
                return false;
            }

            @Override
            public void setInputBufferSize(int i) {

            }

            @Override
            public int getInputBufferSize() {
                return 0;
            }

            @Override
            public void setOutputBufferSize(int i) {

            }

            @Override
            public int getOutputBufferSize() {
                return 0;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public OutputStream getOutputStream() throws IOException {
                return null;
            }
        }.close();
        return relist;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        switch (serialPortEvent.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            case SerialPortEvent.DATA_AVAILABLE:// 当有可用数据时读取数据
                byte[] readBuffer = new byte[100];
                try {
                    for (int j=0;j<inputStream.length;j++){
                        while (inputStream[j].available() > 0) {
                            inputStream[j].read(readBuffer);
                            s = new String(readBuffer);
                            char[] ca=s.toCharArray();
                            for (int i = 0; i < ca.length; i++) {
                                if((ca[i]>='0'&&ca[i]<='9')||(ca[i]>='A'&&ca[i]<='Z')){
                                    if(ca[i]=='U'){
                                        if (charcount>1) {
                                            String ss=new String(rebuf);
                                            msgQueue.add(ss.substring(22, ss.length()));
                                        }
                                        charcount=0;
                                    }
                                    rebuf[charcount]=ca[i];
                                    charcount++;
                                }
                            }
                            readBuffer = new byte[100];
                        }
                    }
                } catch (IOException e) {
                }
                break;
        }
    }
    /**
     *
     * 通过程序打开串口，设置监听器以及相关的参数
     *
     * @return 返回1 表示端口打开成功，返回 0表示端口打开失败
     */
    public int startComPort(List<String> listcom) {
        // 通过串口通信管理类获得当前连接上的串口列表
        portList = CommPortIdentifier.getPortIdentifiers();
        int re=0;
        while (portList.hasMoreElements()) {

            // 获取相应串口对象
            portIds = (CommPortIdentifier) portList.nextElement();
            System.out.println("设备类型：--->" + portIds.getPortType());
            System.out.println("设备名称：---->" + portIds.getName());
            // 判断端口类型是否为串口
            if (portIds.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                System.out.println("判断端口类型是否为串口");
                for (int i=0;i<listcom.size();i++){
                    System.out.println(listcom.get(i));
                    if (portIds.getName().equals(listcom.get(i))) {
                        try {
                            System.out.println("serialPort:"+listcom.get(i));
                            serialPort[i] = (SerialPort) portIds.open(listcom.get(i)+"s", 2000);
                        } catch (PortInUseException e) {
                            e.printStackTrace();
                            return 0;
                        }
                        // 设置当前串口的输入输出流
                        try {
                            inputStream[i] = serialPort[i].getInputStream();
                            outputStream[i] = serialPort[i].getOutputStream();
                        } catch (IOException e) {
                            e.printStackTrace();
                            return 0;
                        }
                        // 给当前串口添加一个监听器
                        try {
                            serialPort[i].addEventListener(this);
                        } catch (TooManyListenersException e) {
                            e.printStackTrace();
                            return 0;
                        }
                        // 设置监听器生效，即：当有数据时通知
                        serialPort[i].notifyOnDataAvailable(true);

                        // 设置串口的一些读写参数
                        try {
                            // 比特率、数据位、停止位、奇偶校验位
                            serialPort[i].setSerialPortParams(38400,
                                    SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                                    SerialPort.PARITY_NONE);
                        } catch (UnsupportedCommOperationException e) {
                            e.printStackTrace();
                            return 0;
                        }
                        re=1;
                    }
                }
            }
        }
        return re;
    }
    @Override
    public void run() {
        try {
            while (runboolean) {
                // 如果堵塞队列中存在数据就将其输出
                if (msgQueue.size() > 0) {
                    String rFIDString=msgQueue.take();
                    if(list!=null){
                        if(rFIDString!=null&&!list.contains(rFIDString)){
                            list.add(rFIDString);
                            System.out.println(rFIDString);
                        }
                    }else {
                        list.add(rFIDString);
                    }

                }
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void stops() {
        for (int i=0;i<serialPort.length;i++){
            serialPort[i].close();
            serialPort[i]=null;
            runboolean=false;
        }
    }
    public static void runs(List<String> listcom) {
        inputStream= new InputStream[listcom.size()];
        outputStream = new OutputStream[listcom.size()];
        serialPort=new SerialPort[listcom.size()];
        com cRead1 = new com();
        int i = cRead1.startComPort(listcom);
        int counts=0;
        runboolean=true;
        if (i == 1) {
            // 启动线程来处理收到的数据
            cRead1.start();
            try {
                String st = "\nU\r";
                //System.out.println("发出字节数：" + st.getBytes("ASCII").length);
                while (runboolean) {
                    counts++;
                        outputStream[counts%listcom.size()].write(st.getBytes("ASCII"), 0,
                                st.getBytes("ASCII").length);
                        outputStream[counts%listcom.size()].close();
                    if(counts>=65500) counts=0;
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            return;
        }
    }
    public static void main(String arg[]){
        List<String> listcom=new com().listPorts();
        new com().runs(listcom);
    }
}
