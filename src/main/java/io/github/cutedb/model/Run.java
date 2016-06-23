package io.github.cutedb.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Run  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;
    @Column
    private String uuid = null;
    @Column
    private String jdbcUrl = null;
    @Column
    private String server = null;
    @Column
    private String host = null;
    @Column
    private String user = null;
    @Column
    private String databaseProductName = null;
    @Column
    private BuildStatus status = null;
    @Column
    private Timestamp started = null;
    @Column
    private Timestamp ended = null;
    @Column
    private Integer totalHits = null;

    public Run(){
        started = new Timestamp(new DateTime().getMillis());
        status = BuildStatus.PENDING;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public BuildStatus getStatus() {
        return status;
    }
    public void setStatus(BuildStatus status) {
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

    public Integer getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(Integer totalHits) {
        this.totalHits = totalHits;
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
        return Objects.equals(id, run.id) &&
                Objects.equals(jdbcUrl, run.jdbcUrl) &&
                Objects.equals(server, run.server) &&
                Objects.equals(host, run.host) &&
                Objects.equals(user, run.user) &&
                Objects.equals(databaseProductName, run.databaseProductName) &&
                Objects.equals(status, run.status) &&
                Objects.equals(started, run.started) &&
                Objects.equals(ended, run.ended)&&
                Objects.equals(uuid, run.uuid)&&
                Objects.equals(totalHits, run.totalHits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jdbcUrl, server, host, user, databaseProductName, status, started, ended, totalHits, uuid);
    }

    @Override
    public String toString()  {
        StringBuilder sb = new StringBuilder();
        sb.append("class Run {\n");

        sb.append("  id: ").append(id).append("\n");
        sb.append("  uuid: ").append(uuid).append("\n");
        sb.append("  jdbcUrl: ").append(jdbcUrl).append("\n");
        sb.append("  server: ").append(server).append("\n");
        sb.append("  host: ").append(host).append("\n");
        sb.append("  user: ").append(user).append("\n");
        sb.append("  databaseProductName: ").append(databaseProductName).append("\n");
        sb.append("  status: ").append(status).append("\n");
        sb.append("  totalHits: ").append(totalHits).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
