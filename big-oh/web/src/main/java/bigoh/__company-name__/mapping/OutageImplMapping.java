#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.${company-name}.mapping;

import com.ifactorconsulting.bigoh.client.domain.ArticleNumber;
import com.ifactorconsulting.bigoh.client.domain.Confidence;
import com.ifactorconsulting.zest.mapper.AbstractMapping;
import ${package}.bigoh.${company-name}.client.domain.OutageImpl;
import generated.Outages.OutageWrapper.Outage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ${package}.bigoh.${company-name}.core.batch.json.JsonAffectedDevice;
import ${package}.bigoh.${company-name}.core.batch.json.JsonOutage;


@Component
public class OutageImplMapping extends AbstractMapping<OutageImpl>{
	private final Logger logger= LoggerFactory.getLogger(OutageImplMapping.class);

	/**
	 * TODO Example mapping from outage xml
	 */
	public OutageImpl from(Outage from){
		OutageImpl to = new OutageImpl();
		to.setExternalId(from.getId());
		to.setTotalCustomersAffected(from.getTotalCustOut());
		to.setEtr(from.getEtr());
		if(from.getEtrConfidenceLevel()==null){
			to.setEtrConfidence(Confidence.HIGH);
		}
		else{
			to.setEtrConfidence(Confidence.valueOf(from.getEtrConfidenceLevel().toString()));
		}

		if(from.getAffectedDevices() != null && from.getAffectedDevices().getDevice() != null) {
			for (Outage.AffectedDevices.Device device : from.getAffectedDevices().getDevice()) {
				to.getAffectedArticleNumbers().add(ArticleNumber.create(device.getId()));
				if (device.isReported() != null && device.isReported()) {
					to.getReportedArticles().add(ArticleNumber.create(device.getId()));
				}
			}

			for (Outage.AffectedDevices.Device device : from.getAffectedDevices().getDevice()) {
				to.getAffectedDeliveryNodes().add(device.getId());
			}
		}

		to.setCause(from.getCause());
		to.setCauseShort(from.getCauseShort());
		to.setCrewId(from.getCrewId());
		to.setCrewStatus(from.getCrewStatus());
		to.setCrewStatusAt(from.getCrewStatusAt());
		to.setStartedAt(from.getStartedAt());
		//TODO Map implementation specific fields
		return to;
	}


	/**
	 * TODO Example mapping from outage json
	 */
	public OutageImpl from(JsonOutage from){
		OutageImpl to = new OutageImpl();
		to.setExternalId(from.getId());
		to.setTotalCustomersAffected(from.getTotalCustOut());
		to.setEtr(from.getEtr());
		if(from.getEtrConfidenceLevel()==null){
			to.setEtrConfidence(Confidence.HIGH);
		}
		else{
			to.setEtrConfidence(Confidence.valueOf(from.getEtrConfidenceLevel().toString()));
		}

		if(from.getAffectedDevices() != null) {
			for (JsonAffectedDevice device : from.getAffectedDevices()) {
				to.getAffectedArticleNumbers().add(ArticleNumber.create(device.getId()));
				if (device.isReported()) {
					to.getReportedArticles().add(ArticleNumber.create(device.getId()));
				}
			}

			for (JsonAffectedDevice device : from.getAffectedDevices()) {
				to.getAffectedDeliveryNodes().add(device.getId());
			}
		}

		to.setCause(from.getCause());
		to.setCauseShort(from.getCauseShort());
		to.setCrewId(from.getCrewId());
		to.setCrewStatus(from.getCrewStatus());
		to.setCrewStatusAt(from.getCrewStatusAt());
		to.setStartedAt(from.getStartedAt());
		//TODO Map implementation specific fields
		return to;
	}


}
