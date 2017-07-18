#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.${company-name}.core.batch.json;

import org.joda.time.DateTime;

import java.util.List;

public class JsonOutage {

    private String id;
    private DateTime startedAt;
    private DateTime etr;
    private String etrConfidenceLevel;
    private String crewId;
    private DateTime crewStatusAt;
    private String cause;
    private String causeShort;
    private String crewStatus;
    private String comments;
    private String commentsInternal;
    private int totalCustOut;
    private List<JsonAffectedDevice> affectedDevices;

    public DateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(DateTime startedAt) {
        this.startedAt = startedAt;
    }

    public DateTime getEtr() {
        return etr;
    }

    public void setEtr(DateTime etr) {
        this.etr = etr;
    }

    public String getEtrConfidenceLevel() {
        return etrConfidenceLevel;
    }

    public void setEtrConfidenceLevel(String etrConfidenceLevel) {
        this.etrConfidenceLevel = etrConfidenceLevel;
    }

    public String getCrewId() {
        return crewId;
    }

    public void setCrewId(String crewId) {
        this.crewId = crewId;
    }

    public DateTime getCrewStatusAt() {
        return crewStatusAt;
    }

    public void setCrewStatusAt(DateTime crewStatusAt) {
        this.crewStatusAt = crewStatusAt;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getCauseShort() {
        return causeShort;
    }

    public void setCauseShort(String causeShort) {
        this.causeShort = causeShort;
    }

    public String getCrewStatus() {
        return crewStatus;
    }

    public void setCrewStatus(String crewStatus) {
        this.crewStatus = crewStatus;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCommentsInternal() {
        return commentsInternal;
    }

    public void setCommentsInternal(String commentsInternal) {
        this.commentsInternal = commentsInternal;
    }

    public List<JsonAffectedDevice> getAffectedDevices() {
        return affectedDevices;
    }

    public void setAffectedDevices(List<JsonAffectedDevice> affectedDevices) {
        this.affectedDevices = affectedDevices;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTotalCustOut() {
        return totalCustOut;
    }

    public void setTotalCustOut(int totalCustOut) {
        this.totalCustOut = totalCustOut;
    }


}