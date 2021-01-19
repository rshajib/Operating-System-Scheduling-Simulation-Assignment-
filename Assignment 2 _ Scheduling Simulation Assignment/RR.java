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
public class RR extends CPUschedule{   
    
    private int timeslice;
    
    public RR(process[] p , int timeslice)
    {
        this.p = p;
        this.timeslice = timeslice;
    }
    
    public void scheduler()
    {            
        int[] originalburst = new int[p.length];
        for(int i=0; i<originalburst.length; i++)
        {
            originalburst[i] = p[i].getBursttime();
        }
        
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
            if(head.getResponsetime() == null)
            {
                head.setResponsetime(systemtime - head.getArrivaltime());
            }
            int burst = head.getBursttime();
            for(int i=0; i<(timeslice<burst?timeslice:burst); i++ , systemtime++)
            {
                System.out.println("<system time    " + systemtime + "> process " + head.getProcessid() + " is running....");
                head.setBursttime(head.getBursttime()-1);
                if(head.getBursttime() == 0)
                {
                    System.out.println("<system time    " + (systemtime+1) + "> process " + head.getProcessid() + " is finished");
                    head.setTurnaroundtime(systemtime + 1 - head.getArrivaltime());
                }
                checkprocessarrival();
            }
            checkprocessarrival();
            if(head.getBursttime() > 0)
            {
                readyqueue.add(head);
            }
        } 
        System.out.println("<system time    " + systemtime + "> All processes finished");
        
        for(int i=0; i<p.length; i++)
        {
            avgturnaroundtime = avgturnaroundtime + p[i].getTurnaroundtime();
            avgwaitingtime = avgwaitingtime + (p[i].getTurnaroundtime() - originalburst[i]);
            p[i].setWaitingtime(p[i].getTurnaroundtime() - originalburst[i]);
            avgresponsetime = avgresponsetime + (p[i].getResponsetime());
        }
        avgcpuusage = ((systemtime - (float)idletime) / systemtime) * 100;
        System.out.println("Average CPU usage:\t" + avgcpuusage);
        System.out.println("Average Waiting time:\t" + avgwaitingtime/p.length);
        System.out.println("Average Response time:\t" + avgresponsetime/p.length);
        System.out.println("Average Turnaround time:\t" + avgturnaroundtime/p.length);
    }
}
