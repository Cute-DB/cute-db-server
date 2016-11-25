package io.github.cutedb.utils;

import io.github.cutedb.model.Run;

/**
 * Created by barmi83 on 11/25/16.
 */
public class ScoredTable {

    private String name;
    private Integer criticalHits = 0;
    private Integer highHits = 0;
    private Integer mediumHits = 0;
    private Integer lowHits = 0;

    public ScoredTable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getScore(){
        return criticalHits >= 1 ? 0 : (highHits >= 1 ? 1 : (mediumHits >= 1 ? 2 : (lowHits >= 1 ? 3 : 4)));
    }

    public Integer getWeightedScore(){
        int totalHits = criticalHits+highHits+mediumHits+lowHits;

        if(totalHits == 0)
            return 0;

        return (criticalHits*Run.CRITICAL_WEIGHT + highHits*Run.HIGH_WEIGHT + mediumHits*Run.MEDIUM_WEIGHT + lowHits*Run.LOW_WEIGHT)/totalHits;
    }


}
