/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this dummylate file, choose Tools | Templates
 * and open the dummylate in the editor.
 */

import java.util.*;

/**
 *
 * @author Admin
 */
public class SRTF extends CPUschedule{
    
    public SRTF(process[] p)
    {
        this.p = p;
    }
    
    public void sortbursttime()
    {
        process[] readyqueuelist = new process[p.length];
        readyqueuelist = readyqueue.toArray(readyqueuelist);
        int readyqueuesize = readyqueue.size();
        process dummy;
        for(int j=0; j < readyqueuesize; j++)
        {  
            for(int k=0; k < (readyqueue.size()-j-1); k++)
            {  
               if(readyqueuelist[k].getBursttime() > readyqueuelist[k+1].getBursttime())
               {  
                    dummy = readyqueuelist[k];  
                    readyqueuelist[k] = readyqueuelist[k+1];  
                    readyqueuelist[k+1] = dummy;  
               }  
            }  
        }
        readyqueue.clear();
        for(int j=0; j<readyqueuesize; j++)
        {
            readyqueue.add(readyqueuelist[j]);
        }
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
            sortbursttime();
            process head = readyqueue.remove();
            //System.out.println("taking bursttime " + head.getBursttime() + " of " + head.getProcessid());
            if(head.getResponsetime() == null)
            {
                head.setResponsetime(systemtime - head.getArrivaltime());
            }
            int burst = head.getBursttime();
            for(int i=0; i<burst; i++ , systemtime++)
            {
                checkprocessarrival();
                if(readyqueue.size() > 0 && readyqueue.getLast().getBursttime() < head.getBursttime())
                {
                        //System.out.println("old " + head.getProcessid() + " new " + readyqueue.getLast().getProcessid());
                        if(head.getBursttime() > 0)
                        {
                                readyqueue.add(head);
                        }
                        break;
                }
                System.out.println("<system time    " + systemtime + "> process " + head.getProcessid() + " is running....");
                head.setBursttime(head.getBursttime()-1);
                if(head.getBursttime() == 0)
                {
                    System.out.println("<system time    " + (systemtime+1) + "> process " + head.getProcessid() + " is finished");
                    head.setTurnaroundtime(systemtime + 1 - head.getArrivaltime());
                }
            }
            checkprocessarrival();
        }
        System.out.println("<system time    " + systemtime + "> All processes finished");
        for(int i=0; i<p.length; i++)
        {
            avgturnaroundtime = avgturnaroundtime + p[i].getTurnaroundtime();
            avgwaitingtime = avgwaitingtime + (p[i].getTurnaroundtime() - originalburst[i]);
            p[i].setWaitingtime(p[i].getTurnaroundtime() - originalburst[i]);
            avgresponsetime = avgresponsetime + p[i].getResponsetime();
        }
        avgcpuusage = ((systemtime - (float)idletime) / systemtime) * 100;
        System.out.println("Average CPU usage:\t" + avgcpuusage);
        System.out.println("Average Waiting time:\t" + avgwaitingtime/p.length);
        System.out.println("Average Response time:\t" + avgresponsetime/p.length);
        System.out.println("Average Turnaround time:\t" + avgturnaroundtime/p.length);
    }
}
