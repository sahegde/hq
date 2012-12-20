package org.hyperic.hq.management.shared;

import java.util.Collection;

import org.hyperic.hibernate.PersistedObject;
import org.hyperic.hq.authz.server.session.Resource;
import org.hyperic.hq.common.server.session.Crispo;

@SuppressWarnings("serial")
public class ManagementProfile extends PersistedObject {
    
    private String description;
    private Resource resource;
    private Crispo config;
    private Collection<MeasurementInstruction> measurementInstructionBag;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Resource getResource() {
        return resource;
    }
    public void setResource(Resource resource) {
        this.resource = resource;
    }
    public Crispo getConfig() {
        return config;
    }
    public void setConfig(Crispo config) {
        this.config = config;
    }
    public Collection<MeasurementInstruction> getMeasurementInstructionBag() {
        return measurementInstructionBag;
    }
    public void setMeasurementInstructionBag(Collection<MeasurementInstruction> measurementInstructions) {
        this.measurementInstructionBag = measurementInstructions;
    }
    
    public String toString() {
        return new StringBuilder()
            .append("resource=").append(resource).append(",name=").append(resource.getName())
            .append(",description=").append(description)
            .append(",prototype=").append(resource.getPrototype().getName()).toString();
    }

}
