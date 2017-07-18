#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.${company-name};

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ifactorconsulting.bigoh.core.area.AreaService;
import com.ifactorconsulting.bigoh.core.area.internal.AreaServiceImpl;
import com.ifactorconsulting.bigoh.core.batch.FileHandler;
import com.ifactorconsulting.bigoh.core.persistence.entity.Area;
import com.ifactorconsulting.bigoh.core.persistence.entity.transients.DeliveryNodeLookup;
import com.ifactorconsulting.bigoh.core.service.CustomerLookupService;
import com.ifactorconsulting.es.alliant.premise.schema.premiselist.AlliantPremise;
import com.ifactorconsulting.es.alliant.premise.schema.premiselist.Premises;
import com.ifactorconsulting.zest.batch.BatchFile;
import com.ifactorconsulting.zest.batch.FileException;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXParseException;
import ${package}.bigoh.ext.RetryingFileUnmarshaller;
import java.io.InputStream;



public class PremiseFileHandler implements FileHandler<Void> {

    private static final Logger logger = LoggerFactory.getLogger(PremiseFileHandler.class);

    private CustomerLookupService customerLookupService;
    private AreaService areaService;
    private RetryingFileUnmarshaller unmarshaller;
    private Float minLatitude;
    private Float maxLatitude;
    private Float minLongitude;
    private Float maxLongitude;

    private String subscriptionsDir;

    @Autowired
    public void setUnmarshaller(RetryingFileUnmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    @Autowired
    public void setAreaService(AreaService areaService) {
        this.areaService = areaService;
    }

    @Autowired
    public void setCustomerLookupService(CustomerLookupService customerLookupService) {
        this.customerLookupService = customerLookupService;
    }

    /** (optional) min/max values for lat/long used for generating premise reject file / coordinate cleansing */

    @Value("${symbol_dollar}{bigoh.delivery_node.filter.max_longitude:#{null}}")
    public void setMaxLongitude(Float maxLongitude) {
        this.maxLongitude = maxLongitude;
    }

    @Value("${symbol_dollar}{bigoh.delivery_node.filter.min_longitude:#{null}}")
    public void setMinLongitude(Float minLongitude) {
        this.minLongitude = minLongitude;
    }

    @Value("${symbol_dollar}{bigoh.delivery_node.filter.max_latitude:#{null}}")
    public void setMaxLatitude(Float maxLatitude) {
        this.maxLatitude = maxLatitude;
    }

    @Value("${symbol_dollar}{bigoh.delivery_node.filter.min_latitude:#{null}}")
    public void setMinLatitude(Float minLatitude) {
        this.minLatitude = minLatitude;
    }


    @Override
    public Void handle(BatchFile batchFile) throws Exception {
        logger.info("Loading premise file {}", batchFile.getFullName());

        InputStream fileInputStream = batchFile.getContents();

        /** TODO unmarshall from JSON/XML file using the unmarshaller  */

        /** TODO handle premise data... **/

        return null; // nothing is returned
    }




}
