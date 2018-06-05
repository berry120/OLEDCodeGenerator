package org.berry120.ssd1306;

import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Generates code to display a graphic from a CSV file. Designed for the SSD1306
 * series of OLED screens, though may work with others. Each cell in the CSV
 * file represents a pixel. If the pixel should be on, a character should be
 * inserted in the corresponding CSV cell; if the pixel should be off, the cell
 * should be left empty.
 *
 * @author Michael Berry
 */
public class CodeGenerator {

    private final String fileName;
    private final String varName;

    /**
     * Create a code generator object from a CSV file, specifying a file name
     * and variable name.
     *
     * @param fileName the path to the CSV file.
     * @param varName the variable name for the array in the generated code.
     */
    public CodeGenerator(String fileName, String varName) {
        this.fileName = fileName;
        this.varName = varName;
    }

    /**
     * Create a code generator object from a CSV file the variable name will be
     * automatically generated from the file name. A primitive attempt is made
     * to produce a valid variable name from the file name, however this should
     * not be relied upon! For uses where the variable name has to be valid, it
     * must be specified manually using the alternate constructor.
     *
     * @param fileName the path to the CSV file.
     */
    public CodeGenerator(String fileName) {
        this.fileName = fileName;
        varName = fileName.replaceFirst("[.][^.]+$", "").replace(" ", "_");
    }

    /**
     * Get a GraphicInfo object from the CSV file specified in the constructor.
     *
     * @return the GraphicInfo object.
     */
    public GraphicInfo getInfo() {
        StringBuilder definitionCodeBuilder = new StringBuilder("static unsigned char ");
        definitionCodeBuilder.append(varName);
        definitionCodeBuilder.append("[] U8G_PROGMEM = {\n");
        try (FileReader in = new FileReader(fileName)) {
            CSVParser records = CSVFormat.EXCEL.parse(in);
            int xSize = 0;
            int ySize = 0;
            for (CSVRecord record : records) {
                ySize++;
                if (record.size() > xSize) {
                    xSize = record.size();
                }
                StringBuilder lineBuilder = new StringBuilder();
                for (String rec : record) {
                    if (rec.isEmpty()) {
                        rec = " ";
                    } else {
                        rec = "x";
                    }
                    lineBuilder.append(rec);
                }
                definitionCodeBuilder.append(generateHexLine(lineBuilder.toString()));
                definitionCodeBuilder.append("\n");
            }
            definitionCodeBuilder.append("};");
            StringBuilder drawingCodeBuilder = new StringBuilder();
            drawingCodeBuilder.append("u8g.drawXBMP(xPos, yPos, ").append(xSize).append(", ").append(ySize).append(", ").append(varName).append(");\n");
            return new GraphicInfo(drawingCodeBuilder.toString(), definitionCodeBuilder.toString(), xSize, ySize);
        } catch (IOException ex) {
            System.err.println("Error reading file");
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Generate a line of hex from a CSV line, ensuring the byte order is
     * correct.
     *
     * @param line the sanitised CSV line.
     * @return the line of hex for the code.
     */
    private String generateHexLine(String line) {
        StringBuilder hex = new StringBuilder("  ");
        while (!line.isEmpty()) {
            int finishIndex = line.length() >= 8 ? 8 : line.length();
            String byteSub = line.substring(0, finishIndex);
            line = line.substring(finishIndex);
            int byteNum = 0;
            for (int i = 0; i < byteSub.length(); i++) {
                if (!Character.isWhitespace(byteSub.charAt(i))) {
                    byteNum += Math.pow(2, i);
                }
            }
            hex.append("0x");
            hex.append(Integer.toHexString(byteNum).toUpperCase());
            hex.append(",");
        }
        return hex.toString();
    }

}
