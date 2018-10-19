import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application 
{
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage arg0) throws Exception 
	{
		Stage window = arg0;

		Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
		window.setTitle("Basic Calculator");
      
		Scene newScene = new Scene(root, 280, 205);
      

		window.setScene(newScene);
		window.show();
	}
}













