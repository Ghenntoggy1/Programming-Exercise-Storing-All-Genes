public class Part2 {
    public float cgRatio (String dna) {
        int counter = 0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                counter++;
            }
        }
        if (dna.length() == 0) {
            return 0;
        }
        return ((float) counter)/dna.length();
    }
    
    public int countCTG (String dna) {
        int currIndex = dna.indexOf("CTG");
        if (currIndex == -1) {
            return 0;
        }
        int counter = 0;
        while (currIndex != -1) {
            if (currIndex != -1) {
                counter++;
            }
            currIndex = dna.indexOf("CTG", currIndex + 1);
        }
        return counter;
    }
    
    public void test () {
        String dna = "ATGCCTGAA";
        System.out.println("DNA strand is " + dna);
        float ratio = cgRatio(dna);
        System.out.println("CG Ratio is " + ratio);
        int ctg = countCTG(dna);
        System.out.println("CTG Counter is " + ctg);
        
        dna = "CTGCTCTGCTGCTG";
        System.out.println("DNA strand is " + dna);
        ratio = cgRatio(dna);
        System.out.println("CG Ratio is " + ratio);
        ctg = countCTG(dna);
        System.out.println("CTG Counter is " + ctg);
        
        dna = "ATATTAAT";
        System.out.println("DNA strand is " + dna);
        ratio = cgRatio(dna);
        System.out.println("CG Ratio is " + ratio);
        ctg = countCTG(dna);
        System.out.println("CTG Counter is " + ctg);
        
        dna = "ATGCGGGCG";
        System.out.println("DNA strand is " + dna);
        ratio = cgRatio(dna);
        System.out.println("CG Ratio is " + ratio);
        ctg = countCTG(dna);
        System.out.println("CTG Counter is " + ctg);
    }
}
