public class Main {
    public static void main(String[] args){
        String[] filenames = new String[]{
            "ks_4_0", "ks_19_0", "ks_30_0", "ks_40_0", "ks_45_0", "ks_50_0", "ks_50_1", "ks_60_0",
            "ks_82_0",// this ran with the boolean[] version (2)
            // "ks_100_0", "ks_100_1", "ks_100_2",
            // "ks_106_0",// this ran with the boolean[] version (2) (took a while)
            // "ks_200_0", "ks_200_1", "ks_300_0",
            // "ks_400_0",// this ran with 14 gb
            // "ks_500_0", "ks_1000_0",
            // "ks_10000_0"// may need a bit of ram (16 gb) but runs relatively fast
        };// the rest runs pretty quickly
        for (String filename : filenames) {
            System.out.println("Running " + filename);
            Knapsack ks = Knapsack.LoadFromInputFile("../input/" + filename);
            Sack solution = ks.GetSolutionSack();
            ks.saveToFile("../output/" + filename);
            System.out.println(solution);
        }
    }
}