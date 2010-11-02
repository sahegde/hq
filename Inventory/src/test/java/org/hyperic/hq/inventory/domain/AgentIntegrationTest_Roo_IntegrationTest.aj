// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.hyperic.hq.inventory.domain;

import org.hyperic.hq.inventory.domain.AgentDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect AgentIntegrationTest_Roo_IntegrationTest {
    
    declare @type: AgentIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: AgentIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    declare @type: AgentIntegrationTest: @Transactional;
    
    @Autowired
    private AgentDataOnDemand AgentIntegrationTest.dod;
    
    @Test
    public void AgentIntegrationTest.testCountAgents() {
        org.junit.Assert.assertNotNull("Data on demand for 'Agent' failed to initialize correctly", dod.getRandomAgent());
        long count = org.hyperic.hq.inventory.domain.Agent.countAgents();
        org.junit.Assert.assertTrue("Counter for 'Agent' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void AgentIntegrationTest.testFindAgent() {
        org.hyperic.hq.inventory.domain.Agent obj = dod.getRandomAgent();
        org.junit.Assert.assertNotNull("Data on demand for 'Agent' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Agent' failed to provide an identifier", id);
        obj = org.hyperic.hq.inventory.domain.Agent.findAgent(id);
        org.junit.Assert.assertNotNull("Find method for 'Agent' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Agent' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void AgentIntegrationTest.testFindAllAgents() {
        org.junit.Assert.assertNotNull("Data on demand for 'Agent' failed to initialize correctly", dod.getRandomAgent());
        long count = org.hyperic.hq.inventory.domain.Agent.countAgents();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Agent', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<org.hyperic.hq.inventory.domain.Agent> result = org.hyperic.hq.inventory.domain.Agent.findAllAgents();
        org.junit.Assert.assertNotNull("Find all method for 'Agent' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Agent' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void AgentIntegrationTest.testFindAgentEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Agent' failed to initialize correctly", dod.getRandomAgent());
        long count = org.hyperic.hq.inventory.domain.Agent.countAgents();
        if (count > 20) count = 20;
        java.util.List<org.hyperic.hq.inventory.domain.Agent> result = org.hyperic.hq.inventory.domain.Agent.findAgentEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'Agent' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Agent' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void AgentIntegrationTest.testFlush() {
        org.hyperic.hq.inventory.domain.Agent obj = dod.getRandomAgent();
        org.junit.Assert.assertNotNull("Data on demand for 'Agent' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Agent' failed to provide an identifier", id);
        obj = org.hyperic.hq.inventory.domain.Agent.findAgent(id);
        org.junit.Assert.assertNotNull("Find method for 'Agent' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyAgent(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Agent' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void AgentIntegrationTest.testMerge() {
        org.hyperic.hq.inventory.domain.Agent obj = dod.getRandomAgent();
        org.junit.Assert.assertNotNull("Data on demand for 'Agent' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Agent' failed to provide an identifier", id);
        obj = org.hyperic.hq.inventory.domain.Agent.findAgent(id);
        boolean modified =  dod.modifyAgent(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        org.hyperic.hq.inventory.domain.Agent merged = (org.hyperic.hq.inventory.domain.Agent) obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'Agent' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void AgentIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Agent' failed to initialize correctly", dod.getRandomAgent());
        org.hyperic.hq.inventory.domain.Agent obj = dod.getNewTransientAgent(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Agent' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Agent' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Agent' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void AgentIntegrationTest.testRemove() {
        org.hyperic.hq.inventory.domain.Agent obj = dod.getRandomAgent();
        org.junit.Assert.assertNotNull("Data on demand for 'Agent' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Agent' failed to provide an identifier", id);
        obj = org.hyperic.hq.inventory.domain.Agent.findAgent(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Agent' with identifier '" + id + "'", org.hyperic.hq.inventory.domain.Agent.findAgent(id));
    }
    
}
