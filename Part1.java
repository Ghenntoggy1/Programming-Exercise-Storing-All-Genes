import edu.duke.*;

public class Part1 {
     public int findStopCodon (String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }
    
    public String findGene (String dna, int where) {
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(Math.min(taaIndex, tagIndex), tgaIndex);
        
        if (minIndex == dna.length()) {
            return "";
        }
        else {
            return dna.substring(startIndex, minIndex + 3);
        }
    }
    
    public void testFindGene () {
        String dna = "TGACGAATTTGATAA";  // no ATG;
        System.out.println("DNA strand is " + dna);
        String gene = findGene(dna, 0);
        System.out.println("Gene is " + gene);
        
        dna = "TGATGTCAATAATTTGA";  // ATG + one valid stop codon;
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene is " + gene);
        
        dna = "CGATGATGTCAAGCTAATTTTGA";  // ATG + multiple valid stop codon;
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene is " + gene);
        
        dna = "ATGCGATCATGTCAAGCTAATTTTGA";  // ATG + no valid stop codon;
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene is " + gene);
        
        dna = "ATGCGATGATGTCAAGCTAATTTTGA";  // ATG + multiple valid stop codon;
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene is " + gene);
        
        dna = "AATGCTAACTAGCTGACTAAT";  // ATG + multiple valid stop codon;
        System.out.println("DNA strand is " + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene is " + gene);
    }
    
    public StorageResource getAllGenes (String dna) {
        StorageResource genesList = new StorageResource();
        int startIndex = 0;
        while (true) {
            String currGene = findGene(dna, startIndex);
            if (currGene.isEmpty()) {
                break;
            }
            genesList.add(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return genesList;
    }
    
    public void testOnGet (String dna) {
        System.out.println("Testing printAllGenes on " + dna);
        StorageResource genes = getAllGenes(dna);
        for (String gene : genes.data()) {
            System.out.println(gene);
        }
    }
    
    public void testGetAll () {
        testOnGet("TGACGAATTTGATAA");   // no ATG;
        testOnGet("");
        testOnGet("TGATGTCAATAATTTGA");  // ATG + one valid stop codon;
        testOnGet("CGATGATGTCAAGCTAATTTTGA");  // ATG + multiple valid stop codon;
        testOnGet("ATGCGATCATGTCAAGCTAATTTTGA");  // ATG + no valid stop codon;
        testOnGet("ATGCGATGATGTCAAGCTAATTATGTTCGATTAGTTGATTAATGCGCGTGATGTGA");  // 3 x ATG + multiple valid stop codon;
        testOnGet("ATGCGATGATATGAAGCTAATTTGA");  // 2 x ATG + multiple valid stop codon;
    }
    
    public void testFindStopCodon () {
        String dna = "ATGTCACAGTGA";  // TGA index 9
        System.out.println("DNA strand is " + dna);
        int index = findStopCodon(dna, 0, "TGA");
        System.out.println("Index of stop codon is " + index);
        
        dna = "ATGTCACATGAATAG";  // TAG index 12 present TGA
        System.out.println("DNA strand is " + dna);
        index = findStopCodon(dna, 0, "TAG");
        System.out.println("Index of stop codon is " + index);
        
        dna = "ATGTAACAGTAA";  // TAA index 3 present TAA
        System.out.println("DNA strand is " + dna);
        index = findStopCodon(dna, 0, "TAA");
        System.out.println("Index of stop codon is " + index);
        
        dna = "ATGTCACAGTCA";  // nothing
        System.out.println("DNA strand is " + dna);
        index = findStopCodon(dna, 0, "TGA");
        System.out.println("Index of stop codon is " + index);
        
        dna = "ATGTCATGATAATAG";  // TGA index 6
        System.out.println("DNA strand is " + dna);
        index = findStopCodon(dna, 0, "TGA");
        System.out.println("Index of stop codon is " + index);
        
        dna = "ATGTCATTGATAAATGTAG";  // TGA index 6
        System.out.println("DNA strand is " + dna);
        index = findStopCodon(dna, 7, "TAG");
        System.out.println("Index of stop codon is " + index);
    }
    
}
