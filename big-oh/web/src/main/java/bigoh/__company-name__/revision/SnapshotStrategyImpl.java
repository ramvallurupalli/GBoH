#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.${company-name}.revision;

import com.ifactorconsulting.bigoh.client.domain.Dto;
import com.ifactorconsulting.bigoh.core.persistence.entity.OutageDetail;
import com.ifactorconsulting.bigoh.core.revision.MissingContext;
import com.ifactorconsulting.bigoh.core.revision.SnapshotStrategy;
import com.ifactorconsulting.zest.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO if implementation specific outage fields use outagedetailimpl
 */
@Component
public class SnapshotStrategyImpl implements SnapshotStrategy {

	@Autowired
	Mapper mapper;

	@Override
	public OutageDetail createActiveOutage(Dto.Outage outage) {
		return mapper.map(OutageDetail.class).from(outage);
		//return mapper.map(OutageDetailImpl.class).from(outage);
	}

	@Override
	public OutageDetail createRestoration(Dto.Restoration restoration) {
		return mapper.map(OutageDetail.class).from(restoration);
		//return mapper.map(OutageDetailImpl.class).from(restoration);
	}

	@Override
	public OutageDetail createCancellation(Dto.Cancellation cancellation) {
		return mapper.map(OutageDetail.class).from(cancellation);
		//return mapper.map(OutageDetailImpl.class).from(cancellation);
	}


	/**
	 * @param context
	 * @param externalId
	 *
	 */
	@Override
	public void missingAccount(MissingContext context, String externalId) {
		//TODO if implicit restoration use restoration code, uncomment and use below code
//		Dto.Restoration restoration = new Dto.Restoration();
//		restoration.setExternalId(externalId);
//		context.submitRestore(restoration);
		//TODO if implicit cancellation use below code
		Dto.Cancellation cancellation=new Dto.Cancellation();
		cancellation.setExternalId(externalId);
		context.submitCancellation(cancellation);
	}

	@Override
	public void missingOutage(MissingContext context, String externalId, OutageDetail missingOutage) {
		//TODO if implicit restoration use restoration code (uncomment and use below code)
//		Dto.Restoration restoration = new Dto.Restoration();
//		restoration.setExternalId(externalId);
//		context.submitRestore(restoration);
		//TODO if implicit cancellation use below code
		Dto.Cancellation cancellation=new Dto.Cancellation();
		cancellation.setExternalId(externalId);
		context.submitCancellation(cancellation);
	}
}

