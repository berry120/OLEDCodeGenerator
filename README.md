# OLEDCodeGenerator
Small utility to generate graphics code for the SSD1306 series of OLED screens from CSV files.

The graphic code will be produced assuming each cell in the CSV file is a pixel. If the cell contains any character the pixel will be turned on, if it is empty the pixel will be off.

Example usage:

    /**
     * Simple demo usage.
     */
    public static void main(String[] args) {
        GraphicInfo gi = new CodeGenerator(args[0]).getInfo();
        System.out.println(gi.getDefinitionCode());
    }
