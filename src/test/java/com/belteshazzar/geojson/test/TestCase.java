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
package com.belteshazzar.geojson.test;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.URL;

import org.junit.Test;

import com.belteshazzar.geojson.GeoJSON;
import com.belteshazzar.geojson.validation.LonLatValidator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestCase
{
	@Test
	public void testFeatureCollection01()
	{
		test();
	}

	@Test
	public void testFeatureCollection02()
	{
		test();
	}

	@Test
	public void testGeometryCollection01()
	{
		test();
	}

	@Test
	public void testLineString01()
	{
		test();
	}

	@Test
	public void testMultiLineString01()
	{
		test();
	}

	@Test
	public void testMultiPoint01()
	{
		test();
	}

	@Test
	public void testMultiPolygon01()
	{
		test();
	}

	@Test
	public void testPoint01()
	{
		test();
	}

	@Test
	public void testPolygonNoHoles01()
	{
		test();
	}

	@Test
	public void testPolygonWithHoles01()
	{
		test();
	}

	private void test()
	{
		System.out.println("running test");

		String filename = null;
		try
		{
			throw new Exception();
		}
		catch ( Exception ex )
		{
			try
			{
				System.out.println("Called by: " + ex.getStackTrace()[1].getMethodName());
				filename = ex.getStackTrace()[1].getMethodName().substring(4);
				System.out.println("filename = " + filename);
			}
			catch (IndexOutOfBoundsException iex)
			{
				iex.printStackTrace();
				fail("Could not get filename.");
			}
		}
		
		filename = "/" + filename + ".json";
		System.out.println("filename = " + filename);

		URL url = this.getClass().getResource(filename);

		System.out.println("url = " + url);
		try
		{
			ObjectMapper mapper = new ObjectMapper();

			GeoJSON geojson = mapper.readValue(url, GeoJSON.class);
			
			System.out.println("parsed: " + geojson);
			
			System.out.println("isValid = " + geojson.isValid(new LonLatValidator()));
		}
		catch (JsonParseException e)
		{
			e.printStackTrace();
			fail();
		}
		catch (JsonMappingException e)
		{
			e.printStackTrace();
			fail();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			fail();
		}
	}
}
