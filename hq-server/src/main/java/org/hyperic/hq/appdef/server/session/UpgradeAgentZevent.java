/*
 * NOTE: This copyright does *not* cover user programs that use HQ
 * program services by normal system calls through the application
 * program interfaces provided as part of the Hyperic Plug-in Development
 * Kit or the Hyperic Client Development Kit - this is merely considered
 * normal use of the program, and does *not* fall under the heading of
 * "derived work".
 *
 * Copyright (C) [2004-2008], Hyperic, Inc.
 * This file is part of HQ.
 *
 * HQ is free software; you can redistribute it and/or modify
 * it under the terms version 2 of the GNU General Public License as
 * published by the Free Software Foundation. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 */

package org.hyperic.hq.appdef.server.session;

import org.hyperic.hq.appdef.shared.AppdefEntityID;
import org.hyperic.hq.zevents.Zevent;
import org.hyperic.hq.zevents.ZeventManager;
import org.hyperic.hq.zevents.ZeventPayload;
import org.hyperic.hq.zevents.ZeventSourceId;

/**
 * A Zevent for asynchronous upgrading of agents.
 */
public class UpgradeAgentZevent extends Zevent {
    
    static {
        ZeventManager.getInstance()
            .registerEventClass(UpgradeAgentZevent.class);
    }

    public UpgradeAgentZevent(String agentBundleFile, AppdefEntityID aid) {
        super(new UpgradeAgentZeventSource(agentBundleFile, aid), 
              new UpgradeAgentZeventPayload(agentBundleFile, aid));
    }
    
    public String getAgentBundleFile() {
        return ((UpgradeAgentZeventPayload)getPayload()).getAgentBundleFile();
    }
    
    public AppdefEntityID getAgent() {
        return ((UpgradeAgentZeventPayload)getPayload()).getAgent();        
    }
    
    private static class UpgradeAgentZeventSource implements ZeventSourceId {
        
        private static final long serialVersionUID = 6843413779833224584L;
        
        private String _agentBundleFile;
        private AppdefEntityID _aid;

        public UpgradeAgentZeventSource(String agentBundleFile, AppdefEntityID aid) {
            checkForNullParameters(agentBundleFile, aid);
            _agentBundleFile = agentBundleFile;
            _aid = aid;
        }

        public int hashCode() {
            int result = 17;
            result = 37*result+_aid.hashCode();
            result = 37*result+_agentBundleFile.hashCode();
            return result;
        }

        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            
            if (other instanceof UpgradeAgentZeventSource) {
                UpgradeAgentZeventSource src = 
                    (UpgradeAgentZeventSource)other;
                
                return _aid.equals(src._aid) && 
                       _agentBundleFile.equals(src._agentBundleFile);                
            }
            
            return false;
        }
    }

    private static class UpgradeAgentZeventPayload implements ZeventPayload {
        
        private final String _agentBundleFile;
        private final AppdefEntityID _aid;

        public UpgradeAgentZeventPayload(String agentBundleFile, AppdefEntityID aid) {
            checkForNullParameters(agentBundleFile, aid);
            _agentBundleFile = agentBundleFile;
            _aid = aid;
        }

        public String getAgentBundleFile() {
            return _agentBundleFile;
        }

        public AppdefEntityID getAgent() {
            return _aid;
        }
    }
    
    private static void checkForNullParameters(String agentBundleFile, AppdefEntityID aid) {
        if (agentBundleFile == null) {
            throw new NullPointerException("agent bundle file is null");
        }
        
        if (aid == null) {
            throw new NullPointerException("agent id is null");
        }
    }

}
