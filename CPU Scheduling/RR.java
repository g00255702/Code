
/**
 * CPU Scheduling using Round robin
 *
 * @author Emeka Okonkwo
 * @version 11.26.2018
 */
import java.util.Random;
public class RR {
    private Random rand = new Random();
    
 public void RR()
 {
       int q = 4;//Time quantum
       int n = 10;//Process ID
       int process[][] = new int[n + 1][4];
       System.out.println("\nCPU Scheduling using the Round Robin Algorithm\n");
       System.out.println("Time quantum = 4");
       System.out.println("Process ID\t Burst");
       for(int i = 1; i <= n; i++)
       {
      process[i][1] = rand.nextInt(10)+1;//Number of Burst
      System.out.printf("%d\t%20dms\n",i,process[i][1]);
     }
       System.out.println();
     
       //Calculation of Total Time and Initialization of Gantt Chart array
     int total_time = 0;
     for(int i = 1; i <= n; i++)
     {
      total_time += process[i][1];
     }
     int gantt_chart[] = new int[total_time];
     
     int sel_process = 1;
     int current_q = 0;
     for(int i = 0; i < total_time; i++)
     {
      //Assign selected processess to current time in the Chart
      gantt_chart[i] = sel_process;
      
      //Decrement Remaining Time of selected processess by 1 since it has been assigned the CPU for 1 unit of time
      process[sel_process][1]--;
      
      //WT and TT Calculation
      for(int j = 1; j <= n; j++)
      {
       if(process[j][1] != 0)
       {
        process[j][3]++;//If processess has not completed execution its TT is incremented by 1
        if(j != sel_process)//If the processess has not been currently assigned the CPU its WT is incremented by 1
         process[j][2]++;
       }
       else if(j == sel_process)//This is a special case in which the processess has been assigned CPU and has completed its execution
        process[j][3]++;
      }
      
      //Printing the Gantt Chart
      if(i != 0)
      {
       if(sel_process != gantt_chart[i - 1])
        //If the CPU has been assigned to a different Process we need to print the current value of time and the name of 
        //the new Process
       {
        System.out.print("--" + i + "--P" + sel_process);
       }
      }
      else//If the current time is 0 i.e the printing has just started we need to print the name of the First selected Process
       System.out.print(i + "--P" + sel_process);
      if(i == total_time - 1)//All the processess names have been printed now we have to print the time at which execution ends
       System.out.print("--" + (i + 1));
      
      //Updating value of sel_process for next iteration
      current_q++;
      if(current_q == q || process[sel_process][1] == 0)//If Time slice has expired or the current processess has completed execution
      {
       current_q = 0;
       //This will select the next valid value for sel_process
       for(int j = 1; j <= n; j++)
       {
        sel_process++;
        if(sel_process == (n + 1))
            sel_process = 1;
        if(process[sel_process][1] != 0)
         break;
       }
      }
     }
     System.out.println();
     System.out.println();
     
     //Printing the Waiting time and Turnaround time for each Process
     System.out.println("Process ID\t WT  \t    TT  ");
     for(int i = 1; i <= n; i++)
     {
      System.out.printf("%d\t%15dms\t%10dms",i,process[i][2],process[i][3]);
      System.out.println();
     }
     
     System.out.println();
     
     //Printing the average Waiting time and Turnaround time
     float WT = 0,TT = 0;
     for(int i = 1; i <= n; i++)
     {
      WT += process[i][2];
      TT += process[i][3];
     }
     WT /= n;
     TT /= n;
     System.out.println("The AWT is: " + WT + "ms");
     System.out.println("The ATT is: " + TT + "ms\n");
 }
    
}