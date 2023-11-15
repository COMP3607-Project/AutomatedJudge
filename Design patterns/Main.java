public class Main {
    public static void main(String[] args) {
        try {
            CustomZipProcessor zipProcessor = new CustomZipProcessor();
            zipProcessor.process("path/to/your/file.zip");

            // Using the iterator to iterate through files
            ZipFileIterator zipIterator = new ZipFileIterator(zipProcessor);
            while (zipIterator.hasNext()) {
                ZipEntry entry = zipIterator.next();
                // Custom code for processing each file if needed
                // You can access the entry information, and the actual processing is handled in CustomZipProcessor
                // Example: System.out.println("Processing file: " + entry.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}