package degreesmart.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.control.Label;
import javafx.scene.chart.PieChart;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import java.lang.reflect.*;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Comparator;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import degreesmart.model.Application;
import degreesmart.model.StudentApplication;
import degreesmart.model.Student;
import degreesmart.model.Course;
import degreesmart.model.CourseList;
import degreesmart.model.CompletedCourse;
import degreesmart.model.PlannedCourse;
import degreesmart.model.Grade;
import degreesmart.model.Semester;
import degreesmart.model.SemesterPlan;
import degreesmart.model.SemesterNode;
import degreesmart.model.SemesterState;
import degreesmart.model.RequirementTree;
import degreesmart.model.Set;


public class StudentGraduationPlanController extends StudentController implements Initializable {
    @FXML
    private HeaderPaneController headerPaneController;

    @FXML
    private StackPane stackrRoot;

    @FXML
    private Label degree;

    @FXML
    private Label classification;

    @FXML
    private Label overallGpa;

    @FXML
    private Label majorGpa;

    @FXML
    private VBox semesterList;

    private SemesterPlan plan;

    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        HBox notesContainer = new HBox();
        ImageView notes = new ImageView();
        notesContainer.getChildren().add(notes);
        notes.setPreserveRatio(true);
        notes.setFitWidth(20);
        notes.setFitHeight(20);
        notes.setStyle("-fx-padding: 5;");
        notes.setImage(new Image(App.class.getResource("/images/pinned-notes.png").toExternalForm()));
        notesContainer.setOnMouseClicked(e -> {
            App.setRoot("student-advising-notes");
        });

        HBox printContainer = new HBox();
        ImageView print = new ImageView();
        printContainer.getChildren().add(print);
        print.setPreserveRatio(true);
        print.setFitWidth(25);
        print.setFitHeight(25);
        print.setStyle("-fx-padding: 5;");
        print.setImage(new Image( App.class.getResource("/images/print.png").toExternalForm()));
        printContainer.setOnMouseClicked(e -> {
            System.out.println("TODO");
        });

        headerPaneController.getIcons().getChildren().add(0, printContainer);
        headerPaneController.getIcons().getChildren().add(0, notesContainer);

