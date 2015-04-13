package uk.ac.kent.shed.oledcodegenerator;

/**
 * Simple wrapper object containing information about the graphic to be
 * displayed on the OLED screen.
 *
 * @author Michael Berry
 */
public class GraphicInfo {

    private final String drawingCode;
    private final String definitionCode;
    private final int xSize;
    private final int ySize;

    /**
     * Create a new graphic info object.
     *
     * @param drawingCode the drawing code
     * @param definitionCode the drawing code
     * @param xSize the horizontal size of the graphic.
     * @param ySize the vertical size of the graphic.
     */
    public GraphicInfo(String drawingCode, String definitionCode, int xSize, int ySize) {
        this.drawingCode = drawingCode;
        this.definitionCode = definitionCode;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    /**
     * Get the drawing code.
     *
     * @return the drawing code.
     */
    public String getDrawingCode() {
        return drawingCode;
    }

    /**
     * Get the definition code.
     *
     * @return the definition code.
     */
    public String getDefinitionCode() {
        return definitionCode;
    }

    /**
     * Get the horizontal size of the graphic.
     *
     * @return the horizontal size of the graphic.
     */
    public int getxSize() {
        return xSize;
    }

    /**
     * Get the vertical size of the graphic.
     *
     * @return the vertical size of the graphic.
     */
    public int getySize() {
        return ySize;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("//Drawing code:\n");
        ret.append(drawingCode);
        ret.append("//Definition code:\n");
        ret.append(definitionCode);
        return ret.toString();
    }

}
