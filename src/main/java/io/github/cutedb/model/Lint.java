package io.github.cutedb.model;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by barmi83 on 05/08/16.
 */
@Entity
public class Lint implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column
    String uuid;
    @Column
    String linter;
    @Column
    String objectName;
    @Column
    LintSeverity severity;
    @Column
    String message;
    @Column
    @Length(max = 1000)
    String value;
    @ManyToOne(targetEntity = Run.class)
    @JoinColumn(name = "id_run")
    Run run;

    public Lint(){

    }

    public Lint(String uuid, String linter, String objectName, String severity, String message, String value, Run run){
        this.uuid = uuid;
        this.linter = linter;
        this.objectName = objectName;
        this.severity = LintSeverity.getSeverity(severity);
        this.message = message;
        this.value = value;
        this.run = run;
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

    public String getLinter() {
        return linter;
    }

    public void setLinter(String linter) {
        this.linter = linter;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public LintSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(LintSeverity severity) {
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Run getRun() {
        return run;
    }

    public void setRun(Run run) {
        this.run = run;
    }
}
