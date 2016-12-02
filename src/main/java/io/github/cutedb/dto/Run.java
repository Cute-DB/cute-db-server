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
    private String dbHost = null;
    private String dbName = null;
    private String host = null;
    private String user = null;
    private String databaseProductName = null;
    private BuildStatus status = null;
    private Date started = null;
    private Date ended = null;
    private String runnerVersion = null;
    private String schemaCrawlerVersion = null;
    private String additionalLintsVersion = null;
    private String lintConfig = null;
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

    public String getRunnerVersion() {
        return runnerVersion;
    }

    public void setRunnerVersion(String runnerVersion) {
        this.runnerVersion = runnerVersion;
    }

    public String getSchemaCrawlerVersion() {
        return schemaCrawlerVersion;
    }

    public void setSchemaCrawlerVersion(String schemaCrawlerVersion) {
        this.schemaCrawlerVersion = schemaCrawlerVersion;
    }

    public String getAdditionalLintsVersion() {
        return additionalLintsVersion;
    }

    public void setAdditionalLintsVersion(String additionalLintsVersion) {
        this.additionalLintsVersion = additionalLintsVersion;
    }

    public String getLintConfig() {
        return lintConfig;
    }

    public void setLintConfig(String lintConfig) {
        this.lintConfig = lintConfig;
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

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

}
