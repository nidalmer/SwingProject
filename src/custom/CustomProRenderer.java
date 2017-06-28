package custom;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.util.*;

public class CustomProRenderer extends DefaultTableCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Color> desiredColors = new ArrayList<Color>();

    public void setColors(Color incomingColor)
    {
        desiredColors.add(incomingColor);
    }

    @Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        for (int i = 0; i < desiredColors.size(); i++) {
            cellComponent.setBackground(desiredColors.get(i));
        }
        
        boolean valid = (boolean)table.getModel().getValueAt(row, 8);
        		
        
        if (isSelected)
        	cellComponent.setBackground(table.getSelectionBackground());
        else if (valid)
            cellComponent.setBackground(new Color(230, 242, 255));
		else 
			cellComponent.setBackground(table.getBackground());
        return cellComponent;
    }
}
