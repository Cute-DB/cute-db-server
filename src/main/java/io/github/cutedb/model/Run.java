package io.github.cutedb.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Run  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long runId = null;
    private String jdbcUrl = null;
    private String server = null;
    private String host = null;
    private String user = null;
    private String databaseProductName = null;
    private Boolean status = null;
    private Timestamp started = null;
    private Timestamp ended = null;

    public Long getRunId() {
        return runId;
    }
    public void setRunId(Long runId) {
        this.runId = runId;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }
    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getServer() {
        return server;
    }
    public void setServer(String server) {
        this.server = server;
    }

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public String getDatabaseProductName() {
        return databaseProductName;
    }
    public void setDatabaseProductName(String databaseProductName) {
        this.databaseProductName = databaseProductName;
    }

    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Timestamp getStarted() {
        return started;
    }

    public void setStarted(Timestamp started) {
        this.started = started;
    }

    public Timestamp getEnded() {
        return ended;
    }

    public void setEnded(Timestamp ended) {
        this.ended = ended;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Run run = (Run) o;
        return Objects.equals(runId, run.runId) &&
                Objects.equals(jdbcUrl, run.jdbcUrl) &&
                Objects.equals(server, run.server) &&
                Objects.equals(host, run.host) &&
                Objects.equals(user, run.user) &&
                Objects.equals(databaseProductName, run.databaseProductName) &&
                Objects.equals(status, run.status) &&
                Objects.equals(started, run.started) &&
                Objects.equals(ended, run.ended);
    }

    @Override
    public int hashCode() {
        return Objects.hash(runId, jdbcUrl, /*server, host, user, databaseProductName,*/ status);
    }

    @Override
    public String toString()  {
        StringBuilder sb = new StringBuilder();
        sb.append("class Run {\n");

        sb.append("  runId: ").append(runId).append("\n");
        sb.append("  jdbcUrl: ").append(jdbcUrl).append("\n");
        sb.append("  server: ").append(server).append("\n");
        sb.append("  host: ").append(host).append("\n");
        sb.append("  user: ").append(user).append("\n");
        sb.append("  databaseProductName: ").append(databaseProductName).append("\n");
        sb.append("  status: ").append(status).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
