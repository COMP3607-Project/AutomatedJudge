// Concrete implementation using both patterns
class CustomZipProcessor extends ZipFileProcessor {
    @Override
    protected void processFile(ZipEntry entry) throws IOException {
        // Code for reading data from each file
        System.out.println("Processing " + entry.getName() + ": " + readData(entry));
    }

    private String readData(ZipEntry entry) throws IOException {
        // Code for reading data from the zip entry
        //For text data
        StringBuilder data = new StringBuilder();
        try (var inputStream = getZipFile().getInputStream(entry)) {
            int byteRead;
            while ((byteRead = inputStream.read()) != -1) {
                data.append((char) byteRead);
            }
        }
        return data.toString();
    }
}
