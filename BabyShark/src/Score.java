
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class Score extends Label {

	int score;
	
	public Score(double score) {
		super();
		setScore(0);		
		setFont(Font.font("Verdana", FontWeight.BOLD, 18.0));
		setTextFill(Color.ORANGE);
		setAlignment(Pos.TOP_LEFT);
		getStylesheets().add("/style.css");
		setId("scoreFont");
		setTextAlignment(TextAlignment.LEFT);		
	}

	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
		setText("Score: " + score);
	}

}
