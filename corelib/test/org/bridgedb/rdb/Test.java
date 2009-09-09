// BridgeDb,
// An abstraction layer for identifer mapping services, both local and online.
// Copyright 2006-2009 BridgeDb developers
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
package org.bridgedb.rdb;

import buildsystem.Measure;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.bridgedb.AttributeMapper;
import org.bridgedb.BridgeDb;
import org.bridgedb.DataSource;
import org.bridgedb.IDMapper;
import org.bridgedb.IDMapperException;
import org.bridgedb.Xref;

/**
 * Test access to the derby client running on the webservice.
 */
public class Test extends TestCase 
{
	private Measure measure;
	
	@Override public void setUp()
	{
		measure = new Measure("bridgedb_timing.txt");
	}

	public void testDerbyClient() throws IDMapperException, ClassNotFoundException
	{
		long start, end, delta;
		start = System.currentTimeMillis();
		Class.forName ("org.bridgedb.rdb.IDMapperRdb");
		DBConnectorDerbyServer.init ("wikipathways.org", 1527);
		IDMapper mapper = BridgeDb.connect ("idmapper-derbyclient:Homo sapiens");
		IDMapper mapper2 = BridgeDb.connect ("idmapper-derbyclient:metabolites");
		end = System.currentTimeMillis();
		delta = end - start;
		measure.add ("timing::idmapper-derbyclient connect to two databases", "" + delta, "msec");
		
		System.out.println (mapper.getCapabilities().getSupportedTgtDataSources());
		
		Set <String> symbols = new HashSet<String>();
		AttributeMapper attr = (AttributeMapper)mapper;
		
		// time the common case of doing a free search and then querying all for symbol
		start = System.currentTimeMillis();
		for (Xref ref : attr.freeAttributeSearch("p53", "symbol", 100))
		{
			symbols.addAll (attr.getAttributes(ref, "Symbol"));
			
		}
		end = System.currentTimeMillis();
		delta = end - start;
		System.out.println (delta);
		measure.add ("timing::idmapper-derbyclient free query for p53", "" + delta, "msec");
		System.out.println (symbols);
		
		// time the case of getting all attributes for backpage info
		start = System.currentTimeMillis();
		Xref insr = new Xref ("ENSG00000171105", DataSource.getBySystemCode("EnHs"));
		for (String x : new String[] {"Description", "Symbol", "Synonyms", "Chromosome"})
		{
			Set<String> result = attr.getAttributes(insr, x);
			assertTrue ("No result for " + x, result.size() > 0);
			System.out.println (result);
		}
		
		end = System.currentTimeMillis();
		delta = end - start;
		measure.add ("timing::idmapper-derbyclient query for backpage attributes", "" + delta, "msec");
		System.out.println (delta);
	}
}