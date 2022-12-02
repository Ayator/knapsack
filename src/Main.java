public class Main {
    public static void main(String[] args){
        String[] filenames = new String[]{
            "ks_4_0", "ks_19_0", "ks_30_0", "ks_40_0", "ks_45_0", "ks_50_0", "ks_50_1", "ks_60_0",
            "ks_82_0",// 9 gb 3 mins
            "ks_100_0", "ks_100_1", "ks_100_2",
            "ks_106_0",// 9 gb 2.5 mins
            "ks_200_0", "ks_200_1", "ks_300_0",
            "ks_400_0",// 1.2 gb 1 min
            "ks_500_0", "ks_1000_0",
            "ks_10000_0"// 1.3 gb 3.6 min
        };// the rest runs pretty quickly
        for (String filename : filenames) {
            // SpaceTime st = new SpaceTime();
            // st.start();
            System.out.println();
            System.out.println("Running: " + filename);
            Knapsack ks = Knapsack.LoadFromInputFile("../input/" + filename);
            Sack solution = ks.GetSolutionSack();
            ks.saveToFile("../output/" + filename);
            System.out.println(ks);
            System.out.println(solution);
            // st.stopThread();
            // st.printData(true, true);
        }

    }
}