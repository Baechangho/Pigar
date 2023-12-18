/*
 * Copyright (c) 2006-2007 Hyperic, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package server_test;

import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.cmd.Shell;

/**
 * Display network info.
 */

public class NetInfo extends SigarCommandBase {

    public NetInfo(Shell shell) {
        super(shell);
    }

    public NetInfo() {
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
        new NetInfo().processCommand(args);
    }
}
