import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ScoreLabel extends Label {
	public ScoreLabel(double score) {
		super();
		setScore(0);		
		setFont(new Font("Arial", 18.0));
		setAlignment(Pos.TOP_LEFT);
		setTextAlignment(TextAlignment.LEFT);		
	}

	public void setScore(int score) {
		setText("Score: " + score);
	}

}
