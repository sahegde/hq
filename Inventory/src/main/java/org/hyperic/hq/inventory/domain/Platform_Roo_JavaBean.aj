// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.hyperic.hq.inventory.domain;

import java.lang.String;
import java.util.Set;
import org.hyperic.hq.inventory.domain.Agent;
import org.hyperic.hq.inventory.domain.Config;
import org.hyperic.hq.inventory.domain.Ip;

privileged aspect Platform_Roo_JavaBean {
    
    public String Platform.getFqdn() {
        return this.fqdn;
    }
    
    public void Platform.setFqdn(String fqdn) {
        this.fqdn = fqdn;
    }
    
    public String Platform.getName() {
        return this.name;
    }
    
    public void Platform.setName(String name) {
        this.name = name;
    }
    
    public Config Platform.getConfig() {
        return this.config;
    }
    
    public void Platform.setConfig(Config config) {
        this.config = config;
    }
    
    public Agent Platform.getAgent() {
        return this.agent;
    }
    
    public void Platform.setAgent(Agent agent) {
        this.agent = agent;
    }
    
    public Set<Ip> Platform.getIps() {
        return this.ips;
    }
    
    public void Platform.setIps(Set<Ip> ips) {
        this.ips = ips;
    }
    
}
