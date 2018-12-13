package genefinding;

/**
 * 
 * @author MD Al Mamunur Rashid
 * This class finds a gene in a strand of DNA where the stop codon could be any of the three stop codons “TAA”, “TAG”, or “TGA”.
 * and all the genes (where the stop codon could be any of the three stop codons) in a strand of DNA.
 *
 */
public class FindGene 
{
	//This method returns the index of the first occurrence of stopCodon that appears 
	//past startIndex and is a multiple of 3 away from startIndex. If there is no such stopCodon, 
	//this method returns the length of the dna strand.
	 public int findStopCodon(String dnaStr, int startIndex, String stopCodon)
	    {
	        int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
	        
	        while(currIndex != -1)
	        {
	            int diff = currIndex - startIndex;
	            
	            if(diff % 3 == 0)
	            {
	                return currIndex;
	            }
	            else
	            {
	                currIndex = dnaStr. indexOf(stopCodon, currIndex + 1);
	            }
	        }
	        
	        return   dnaStr.length();
	    }
	    
	    public void testFindStop()
	    {
	        //            01234567890123456789012345
	        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
	        
	        int dex = findStopCodon(dna, 0, "TAA");
	        
	        if(dex != 9) System.out.println("error on 9");
	        
	        dex = findStopCodon(dna, 9, "TAA");
	                
	        if(dex != 21) System.out.println("error on 21");
	        
	        dex = findStopCodon(dna, 1, "TAA");
	        
	        if(dex != 26) System.out.println("error on 26");
	        
	        dex = findStopCodon(dna, 0, "TAG");
	        
	        if(dex != 26) System.out.println("error on 26");
	        
	        System.out.println("Testing finished!");
	    }
	    /*
	     * Find the index of the first occurrence of the start codon “ATG”. If there is no “ATG”, return the empty string.
		 * Find the index of the first occurrence of the stop codon “TAA” after the first occurrence of “ATG” 
		 * that is a multiple of three away from the “ATG”. Hint: call findStopCodon.
		 * Find the index of the first occurrence of the stop codon “TAG” after the first occurrence of “ATG” 
		 * that is a multiple of three away from the “ATG”. Find the index of the first occurrence of the stop codon “TGA” 
		 * after the first occurrence of “ATG” that is a multiple of three away from the “ATG”.
		 * Return the gene formed from the “ATG” and the closest stop codon that is a multiple of three away. 
		 * If there is no valid stop codon and therefore no gene, return the empty string.
	     */
	    public String findGene(String dna, int where)
	    {
	        int startIndex = dna.indexOf("ATG", where);
	        
	        if(startIndex == -1)
	        {
	            return "";
	        }
	        
	        int taaIndex = findStopCodon(dna, startIndex, "TAA");
	        int tagIndex = findStopCodon(dna, startIndex, "TAG");
	        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
	        
	        //int minIndex =Math.min(Math.min(taaIndex, tagIndex),tgaIndex);
	        //int minIndex = Math.min(temp, tgaIndex);
	        
	        int minIndex = 0;
	        
	        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex))
	        {
	             minIndex = tgaIndex;
	            
	        }
	        else
	        {
	            minIndex = taaIndex;
	        }
	        
	        if(minIndex == -1 || (tagIndex != -1 && tagIndex< minIndex))
	        {
	            minIndex = tagIndex;
	        }
	        
	        if(minIndex == -1)
	        {
	            return "";
	        }
	        
	        return dna.substring(startIndex, minIndex + 3);
	    }
	    
	    
	    public void testFindeGene()
	    {
	       String dna = "AATGTAAATAATATAATAGAGATTA";
	       String gene = findGene(dna, 0);
	       if(gene.equals("ATGTAA"));
	       System.out.println("Gene is " + gene);
	       
	       dna = "AATGTTATTAATATAATAGAGATTA";
	       gene = findGene(dna,0);
	       System.out.println("Gene is " + gene); 
	       
	       dna = "AAGGTTATTAATATAATAGAGATTA";
	       gene = findGene(dna,0);
	       System.out.println("Gene is " + gene); 
	    }
	    
	    public void printAllGenes(String dna)
	    {
	        int startIndex = 0;
	        
	        while(true)
	        {
	          String currentGene = findGene(dna, startIndex);  
	          if(currentGene.isEmpty())
	          {
	              break;
	          }
	          
	          System.out.println(currentGene);
	          
	          startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
	          
	        }
	    }
	    /*
	     * This method returns an integer indicating how many times stringa appears in stringb, 
	     * where each occurrence of stringa must not overlap with another occurrence of it. 
	     * For example, the call howMany(“GAA”, “ATGAACGAATTGAATC”) returns 3 as GAA occurs 3 times. 
	     * The call howMany(“AA”, “ATAAAA”) returns 2. Note that the AA’s found cannot overlap.
	     */
	    public int howMany(String stringa, String stringb)
	    {
	         
	        
	        int counter = 0; //this variable keep the number of each occurance of stop codon
	        //int startIndex = 0;
	        int currentIndex = stringb.indexOf(stringa); //keep the first occurance of stop codon
	         
	        int length_stringa = stringa.length();
	        if(currentIndex == -1) //if not stop codon was found, return the empty counter
	        {
	           return counter;
	        }
	        
	        while(currentIndex != -1) //if stop codon was found then continue to count all the occurance 
	        {
	            counter++; //when first occurance of stop codon is found, increase the counter by 1
	            int startIndex = currentIndex + length_stringa; //update the startIndex with founding one
	            currentIndex = stringb.indexOf(stringa, startIndex); //get the next index of stop codon counting from the startIndex
	             
	        }
	        
	        return counter;
	        
	    }
	    
	    public void testHowMany()
	    {
	       int result = howMany("GAA", "ATGAACGAATTGAATC");
	       System.out.println("Reuslt1 " + result);
	       
	       result = howMany("AA", "ATGAACGAATTGAATC");
	       System.out.println("Reuslt2 " + result);
	       
	       result = howMany("AA", "ATAAAA");
	       System.out.println("Reuslt3 " + result);
	    }
	    
	    /*
	     * This method returns the number of genes found in dna. 
	     * For example the call countGenes(“ATGTAAGATGCCCTAGT”) returns 2, finding the gene ATGTAA first and then the gene ATGCCCTAG. 
	     */
	    public int countGenes(String dna)
	    {
	        int startIndex = 0;
	        int counter = 0;
	        
	        while(true)
	        {
	          String currentGene = findGene(dna, startIndex); 
	          
	          if(currentGene.isEmpty())
	          {
	               break;
	          }
	          startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
	          counter++;
	        }
	        return counter;
	    }
	    
	    public void testCountGenes()
	    {
	        int number_Genes = countGenes("ATGTAAGATGCCCTAGT");
	        System.out.println("Number of Genes is: " +number_Genes );
	        
	        number_Genes = countGenes("ATGTAAGATGCCCTAGTATGTAA");
	        System.out.println("Number of Genes is: " +number_Genes );
	        
	        number_Genes = countGenes("ATGTAAGATGCCCTAGTATGTAAGATGCCCTAGTATGTAA");
	        System.out.println("Number of Genes is: " +number_Genes );
	        
	        number_Genes = countGenes("ATGTAAGATGCCCTAGTATGTAAGATGCCCTAGTATGTAAGATGCCCTAGTATGTAA");
	        System.out.println("Number of Genes is: " +number_Genes );
	    }

}
