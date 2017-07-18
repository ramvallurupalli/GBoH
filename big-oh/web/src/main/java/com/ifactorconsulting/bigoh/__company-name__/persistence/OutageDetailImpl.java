#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

/**
 * TODO determine how to specify in the archetype this class should not be to src/main/java/com/kubra/es/bigoh/
 * and instead to src/main/java/com/ifactorconsulting/bigoh
 *
 * NOTE: any entity class must have a package prefix of 'com.ifactorconsulting.bigoh'
 *
 * see configuration at https://github.com/iFactor/big-oh/blob/master/core/src/main/resources/spring/core/persistence-config.xml#L24
 */
package com.ifactorconsulting.bigoh.${company-name}.persistence;

import com.ifactorconsulting.bigoh.core.persistence.entity.OutageDetail;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TODO to be used only when there is implementation specific outage detail fields
 */
@Entity
@Table(name="OUTAGE_DETAIL_AUX")
public class OutageDetailImpl extends OutageDetail{

//    @Column(name="IMPL_SPECIFIC")
//    private String implSpecific;
//
//    public String getImplSpecific() {
//        return implSpecific;
//    }
//
//    public void setImplSpecific(String implSpecific) {
//        this.implSpecific = implSpecific;
//    }
}