// PathVisio,
// a tool for data visualization and analysis using Biological Pathways
// Copyright 2006-2009 BiGCaT Bioinformatics
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bridgedb.AttributeMapper;
import org.bridgedb.BridgeDb;
import org.bridgedb.DataSource;
import org.bridgedb.IDMapper;
import org.bridgedb.IDMapperException;
import org.bridgedb.Xref;

/**
 * Interface for all classes that provide Gdb-like functionality,
 * such as looking up cross-references and backpage text.
 */
public abstract class IDMapperRdb implements IDMapper, AttributeMapper
{
	static
	{
		BridgeDb.register ("idmapper-pgdb", new Driver());
	}
	
	private static final class Driver implements org.bridgedb.Driver
	{
		/** private constructor to prevent instantiation. */
		private Driver() { } 
		
		/** {@inheritDoc} */
		public IDMapper connect(String location) throws IDMapperException 
		{
			return SimpleGdbFactory.createInstance(location, new DataDerby(), 0);
		}
	}
	
	/**
	 * Gets the name of te currently used gene database.
	 * @return the database name as specified in the connection string
	 */
	public abstract String getDbName();
	
	/**
	 * Get all cross-references for the given entity.
	 * @param idc The entity to get the cross references for
	 * @return An {@link List} containing the cross references, or an empty
	 * ArrayList when no cross references could be found
	 * @throws IDMapperException when the database is unavailable
	 */
	public List<Xref> mapID(Xref idc) throws IDMapperException
	{
		return mapID(idc, null);
	}

	/**
	 * Get all cross-references for the given entity, restricting the
	 * result to contain only references from the given set of data sources.
	 * @param idc The id/code pair to get the cross references for
	 * @param resultDs The system code to restrict the results to
	 * @return An {@link List} containing the cross references, or an empty
	 * ArrayList when no cross references could be found
	 * @throws IDMapperException if the mapping service is (temporarily) unavailable 
	 */
	public abstract List<Xref> mapID (Xref idc, DataSource resultDs) throws IDMapperException;
	
	/**
	 * Map a set of Xrefs at once.
	 * @param tgtDataSources only return xrefs from these DataSources.
	 * @param srcXrefs the cross-references that should be mapped
	 * @return Map of source to destination refs. The keys will be a subset of the srcXrefs argument
	 * Implemented using multiple calls of getCrossRefs().
	 * May be overridden if there is a more efficient implementation possible.
	 * @throws IDMapperException if the mapping service is (temporarily) unavailable
	 */
	public Map<Xref, Set<Xref>> mapID(Set<Xref> srcXrefs, Set<DataSource> tgtDataSources) throws IDMapperException 
	{
		final Map<Xref, Set<Xref>> result = new HashMap<Xref, Set<Xref>>();
		for (Xref src : srcXrefs)
		{
			final Set<Xref> refs = new HashSet<Xref>();
			for (Xref dest : mapID(src))
			{
				if (tgtDataSources.contains(dest.getDataSource()))
				{
					refs.add (dest);
				}
			}
			if (refs.size() > 0)
				result.put (src, refs);
		}
		return result;
	}	
}