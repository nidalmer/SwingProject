package custom;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.util.*;

public class CustomTaskRenderer extends DefaultTableCellRenderer {
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

        String stat = (String)table.getModel().getValueAt(row, 7);
        if (isSelected)
        	cellComponent.setBackground( table.getSelectionBackground() );
		else if (stat.equals("Unapproved"))
			cellComponent.setBackground(new Color(255, 204, 204)); 
		else if (stat.equals("Approved"))
			cellComponent.setBackground(new Color(214, 245, 214)); 
		else if (stat.equals("Waiting"))
			cellComponent.setBackground(new Color(255, 255, 153)); 
		else
			cellComponent.setBackground(new Color(204, 204, 255)); 
		
        return cellComponent;
    }
}