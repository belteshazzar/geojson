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
package com.belteshazzar.geojson.validation;

import java.util.List;

import com.belteshazzar.geojson.PositionValidator;

public class LonLatValidator implements PositionValidator
{
	public boolean isValid(List<Double> position)
	{
		if (position==null) return false;
		if (position.size()!=2) return false;
		double lon = position.get(0);
		if ( lon>180 || lon<-180 ) return false;
		double lat = position.get(1);
		if ( lat>90 || lat<-90 ) return false;
		return true;
	}

	public boolean isValidBB(List<Double> bbox)
	{
		// bbox can be null
		if ( bbox==null ) return true;

		if (bbox.size()!=4) return false;
		
		double minLon = bbox.get(0);
		if ( minLon>180 || minLon<-180 ) return false;
		double minLat = bbox.get(1);
		if ( minLat>90 || minLat<-90 ) return false;
		double maxLon = bbox.get(2);
		if ( maxLon>180 || maxLon<-180 ) return false;
		double maxLat = bbox.get(3);
		if ( maxLat>90 || maxLat<-90 ) return false;
		
		if (minLon>maxLon) return false;
		if (minLat>maxLat) return false;
		
		return true;
	}

	public boolean isEquivalent(List<Double> p1, List<Double> p2)
	{
		for ( int i = 0 ; i<p1.size() ; i++ )
		{
			if ( (double)p1.get(i) != (double)p2.get(i) ) return false;
		}
		return true;
	}
}
