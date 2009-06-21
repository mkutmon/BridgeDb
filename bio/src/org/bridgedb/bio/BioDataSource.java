package org.bridgedb.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import org.bridgedb.DataSource;
import org.bridgedb.DataSourcePatterns;

public class BioDataSource 
{
	public static final DataSource TAIR = DataSource.register (
		 "A", "TAIR").asDataSource();
	public static final DataSource AGILENT = DataSource.register (
		"Ag", "Agilent").asDataSource();
	public static final DataSource BIOGRID = DataSource.register (
		"Bg", "BioGrid").asDataSource();
	public static final DataSource CINT = DataSource.register (
		"C", "Cint").asDataSource();
	public static final DataSource CCDS = DataSource.register (
		"Cc", "CCDS").asDataSource();
	public static final DataSource CAS = DataSource.register (
		"Ca", "CAS").asDataSource();
	public static final DataSource CHEBI = DataSource.register (
		"Ce", "ChEBI").asDataSource();
	public static final DataSource HMDB = DataSource.register (
		"Ch", "HMDB").asDataSource();
	public static final DataSource KEGG_COMPOUND = DataSource.register (
		"Ck", "Kegg Compound").asDataSource();
	public static final DataSource PUBCHEM = DataSource.register (
		"Cp", "PubChem").asDataSource();
	public static final DataSource CHEMSPIDER = DataSource.register (
		"Cs", "Chemspider").asDataSource();
	public static final DataSource SGD = DataSource.register (
		"D", "SGD").asDataSource();
	public static final DataSource ENZYME_CODE = DataSource.register (
		"E", "EC Number").asDataSource();
	public static final DataSource ECOLI = DataSource.register (
		"Ec", "Ecoli").asDataSource();
	public static final DataSource EMBL = DataSource.register (
		"Em", "EMBL").asDataSource();
	/** @deprecated use one of the organism-specific system codes instead */ 
	public static final DataSource ENSEMBL = DataSource.register (
		"En", "Ensembl").asDataSource();
	public static final DataSource ENSEMBL_MOSQUITO = DataSource.register (
		"EnAg", "Ensembl Mosquito").asDataSource();
	public static final DataSource GRAMENE_ARABIDOPSIS = DataSource.register (
		"EnAt", "Gramene Arabidopsis").asDataSource();
	public static final DataSource ENSEMBL_BSUBTILIS = DataSource.register (
		"EnBs", "Ensembl B. subtilis").asDataSource();
	public static final DataSource ENSEMBL_COW = DataSource.register (
		"EnBt", "Ensembl Cow").asDataSource();
	public static final DataSource ENSEMBL_CELEGANS = DataSource.register (
		"EnCe", "Ensembl C. elegans").asDataSource();
	public static final DataSource ENSEMBL_DOG = DataSource.register (
		"EnCf", "Ensembl Dog").asDataSource();
	public static final DataSource ENSEMBL_FRUITFLY = DataSource.register (
		"EnDm", "Ensembl Fruitfly").asDataSource();
	public static final DataSource ENSEMBL_ZEBRAFISH = DataSource.register (
		"EnDr", "Ensembl Zebrafish").asDataSource();
	public static final DataSource ENSEMBL_ECOLI = DataSource.register (
		"EnEc", "Ensembl E. coli").asDataSource();
	public static final DataSource ENSEMBL_CHICKEN = DataSource.register (
		"EnGg", "Ensembl Chicken").asDataSource(); 
	public static final DataSource ENSEMBL_HUMAN = DataSource.register (
		"EnHs", "Ensembl Human").asDataSource();
	public static final DataSource ENSEMBL_MOUSE = DataSource.register (
		"EnMm", "Ensembl Mouse").asDataSource();
	public static final DataSource GRAMENE_RICE = DataSource.register (
		"EnOj", "Gramene Rice").asDataSource();
	public static final DataSource ENSEMBL_CHIMP = DataSource.register (
		"EnPt", "Ensembl Chimp").asDataSource();
	public static final DataSource ENSEMBL_HORSE = DataSource.register (
		"EnQc", "Ensembl Horse").asDataSource();
	public static final DataSource ENSEMBL_RAT = DataSource.register (
		"EnRn", "Ensembl Rat").asDataSource();
	public static final DataSource ENSEMBL_SCEREVISIAE = DataSource.register (
		"EnSc", "Ensembl Yeast").asDataSource();
	public static final DataSource ENSEMBL_XENOPUS = DataSource.register (
		"EnXt", "Ensembl Xenopus").asDataSource();
	public static final DataSource FLYBASE = DataSource.register (
		"F", "FlyBase").asDataSource();
	public static final DataSource GENBANK = DataSource.register (
		"G", "GenBank").asDataSource(); 
	public static final DataSource CODELINK = DataSource.register (
		"Ge", "CodeLink").asDataSource(); 
	public static final DataSource GRAMENE_GENES_DB = DataSource.register (
		"Gg", "Gramene Genes DB").asDataSource(); 
	public static final DataSource GRAMENE_LITERATURE = DataSource.register (
		"Gl", "Gramene Literature").asDataSource();
	public static final DataSource GRAMENE_PATHWAY = DataSource.register (
		"Gp", "Gramene Pathway").asDataSource();
	public static final DataSource GEN_PEPT = DataSource.register (
		"Gp", "GenPept").asDataSource(); 
	public static final DataSource HUGO = DataSource.register (
		"H", "HUGO").asDataSource();
	public static final DataSource HSGENE = DataSource.register (
		"Hs", "HsGene").asDataSource();
	public static final DataSource INTERPRO = DataSource.register (
		"I", "InterPro").asDataSource();
	public static final DataSource ILLUMINA = DataSource.register (
		"Il", "Illumina").asDataSource(); 
	public static final DataSource IPI = DataSource.register (
		"Ip", "IPI").asDataSource(); 
	public static final DataSource IRGSP_GENE = DataSource.register (
		"Ir", "IRGSP Gene").asDataSource();
	public static final DataSource ENTREZ_GENE = DataSource.register (
		"L", "Entrez Gene").asDataSource();
	public static final DataSource MGI = DataSource.register (
		"M", "MGI").asDataSource();
	public static final DataSource MIRBASE = DataSource.register (
		"Mb", "miRBase").asDataSource();
	public static final DataSource MAIZE_GDB = DataSource.register (
		"Mg", "MaizeGDB").asDataSource();
	public static final DataSource NASC_GENE = DataSource.register (
		"N", "NASC Gene").asDataSource(); 
	public static final DataSource NUGOWIKI = DataSource.register (
		"Nw", "NuGO wiki").asDataSource();
	public static final DataSource OTHER = DataSource.register (
		"O", "Other").asDataSource();
	public static final DataSource ORYZA_BASE = DataSource.register (
		"Ob", "Oryzabase").asDataSource();
	public static final DataSource OMIM = DataSource.register (
		"Om", "OMIM").asDataSource();
	public static final DataSource RICE_ENSEMBL_GENE = DataSource.register (
		"Os", "Rice Ensembl Gene").asDataSource();
	public static final DataSource PDB = DataSource.register (
		"Pd", "PDB").asDataSource();
	public static final DataSource PFAM = DataSource.register (
		"Pf", "Pfam").asDataSource();
	public static final DataSource PLANTGDB = DataSource.register (
		"Pl", "PlantGDB").asDataSource(); 
	public static final DataSource REFSEQ = DataSource.register (
		"Q", "RefSeq").asDataSource();
	public static final DataSource RGD = DataSource.register (
		"R", "RGD").asDataSource(); 
	public static final DataSource RFAM = DataSource.register (
		"Rf", "Rfam").asDataSource();
	public static final DataSource UNIPROT = DataSource.register (
		"S", "Uniprot/TrEMBL").asDataSource();
	public static final DataSource SNP = DataSource.register (
		"Sn", "dbSNP").asDataSource();
	public static final DataSource GENE_ONTOLOGY = DataSource.register (
		"T", "GeneOntology").asDataSource();
	public static final DataSource TIGR = DataSource.register (
		"Ti", "TIGR").asDataSource(); 
	public static final DataSource UNIGENE = DataSource.register (
		"U", "UniGene").asDataSource();
	public static final DataSource UCSC = DataSource.register (
		"Uc", "UCSC Genome Browser").asDataSource();
	public static final DataSource WORMBASE = DataSource.register (
		"W", "WormBase").asDataSource();
	public static final DataSource WIKIPEDIA = DataSource.register (
		"Wi", "Wikipedia").asDataSource();
	public static final DataSource WHEAT_GENE_CATALOG = DataSource.register (
		"Wc", "Wheat gene catalog").asDataSource(); 
	public static final DataSource WHEAT_GENE_NAMES = DataSource.register (
		"Wn", "Wheat gene names").asDataSource();
	public static final DataSource WHEAT_GENE_REFERENCES= DataSource.register (
		"Wr", "Wheat gene refs").asDataSource();
	public static final DataSource AFFY = DataSource.register (
		"X", "Affy").asDataSource();
	public static final DataSource ZFIN = DataSource.register (
		"Z", "ZFIN").asDataSource();

