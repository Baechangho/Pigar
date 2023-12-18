package server_test;

import java.util.*;

import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarNotImplementedException;
import org.hyperic.sigar.cmd.ProcModuleInfo;
import org.hyperic.sigar.cmd.Shell;

import com.google.gson.Gson;

public class practice extends SigarCommandBase {
   public practice(Shell shell) {
        super(shell);
    }

    public practice() {
        super();
    }
    public void descending_sort(long[] pids) {
       for(int i = 0; i < pids.length - 1; i++) {
          for(int j = 1; j < pids.length - i; j++) {
             int result = Long.compare(pids[j - 1], pids[j]);
             if(result < 0) {
                long tmp = pids[j - 1];
                pids[j - 1] = pids[j];
                pids[j] = tmp;
             }
          }
       }
       return;
    }
    public void name_sort(long[] pids) throws SigarException {
      for(int i = 0; i < pids.length - 1; i++) {
          for(int j = 0; j < pids.length - i - 1; j++) {
             if(sigar.getProcState(pids[j + 1]).getName().compareTo(sigar.getProcState(pids[j]).getName()) < 0){
                    long tmp = pids[j];
                    pids[j] = pids[j + 1];
                    pids[j + 1] = tmp;
                }
          }
       }
       return;
    }
    public Deque<Transmission> find_thread_name(long[] pids, String str, List<Transmission> trans) throws SigarException {
       int i = 0;
       Deque<Transmission> data = new ArrayDeque<Transmission>();
       for(; i < pids.length; i++) {
          if(str.compareTo(trans.get(i).getProcState().getName()) == 0) {
            data.add(trans.get(i));
            i++;
             break;
          }
       }
       for(int j = i; j < pids.length; j++) {
          if(str.compareTo(trans.get(j).getProcState().getName()) == 0) {
             data.add(trans.get(j));
          }
          else break;
       }
       
       return data;
    }
    @Override
    public void output(String[] args) throws SigarException {
       // TODO Auto-generated method stub
       long[] pids = sigar.getProcList();
       List<Transmission> t = new ArrayList<Transmission>();
       Arrays.sort(pids);
       name_sort(pids);
       
       for(int i = 0; i < pids.length; i++) {
          t.add(output(pids[i]));
       }
       
       Scanner sc = new Scanner(System.in);
       System.out.println("Please enter the threadname");
       
       String str = sc.nextLine();
       Deque<Transmission> Thread_data = find_thread_name(pids, str, t);
       
       Gson gson = new Gson();
       List<String> json = new ArrayList<String>();
       while(!Thread_data.isEmpty()) {
          json.add(gson.toJson(Thread_data.pop()));
       }
      
       for(int i = 0; i < json.size(); i++) {
          System.out.println(json.get(i));
       }
       try {
          t.get(0).setThreadCpu(sigar.getThreadCpu());
       }catch(Exception e) {
          System.err.println("Thread Cpu isn`t saved, Exception naem is " + e);
       }
   }
   public Transmission output(long pid){
      Transmission tmp = new Transmission();
      try {
         tmp.setPid(pid);
      }catch(Exception ex) {
         System.err.println("pid " + ex);
      }
      try {
         tmp.setProcCpu(sigar.getProcCpu(pid));
      }catch(SigarException ex) {
         System.err.println("procCpu " + ex);
      }
      try {
         tmp.setProcMem(sigar.getProcMem(pid));
      }catch(SigarException ex) {
         System.err.println("procMem " + ex);
      }
      try {
         tmp.setProcState(sigar.getProcState(pid));
      }catch(SigarException ex) {
         System.err.println("procState " + ex);
      }
      try {
         tmp.setProcTime(sigar.getProcTime(pid));
      }catch(SigarException ex) {
         System.err.println("procTime " + ex);
      }
      return tmp;
    }
       
   public static void main(String[] args) throws Exception {
        new practice().processCommand(args); 
   }
}
