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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bridgedb.DataSource;
import org.bridgedb.IDMapper;
import org.bridgedb.IDMapperCapabilities;
import org.bridgedb.IDMapperException;
import org.bridgedb.Xref;

/**
 * combines multiple {@link IDMapperRdb}'s in a stack.
 * To keep the user interface simple, DoubleGdb is limited
 * to a stack of two but there is no reason why this can't be extended further.
 * <p>
 * The two databases are called metabolite database and gene database,
 * although in principle there is no difference between the two.
 * DoubleGdb won't complain if you try to set two different
 * Gene databases and no metabolite database.
 * <p>
 * The behavior of the {@link IDMapperRdb} interface implementations
 * differs per method:
 * if the method returns a single result, usually it is 
 * from the first child database that has a sensible result.
 * This also means that the child databases have a definitive
 * ordering: the first one shadows the second one for some results.
 * <p>
 * If the method returns a list, DoubleGdb joins
 * the result from all connected child databases together.
 */
public class DoubleGdb extends IDMapperRdb
{
	private static final int GENE_DB = 0;
	private static final int METABOLITE_DB = 1;
	private SimpleGdb[] gdbs = {null, null};
	
	/**
	 * set the metabolite database
	 * closes any pre-existing metabolite databases.
	 * 
	 * @param gdb pass null to close the connection.
	 */
	public void setMetaboliteDb (SimpleGdb gdb)
	{
		if (gdb == gdbs[METABOLITE_DB]) return;

		try
		{
			if (gdbs [METABOLITE_DB] != null) gdbs[METABOLITE_DB].close();
		}
		catch (IDMapperException e)
		{
//			Logger.log.error ("Problem closing metabolite database", e);
			// may be ignored
		}
		gdbs [METABOLITE_DB] = gdb;
	}

	/**
	 * Set the gene database.
	 * Closes any pre-existing gene databases.
	 */
	public void setGeneDb (SimpleGdb gdb)
	{
		if (gdb == gdbs[GENE_DB]) return;
		
		try
		{
			if (gdbs [GENE_DB] != null) gdbs[GENE_DB].close();
		}
		catch (IDMapperException e)
		{
//			Logger.log.error ("Problem closing gene database", e);
			// may be ignored
		}
		gdbs [GENE_DB] = gdb;		
	}

	/**
	 * closes all child databases. 
	 */
	public void close() throws IDMapperException 
	{
		for (SimpleGdb child : gdbs)
		{
			if (child != null)
			{
				child.close();
				child = null; // garbage collect
			}
		}
	}

	/**
	 * Check if the reference exists in either one of the 
	 * child databases
	 * @throws IDMapperException 
	 */
	public boolean xrefExists(Xref xref) throws IDMapperException 
	{
		for (SimpleGdb child : gdbs)
		{
			if (child != null && child.isConnected())
			{
				if(child.xrefExists(xref)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Return the aggregate of the child results.
	 */
	public List<Xref> mapID(Xref idc) throws IDMapperException 
	{	
		List<Xref> result = new ArrayList<Xref>();
		
		for (SimpleGdb child : gdbs)
		{
			if (child != null && child.isConnected())
			{
				result.addAll (child.mapID (idc));
			}
		}
		return result;
	}

	/**
	 * Return the aggregate of the child results.
	 */
	public List<Xref> mapID(Xref idc, DataSource resultDs) throws IDMapperException
	{
		List<Xref> result = new ArrayList<Xref>();
		
		for (SimpleGdb child : gdbs)
		{
			if (child != null && child.isConnected())
			{
				result.addAll (child.mapID (idc, resultDs));
			}
		}
		return result;
	}
	
	/**
	 * This implementation concatenates the dbname's of all
	 * connected child databases.
	 */
	public String getDbName() 
	{
		String result = null;
		for (SimpleGdb child : gdbs)
		{
			if (child != null && child.isConnected())
			{
				if (result == null)
					result = child.getDbName();
				else
					result = result + " and " + child.getDbName();
			}
		}
		return result;		
	}

	/**
	 * Returns true if at least one of the child databases
	 * are connected.
	 */
	public boolean isConnected() 
	{
		for (SimpleGdb child : gdbs)
		{
			if (child != null && child.isConnected())
			{
				return true;
			}
		}
		return false;
	}

	/** {@inheritDoc} */
	public Set<Xref> freeSearch(String text, int limit) throws IDMapperException
	{
		Set<Xref> result = new HashSet<Xref>();
		
		for (SimpleGdb child : gdbs)
		{
			if (child != null && child.isConnected())
			{
				result.addAll (child.freeSearch(text, limit));
			}
			// don't need to continue if we already reached limit.
			if (result.size() >= limit) break;
		}
		return result;
	}

	private final IDMapperCapabilities caps = new IDMapperCapabilities()
	{
		public Set<DataSource> getSupportedSrcDataSources() 
		{
			final Set<DataSource> result = new HashSet<DataSource>();
			for (IDMapper idm : DoubleGdb.this.gdbs)
			{
                Set<DataSource> dss = null;
                try {
                    dss = idm.getCapabilities().getSupportedSrcDataSources();
                } catch (IDMapperException e) {
                    e.printStackTrace(); //TODO: is this the correct logic?
                }

                if (dss!=null) {
                    result.addAll (dss);
                }
			}
			return result;
		}

		public Set<DataSource> getSupportedTgtDataSources() 
		{
			final Set<DataSource> result = new HashSet<DataSource>();
			for (IDMapper idm : DoubleGdb.this.gdbs)
			{
				Set<DataSource> dss = null;
                try {
                    dss = idm.getCapabilities().getSupportedTgtDataSources();
                } catch (IDMapperException e) {
                    e.printStackTrace(); //TODO: is this the correct logic?
                }

                if (dss!=null) {
                    result.addAll (dss);
                }
			}
			return result;
		}

		public boolean isFreeSearchSupported() 
		{
			// returns true if any returns true
			// TODO: not sure if this is the right logic?
			for (IDMapper idm : DoubleGdb.this.gdbs)
			{
				if (idm.getCapabilities().isFreeSearchSupported())
					return true;
			}
			return false;
		}
	};
	
	public IDMapperCapabilities getCapabilities() 
	{
		return caps;
	}

	/** {@inheritDoc} */
	public Set<String> getAttributes(Xref ref, String attrname)
			throws IDMapperException 
	{
		Set<String> result = new HashSet<String>();
		// return the first database with a result.
		for (SimpleGdb child : gdbs)
		{
			if (child != null && child.isConnected())
			{
				result.addAll (child.getAttributes(ref, attrname));
			}
		}
		return result;
	}

	/** {@inheritDoc} */
	public Set<Xref> freeAttributeSearch (String query, String attrType, int limit) throws IDMapperException
	{
		Set<Xref> result = new HashSet<Xref>();
		// return the first database with a result.
		for (SimpleGdb child : gdbs)
		{
			if (child != null && child.isConnected())
			{
				result.addAll (child.freeAttributeSearch(query, attrType, limit));
			}
		}
		return result;
	}
}