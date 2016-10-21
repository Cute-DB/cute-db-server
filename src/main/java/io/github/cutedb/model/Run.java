package io.github.cutedb.model;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Run  {

    private static final int CRITICAL_WEIGHT = 5;
    private static final int HIGH_WEIGHT = 3;
    private static final int MEDIUM_WEIGHT = 2;
    private static final int LOW_WEIGHT = 1;

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
    private String dbHost = null;
    @Column
    private String dbName = null;
    @Column
    private String host = null;
    @Column
    private String user = null;
    @Column
    private String databaseProductName = null;
    @Column
    private BuildStatus status = null;
    @Column
    private Date started = null;
    @Column
    private Date ended = null;
    @Column
    private String runnerVersion = null;
    @Column
    private String schemaCrawlerVersion = null;
    @Column
    private String additionalLintsVersion = null;
    @Column
    private String lintConfig = null;
    @Column
    private Integer criticalHits = 0;
    @Column
    private Integer highHits = 0;
    @Column
    private Integer mediumHits = 0;
    @Column
    private Integer lowHits = 0;

    public Run(){
        started = new Date();
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

    public static int getCriticalWeight() {
        return CRITICAL_WEIGHT;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Run run = (Run) o;
        return Objects.equals(id, run.id) &&
                Objects.equals(uuid, run.uuid) &&
                Objects.equals(jdbcUrl, run.jdbcUrl) &&
                Objects.equals(server, run.server) &&
                Objects.equals(dbHost, run.dbHost) &&
                Objects.equals(dbName, run.dbName) &&
                Objects.equals(host, run.host) &&
                Objects.equals(user, run.user) &&
                Objects.equals(databaseProductName, run.databaseProductName) &&
                status == run.status &&
                Objects.equals(started, run.started) &&
                Objects.equals(ended, run.ended) &&
                Objects.equals(runnerVersion, run.runnerVersion) &&
                Objects.equals(schemaCrawlerVersion, run.schemaCrawlerVersion) &&
                Objects.equals(additionalLintsVersion, run.additionalLintsVersion) &&
                Objects.equals(lintConfig, run.lintConfig) &&
                Objects.equals(criticalHits, run.criticalHits) &&
                Objects.equals(highHits, run.highHits) &&
                Objects.equals(mediumHits, run.mediumHits) &&
                Objects.equals(lowHits, run.lowHits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, jdbcUrl, server, dbHost, dbName, host, user, databaseProductName, status, started, ended, criticalHits, highHits, mediumHits, lowHits);
    }

    @Override
    public String toString() {
        return "Run{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", jdbcUrl='" + jdbcUrl + '\'' +
                ", server='" + server + '\'' +
                ", dbHost='" + dbHost + '\'' +
                ", dbName='" + dbName + '\'' +
                ", host='" + host + '\'' +
                ", user='" + user + '\'' +
                ", databaseProductName='" + databaseProductName + '\'' +
                ", status=" + status +
                ", started=" + started +
                ", ended=" + ended +
                ", runnerVersion='" + runnerVersion + '\'' +
                ", schemaCrawlerVersion='" + schemaCrawlerVersion + '\'' +
                ", additionalLintsVersion='" + additionalLintsVersion + '\'' +
                ", lintConfig='" + lintConfig + '\'' +
                ", criticalHits=" + criticalHits +
                ", highHits=" + highHits +
                ", mediumHits=" + mediumHits +
                ", lowHits=" + lowHits +
                '}';
    }

    @Transient
    public Integer getScore(){
        return criticalHits >= 1 ? 0 : (highHits >= 1 ? 1 : (mediumHits >= 1 ? 2 : (lowHits >= 1 ? 3 : 4)));
    }

    @Transient
    public Integer getWeightedScore(){
        int totalHits = criticalHits+highHits+mediumHits+lowHits;

        if(totalHits == 0)
            return 0;

        return (criticalHits*CRITICAL_WEIGHT + highHits*HIGH_WEIGHT + mediumHits*MEDIUM_WEIGHT + lowHits*LOW_WEIGHT)/totalHits;
    }


}
