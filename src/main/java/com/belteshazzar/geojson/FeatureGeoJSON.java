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

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * A Bean representation of a GeoJSON Feature.
 * 
 * From the GeoJSON Specification version 1.0:
 * 
 * A feature object must have a member with the name "geometry". The value
 * of the geometry member is a geometry object as defined above or a JSON
 * null value.
 * 
 * A feature object must have a member with the name "properties". The value
 * of the properties member is an object (any JSON object or a JSON null
 * value).
 * 
 * If a feature has a commonly used identifier, that identifier should be
 * included as a member of the feature object with the name "id".
 */
public class FeatureGeoJSON extends GeoJSON
{
	public GeoJSON geometry;
	public Map<String,JsonNode> properties;
	public String id;
	
	public FeatureGeoJSON() {
		super();
	}

	public FeatureGeoJSON(String id) {
		super();
		this.id = id;
	}

	public FeatureGeoJSON(GeoJSON geometry) {
		super();
		this.geometry = geometry;
	}

	public FeatureGeoJSON(String id, GeoJSON geometry) {
		super();
		this.id = id;
		this.geometry = geometry;
	}

	public boolean isValid( PositionValidator validator )
	{
		if (geometry==null ) return false;
		if ( !geometry.isValid(validator) ) return false;

		return super.isValid(validator);
	}
}
