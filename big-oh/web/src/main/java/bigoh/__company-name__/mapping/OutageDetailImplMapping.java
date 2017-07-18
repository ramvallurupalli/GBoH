#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.${company-name}.mapping;

import com.ifactorconsulting.bigoh.client.domain.Dto;
import com.ifactorconsulting.bigoh.client.domain.Status;
import com.ifactorconsulting.bigoh.core.mapping.entity.OutageDetailMapping;
import com.ifactorconsulting.bigoh.core.persistence.entity.OutageDetail;
import org.springframework.stereotype.Component;
import ${package}.bigoh.${company-name}.persistence.OutageDetailImpl;
import com.ifactorconsulting.zest.mapper.AbstractMapping;
import ${package}.bigoh.${company-name}.client.domain.OutageImpl;
import ${package}.bigoh.${company-name}.client.domain.RestorationImpl;

/**
 * //TODO Use this if there is implementation specific outage detail fields: public class OutageDetailImplMapping extends AbstractMapping<OutageDetailImpl>
 */
//@Component
public class OutageDetailImplMapping extends AbstractMapping<OutageDetailImpl> {

    //TODO uncomment and use the below code if implementation specific outagedetail fields
    public OutageDetailImpl from(Dto.Outage from){

        OutageDetailImpl to = new OutageDetailImpl();
        to.setCause(from.getCause());
        to.setCauseShort(from.getCauseShort());
        to.setComments(from.getComments());
        to.setStartedAt(from.getStartedAt());
        to.setEtr(from.getEtr());
        to.setEtrConfidence(from.getEtrConfidence());
        to.setCrewId(from.getCrewId());
        to.setCrewStatus(from.getCrewStatus());
        to.setTotalAffected(from.getTotalCustomersAffected().intValue());
        to.setStatus(Status.ACTIVE);
        //TODO map implementaion specific outage detail fields
        return to;
    }
    public OutageDetailImpl from(OutageImpl from){
        OutageDetailImpl to = new OutageDetailImpl();
        to.setCause(from.getCause());
        to.setCauseShort(from.getCauseShort());
        to.setComments(from.getComments());
        to.setStartedAt(from.getStartedAt());
        to.setEtr(from.getEtr());
        to.setEtrConfidence(from.getEtrConfidence());
        to.setCrewId(from.getCrewId());
        to.setCrewStatus(from.getCrewStatus());
        if(from.getTotalCustomersAffected()!=null){
            to.setTotalAffected(from.getTotalCustomersAffected().intValue());
        }
        to.setStatus(Status.ACTIVE);
        //TODO map implementaion specific outage detail fields
        return to;
    }
    public OutageDetailImpl from(Dto.Restoration from){
        OutageDetailImpl to = new OutageDetailImpl();
        to.setEtrConfidence(from.getConfidence());
        to.setCause(from.getCause());
        to.setComments(from.getComments());
        to.setEtr(from.getRestoredAt());
        to.setStatus(Status.RESTORE);
        return to;
    }

    public OutageDetailImpl from(RestorationImpl from){
        OutageDetailImpl to = new OutageDetailImpl();
        to.setEtrConfidence(from.getConfidence());
        to.setCause(from.getCause());
        to.setComments(from.getComments());
        to.setEtr(from.getRestoredAt());
        to.setStatus(Status.RESTORE);
        return to;
    }

    public OutageDetailImpl from(Dto.Cancellation from){
        OutageDetailImpl to=new OutageDetailImpl();
        to.setStatus(Status.CANCEL);
        return to;
    }




}