        plan = new SemesterPlan(new ArrayList<>(Arrays.asList(Set.generateComputerScience())));
        plan.getPlanned().get(0).setCompleted(Grade.B);
        plan.getPlanned().get(0).setCompleted(Grade.A);
        plan.getPlanned().get(0).setCompleted(Grade.A);
        plan.getPlanned().get(0).setCompleted(Grade.C);
        plan.getPlanned().get(0).setCourse(plan.getPlanned().get(0).getRequirement().getPossibleCourses().get(3));
        plan.getPlanned().get(0).setCompleted(Grade.B);
        plan.getPlanned().get(0).setCurrent();
        plan.getPlanned().get(0).setCurrent();
        plan.getPlanned().get(0).setCurrent();
        plan.getPlanned().get(0).setCurrent();
        refresh();
    }

    private GridPane getBlankBranchRow() {
        GridPane blankRow = new GridPane();

        ColumnConstraints one = new ColumnConstraints();
        ColumnConstraints two = new ColumnConstraints();
        one.setPercentWidth(60);
        two.setPercentWidth(40);

        blankRow.getColumnConstraints().addAll(
            one,
            two
        );

        return blankRow;
    }

    private GridPane getBlankSemesterRow() {
        GridPane blankRow = new GridPane();

        ColumnConstraints one = new ColumnConstraints();
        ColumnConstraints two = new ColumnConstraints();
        ColumnConstraints three = new ColumnConstraints();
        ColumnConstraints four = new ColumnConstraints();
        ColumnConstraints five = new ColumnConstraints();
        one.setPercentWidth(15);
        two.setPercentWidth(35);
        three.setPercentWidth(5);
        four.setPercentWidth(5);
        five.setPercentWidth(40);

        blankRow.getColumnConstraints().addAll(
            one,
            two,
            three,
            four,
            five
        );
        return blankRow;
    }

    private String getBaseHeaderStyle() {
        return " -fx-text-fill: black; -fx-max-width: INFINITY; -fx-max-height: INFINITY; -fx-padding: 3 5; -fx-font-size: 13;";
    }

    private String getBaseCellStyle() {
        return "-fx-padding: 3 5;";
    }

    private GridPane getSemesterHeader(SemesterNode node) {
        GridPane header = getBlankSemesterRow();

        ArrayList<String> columnHeaders = new ArrayList<String>();
        columnHeaders.add("Number");
        columnHeaders.add("Name");
        columnHeaders.add("Hours");
        columnHeaders.add("Grade");
        columnHeaders.add("Required By");

        int count = 0;
        for (String text : columnHeaders) {
            Label tmp = new Label(text);
            switch (node.getState()) {
            case COMPLETED:
                tmp.setStyle(getBaseHeaderStyle() + "-fx-background-color: rgba(54, 188, 152, 0.2);");
                break;
            case CURRENT:
                tmp.setStyle(getBaseHeaderStyle() + "-fx-background-color: rgba(255, 193, 7, 0.2);");
                break;
            case PLANNED:
                tmp.setStyle(getBaseHeaderStyle() + "-fx-background-color: rgba(108, 108, 108, 0.2);");
                break;
            }
            GridPane.setConstraints(tmp, count++, 0);
            header.getChildren().add(tmp);
        }

        return header;
    }

    private GridPane getSemesterRow(SemesterNode node) {
        GridPane row = getBlankSemesterRow();

        String number = "-";
        String name = "-";
        String hours = "-";
        String grade = "-";
        String requiredBy = "-";

        ArrayList<String> data = new ArrayList<String>();

        switch (node.getState()) {
        case COMPLETED:
            number = node.getCourse().getShortName();
            name = node.getCourse().getName();
            hours = "" + node.getCourse().getCreditHours();
            grade = node.getGrade().toString();
            if (node.getRequirement() != null) {
                if (node.getRequirement().getName() != null
                        && node.getRequirement().getName().trim().length() > 0) {
                    requiredBy = node.getRequirement().getName();
                } else {
                    requiredBy = node.getRequirement().getParent().getParent().getName() + " - " + node.getRequirement().getParent().getName();
                }
            }
            break;
        case CURRENT:
            number = node.getCourse().getShortName();
            name = node.getCourse().getName();
            hours = "" + node.getCourse().getCreditHours();
            if (node.getRequirement() != null) {
                if (node.getRequirement().getName() != null
                        && node.getRequirement().getName().trim().length() > 0) {
                    requiredBy = node.getRequirement().getName();
                } else {
                    requiredBy = node.getRequirement().getParent().getParent().getName() + " - " + node.getRequirement().getParent().getName();
                }
            }
            break;
        case PLANNED:
            if (node.getCourse() != null) {
                number = node.getCourse().getShortName();
                name = node.getCourse().getName();
                hours = "" + node.getCourse().getCreditHours();
                grade = node.getRequirement().getGrade().toString();
            }

           if (node.getRequirement() != null) {
                if (node.getRequirement().getName() != null
                        && node.getRequirement().getName().trim().length() > 0) {
                    requiredBy = node.getRequirement().getName();
                } else {
                    requiredBy = node.getRequirement().getParent().getParent().getName() + " - " + node.getRequirement().getParent().getName();
                }
            }
            break;
        }

        data.add(number);
        data.add(name);
        data.add(hours);
        data.add(grade);
        data.add(requiredBy);

        int count = 0;
        for (String text : data) {
            Label tmp = new Label(text);
            tmp.setStyle(getBaseCellStyle());
            GridPane.setConstraints(tmp, count++, 0);
            row.getChildren().add(tmp);
        }

        row.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                openNodeMenu(node);
            }
        });

        row.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switch (node.getState()) {
                case COMPLETED:
                    row.setStyle("-fx-background-color: rgba(54, 188, 152, 0.1);");
                    break;
                case CURRENT:
                    row.setStyle("-fx-background-color: rgba(255, 193, 7, 0.1);");
                    break;
                case PLANNED:
                    row.setStyle("-fx-background-color: rgba(108, 108, 108, 0.1);");
                    break;
                }
            }
        });

        row.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                row.setStyle(" -fx-background-color: transparent;");
            }
        });

        return row;
    }

    private void addUnplannedRequirements() {
        VBox semester = getSemesterVBox();

        VBox details = (VBox) semester.lookup("#semesterDetails");
        HBox spacer = (HBox) semester.lookup("#spacer");
        String blockLabel = "UNPLANNED";
        ((Label) semester.lookup("#semesterName")).setText(plan.getUndecidedBranches().size() + "  " + blockLabel);
        ((Label) semester.lookup("#semesterName")).setStyle("-fx-font-weight: BOLD; -fx-padding: 0 10; -fx-text-fill: white; -fx-font-size: 15; -fx-background-radius: 20; -fx-background-color: rgba(244, 81, 108, 1);");

        GridPane header = getBlankBranchRow();

        ArrayList<String> columnHeaders = new ArrayList<String>();
        columnHeaders.add("Name");
        columnHeaders.add("Required By");

        int count = 0;
        for (String text : columnHeaders) {
            Label tmp = new Label(text);
            tmp.setStyle(getBaseHeaderStyle() + "-fx-background-color: rgba(244, 81, 108, 0.2);");
            GridPane.setConstraints(tmp, count++, 0);
            header.getChildren().add(tmp);
        }

        details.getChildren().add(header);

        for (RequirementTree branch : plan.getUndecidedBranches()) {
            GridPane row = getBlankBranchRow();
            Label name = new Label(branch.getName());
            Label requiredBy = new Label(branch.getParent().getName());
            GridPane.setConstraints(name, 0, 0);
            GridPane.setConstraints(requiredBy, 1, 0);
            row.getChildren().add(name);
            row.getChildren().add(requiredBy);
            name.setStyle(getBaseCellStyle());
            requiredBy.setStyle(getBaseCellStyle());

            row.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    row.setStyle("-fx-background-color: rgba(244, 81, 108, 0.1);");
                }
            });

            row.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    row.setStyle(" -fx-background-color: transparent;");
                }
            });

            details.getChildren().add(row);

            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    openBranchMenu(branch);
                }
            });
        }

        details.setVisible(true);
        details.setManaged(true);
        spacer.setVisible(true);
        spacer.setManaged(true);
    }

    private VBox getSemesterVBox() {
        VBox semester = null;

        try {
            // https://coderanch.com/t/723526/java/fxml-include-dynamically-adding-removing
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("/library/graduation-plan-semester.fxml"));
            semester = fxmlLoader.<VBox>load();
            VBox details = (VBox) semester.lookup("#semesterDetails");
            HBox spacer = (HBox) semester.lookup("#spacer");

            ((ImageView)semester.lookup("#expandSemester")).setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (details.isVisible()) {
                        details.setVisible(false);
                        details.setManaged(false);
                        spacer.setVisible(false);
                        spacer.setManaged(false);
                    } else {
                        details.setVisible(true);
                        details.setManaged(true);
                        spacer.setVisible(true);
                        spacer.setManaged(true);
                    }
                }
            });

            details.setVisible(false);
            details.setManaged(false);
            spacer.setVisible(false);
            spacer.setManaged(false);
            semesterList.getChildren().add(semester);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return semester;
    }

    private void addSemesters(ArrayList<SemesterNode> nodes) {
        Collections.sort(nodes);

        String blockLabel = "";
        VBox semester = null;
        VBox details = null;
        HBox spacer = null;
        int row = 0;
        int col = 0;

        for (SemesterNode node : nodes) {
            if (!node.getTerm().toString().equals(blockLabel)) {
                semester = getSemesterVBox();
                details = (VBox) semester.lookup("#semesterDetails");
                blockLabel = node.getTerm().toString();
                spacer = (HBox) semester.lookup("#spacer");
                switch (node.getState()) {
                case COMPLETED:
                    ((Label) semester.lookup("#semesterName")).setText(blockLabel);
                    ((Label) semester.lookup("#semesterName")).setStyle("-fx-font-weight: BOLD; -fx-padding: 0 10; -fx-text-fill: white; -fx-font-size: 15; -fx-background-radius: 20; -fx-background-color: rgba(54, 188, 152, 1);");
                    
                    break;
                case CURRENT:
                    ((Label) semester.lookup("#semesterName")).setText(blockLabel + "  —  CURRENT");
                    ((Label) semester.lookup("#semesterName")).setStyle("-fx-font-weight: BOLD; -fx-padding: 0 10; -fx-text-fill: white; -fx-font-size: 15; -fx-background-radius: 20; -fx-background-color: rgba(255, 193, 7, 1);");
                    details.setVisible(true);
                    details.setManaged(true);
                    spacer.setVisible(true);
                    spacer.setManaged(true);
                    break;
                case PLANNED:
                    ((Label) semester.lookup("#semesterName")).setText(blockLabel);
                    ((Label) semester.lookup("#semesterName")).setStyle("-fx-font-weight: BOLD; -fx-padding: 0 10; -fx-text-fill: white; -fx-font-size: 15; -fx-background-radius: 20; -fx-background-color: rgba(108, 108, 108, 1);");
                    details.setVisible(true);
                    details.setManaged(true);
                    spacer.setVisible(true);
                    spacer.setManaged(true);
                    break;
                }
                details.getChildren().add(getSemesterHeader(node));
                row = 0;
            }
            col = 0;
            row++;

            details.getChildren().add(getSemesterRow(node));
        }
    }

    private void refresh() {
        semesterList.getChildren().clear();

        StudentApplication application = StudentApplication.getInstance();

        String title = Application.getInstance().getFirstName() + "'s Graduation Plan";
        headerPaneController.getPageTitle().setText(title);
        try {
            degree.setText(application.getMajors().get(0).getName());
        } catch (Exception e) {
            degree.setText("Undeclared");
        }

        classification.setText("Freshman");
        overallGpa.setText("" + application.getOverallGpa());
        majorGpa.setText("" + application.getOverallGpa());

        // System.out.println(plan.getCompleted());
        // System.out.println(plan.getCurrent());
        // ArrayList<SemesterNode> planned = plan.getPlanned();
        // Collections.sort(planned);
        // for (SemesterNode n : planned) {
        //     System.out.println(n);
        // }

        addSemesters(plan.getCompleted());
        addSemesters(plan.getCurrent());
        addUnplannedRequirements();
        addSemesters(plan.getPlanned());
    }

    private void openNodeMenu(SemesterNode node) {
        if (node.getState() != SemesterState.PLANNED) {
            System.out.println("View-only");
        } else {
            System.out.println("Edit");
        }
    }

    private void openBranchMenu(RequirementTree branch) {
        System.out.println("Select");
    }
}
