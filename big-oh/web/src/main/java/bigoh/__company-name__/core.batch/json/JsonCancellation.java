#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.${company-name}.core.batch.json;

import org.joda.time.DateTime;

import java.util.List;

public class JsonCancellation {

    private String id;
    private DateTime cancelledAt;
    private List<JsonAffectedDevice> affectedDevices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DateTime getCancelledAt() {
        return cancelledAt;
    }

    public void setCancelledAt(DateTime cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public List<JsonAffectedDevice> getAffectedDevices() {
        return affectedDevices;
    }

    public void setAffectedDevices(List<JsonAffectedDevice> affectedDevices) {
        this.affectedDevices = affectedDevices;
    }


}