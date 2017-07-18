#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.${company-name}.mapping;

import com.ifactorconsulting.bigoh.client.domain.ArticleNumber;
import com.ifactorconsulting.zest.mapper.AbstractMapping;
import ${package}.bigoh.${company-name}.client.domain.RestorationImpl;
import ${package}.bigoh.${company-name}.core.batch.json.JsonAffectedDevice;
import ${package}.bigoh.${company-name}.core.batch.json.JsonRestoration;
import generated.Outages.RestorationWrapper.Restoration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RestorationImplMapping extends AbstractMapping<RestorationImpl> {
    private final Logger logger = LoggerFactory.getLogger(RestorationImplMapping.class);

    public RestorationImpl from (Restoration from){
        RestorationImpl to =new RestorationImpl();

        to.setExternalId(from.getId());
        to.setCause(from.getCause());
        to.setRestoredAt(from.getRestoredAt());
        for(Restoration.AffectedDevices.Device device : from.getAffectedDevices().getDevice()){

            to.getAffectedArticles().add(ArticleNumber.create(device.getId()));
            if(device.isReported()!=null && device.isReported()){
                to.getReportedArticles().add(ArticleNumber.create(device.getId()));
            }
        }
        //TODO Map implementation specific fields
        return to;
    }

    public RestorationImpl from (JsonRestoration from){
        RestorationImpl to =new RestorationImpl();

        to.setExternalId(from.getId());
        to.setCause(from.getCause());
        to.setRestoredAt(from.getRestoredAt());
        to.setExternalId(from.getId());
        for(JsonAffectedDevice device : from.getAffectedDevices()){

            to.getAffectedArticles().add(ArticleNumber.create(device.getId()));
            if(device.isReported()){
                to.getReportedArticles().add(ArticleNumber.create(device.getId()));
            }
        }
        //TODO Map implementation specific fields
        return to;
    }

}
