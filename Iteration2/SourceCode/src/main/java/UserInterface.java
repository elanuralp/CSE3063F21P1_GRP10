import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserInterface extends Application {
    @SuppressWarnings("unchecked")
    @Override
    public void start(Stage primaryStage) {

        Button btn = new Button();
        btn.setText("Start Registration");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            private static final double BUTTON_PADDING   = 5;

            @Override
            public void handle(ActionEvent event) {
                GridPane grid = new GridPane();
                grid.setPadding(new Insets(BUTTON_PADDING));
                grid.setHgap(BUTTON_PADDING);
                grid.setVgap(BUTTON_PADDING);

                UniversitySystem universitySystem = new UniversitySystem();
                universitySystem.initializeUniversityCourseSelectionSystem();
                ArrayList<Student> allStudents = new ArrayList<Student>();
                allStudents=universitySystem.getAllStudentList();

                ListView<String> listViewReference = new ListView<String>();
                for(Student std : allStudents){
                    listViewReference.getItems().add(std.getId().getID()+"");
                }
                listViewReference.setOrientation(Orientation.VERTICAL);
                VBox vbox = new VBox(listViewReference);
                vbox.setPadding(new Insets(10, 50, 40, 40));
                vbox.setAlignment(Pos.CENTER);

                Scene scene = new Scene(vbox, 700, 600);
                ScrollPane scrollPane = new ScrollPane(grid);

                Scene scene2 = new Scene(scrollPane, 900, 300);

                Button getJsonButton = new Button("Open Selected Transcript JSON");
                Alert PleaseSelectStdAlert = new Alert(Alert.AlertType.NONE);
                getJsonButton.setPadding(new Insets(BUTTON_PADDING));
                vbox.getChildren().add(getJsonButton);

                getJsonButton.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {

                        String goToSelectedStudent = listViewReference.getSelectionModel().getSelectedItem() +"";
                        File myFile = new File(goToSelectedStudent+".json");
                        try {
                            Desktop.getDesktop().open(myFile);
                        } catch (Exception e) {
                            PleaseSelectStdAlert.setAlertType(Alert.AlertType.ERROR);
                            PleaseSelectStdAlert.setContentText("PLEASE BEFORE PRESSING THE BUTTON, SELECT A STUDENT :)");
                            PleaseSelectStdAlert.show();
                        }

                    }
                });

                Button getSemesterJSONBtn = new Button("Open Semester Statistic JSON");

                vbox.getChildren().add(getSemesterJSONBtn);
                vbox.setSpacing(50);

                getSemesterJSONBtn.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {

                        File myFile = new File("DEPARTMENT_OUTPUT_SEMESTER.json");
                        try {
                            Desktop.getDesktop().open(myFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });



                primaryStage.setTitle("Student List!");
                primaryStage.setScene(scene);
                primaryStage.show();
            }

        });


        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 500, 250);
        primaryStage.setTitle("Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args)
    {

        launch(args);

    }
}
