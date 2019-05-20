package gui;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kolekce.AbstrDoubleList;
import kolekce.DoubleList;
import kolekce.KolekceException;
import kolekce.Serializator;
import pamatky.Pamatky;
import pamatky.PamatkyInterface;
import pamatky.Zamek;
import pamatky.ZamekGenerator;

/**
 *
 * @author user
 */
public class ProgPamatky extends Application {

    private Stage primaryStage;

    private ListView<Zamek> zamekListView;
    private PamatkyInterface pamatky;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        pamatky = new Pamatky();
        zamekListView = createListView(700, 300);
        VBox tlacitkaVBox = createTlacitkaVBox();

        BorderPane root = new BorderPane();
        root.setRight(zamekListView);
        root.setCenter(tlacitkaVBox);
        root.setPadding(new Insets(5));

        Scene scene = new Scene(root, 1200, 700);

        stage.setTitle("Památky");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private ListView<Zamek> createListView(double width, double height) {
        ListView<Zamek> temp = new ListView<>();

        temp.setPrefWidth(width);
        temp.setPrefHeight(height);

        return temp; //vytvoření listview
    }

    private VBox createTlacitkaVBox() {
        VBox temp = new VBox(10);

        temp.getChildren().addAll(
                createButton("Import dat ze souboru", () -> {
                    try {
                        pamatky.zrus();
                        pamatky.importDatZTXT();
                        refreshList();
                    } catch (IOException | KolekceException e) {
                        showDialog("IO", "Data se nepodařilo nahrát", Alert.AlertType.ERROR);
                    }

                }),
                createButton("Vlož zámek", () -> {
                    new VlozZamekDialog().showAndWait()
                            .ifPresent((response) -> {
                                try {
                                    pamatky.vloz(response);
                                    refreshList();
                                } catch (KolekceException ex) {
                                    Logger.getLogger(ProgPamatky.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });

                }),
                createButton("Zpřístupni max", () -> { //Najde max zámek a selectne ho
                    try {
                        Zamek zamek = pamatky.zpristupniMax();
                        zamekListView.getSelectionModel().select(zamek);
                    } catch (KolekceException ex) {
                        Logger.getLogger(ProgPamatky.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }),
                createButton("Odeber max", () -> { //Odebere max zámek a vypíše ho v dialogu
                    try {
                        Zamek zamek = pamatky.odeberMax();
                        showDialog("Max prvek", "Maximální prvek: " + zamek, AlertType.INFORMATION);
                        refreshList();
                    } catch (KolekceException ex) {
                        Logger.getLogger(ProgPamatky.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }),
                createButton("Je prazdný", () -> {
                    showDialog("Prázdnost heapu", "Heap je prázdný: " + pamatky.jePrazdny(), AlertType.INFORMATION);
                }),
                createButton("Zruš", () -> {
                    pamatky.zrus();
                    refreshList();
                }),
                createButton("Ulož", () -> { //Serializuje
                    File file = new FileChooser().showSaveDialog(primaryStage);
                    try {
                        if (file != null) {
                            Iterator<Zamek> iterator = pamatky.iterator();
                            DoubleList<Zamek> zamky = new AbstrDoubleList<>(); //Zde jsem taky použil vlastní list ze SEM A

                            while (iterator.hasNext()) {
                                zamky.vlozPosledni(iterator.next());
                            }
                            Serializator.uloz(file.getPath(), zamky);
                        }
                    } catch (IOException | NullPointerException ex) {
                        showDialog("Error při ukládání", "Error", AlertType.ERROR);
                    }
                }),
                createButton("Načti", () -> { //Načte serializovaný soubor
                    try {
                        File file = new FileChooser().showOpenDialog(primaryStage);
                        if (file != null) {
                            DoubleList<Zamek> zamky = new AbstrDoubleList<>(); //Použil jsem vlastní list ze SEM A
                            zamky = (AbstrDoubleList<Zamek>) Serializator.nacti(file.getPath(), zamky);

                            Iterator<Zamek> iterator = zamky.iterator();
                            pamatky.zrus();
                            while (iterator.hasNext()) {
                                Zamek z = iterator.next();
                                pamatky.vloz(z);
                            }
                            refreshList();
                        }
                    } catch (IOException | KolekceException | NullPointerException ex) {
                        showDialog("Error při načítání", "Error", AlertType.ERROR);
                    }

                }),
                createButton("Generuj", () -> { //Vygeneruje zámky, vrátí iterátor a naplní
                    try {
                        Iterator<Zamek> iterator = ZamekGenerator.generujZamky(10);
                        while (iterator.hasNext()) {
                            Zamek z = iterator.next();
                            pamatky.vloz(z);
                        }
                        refreshList();
                    } catch (KolekceException e) {
                        showDialog("Error", "Error při vkládání", AlertType.ERROR);
                    }
                }),
                createButton("Přebuduj", () -> {
                    try {
                        pamatky.prebuduj();
                        refreshList();
                    } catch (KolekceException ex) {
                        Logger.getLogger(ProgPamatky.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }),
                createButton("Vybuduj", () -> { //Vygeneruje zámky a vybuduje z nich
                    try {
                        Zamek[] pole = new Zamek[10];
                        int i = 0;
                        //Vygeneruje zámky, vrátí iterátor a naplní
                        Iterator<Zamek> iterator = ZamekGenerator.generujZamky(10);
                        while (iterator.hasNext()) {
                            Zamek z = iterator.next();
                            pole[i++] = z;
                        }
                        pamatky.vybuduj(pole);
                        refreshList();
                    } catch (KolekceException ex) {
                        Logger.getLogger(ProgPamatky.class.getName()).log(Level.SEVERE, null, ex);
                    }
                })
        );

        return temp;

    }

    private void showDialog(String title, String context, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(context);
        alert.setHeaderText("");
        alert.showAndWait(); //Pomocná metoda na tvorbu dialogů
    }

    private Button createButton(String text, Runnable action) {
        Button temp = new Button(); //Pomocná metoda na vytvoření tlačítek
        temp.setMaxWidth(Double.MAX_VALUE);
        temp.setText(text);
        temp.setOnAction((e) -> action.run());
        temp.setPrefWidth(100);

        return temp;
    }

    private void refreshList() {
        zamekListView.getItems().clear();

        Iterator<Zamek> iterator = pamatky.iterator();
        while (iterator.hasNext()) {
            zamekListView.getItems().add(iterator.next());
        }
    }

}
