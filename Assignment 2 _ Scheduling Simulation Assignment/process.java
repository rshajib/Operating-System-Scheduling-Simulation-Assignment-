/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class process implements Comparable<process>
{
    private Integer processid;
    private Integer arrivaltime;
    private Integer bursttime;
    private Integer waitingtime;
    private Integer responsetime;
    private Integer turnaroundtime;
    
    public process(Integer processid , Integer arrivaltime , Integer bursttime)
    {
        this.processid = processid;
        this.arrivaltime = arrivaltime;
        this.bursttime = bursttime;
        this.waitingtime = null;
        this.responsetime = null;
        this.turnaroundtime = null;
    }
    
    public Integer getProcessid()
    {
        return processid;
    }
    public Integer getArrivaltime()
    {
        return arrivaltime;
    }
    public Integer getBursttime()
    {
        return bursttime;
    }
    public Integer getWaitingtime()
    {
        return waitingtime;
    }
    public Integer getResponsetime()
    {
        return responsetime;
    }
    public Integer getTurnaroundtime()
    {
        return turnaroundtime;
    }
    
    public void setProcessid(Integer processid)
    {
        this.processid = processid;
    }
    public void setArrivaltime(Integer arrivaltime)
    {
        this.arrivaltime = arrivaltime;
    }
    public void setBursttime(Integer bursttime)
    {
        this.bursttime = bursttime;
    }
    public void setWaitingtime(Integer waitingtime)
    {
        this.waitingtime = waitingtime;
    }
    public void setResponsetime(Integer responsetime)
    {
        this.responsetime = responsetime; 
    }
    public void setTurnaroundtime(Integer turnaroundtime)
    {
        this.turnaroundtime = turnaroundtime;
    }
    
    @Override
    public int compareTo(process p)
    {
        return this.getArrivaltime() - p.getArrivaltime();
    }    
}
