#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.${company-name}.core.batch.json;

import java.util.List;

public class JsonOutageBatch {

    private List<JsonOutage> outages;

    private List<JsonRestoration> restorations;

    private List<JsonCancellation> cancellations;

    public List<JsonOutage> getOutages() {
        return outages;
    }

    public void setOutages(List<JsonOutage> outages) {
        this.outages = outages;
    }

    public List<JsonRestoration> getRestorations() {
        return restorations;
    }

    public void setRestorations(List<JsonRestoration> restorations) {
        this.restorations = restorations;
    }

    public List<JsonCancellation> getCancellations() {
        return cancellations;
    }

    public void setCancellations(List<JsonCancellation> cancellations) {
        this.cancellations = cancellations;
    }
}