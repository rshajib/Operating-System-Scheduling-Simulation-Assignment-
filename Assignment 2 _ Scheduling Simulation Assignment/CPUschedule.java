/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;

/**
 *
 * @author Admin
 */
abstract public class CPUschedule {
    protected process[] p;
    protected ArrayDeque<process> readyqueue = new ArrayDeque<>();
    protected int systemtime;
    protected int processtrack;
    protected float avgwaitingtime;
    protected float avgresponsetime;
    protected float avgturnaroundtime;
    protected float maxruntime;
    protected int idletime;
    protected float avgcpuusage;
    
    public void checkprocessarrival()
    {
        while(processtrack < p.length && p[processtrack].getArrivaltime() == systemtime)
        {
            //System.out.println(p[processtrack].getProcessid() + "arrived at " + systemtime);
            readyqueue.add(p[processtrack]);
            /*for(process j : readyqueue)
            {
                System.out.print(j.getProcessid() + " ");
            }*/
            processtrack++;
        }
    }
}
