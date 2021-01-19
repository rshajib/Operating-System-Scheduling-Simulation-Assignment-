/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import java.io.*;

/**
 *
 * @author Admin
 */
public class Assignment2 {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) throws Exception
	{					
		String filedata = "";
		
		FileInputStream fstream = new FileInputStream("assignment2.txt");
		int data;
		while((data = fstream.read()) != -1)
		{
			filedata = filedata + Character.toString((char)data);
		}
		
		String[] processdata = filedata.split("\n");
		
		process[] p = new process[processdata.length];
		int index = 0;
		for(String i : processdata)
		{
			String[] params = i.split(" ");
			p[index] = new process(Integer.parseInt(params[0]) , Integer.parseInt(params[1]) , Integer.parseInt(params[2].substring(0 , params[2].length() - 1)));
			index++;
		}
		
		Arrays.sort(p);
		
		if(args.length > 0)
		{
			if(args[0].equals("FCFS"))
			{
				new FCFS(p).scheduler();
			}
			else if(args[0].equals("RR"))
			{
				if(args.length == 1)
				{
					System.out.println("Please enter time quanta");
				}
				else
				{
					new RR(p , Integer.parseInt(args[1])).scheduler();
				}
			}
			else if(args[0].equals("SRTF"))
			{
				new SRTF(p).scheduler();
			}
			else
			{
				System.out.println("Please enter appropriate CPU scheduler");
			}
		}
		else
		{
			System.out.println("Please enter CPU scheduler");
		}
    }
}
