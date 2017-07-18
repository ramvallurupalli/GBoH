#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.ext;


import com.ifactorconsulting.bigoh.core.outage.OutageService;
import com.ifactorconsulting.bigoh.core.persistence.entity.OutageDetail;
import com.ifactorconsulting.bigoh.core.service.outage.ArticleOutageVoter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExternalIdVoterImpl extends ArticleOutageVoter {

    private static final Logger logger = LoggerFactory.getLogger(ExternalIdVoterImpl.class);

    @Autowired
    private OutageService outageService;

    private ArticleVoterHelper helper = new ArticleVoterHelper();

    @Override
    public int vote(final OutageDetail detailA, final OutageDetail detailB) {
        long outageDetailIdA = detailA.getId();
        long outageDetailIdB = detailB.getId();

        long outageIdA = detailA.getOutageId();
        long outageIdB = detailB.getOutageId();

        String externalIdA = outageService.outage(outageIdA).getExternalId();
        String externalIdB = outageService.outage(outageIdB).getExternalId();

        int result = externalIdA.compareTo(externalIdB);

        logger.info("Comparing outage detail 'A' {} and outage detail 'B' {} - {}",
                helper.formatOutageDetail(outageDetailIdA, outageIdA, externalIdA),
                helper.formatOutageDetail(outageDetailIdB, outageIdB, externalIdB),
                helper.formatResult(result)
        );

        return result;

    }

}