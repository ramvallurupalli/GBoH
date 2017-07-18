#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.bigoh.${company-name}.core.hash;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.hash.Funnels;
import com.google.common.hash.Hasher;
import com.ifactorconsulting.bigoh.core.hash.ArticlesFunnel;
import com.ifactorconsulting.bigoh.core.hash.OhFunnels;
import com.ifactorconsulting.bigoh.core.hash.OutageHasher;
import com.ifactorconsulting.zest.hash.IterableFunnel;
import ${package}.bigoh.${company-name}.client.domain.OutageImpl;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


@Component
public class OutageHasherImpl implements OutageHasher<OutageImpl> {
    private final ArticlesFunnel articlesFunnel = new ArticlesFunnel(OhFunnels.articleFunnel());
    private final IterableFunnel<CharSequence> deliveryNodesFunnel = new IterableFunnel<>(Funnels.stringFunnel(Charsets.UTF_8));

    @Override
    public void hash(Hasher hasher, OutageImpl outage) {
        if (outage.getEtr() != null) {
            hasher.putLong(outage.getEtr().getMillis());
        }
        if (outage.getEtrConfidence() != null) {
            hasher.putInt(outage.getEtrConfidence().ordinal());
        }
        if (outage.getStartedAt() != null) {
            hasher.putLong(outage.getStartedAt().getMillis());
        }
        if (outage.getTotalCustomersAffected() != null) {
            hasher.putInt(outage.getTotalCustomersAffected());
        }
        if (outage.getCrewStatus() != null) {
            hasher.putString(outage.getCrewStatus(), Charset.defaultCharset());
        }
        if (outage.getCause() != null) {
            hasher.putString(outage.getCause(), Charset.defaultCharset());
        }
        if (outage.getComments() != null) {
            hasher.putString(outage.getComments(), Charset.defaultCharset());
        }
        if(outage.getCauseShort()!=null){
            hasher.putString(outage.getCauseShort(),Charset.defaultCharset());
        }
        if(outage.getCommentsInternal()!=null){
            hasher.putString(outage.getCommentsInternal(),Charset.defaultCharset());
        }


        if (outage.getExternalId() != null) {
            hasher.putString(outage.getExternalId(), Charset.defaultCharset());
        }

        //TODO Add any other implementation specific fields to the Hasher

        hasher.putObject(outage.getAffectedArticleNumbers(), articlesFunnel);

        hasher.putObject(sortDeliveryNodes(outage.getAffectedDeliveryNodes()), deliveryNodesFunnel);

    }

    private List<CharSequence> sortDeliveryNodes(Set<String> affectedDeliveryNodes) {
        List<String> nodeList = Lists.newArrayList(affectedDeliveryNodes);
        Collections.sort(nodeList);
        return new ArrayList<CharSequence>(nodeList);
    }

}