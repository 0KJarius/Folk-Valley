package stageselectpackage;

import java.awt.Color;
import javax.swing.JPanel;

public class StageBuilder {
	private String stageName = "";
	private Color textColor = Color.white;
	private String stageFrameName;
	private boolean isLocked = true;
	private JPanel destination;
	private Stage nextStage;
	private Integer layer = null;
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}
	public void setStageFrameName(String stageFrameName) {
		this.stageFrameName = stageFrameName;
	}
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	public void setDestination(JPanel destination) {
		this.destination = destination;
	}
	public void setNextStage(Stage nextStage) {
		this.nextStage = nextStage;
	}
	public void setLayer(Integer layer) {
		this.layer = layer;
	}
	
	public Stage build() {
		if (layer != null) {
			return new Stage(stageName, textColor, stageFrameName, isLocked, destination, layer, nextStage);
		} if (nextStage != null) {
			if (layer != null) {
				return new Stage(stageName, textColor, stageFrameName, isLocked, destination, layer, nextStage);
			}
			return new Stage(stageName, textColor, stageFrameName, isLocked, destination, nextStage);
		}
		return new Stage(stageName, textColor, stageFrameName, isLocked, destination);
	}

}
