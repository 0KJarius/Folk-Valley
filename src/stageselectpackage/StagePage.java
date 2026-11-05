package stageselectpackage;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;


public class StagePage extends JPanel{
	public StagePage(Stage ...stages) {
		FlowLayout layout = new FlowLayout();
		layout.setVgap(0);
		this.setPreferredSize(new Dimension(673, 0));
		this.setLayout(layout);
		this.setOpaque(false);
		
		for (Stage stage : stages) {
			this.add(stage);
		}
	}
	
	public void addStage(Stage stage) {
		this.add(stage);
	}
	
}
