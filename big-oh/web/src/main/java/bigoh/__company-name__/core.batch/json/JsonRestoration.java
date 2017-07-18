#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.${company-name}.core.batch.json;

import org.joda.time.DateTime;

import java.util.List;

public class JsonRestoration {

    private String id;
    private DateTime restoredAt;
    private String cause;
    private List<JsonAffectedDevice> affectedDevices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DateTime getRestoredAt() {
        return restoredAt;
    }

    public void setRestoredAt(DateTime restoredAt) {
        this.restoredAt = restoredAt;
    }

    public List<JsonAffectedDevice> getAffectedDevices() {
        return affectedDevices;
    }

    public void setAffectedDevices(List<JsonAffectedDevice> affectedDevices) {
        this.affectedDevices = affectedDevices;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }


}