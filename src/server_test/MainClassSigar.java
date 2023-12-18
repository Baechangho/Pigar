package server_test;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.cmd.Shell;

public class MainClassSigar extends SigarCommandBase {
	public MainClassSigar(Shell shell) {
        super(shell);
    }

    public MainClassSigar() {
        super();
    }

    public String getUsageShort() {
        return "Display network info";
    }

    public void output(String[] args) throws SigarException {
        NetInterfaceConfig config = this.sigar.getNetInterfaceConfig(null);
        System.out.println("primary interface....." + config.getName());
        System.out.println("primary ip address...." + config.getAddress());
        System.out.println("primary mac address..." + config.getHwaddr());
        System.out.println("primary netmask......." + config.getNetmask());

        org.hyperic.sigar.NetInfo info =
            this.sigar.getNetInfo();

        System.out.println("host name............." + info.getHostName());
        System.out.println("domain name..........." + info.getDomainName());
        System.out.println("default gateway......." + info.getDefaultGateway());
        System.out.println("primary dns..........." + info.getPrimaryDns());
        System.out.println("secondary dns........." + info.getSecondaryDns());
    }
	public static void main(String[] args) throws Exception {
		new MainClassSigar().processCommand(args);
		int flags = 100;
		flags = flags & NetFlags.CONN_TCP;
		System.out.println(flags);
	}
}