	/* 
	 * Make patterns of regular expressions for matching 
	 * the gene identifiers with specific gene databases.
	 * (see, 
	 * http://www.childrens-mercy.org/stats/model/arrayDataManagement.htm ) 
	 */	

	static {
		DataSourcePatterns.registerPattern(
			BioDataSource.SGD, 
			Pattern.compile("S\\d{9}"));
		DataSourcePatterns.registerPattern(
			BioDataSource.FLYBASE, 
			Pattern.compile("(C[RG]\\d{4,5}|FBgn\\d{7})")
		);		
		//genbank (http://www.ncbi.nlm.nih.gov/Sequin/acc.html)		
		DataSourcePatterns.registerPattern(
				BioDataSource.GENBANK, 
				Pattern.compile("(\\w\\d{5})|(\\w{2}\\d{6})|(\\w{3}\\d{5})")
		);
		//interpro
		DataSourcePatterns.registerPattern(
				BioDataSource.INTERPRO, 				
				Pattern.compile("IPR\\d{6}")
			);
		//entrez gene
		DataSourcePatterns.registerPattern(
				BioDataSource.ENTREZ_GENE, 
				Pattern.compile("\\d+")
		);

		//MGI
		DataSourcePatterns.registerPattern(
				BioDataSource.MGI, 
				Pattern.compile("MGI:\\d+")
		);

		DataSourcePatterns.registerPattern (
				BioDataSource.RFAM,
				Pattern.compile ("RF\\d+")
		);
		DataSourcePatterns.registerPattern (
				BioDataSource.IPI,
				Pattern.compile ("IPI\\d+")
		);
		DataSourcePatterns.registerPattern (
				BioDataSource.UCSC,
				Pattern.compile ("uc\\d{3}[a-z]{3}\\.\\d")
		);
		DataSourcePatterns.registerPattern (
				BioDataSource.ILLUMINA,
				Pattern.compile ("ILMN_\\d+")
		);
		DataSourcePatterns.registerPattern (
				BioDataSource.MIRBASE,
				Pattern.compile ("MI\\d+")
		);
		//refseq
		DataSourcePatterns.registerPattern(
				BioDataSource.REFSEQ, 
				Pattern.compile("\\w{2}_\\d+")
		);

		//RGD
		DataSourcePatterns.registerPattern(
				BioDataSource.RGD, 
				Pattern.compile("RGD:\\d+")
		);

		//Swiss Prot (http://expasy.org/sprot/userman.html#AC_line)
		DataSourcePatterns.registerPattern(
				BioDataSource.UNIPROT, 
				Pattern.compile("([A-N,R-][0-9][A-Z][A-Z,0-9][A-Z,0-9][0-9])|([O,P,Q][0-9][A-Z,0-9][A-Z,0-9][A-Z,0-9][0-9])")
		);

		//gene ontology
		DataSourcePatterns.registerPattern(
				BioDataSource.GENE_ONTOLOGY, 
				Pattern.compile("GO:\\d+")
		);

		//unigene
		DataSourcePatterns.registerPattern(
				BioDataSource.UNIGENE, 
				Pattern.compile("[A-Z][a-z][a-z]?\\.\\d+")
		);

		//Wormbase
		DataSourcePatterns.registerPattern(
				BioDataSource.WORMBASE, 
				Pattern.compile("WBGene\\d{8}")
		);

		//affymetrix
		DataSourcePatterns.registerPattern(
				BioDataSource.AFFY, 
				Pattern.compile(".+_at")
		);

		//Ensemble
		DataSourcePatterns.registerPattern(
				BioDataSource.ENSEMBL_HUMAN, 
				Pattern.compile("ENSG\\d{11}")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.ENSEMBL_MOUSE, 
				Pattern.compile("ENSMUSG\\d{11}")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.ENSEMBL_RAT, 
				Pattern.compile("ENSRNOG\\d{11}")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.ENSEMBL_MOSQUITO, 
				Pattern.compile("AGAP\\d{6}")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.ENSEMBL_BSUBTILIS, 
				Pattern.compile("EBBACG\\d{11}")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.ENSEMBL_ECOLI, 
				Pattern.compile("EBESCG\\d{11}")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.ENSEMBL_CHICKEN, 
				Pattern.compile("ENSGALG\\d{11}")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.ENSEMBL_HORSE, 
				Pattern.compile("ENSECAG\\d{11}")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.ENSEMBL_XENOPUS, 
				Pattern.compile("ENSXETG\\d{11}")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.ENSEMBL_CHIMP, 
				Pattern.compile("ENSPTRG\\d{11}")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.ENSEMBL_COW, 
				Pattern.compile("ENSBTAG\\d{11}")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.ENSEMBL_DOG, 
				Pattern.compile("ENSCAFG\\d{11}")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.ENSEMBL_ZEBRAFISH, 
				Pattern.compile("ENSDARG\\d{11}")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.ENSEMBL_FRUITFLY, 
				Pattern.compile("FBgn\\d{7}")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.TAIR,
				Pattern.compile("AT[\\dCM]G\\d{5}")
				);
		DataSourcePatterns.registerPattern(
				BioDataSource.GRAMENE_ARABIDOPSIS,
				Pattern.compile("AT[\\dCM]G\\d{5}\\-TAIR\\-G")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.IRGSP_GENE,
				Pattern.compile("Os\\d{2}g\\d+")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.GRAMENE_GENES_DB,
				Pattern.compile("GR:\\d+")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.BIOGRID,
				Pattern.compile("\\d+")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.NASC_GENE,
				Pattern.compile("AT[\\dCM]G\\d{5}\\-TAIR\\-G")
		);
		DataSourcePatterns.registerPattern(
				BioDataSource.PLANTGDB,
				Pattern.compile("PUT-[\\w\\d-]+")
		);
		//EMBL		
		DataSourcePatterns.registerPattern(
				BioDataSource.EMBL,
				Pattern.compile("\\w{2}\\d{6}")
		);
		
		//HUGO
		DataSourcePatterns.registerPattern(
				BioDataSource.HUGO,
				Pattern.compile("\\d+")
		);
		
		//OMIM (http://www.ncbi.nlm.nih.gov/Omim/omimfaq.html#numbering_system)		
		DataSourcePatterns.registerPattern(
				BioDataSource.OMIM,		
				Pattern.compile("\\d{6}(\\.\\d{4})?")
		);

		//PDB ( http://www.rcsb.org/robohelp_f/#search_database/query_results.htm )
		DataSourcePatterns.registerPattern(
				BioDataSource.PDB, 
				Pattern.compile("\\d[A-Z\\d]{3}")
		);

		//Pfam (http://pfam.sanger.ac.uk/help)
		DataSourcePatterns.registerPattern(
			BioDataSource.PFAM,		
			Pattern.compile("(PF\\d{5})|(PB\\d{6})")
		);

		//Zfin (http://zfin.org/zf_info/dbase/PAPERS/ZFIN_DataModel/sectioniv_1.html)
		DataSourcePatterns.registerPattern(
				BioDataSource.ZFIN, 
				Pattern.compile("ZDB.+")
		);
		
		DataSourcePatterns.registerPattern(
				BioDataSource.AGILENT,
				Pattern.compile("A_\\d+_.+")
		);

		DataSourcePatterns.registerPattern(
				BioDataSource.HMDB,
				Pattern.compile("HMDB\\d{5}")
		);

		DataSourcePatterns.registerPattern(
				BioDataSource.CAS,
				Pattern.compile("\\d+-\\d+-\\d+")
		);
		
		DataSourcePatterns.registerPattern(
				BioDataSource.ENZYME_CODE,
				Pattern.compile("(\\d+\\.){3}\\d+")
		);

		DataSourcePatterns.registerPattern(
				BioDataSource.CHEBI,
				Pattern.compile("CHEBI\\:\\d+")
		);

		DataSourcePatterns.registerPattern(
				BioDataSource.KEGG_COMPOUND,
				Pattern.compile("C\\d+")
		);
	}
	
