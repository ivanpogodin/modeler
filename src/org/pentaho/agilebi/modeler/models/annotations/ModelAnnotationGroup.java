/*!
 * PENTAHO CORPORATION PROPRIETARY AND CONFIDENTIAL
 *
 * Copyright 2002 - 2014 Pentaho Corporation (Pentaho). All rights reserved.
 *
 * NOTICE: All information including source code contained herein is, and
 * remains the sole property of Pentaho and its licensors. The intellectual
 * and technical concepts contained herein are proprietary and confidential
 * to, and are trade secrets of Pentaho and may be covered by U.S. and foreign
 * patents, or patents in process, and are protected by trade secret and
 * copyright laws. The receipt or possession of this source code and/or related
 * information does not convey or imply any rights to reproduce, disclose or
 * distribute its contents, or to manufacture, use, or sell anything that it
 * may describe, in whole or in part. Any reproduction, modification, distribution,
 * or public display of this information without the express written authorization
 * from Pentaho is strictly prohibited and in violation of applicable laws and
 * international treaties. Access to the source code contained herein is strictly
 * prohibited to anyone except those individuals and entities who have executed
 * confidentiality and non-disclosure agreements or other agreements with Pentaho,
 * explicitly covering such access.
 */
package org.pentaho.agilebi.modeler.models.annotations;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.pentaho.agilebi.modeler.models.annotations.data.DataProvider;
import org.pentaho.metastore.persist.MetaStoreAttribute;
import org.pentaho.metastore.persist.MetaStoreElementType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MetaStoreElementType( name = "ModelAnnotationGroup", description = "ModelAnnotationGroup" )
public class ModelAnnotationGroup extends ArrayList<ModelAnnotation> {

  @MetaStoreAttribute
  private String id;

  @MetaStoreAttribute
  private String name;

  @MetaStoreAttribute
  private String description;

  @MetaStoreAttribute
  private boolean sharedDimension;

  @MetaStoreAttribute
  private List<DataProvider> dataProviders = new ArrayList<DataProvider>();

  @MetaStoreAttribute
  private List<ModelAnnotation> modelAnnotations; // indicate to metastore to persist items (calls the getter/setter)

  public ModelAnnotationGroup() {
    super();
  }

  public ModelAnnotationGroup( ModelAnnotation... modelAnnotations ) {
    super( Arrays.asList( modelAnnotations ) );
  }

  public String getId() {
    return id;
  }

  public void setId( final String id ) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName( String name ) {
    this.name = name;
  }

  public List<ModelAnnotation> getModelAnnotations() {
    return this;
  }

  public void setModelAnnotations( List<ModelAnnotation> modelAnnotations ) {
    removeRange( 0, this.size() ); // remove all
    if ( modelAnnotations != null ) {
      addAll( modelAnnotations );
    }
  }

  public String getDescription() {
    return description;
  }

  public void setDescription( String description ) {
    this.description = description;
  }

  public boolean isSharedDimension() {
    return sharedDimension;
  }

  public void setSharedDimension( boolean sharedDimension ) {
    this.sharedDimension = sharedDimension;
  }

  public List<DataProvider> getDataProviders() {
    return dataProviders;
  }

  public void setDataProviders( List<DataProvider> dataProviders ) {
    this.dataProviders = dataProviders;
  }

  @Override
  public boolean equals( Object obj ) {

    try {
      if ( !EqualsBuilder.reflectionEquals( this, obj ) ) {
        return false;
      }

      // manually check annotations
      ModelAnnotationGroup objGroup = (ModelAnnotationGroup) obj;
      if ( this.size() != objGroup.size() ) {
        return false;
      }

      for ( int i = 0; i < this.size(); i++ ) {
        if ( !this.get( i ).equals( objGroup.get( i ) ) ) {
          return false;
        }
      }

      return true;
    } catch ( Exception e ) {
      return false;
    }
  }
}