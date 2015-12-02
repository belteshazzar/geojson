/**
 * Copyright (c) 2015, Daniel Walton (daniel@belteshazzar.com)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from this
 *    software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.belteshazzar.geojson;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * A Bean representation of a GeoJSON base object.
 */
@JsonTypeInfo(use=Id.NAME,include=As.PROPERTY,property="type")
@JsonSubTypes({
	@JsonSubTypes.Type(value=PointGeoJSON.class,				name="Point"),
	@JsonSubTypes.Type(value=MultiPointGeoJSON.class,			name="MultiPoint"),
	@JsonSubTypes.Type(value=LineStringGeoJSON.class,			name="LineString"),
	@JsonSubTypes.Type(value=MultiLineStringGeoJSON.class,		name="MultiLineString"),
	@JsonSubTypes.Type(value=PolygonGeoJSON.class,				name="Polygon"),
	@JsonSubTypes.Type(value=MultiPolygonGeoJSON.class,			name="MultiPolygon"),
	@JsonSubTypes.Type(value=GeometryCollectionGeoJSON.class,	name="GeometryCollection"),
	@JsonSubTypes.Type(value=FeatureGeoJSON.class,				name="Feature"),
	@JsonSubTypes.Type(value=FeatureCollectionGeoJSON.class,	name="FeatureCollection")
})
public abstract class GeoJSON implements Validation
{
	public CRSGeoJSON crs;
	public List<Double> bbox;
	protected Map<String,JsonNode> properties;

	public GeoJSON() {
	}

	@JsonAnySetter
	public void add(String key, JsonNode value)
	{
		properties.put(key, value);
	}

	@JsonAnyGetter
	public Map<String,JsonNode> getProperties()
	{
		return properties;
	}
	    
	public boolean isValid( PositionValidator validator )
	{
		if ( crs!=null && !crs.isValid(validator) ) return false;
		if ( !validator.isValidBB(bbox) ) return false;
		return true;
	}
}