	/** Call this to initialize the BioDataSource.XXX constants. 
	 * 	You should call this before using any of these constants, 
	 * 	or they may be undefined.
	 */
	public static void init() 
	{
		InputStream is = BioDataSource.class.getClassLoader().getResourceAsStream("org/bridgedb/bio/datasources.txt");
		BufferedReader reader = new BufferedReader (
				new InputStreamReader (is));
		String line;
		try
		{
			while ((line = reader.readLine()) != null)
			{
				String[] fields = line.split ("\\t");
				DataSource.Builder builder = DataSource.register
					(fields[1], // system code 
					fields[0]); // gpml name
				if (fields.length > 2 && fields[2].length() > 0) builder.mainUrl(fields[2]);
				if (fields.length > 3 && fields[3].length() > 0) builder.urlPattern(fields[3]);
				if (fields.length > 4 && fields[4].length() > 0) builder.idExample(fields[4]);
				if (fields.length > 5 && fields[5].length() > 0) builder.type(fields[5]);
				if (fields.length > 6 && fields[6].length() > 0) builder.organism(Organism.fromLatinName(fields[6]));					      
				if (fields.length > 7) builder.primary (fields[7].equals ("1"));					      
			}
		}
		catch (IOException ex)
		{
			throw new Error(ex);
		}
	}
	
}