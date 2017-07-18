#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.${company-name}.mapping;

import com.ifactorconsulting.bigoh.core.outage.OutageService;
import com.ifactorconsulting.bigoh.core.persistence.entity.OutageDetail;
import com.ifactorconsulting.zest.mapper.AbstractMapping;
import ${package}.bigoh.${company-name}.client.domain.ArticleOutageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Provider;


/**
 * TODO Use this only if there is implementation specific article outage fields
 */
@Component
public class ArticleOutageImplMapping extends AbstractMapping<ArticleOutageImpl> {
    @Autowired
    private Provider<OutageService> outageService;
    public ArticleOutageImpl from(OutageDetail from){
        ArticleOutageImpl to=new ArticleOutageImpl();
        to.setExternalId(outageService.get().externalId(from));
        to.setHash(from.getHash());
        to.setStatus(from.getStatus());
        to.setStartedAt(from.getStartedAt());
        to.setConfidence(from.getEtrConfidence());
        to.setEtr(from.getEtr());
        to.setOutageConfirmed(from.getOutageConfirmed());
        to.setCrewId(from.getCrewId());
        to.setCrewStatus(from.getCrewStatus());
        to.setCause(from.getCause());
        to.setCauseShort(from.getCauseShort());
        to.setComments(from.getComments());
        to.setTotalCustomerAffected(from.getTotalAffected());
        to.setStatus(from.getStatus());
        return to;

    }
}
