package io.github.cutedb.dto;

import io.github.cutedb.model.BuildStatus;

import java.util.Date;

/**
 * Created by barmi83 on 19/08/16.
 */
public class Run {
    private Long id = null;
    private String uuid = null;
    private String jdbcUrl = null;
    private String server = null;
    private String host = null;
    private String user = null;
    private String databaseProductName = null;
    private BuildStatus status = null;
    private Date started = null;
    private Date ended = null;
    private Integer criticalHits = 0;
    private Integer highHits = 0;
    private Integer mediumHits = 0;
    private Integer lowHits = 0;

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

    public Date getStarted() {
        return started;
    }

    public void setStarted(Date started) {
        this.started = started;
    }

    public Date getEnded() {
        return ended;
    }

    public void setEnded(Date ended) {
        this.ended = ended;
    }

    public Integer getCriticalHits() {
        return criticalHits;
    }

    public void setCriticalHits(Integer criticalHits) {
        this.criticalHits = criticalHits;
    }

    public Integer getHighHits() {
        return highHits;
    }

    public void setHighHits(Integer highHits) {
        this.highHits = highHits;
    }

    public Integer getMediumHits() {
        return mediumHits;
    }

    public void setMediumHits(Integer mediumHits) {
        this.mediumHits = mediumHits;
    }

    public Integer getLowHits() {
        return lowHits;
    }

    public void setLowHits(Integer lowHits) {
        this.lowHits = lowHits;
    }
}
