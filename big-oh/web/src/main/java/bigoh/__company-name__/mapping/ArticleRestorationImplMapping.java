#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.${company-name}.mapping;

import com.ifactorconsulting.bigoh.core.outage.OutageService;
import com.ifactorconsulting.bigoh.core.persistence.entity.OutageDetail;
import com.ifactorconsulting.zest.mapper.AbstractMapping;
import ${package}.bigoh.${company-name}.client.domain.ArticleRestorationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Provider;

//TODO Use only if any implementation specific article restoration fields
@Component
public class ArticleRestorationImplMapping extends AbstractMapping<ArticleRestorationImpl> {

    @Autowired
    private Provider<OutageService> outageService;

    public ArticleRestorationImpl from(OutageDetail from){

        ArticleRestorationImpl to =  new ArticleRestorationImpl();
        to.setExternalId(outageService.get().externalId(from));
        to.setCause(from.getCause());
        to.setComments(from.getComments());
        to.setResolvedAt(from.getEtr());
        //TODO map any implementation specific article restoration field
        return  to;
    }

}
