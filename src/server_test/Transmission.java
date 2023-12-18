package server_test;

import java.util.List;

import org.hyperic.sigar.*;

public class Transmission {
   private long pid;
   private ProcMem procMem;
   private ProcState procState;
   private ProcTime procTime;
   private ProcCpu procCpu;
   private static ThreadCpu threadCpu;
   
   public boolean setPid(long pid) {
      try {
         this.pid = pid;
         return true;
      }catch(Exception e) {
         System.err.println("pid " + e);
         return false;
      }
   }
   public boolean setProcMem(ProcMem temp) {
      try {
         this.procMem = temp;
         return true;
      }catch(Exception e){
         System.err.println("mem " + e);
         return false;
      }
   }
   public boolean setProcState(ProcState temp) {
      try {
         this.procState = temp;
         return true;
      }catch(Exception e){
         System.err.println("prostate" + e);
         return false;
      }
   }
   public boolean setProcTime(ProcTime temp) {
      try {
         this.procTime = temp;
         return true;
      }catch(Exception e){
         System.err.println("procTime " + e);
         return false;
      }
   }
   public boolean setProcCpu(ProcCpu temp) {
      try {
         this.procCpu = temp;
         return true;
      }catch(Exception e){
         System.err.println("cpu " + e);
         return false;
      }
   }
   public boolean setThreadCpu(ThreadCpu temp) {
      try {
         Transmission.threadCpu = temp;
         return true;
      }catch(Exception e) {
         System.err.println("tcpu " + e);
         return false;
      }
   }
   
   public long getPid() {
      return this.pid;
   }
   public ProcMem getProcMem() {
      return this.procMem;
   }
   public ProcState getProcState() {
      return this.procState;
   }
   public ProcTime getProcTime() {
      return this.procTime;
   }
   public ProcCpu getProcCpu() {
      return this.procCpu;
   }
   public ThreadCpu getThreadCpu() {
      return Transmission.threadCpu;
   }
   
   public void printData() {
      System.out.println(this.pid);
      System.out.println(this.procCpu);
      System.out.println(this.procMem);
      System.out.println(this.procState);
      System.out.println(this.procTime);
   }
}
