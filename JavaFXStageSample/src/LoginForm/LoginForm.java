package LoginForm;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tooltip;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;



/**
@author akihiro
 */
public class LoginForm extends Application {
    //variables for storing initial position of the stage at the beginning of drag
	private double initX;
	private double initY;

	private void init(final Stage stage) {
		Group root = new Group();
		Scene scene = new Scene(root, 300, 300, Color.TRANSPARENT);
		stage.setScene(scene);
		stage.setTitle("LoginDemo");
		stage.centerOnScreen();
		stage.show();
		Circle dragger = new Circle(150, 150, 150);
		dragger.setFill(new RadialGradient(-0.3, 135, 0.5, 0.5, 1, true, CycleMethod.NO_CYCLE, new Stop[] {
			new Stop(0, Color.DARKGRAY),
			new Stop(1, Color.BLACK)
		}));
		Text text = new Text("X");
		text.setFill(Color.WHITESMOKE);
		text.setEffect(new Lighting());
		text.setBoundsType(TextBoundsType.VISUAL);
		text.setFont(Font.font(Font.getDefault().getFamily(), 50));
		
		Tooltip tooltip1 = new Tooltip("aaaです！");
		Tooltip tooltip2 = new Tooltip("bbbです！");
		
		Font font = Font.font("Arial",FontPosture.ITALIC,13);
		
		Label label1 = new Label("Username");
		label1.setStyle("-fx-text-fill: white");
		label1.setFont(font);
		label1.setTooltip(tooltip1);
		

		Label label2 = new Label("Password");
		label2.setStyle("-fx-text-fill: white");
		label2.setFont(font);
		label2.setTooltip(tooltip2);
		
		final TextField name = new TextField();
		final PasswordField pass = new PasswordField();
		
		HBox UN = new HBox();
		UN.setSpacing(5);
		HBox PS = new HBox();
		PS.setSpacing(7);
		UN.getChildren().addAll(label1,name);
		PS.getChildren().addAll(label2,pass);
		
		Button button = new Button("GO");
		button.setStyle("-fx-font-size: 17;");
		
		VBox vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(80, 40, 0, 50));
		vbox.setAlignment(Pos.CENTER);
		
		vbox.getChildren().addAll(text,UN,PS,button);
		
		root.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				initX = me.getScreenX() - stage.getX();
				initY = me.getScreenY() - stage.getY();
			}
		});
		
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				stage.setX(me.getScreenX() - initX);
				stage.setY(me.getScreenY() - initY);
			}
		});
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				String username = name.getText();
				String password = pass.getText();
				if(username.equals("aaa") && password.equals("bbb")){
					stage.close();
					//
					String htmlText = 
							"<html>"+
							"<body>"+
							"<h1 align=center><span id='greet'>Welcome</span>,aaa!</h1>"+
							"<p align=center><font size=20>制作中！</font></p>"+
							"</body>";
					WebView webview = new WebView();
					final WebEngine we = webview.getEngine();
					we.loadContent(htmlText);
					//
					Stage stage1 = new Stage();
					VBox root = new VBox();
					root.setAlignment(Pos.CENTER);
					Scene scene = new Scene(root,500,500);
					stage1.setScene(scene);
					stage.setTitle("HTML");
					stage1.centerOnScreen();
					stage1.show();
					
					Label label = new Label();
					label.setGraphic(webview);
					
					Button button = new Button("Goodbye");
					
					root.getChildren().addAll(label,button);
					
					button.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent t) {
								org.w3c.dom.Document doc = we.getDocument();
								org.w3c.dom.Element element = doc.getElementById("greet");
								element.setTextContent("Goodbye");
						}
					});
				}}
		});
		root.getChildren().addAll(dragger, vbox);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		init(stage);
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}

