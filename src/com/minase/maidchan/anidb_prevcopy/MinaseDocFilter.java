package com.minase.maidchan.anidb_prevcopy;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class MinaseDocFilter extends DocumentFilter {

	public void insertString(DocumentFilter.FilterBypass filterBypass,
							 int offset,
							 String text,
							 AttributeSet attribSet)
							 throws BadLocationException {
		
		for (char c : text.toCharArray()) if (!Character.isDigit(c)) return;
		filterBypass.insertString(offset, text.toUpperCase(), attribSet);
	}
	
	public void replace(DocumentFilter.FilterBypass filterBypass,
						int offset,
						int length,
						String text,
						AttributeSet attribSet) 
						throws BadLocationException {
		
		for (char c : text.toCharArray()) if (!Character.isDigit(c)) return;
		filterBypass.replace(offset, length, text, attribSet);
	}
	
}
