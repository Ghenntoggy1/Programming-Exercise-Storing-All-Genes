import edu.duke.*;
import java.io.File;

public class Part3 {
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
    
    public void processGenes (StorageResource sr) {
        int counter = 0;
        System.out.println("All genes in DNA " + sr.size());
        for (String gene : sr.data()) {
            System.out.println(gene);
        }
        System.out.println("================================================");
        System.out.println("Genes longer than 60 chars");
        for (String gene : sr.data()) {
            if (gene.length() > 60) {
                System.out.println(gene + ' ' + gene.length());
                counter++;
            }
        }
        System.out.println("================================================");
        System.out.println("Number of genes that are longer than 60 chars\n" + counter);
        System.out.println("================================================");
        System.out.println("Genes with CG Ratio greater than 0.35");
        counter = 0;
        Part2 p2class = new Part2();
        for (String gene : sr.data()) {
            if (p2class.cgRatio(gene) > 0.35) {
                System.out.println(gene + ' ' + p2class.cgRatio(gene));
                counter++;
            }
        }
        System.out.println("================================================");
        System.out.println("Number of genes that has CG Ratio greater than 0.35\n" + counter);
        int currLongest = 0;
        int maxLength = 0;
        for (String gene : sr.data()) {
            currLongest = gene.length();
            if (currLongest > maxLength) {
                maxLength = currLongest;
            }
        }
        System.out.println("================================================");
        System.out.println("Length of longest gene is\n" + maxLength);
    }
    
    public void testProcessGenes () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            String dna = fr.asString();
            System.out.println("DNA Strand is " + dna.toUpperCase());
            StorageResource geneList = getAllGenes(dna.toUpperCase());
            processGenes(geneList);
            System.out.println("================================================");
            Part2 p2class = new Part2();
            System.out.println("Number of CTG codons in DNA\n" + p2class.countCTG(dna));
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
        }
    }
}
