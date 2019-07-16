[![Codacy Badge](https://api.codacy.com/project/badge/Grade/83f88f5a148741fc8816a7fbb3f4f6a3)](https://app.codacy.com/app/berry120/OLEDCodeGenerator?utm_source=github.com&utm_medium=referral&utm_content=berry120/OLEDCodeGenerator&utm_campaign=Badge_Grade_Dashboard)
[![Build Status](https://travis-ci.org/berry120/OLEDCodeGenerator.svg?branch=master)](https://travis-ci.org/berry120/OLEDCodeGenerator)

# OLEDCodeGenerator
Small utility to generate custom graphics code (compatible with the U8Glib library) for the SSD1306 series of OLED screens from CSV files. These small OLED screens are comaptible out of the box with Arduino and U8Glib and currently cost less than £3 delivered on Ebay, but drawing custom graphics is often a pain as the bytes aren't always in the order you might expect!

The graphic code will be produced assuming each cell in the CSV file is a pixel. If the cell contains any character the pixel will be turned on, if it is empty the pixel will be off.

Example usage:

    /**
     * Simple demo usage.
     */
    public static void main(String[] args) {
        GraphicInfo gi = new CodeGenerator(args[0]).getInfo();
        System.out.println(gi.getDefinitionCode());
    }
