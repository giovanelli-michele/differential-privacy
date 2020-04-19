public class ExampleJava {
    static {
        System.loadLibrary("example");

        System.out.println("\n***********************************************");
        System.out.println("Loaded main-jni library");
        System.out.println("***********************************************\n");
    }


    private native int Sum();
    private native double Mean();
    private native int CountAbove(int limit);
    private native int Max();
    private native double PrivacyBudget();

    private native int PrivateSum();
    private native double PrivateMean();
    private native int PrivateCountAbove(int limit);
    private native int PrivateMax();


    public static void main(String[] args) {

        double privacyBudget;
        int trueSum;
        int privateSum;
        int trueMax;
        int privateMax;
        int countAbove;
        int privateCountAbove;
        double trueMean;
        double privateMean;
        System.out.println("JAVA WRAPPER for GOOGLE's DIFFERENTIAL PRIVACY");
        System.out.println("***********************************************");
        ExampleJava libExampleMain = new ExampleJava();

        System.out.println(
                "\nIt is a new day. Farmer Fred is ready to ask the animals about their carrot consumption.\n");
        System.out.println(
                "\nFarmer Fred asks the animals how many total carrots they have " +
                        "eaten. The animals know the true sum but report the " +
                        "differentially private sum to Farmer Fred. But first, they ensure " +
                        "that Farmer Fred still has privacy budget left.\n");
        privacyBudget = libExampleMain.PrivacyBudget();
        System.out.println("Privacy budget remaining: " + privacyBudget);
        trueSum = libExampleMain.Sum();
        System.out.println("True Sum: " + trueSum);

        privateSum = libExampleMain.PrivateSum();
        System.out.println("Private Sum: " + privateSum);

        System.out.println("\n***********************************************");
        System.out.println(
                "Farmer Fred catches on that the animals are giving him DP results. "+
                        "He asks for the mean number of carrots eaten, but this time, he wants "+
                        "some additional accuracy information to build his intuition.\n");
        privacyBudget = libExampleMain.PrivacyBudget();
        System.out.println("Privacy budget remaining: " + privacyBudget);
        trueMean = libExampleMain.Mean();
        System.out.println("True Mean: " + trueMean);

        privateMean = libExampleMain.PrivateMean();

        System.out.println("\n***********************************************");
        System.out.println(
                "Fred wonders how many gluttons are in his zoo. How many animals ate " +
                        "over 90 carrots? And how accurate is the result?\n");
        privacyBudget = libExampleMain.PrivacyBudget();
        System.out.println("Privacy budget remaining: " + privacyBudget);
        countAbove = libExampleMain.CountAbove(90);
        System.out.println("True count: " + countAbove);

        privateCountAbove = libExampleMain.PrivateCountAbove(90);
        privacyBudget = libExampleMain.PrivacyBudget();

        System.out.println("\n***********************************************");
        System.out.println(
                "\n'And how gluttonous is the biggest glutton of them all?' Fred " +
                        "exclaims. He asks for the maximum number of carrots any animal has " +
                        "eaten.\n");
        privacyBudget = libExampleMain.PrivacyBudget();
        System.out.println("Privacy budget remaining: " + privacyBudget);
        trueMax = libExampleMain.Max();
        System.out.println("True max: " + trueMax);

        privateMax = libExampleMain.PrivateMax();
        System.out.println("Private max: " + privateMax);

        System.out.println("\n***********************************************");
        System.out.println(
                "Fred also wonders how many animals are not eating any carrots at " +
                        "all.\n");
        privacyBudget = libExampleMain.PrivacyBudget();
        System.out.println("Privacy budget remaining: " + privacyBudget);

        privateMax = libExampleMain.PrivateMax();
        if(privateMax > 0) System.out.println("Private max: " + privateMax);
    }
}
