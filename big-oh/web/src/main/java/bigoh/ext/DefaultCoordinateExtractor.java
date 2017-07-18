package ${package}.bigoh.ext;

import com.ifactorconsulting.bigoh.core.extraction.AbstractCoordinateExtractor;
import com.ifactorconsulting.bigoh.core.persistence.entity.Coordinate;
import com.ifactorconsulting.bigoh.core.persistence.entity.OutageDetail;

import javax.annotation.Nonnull;
import java.util.Set;

public class DefaultCoordinateExtractor extends AbstractCoordinateExtractor<OutageDetail> {

    @Nonnull
    @Override
    public Set<Coordinate> processCoordinates(
            @Nonnull OutageDetail outageDetail,
            @Nonnull Set<Coordinate> detailCoordinates,
            @Nonnull Set<Coordinate> deliverNodeCoordinates) {

        return detailCoordinates.isEmpty()
                ? deliverNodeCoordinates
                : detailCoordinates;
    }

}
