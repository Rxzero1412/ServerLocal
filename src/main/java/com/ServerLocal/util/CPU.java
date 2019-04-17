package com.ServerLocal.util;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

/**
 * Created by 83541 on 2019/4/17.
 */
public class CPU extends Thread {
    public static double cpus=0.00;
    public static boolean cpusboolean=false;
    @Override
    public void run() {
        while (cpusboolean) {
            try {
                cpus=getcpu();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (SigarException e) {
                e.printStackTrace();
            }
        }
    }
    public double getcpu() throws SigarException {
        Sigar sigar = new Sigar();
        CpuPerc cpu = sigar.getCpuPerc();
        return cpu.getUser();
    }
}
