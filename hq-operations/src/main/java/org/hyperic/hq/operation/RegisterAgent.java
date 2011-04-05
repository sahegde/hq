package org.hyperic.hq.operation;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author Helena Edelson
 */
public class RegisterAgent extends AbstractAgentOperation {

    private static final long serialVersionUID = 1876820746657883192L;
    
    private String authToken;

    private String version;

    private int cpuCount;

    @JsonCreator
    public RegisterAgent(@JsonProperty("authToken") String authToken, @JsonProperty("version") String version,
                         @JsonProperty("cpuCount") int cpuCount, @JsonProperty("agentIp") String agentIp,
                         @JsonProperty("agentPort") int agentPort, @JsonProperty("username") String username,
                         @JsonProperty("password") String password) {

        super(username, password, agentIp, agentPort);
        this.authToken = authToken;
        this.version = version;
        this.cpuCount = cpuCount;
    }


    public String getAuthToken() {
        return authToken;
    }

    public String getVersion() {
        return version;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    @Override
    public String toString() {
        return new StringBuilder(this.authToken).append(this.version).append(this.cpuCount).append(super.toString()).toString();
    }

    /**
     * TODO better port test
     * @throws IllegalStateException
     */
    @JsonIgnore
    public void validate() throws IllegalStateException {
        if (this.authToken != null && this.version != null && this.cpuCount > 0) {
            throw new IllegalStateException(this + " is not properly initialized: " + this.toString());
        }
    }
}
