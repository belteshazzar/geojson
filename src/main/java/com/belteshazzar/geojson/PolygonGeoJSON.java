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

/**
 * A Bean representation of a GeoJSON Polygon geometry object.
 * 
 * From the GeoJSON Specification version 1.0:
 * 
 * For type "Polygon", the "coordinates" member must
 * be an array of LinearRing coordinate arrays. For
 * Polygons with multiple rings, the first must be the
 * exterior ring and any others must be interior rings
 * or holes.
 * 
 * A LinearRing is closed LineString with 4 or more
 * positions. The first and last positions are equivalent
 * (they represent equivalent points).
 *
 * NOTE: The following is NOT checked.
 * For Polygons with multiple rings, the first must be the
 * exterior ring and any others must be interior rings or holes.
 */
public class PolygonGeoJSON extends GeometryGeoJSON
{
	public List<List<List<Double>>> coordinates;
	
	public PolygonGeoJSON() {
		super();
	}
	
	public PolygonGeoJSON(List<List<List<Double>>> coordinates) {
		super();
		this.coordinates = coordinates;
	}

	@Override
	public boolean isValid( PositionValidator validator )
	{
		if (coordinates==null) return false;
		if (coordinates.size()==0) return false;
		
		for ( List<List<Double>> linearRing : coordinates )
		{
			if (linearRing==null) return false;
			if (linearRing.size()<4) return false;

			for ( List<Double> position : linearRing )
			{
				if ( !validator.isValid(position) ) return false;
			}
			
			List<Double> first = linearRing.get(0);
			List<Double> last = linearRing.get(linearRing.size()-1);
			
			if ( !validator.isEquivalent(first,last) ) return false;
		}

		return super.isValid(validator);
	}
}
