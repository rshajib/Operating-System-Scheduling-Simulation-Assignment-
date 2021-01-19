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
public class FCFS extends CPUschedule{
    
    public FCFS(process[] p)
    {
        this.p = p;
    }
    
    public void scheduler()
    {               
        maxruntime = p[p.length - 1].getArrivaltime();
        
        while(readyqueue.isEmpty())
        {
            if(systemtime == p[0].getArrivaltime())
            {
                readyqueue.add(p[0]);
                processtrack++;
                break;
            }
            systemtime++;
            idletime++;
        }
        
        while(!readyqueue.isEmpty() || systemtime <= maxruntime)
        {
            if(readyqueue.isEmpty())
            {
                checkprocessarrival();
                systemtime++;
                idletime++;
                continue;
            }
            process head = readyqueue.remove();
            head.setWaitingtime(systemtime - head.getArrivaltime());
            head.setResponsetime(systemtime - head.getArrivaltime());
            for(int i=0; i<head.getBursttime(); i++ , systemtime++)
            {
                System.out.println("<system time    " + systemtime + "> process " + head.getProcessid() + " is running....");
                checkprocessarrival();
            }
            System.out.println("<system time    " + systemtime + "> process " + head.getProcessid() + " is finished");
        }
        System.out.println("<system time    " + systemtime + "> All processes finished");
        for(int i=0; i<p.length; i++)
        {
            avgwaitingtime = avgwaitingtime + p[i].getWaitingtime();
            avgturnaroundtime = avgturnaroundtime + (p[i].getWaitingtime() + p[i].getBursttime());
            p[i].setTurnaroundtime(p[i].getWaitingtime() + p[i].getBursttime());
        }
        avgresponsetime = avgwaitingtime;
        avgcpuusage = ((systemtime - (float)idletime) / systemtime) * 100;
        System.out.println("Average CPU usage:\t" + avgcpuusage);
        System.out.println("Average Waiting time:\t" + avgwaitingtime/p.length);
        System.out.println("Average Response time:\t" + avgresponsetime/p.length);
        System.out.println("Average Turnaround time:\t" + avgturnaroundtime/p.length);
    }
}
