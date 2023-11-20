public class UnzipTest {

    public static void main(String[] args) {
        String zipFilePath = "C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 2\\COMP 2603\\Vanessa_Aubin_816031597_A1.zip";
        String dest = "C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 2\\COMP 2606";

        UnzipUtility unzipper = new UnzipUtility();
        
        try {
            unzipper.unzip(zipFilePath, dest);
        } catch (Exception ex) {
            // some errors occurred
            ex.printStackTrace();
        }
    }
}
