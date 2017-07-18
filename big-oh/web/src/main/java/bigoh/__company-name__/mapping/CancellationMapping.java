#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.${company-name}.mapping;

import com.ifactorconsulting.bigoh.client.domain.Dto;
import com.ifactorconsulting.zest.mapper.AbstractMapping;
import generated.Outages.CancellationWrapper.Cancellation;
import org.springframework.stereotype.Component;

//TODO Use only when cancellation are explicitly specified in the feed
@Component
public class CancellationMapping extends AbstractMapping<Dto.Cancellation> {

    public Dto.Cancellation from(Cancellation from){
        Dto.Cancellation to =new Dto.Cancellation();
        to.setExternalId(from.getId());
        return to;
    }

}
