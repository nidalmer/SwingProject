package employee;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.util.*;

public class CustomRender extends DefaultTableCellRenderer {
    private ArrayList<Color> desiredColors = new ArrayList<Color>();
    private static final long serialVersionUID = 6703872492730589499L;

    public void setColors(Color incomingColor)
    {
        desiredColors.add(incomingColor);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        for (int i = 0; i < desiredColors.size(); i++) {
            cellComponent.setBackground(desiredColors.get(i));
        }
        return cellComponent;
    }
}