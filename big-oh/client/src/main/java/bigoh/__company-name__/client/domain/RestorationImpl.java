#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.bigoh.${company-name}.client.domain;

import com.ifactorconsulting.bigoh.client.domain.ArticleNumber;
import com.ifactorconsulting.bigoh.client.domain.Dto.Restoration;


public class RestorationImpl extends Restoration{

    //TODO Specify any implementation specific restoration fields
    //private String implSpecificField;

    @Override
    public Restoration copyForArticle(ArticleNumber articleNumber) {
        RestorationImpl restoration = new RestorationImpl();
        restoration.setExternalId(this.getExternalId());
        restoration.setRestoredAt(this.getRestoredAt());
        restoration.setConfidence(this.getConfidence());
        restoration.setCause(this.getCause());
        restoration.setComments(this.getComments());
        restoration.setCommentsInternal(this.getCommentsInternal());
        restoration.getAffectedArticles().add(articleNumber);
        if(restoration.getReportedArticles().contains(articleNumber)){
            restoration.getReportedArticles().add(articleNumber);
        }
        //TODO
        //restoration.setImplSpecificField(this.implSpecificField());
        return restoration;
    }
}