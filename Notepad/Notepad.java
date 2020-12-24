package Notepad;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;

public class Notepad implements ActionListener{

	
	
	boolean first_save=true;
	String path="";
	int zoom_size=0;
	int zoom_size_out=0;
	String find_last="";
	String replace_last="";
	boolean counter_find=false;
	boolean commit_save=true;
	boolean open_commit=false;
	int replace_last_index=0;
	
	int find_last_index=0;
	
	
	
	
	
	
	
	
		
		
		JFrame jf;
		JMenuBar mb;
		JMenu file,edit,view,format,about;
		JMenuItem open,save,saveas,cut,copy,paste,selectall,changecolour,neww,exit,delete,find,replace,Serif,SansSerif,DialogInput,Dialog,TimesNewRoman,s8,s12,s18,s32,s45,s62;
		JTextArea ta;
		JMenu fonts,fontsize;
		Notepad(){
		jf=new JFrame("Notepad");
		cut=new JMenuItem("Cut");
		copy=new JMenuItem("Copy");
		paste=new JMenuItem("Paste");
		selectall=new JMenuItem("Select All");
		changecolour=new JMenuItem("ChangeColour");
		open=new JMenuItem("Open");
		save=new JMenuItem("save");
		neww=new JMenuItem("new");
		exit=new JMenuItem("exit");
		delete=new JMenuItem("Delete");
		find=new JMenuItem("Find");
		replace=new JMenuItem("Replace");
		Serif=new JMenuItem("Serif");
		SansSerif=new JMenuItem("SansSerif");
		 Dialog=new JMenuItem("Dialog");
		 DialogInput=new JMenuItem("DialogInput");
		TimesNewRoman=new JMenuItem("Times New Roman");
		fonts=new JMenu("Font ");
		fontsize=new JMenu("fontsize");
		s8=new JMenuItem("8px");
		s12=new JMenuItem("12px");
		s18=new JMenuItem("18px");
		s32=new JMenuItem("32px");
		s45=new JMenuItem("45px");
		s62=new JMenuItem("62px");
		
		
		
		Serif.addActionListener(this);
		SansSerif.addActionListener(this);
		Dialog.addActionListener(this);
		DialogInput.addActionListener(this);
		TimesNewRoman.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		selectall.addActionListener(this);
		changecolour.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		neww.addActionListener(this);
		exit.addActionListener(this);
		find.addActionListener(this);
		replace.addActionListener(this);
		s8.addActionListener(this);
		s12.addActionListener(this);
		s18.addActionListener(this);
		s32.addActionListener(this);
		s45.addActionListener(this);
		s62.addActionListener(this);
		
		
		
		
		
		mb=new JMenuBar();
		
		
		file=new JMenu("File");
		edit=new JMenu("Edit");
		view=new JMenu("View");
		format=new JMenu("Format");
		about=new JMenu("About");
		
		
		edit.add(cut);edit.add(copy);edit.add(paste);edit.add(selectall);
		format.add(changecolour);file.add(neww);file.addSeparator();file.add(open);file.add(save);edit.add(find);edit.add(replace);file.add(exit);
		mb.add(file);mb.add(edit);mb.add(view);mb.add(format);mb.add(about);view.add(fonts);view.add(fontsize);fonts.add(Serif);fonts.add(SansSerif);fonts.add(Dialog);
		fonts.add(TimesNewRoman);fonts.add(DialogInput);fontsize.add(s8);fontsize.add(s12);fontsize.add(s18);fontsize.add(s32);fontsize.add(s45);fontsize.add(s62);
		
		
		ta=new JTextArea();
		ta.setBounds(5, 5, 2000, 320);
		
		
		jf.add(mb);jf.add(ta);
		jf.setJMenuBar(mb);  
		jf.setLayout(null);    
		jf.setSize(600,400);    
		jf.setVisible(true);    
		s12.setEnabled(false);
		Dialog.setEnabled(false);
		
		
		
		

	}
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == exit)
                System.exit(0); 
			if(e.getSource()==cut)
				ta.cut();
			if(e.getSource()==copy)
				ta.copy();
			if(e.getSource()==paste)
				ta.paste();
			if(e.getSource()==selectall)
				ta.selectAll();
			if(e.getSource()==changecolour)
			{
				Color c=JColorChooser.showDialog(null,"Choose",Color.CYAN);
				ta.setBackground(c);
			}
			if(e.getSource()==open) {
				JFileChooser fc=new JFileChooser();
				int i=fc.showOpenDialog(null);
				if(i==JFileChooser.APPROVE_OPTION) {
					File f=fc.getSelectedFile();
					String filepath=f.getPath();
					try {
						BufferedReader br=new BufferedReader(new FileReader(filepath));
						String s1="",s2="";
						s1=br.readLine();
						while(s1!=null) {
						    s2+=s1+"\n";   
						}
						ta.setText(s2);
						br.close();
					}catch(Exception ex) {ex.printStackTrace();}
				}
				}
			if(e.getSource()==save) {
				JFileChooser fc=new JFileChooser();
				int i=fc.showSaveDialog(null);
				
				if(i==JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter file = new FileWriter(fc.getSelectedFile().getAbsolutePath());
			        	path=fc.getSelectedFile().getAbsolutePath();
			        	BufferedWriter out = new BufferedWriter(file); 
						out.write(ta.getText());
						out.close();
					}
					catch(Exception ex){System.out.println(ex.getMessage());}
					
				}
			}
			if(e.getSource()==find)
			find_function();
			if(e.getSource()==replace)
			replace_function();
				
		
		if(e.getSource()==TimesNewRoman)
		{
			int font_size=ta.getFont().getSize();
			int font_style=ta.getFont().getStyle();
			ta.setFont(new Font("Times New Roman", font_style, font_size));
			
			//select true
			Dialog.setEnabled(true);
			TimesNewRoman.setEnabled(false);
			DialogInput.setEnabled(true);
			SansSerif.setEnabled(true);
			Serif.setEnabled(true);
		}
		if(e.getSource()==DialogInput)
		{
			int font_size=ta.getFont().getSize();
			int font_style=ta.getFont().getStyle();
			ta.setFont(new Font("DialogInput", font_style, font_size));
			
			//select true
			Dialog.setEnabled(true);
			TimesNewRoman.setEnabled(true);
			DialogInput.setEnabled(false);
			SansSerif.setEnabled(true);
			Serif.setEnabled(true);
		}
		if(e.getSource()==Dialog)
		{
			int font_size=ta.getFont().getSize();
			int font_style=ta.getFont().getStyle();
			ta.setFont(new Font("Dialog", font_style, font_size));	
			
			//select true
			Dialog.setEnabled(false);
			TimesNewRoman.setEnabled(true);
			DialogInput.setEnabled(true);
			SansSerif.setEnabled(true);
			Serif.setEnabled(true);
			
		}
		if(e.getSource()==SansSerif)
		{
			int font_size=ta.getFont().getSize();
			int font_style=ta.getFont().getStyle();
			ta.setFont(new Font("SansSerif", font_style, font_size));	
			
			//select true
			
			Dialog.setEnabled(true);
			TimesNewRoman.setEnabled(true);
			DialogInput.setEnabled(true);
			SansSerif.setEnabled(false);
			Serif.setEnabled(true);
		}
		if(e.getSource()==Serif)
		{
			int font_size=ta.getFont().getSize();
			int font_style=ta.getFont().getStyle();
			ta.setFont(new Font("Serif", font_style, font_size));
			Dialog.setEnabled(true);
			TimesNewRoman.setEnabled(true);
			DialogInput.setEnabled(true);
			SansSerif.setEnabled(true);
			Serif.setEnabled(false);
		}
		if(e.getSource()==s8)
		{
			String font_family=ta.getFont().getFamily();
			int font_style=ta.getFont().getStyle();
			ta.setFont(new Font(font_family, font_style, 8+(zoom_size*5)+(zoom_size_out*5)));
			
			
			s8.setEnabled(false);
			s12.setEnabled(true);
			s18.setEnabled(true);
			s32.setEnabled(true);
			s45.setEnabled(true);
			s62.setEnabled(true);
		}
		if(e.getSource()==s12)
		{
			String font_family=ta.getFont().getFamily();
			int font_style=ta.getFont().getStyle();
			ta.setFont(new Font(font_family, font_style, 12+(zoom_size*5)+(zoom_size_out*5)));
			
			
			s8.setEnabled(true);
			s12.setEnabled(false);
			s18.setEnabled(true);
			s32.setEnabled(true);
			s45.setEnabled(true);
			s62.setEnabled(true);
		}
		if(e.getSource()==s18)
		{
			String font_family=ta.getFont().getFamily();
			int font_style=ta.getFont().getStyle();
			ta.setFont(new Font(font_family, font_style, 18+(zoom_size*5)+(zoom_size_out*5)));
			
			
			s8.setEnabled(true);
			s12.setEnabled(true);
			s18.setEnabled(false);
			s32.setEnabled(true);
			s45.setEnabled(true);
			s62.setEnabled(true);
		}
		if(e.getSource()==s32)
		{
			String font_family=ta.getFont().getFamily();
			int font_style=ta.getFont().getStyle();
			ta.setFont(new Font(font_family, font_style, 32+(zoom_size*5)+(zoom_size_out*5)));
			
			
			s8.setEnabled(true);
			s12.setEnabled(true);
			s18.setEnabled(true);
			s32.setEnabled(false);
			s45.setEnabled(true);
			s62.setEnabled(true);
		}
		if(e.getSource()==s45)
		{
			String font_family=ta.getFont().getFamily();
			int font_style=ta.getFont().getStyle();
			ta.setFont(new Font(font_family, font_style, 45+(zoom_size*5)+(zoom_size_out*5)));
			
			
			s8.setEnabled(true);
			s12.setEnabled(true);
			s18.setEnabled(true);
			s32.setEnabled(true);
			s45.setEnabled(false);
			s62.setEnabled(true);
		}
		if(e.getSource()==s62)
		{
			String font_family=ta.getFont().getFamily();
			int font_style=ta.getFont().getStyle();
			ta.setFont(new Font(font_family, font_style, 62+(zoom_size*5)+(zoom_size_out*5)));
			
			
			s8.setEnabled(true);
			s12.setEnabled(true);
			s18.setEnabled(true);
			s32.setEnabled(true);
			s45.setEnabled(true);
			s62.setEnabled(false);
		}
			
		}
		
		private void replace_function(){
			replace_last="";
			
			JDialog replace_dialog = new JDialog(jf, "Replace");
			
			JLabel l=new JLabel("Find what : ");
			l.setBounds(10,10,100,30); 
			replace_dialog.add(l);
			final JTextField find_text=new JTextField();
			find_text.setBounds(100,15,250,20);  			
			replace_dialog.add(find_text);
			
			JLabel l1=new JLabel("Replace with : ");
			l1.setBounds(10,35,100,30); 
			replace_dialog.add(l1);
			final JTextField replace_text=new JTextField();
			replace_text.setBounds(100,40,250,20);  			
			replace_dialog.add(replace_text);
			
			JButton b=new JButton("Find Next");
			b.setBounds(360,15,100,20);
			replace_dialog.add(b);
			
			JButton b1=new JButton("Replace It");
			b1.setBounds(360,40,100,20);
			replace_dialog.add(b1);
			
			JButton b2=new JButton("Replace All");
			b2.setBounds(360,65,100,20);
			replace_dialog.add(b2);
			
			replace_dialog.setSize(500,140);
			replace_dialog.setLayout(null); 
			replace_dialog.setVisible(true);
			
			 b.addActionListener ( new ActionListener()  
		        {  
		            public void actionPerformed( ActionEvent e )  
		            {  
		            	String str_main=ta.getText();
		            	String find_str=find_text.getText();
		            	if(!replace_last.equals(find_str))
		            	{replace_last_index=0; replace_last=find_str;	}
		            	int strt=str_main.indexOf(find_str,replace_last_index);
		            	if(strt!=-1)
						{
						int end=find_str.length();
		            	ta.setSelectionStart(strt); 
						ta.setSelectionEnd(end+strt);
						str_main=str_main.substring(strt+end);
						replace_last_index=strt+end;
						counter_find=true;
						}
		            	else
		            	JOptionPane.showMessageDialog(jf,"Cannot find \'"+ find_str +"\'","Alert",JOptionPane.WARNING_MESSAGE);
		            	
		            	
		            }  
		        });  
			 
			 b1.addActionListener ( new ActionListener()  
		        {  
		            public void actionPerformed( ActionEvent e )  
		            {  
		            	if(counter_find)
		            	ta.replaceRange(replace_text.getText(),replace_last_index-find_text.getText().length(),replace_last_index);
		            	else
		            	{
		            		String str_main=ta.getText();
			            	String find_str=find_text.getText();
			            	if(!replace_last.equals(find_str))
			            	{replace_last_index=0; replace_last=find_str;	}
			            	int strt=str_main.indexOf(find_str,replace_last_index);
			            	if(strt!=-1)
							{
							int end=find_str.length();
			            	str_main=str_main.substring(strt+end);
							replace_last_index=strt+end;
							counter_find=false;
							ta.replaceRange(replace_text.getText(),strt,strt+end);
							ta.setSelectionStart(strt); 
							ta.setSelectionEnd(replace_text.getText().length()+strt);
							
							
							}
			            	else
			            	JOptionPane.showMessageDialog(jf,"Cannot find \'"+ find_str +"\'","Alert",JOptionPane.WARNING_MESSAGE);
			            	
		            	}
		            	
		            }  
		        });
			 
			 
			 b2.addActionListener ( new ActionListener()  
		        {  
		            public void actionPerformed( ActionEvent e )  
		            {  
		            	int track=0;
		            	String str_main=ta.getText();
		            	String find_str=find_text.getText();
		            	int strt=str_main.indexOf(find_str,track);
		            	if(strt!=-1)
						{
		            	while(strt!=-1)	{		
						int end=find_str.length();
						str_main=str_main.substring(strt+end);
						ta.replaceRange(replace_text.getText(),strt,strt+end);
						str_main=ta.getText();
		            	find_str=find_text.getText();
		            	strt=str_main.indexOf(find_str,track);
						
						
		            	}
						}
		            	else
		            	JOptionPane.showMessageDialog(jf,"Cannot find \'"+ find_str +"\'","Alert",JOptionPane.WARNING_MESSAGE);
		            	
		            	
		            }  
		        });
			
		}
		private void find_function() {
			{
				JDialog find_dialog = new JDialog(jf, "Find..");
				
				find_last="";
				
				JLabel l=new JLabel("Find What....");
				l.setBounds(10,5,100,30); 
				find_dialog.add(l);
				final JTextField find_text=new JTextField();
				find_text.setBounds(10,37,250,20);  			
				find_dialog.add(find_text);
				JButton b=new JButton("Find Next");
				b.setBounds(10,63,100,20);
				find_dialog.add(b);
				find_dialog.setSize(300,140);
				find_dialog.setLayout(null); 
				find_dialog.setVisible(true);
				 b.addActionListener ( new ActionListener()  
			        {  
			            public void actionPerformed( ActionEvent e )  
			            {  
			            	String str_main=ta.getText();
			            	String find_str=find_text.getText();
			            	if(!find_last.equals(find_str))
			            	{find_last_index=0; find_last=find_str;	}
			            	int strt=str_main.indexOf(find_str,find_last_index);
			            	if(strt!=-1)
							{
							int end=find_str.length();
			            	ta.setSelectionStart(strt); 
							ta.setSelectionEnd(end+strt);
							str_main=str_main.substring(strt+end);
							find_last_index=strt+end;
							}
			            	else
			            	JOptionPane.showMessageDialog(jf,"Cannot find \'"+ find_str +"\'","Alert",JOptionPane.WARNING_MESSAGE);
			            	
			            	
			            }  
			        });  
				
			
		}
		}
		
		
		
		
public static void main(String[] args) {
 new Notepad();
}

}
