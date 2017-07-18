#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.ext;

import com.ifactorconsulting.bigoh.core.outage.OutageService;
import com.ifactorconsulting.bigoh.core.persistence.entity.OutageDetail;
import com.ifactorconsulting.bigoh.core.service.outage.ArticleOutageVoter;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeFieldType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistantEtrVoterImpl extends ArticleOutageVoter {

    private static final Logger logger = LoggerFactory.getLogger(DistantEtrVoterImpl.class);

    private DateTimeComparator comparator = DateTimeComparator.getInstance(DateTimeFieldType.minuteOfHour());

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

        DateTime etrA = detailA.getEtr();
        DateTime etrB = detailB.getEtr();

        final int result;
        if (etrA != null && etrB != null) {
            result = comparator.compare(etrA, etrB);
        }
        else if (etrA == null && etrB == null) {
            result = 0; // no winner
        }
        else if (etrA == null) {
            result = -1; // B wins
        }
        else {
            result = 1; // A wins
        }

        logger.info("Comparing outage detail 'A' {} and outage detail 'B' {} - {}",
                helper.formatOutageDetail(outageDetailIdA, outageIdA, externalIdA, etrA),
                helper.formatOutageDetail(outageDetailIdB, outageIdB, externalIdB, etrB),
                helper.formatResult(result)
        );

        return result;
    }
}