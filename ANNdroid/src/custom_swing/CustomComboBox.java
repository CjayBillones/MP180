package ANNdroid.src.custom_swing;

import ANNdroid.src.*;
import ANNdroid.src.events.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomComboBox<E> extends JComboBox<E>{

	public CustomComboBox(){
		setOpaque(false);
		setFocusable(false);
		setForeground(Color.WHITE);
		setRenderer(new DefaultListCellRenderer(){
	    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	        JComponent result = (JComponent)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
					result.setOpaque(false);
					
					if(isSelected){
		        result.setForeground(Color.RED);
						result.setBackground(new Color(172, 172, 172, 1));
					}
	        else
	        	result.setForeground(Color.BLACK);
	        return result;
      }
  	});		
	}

	public CustomComboBox(E[] items){

		super(items);

		setSelectedIndex(0);
		setOpaque(false);
		setFocusable(false);
		setForeground(Color.WHITE);
		setRenderer(new DefaultListCellRenderer(){
	    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	        JComponent result = (JComponent)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
					result.setOpaque(false);
					
					if(isSelected){
		        result.setForeground(Color.RED);
						result.setBackground(new Color(172, 172, 172, 1));
					}
	        else
	        	result.setForeground(Color.BLACK);
	        return result;
      }
  	});
	}
}