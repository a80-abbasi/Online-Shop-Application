package graphics.products;

import Model.Account.Account;
import Model.Account.Customer;
import Model.Product.Comment;
import Model.Product.Product;
import Model.Product.Score;
import graphics.App;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.util.*;

public class ProductPageController {

    public TextField searchField;
    public ImageView magnifier;
    public Label nameLabel;
    public Label categoryLabel;
    public Label propertiesLabel;
    public StackPane imageStackPane;
    public Label sellerNameLabel;
    public Label finalPriceLabel;
    public Text previousPriceLabel;
    public Label offPercentageLabel;
    public Pane addToCartButton;
    public Pane propertiesPane;
    public Pane commentsPane;
    public AnchorPane ratePane;
    public ProgressBar progressBar5;
    public Label scoreLabel;
    public ProgressBar progressBar4;
    public ProgressBar progressBar3;
    public ProgressBar progressBar2;
    public ProgressBar progressBar1;
    public Label numberOfScoresLabel;
    public Label noteForRateLabel;
    public Pane soldOutPane;
    public Label ratesLabel;
    public Label propertiesTitleLabel;
    public Pane firstPane;
    public Pane rateBox;
    public Label numberOfScores5;
    public Label numberOfScores4;
    public Label numberOfScores3;
    public Label numberOfScores2;
    public Label numberOfScores1;
    public Pane imagePane;
    public Label productAddedLabel;
    public Label timeLeftLabel;
    public Label timeLabel;
    public Label calculatedLeftTime;
    public Label numberOfCommentsLabel;
    public Button LeaveCommentButton;
    public Label explanationsLabel;
    public Label companyNameLabel;
    public Label visitNumberLabel;
    public Label helpUsLabel;

    public TextField titleTextField;
    public TextArea commentTextArea;
    public Label XLabel;
    public Label commentNoteLabel;


    private Product product;
    private ArrayList<Pane> showingComments = new ArrayList<>();
    private boolean hasRated;
    private Stage commentPopUp;
    private ProductPageController parentForCommentPage;

    public void initialize(){

    }

    public void setEveryThing(){
        product.setVisitNumber(product.getVisitNumber() + 1);
        setLabels();
        setRates();
        resizeImage();
        setPrice();

        setAddToCartButton();
        setComments();
        setExplanations();
        setPropertiesPane();

        setOffLeftTimeLabel();
    }

    private void setOffLeftTimeLabel() {
        if (product.getOff() != null && product.getOff().isAvailable()){
            timeLeftLabel.setOpacity(1);
            timeLabel.setOpacity(1);
            setOffEndTime(product, timeLabel);
        }
        else {
            timeLeftLabel.setOpacity(0);
            timeLabel.setOpacity(0);
        }
    }

    public static void setOffEndTime(Product product, Label label){
        String endTime = product.getOff().getEndTime().toString();
        String firstAndTimeZone = endTime.substring(0, endTime.lastIndexOf(" "));
        label.setText(firstAndTimeZone.substring(0, firstAndTimeZone.lastIndexOf(" ")) +
                endTime.substring(endTime.lastIndexOf(" ")));
    }

    private void setPropertiesPane() {
        int x = 20;
        int y = 50;
        HashMap<String, Integer> properties = product.getSpecialFeatures();
        for (Map.Entry<String, Integer> entry : properties.entrySet()) {
            Pane pane = getPaneOfProperty(entry);
            propertiesPane.getChildren().add(pane);
            pane.setLayoutX(x);
            pane.setLayoutY(y);
            y += (int) (pane.getBoundsInParent().getHeight()) + 60;
            if (y > propertiesPane.getBoundsInParent().getHeight()){
                propertiesPane.setPrefHeight(y);
            }
        }
    }

    private Pane getPaneOfProperty(Map.Entry<String, Integer> property){
        Label featureLabel = new Label(property.getKey());
        Pane featurePane = new Pane(featureLabel);

        Label valueLabel = new Label(String.valueOf(property.getValue()));
        Pane valuePane = new Pane(valueLabel);

        Pane[] panes = {featurePane, valuePane};
        Label[] labels = {featureLabel, valueLabel};

        Arrays.stream(labels).forEach(e -> {
            e.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 20");
            e.setWrapText(true);
            e.setLayoutY(13);
            e.setLayoutX(30);
        });
        Arrays.stream(panes).forEach(e -> {
            e.setStyle("-fx-background-color:  #d5d5d5");
            e.setMinHeight(50);
            e.setPadding(new Insets(10, 10, 10, 10));
        });
        featureLabel.setPrefWidth(propertiesPane.getPrefWidth() / 3 - 40);
        valueLabel.setPrefWidth(2 * propertiesPane.getPrefWidth() / 3 - 40 - 90);

        Pane mainPane = new Pane(featurePane, valuePane);
        valuePane.setLayoutX(propertiesPane.getPrefWidth() / 3 + 40);
        return mainPane;
    }

    private void setPrice(){
        if (product.getExistingNumber() <= 0){
            soldOutPane.setOpacity(1);
        }
        finalPriceLabel.setText(String.format("%.1f$", product.getPriceWithOff()));
        if (product.getPriceWithOff() != product.getPrice()){
            previousPriceLabel.setText(String.format("%.1f$", product.getPrice()));
            offPercentageLabel.setText(String.format("%d%%", product.getOff().getOffAmount()));
        }
        else {
            previousPriceLabel.setOpacity(0);
            offPercentageLabel.setOpacity(0);
        }
    }

    private void setLabels(){
        nameLabel.setText(product.getProductName());
        categoryLabel.setText(product.getProductCategory() == null ? "Others" : product.getProductCategory().getName());
        companyNameLabel.setText(product.getCompanyName());
        sellerNameLabel.setText(product.getProductSeller().getName());
        visitNumberLabel.setText(String.valueOf(product.getVisitNumber()));
        setPropertiesLabel();
    }

    private void setExplanations(){
        explanationsLabel.setText(product.getExplanations() == null ? "" : product.getExplanations());
    }

    private void setComments(){
        ArrayList<Comment> comments = product.getProductComments();
        showingComments.clear();
        numberOfCommentsLabel.setText(comments.size() + " comments");
        for (Comment comment : comments) {
            showingComments.add(getCommentsPane(comment));
        }
        showComments();
    }

    private void showComments(){
        int x = 20;
        int y = 150;
        for (Pane pane : showingComments) {
            commentsPane.getChildren().add(pane);
            pane.setLayoutX(x);
            pane.setLayoutY(y);
            y += (int) (pane.getBoundsInParent().getHeight()) + 60;
            if (y > commentsPane.getBoundsInParent().getHeight()){
                commentsPane.setPrefHeight(y);
            }
        }
    }

    private Pane getCommentsPane(Comment comment){
        Pane pane = new Pane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setStyle("-fx-background-radius: 20 20 20 0; -fx-background-color: #83d3ff");
        Label title = new Label("title: " + comment.getTitle());
        Label cm = new Label(comment.getComment());
        Account account = comment.getAccount();
        Label name = new Label("name: " + account.getName() + account.getLastName());
        Label[] labels = {title, cm, name};
        Arrays.stream(labels).forEach(e -> {
            e.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-font-size: 20");
            e.setLayoutX(10);
            e.setWrapText(true);
        });
        pane.getChildren().addAll(labels);
        int x = 0;
        if (comment.isBought()){
            ImageView imageView = new ImageView(new Image("file:src\\main\\resources\\Images\\blueTick.png"));
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(40);
            pane.getChildren().add(imageView);
            x = 10;
            name.setLayoutX(50);
        }
        name.setLayoutY(10);
        title.setLayoutY(30 + x);
        cm.setLayoutY(55 + x);
        return pane;
    }

    private void setAddToCartButton(){
        if (product.getExistingNumber() > 0){
            addToCartButton.setOnMouseClicked(e -> {
                productAddedLabel.setOpacity(1);
                Customer.addProductToTmpCart(product);
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), actionEvent -> productAddedLabel.setOpacity(0)));
                timeline.setCycleCount(1);
                timeline.play();
            });
        }
    }

    private void setRates() {
        Rating rating = new Rating(5);
        rating.setUpdateOnHover(false);
        rating.setPartialRating(true);
        rating.setDisable(true);
        rating.setRating(product.getTotalScore());
        rating.setLayoutX(ratesLabel.getLayoutX() + 100);
        rating.setLayoutY(ratesLabel.getLayoutY());
        firstPane.getChildren().add(rating);
        Rating rate = new Rating(5);
        rate.setUpdateOnHover(true);
        rate.setPartialRating(false);
        rate.setLayoutX(noteForRateLabel.getLayoutX() + 25);
        rate.setLayoutY(noteForRateLabel.getLayoutY() - 50);
        rateBox.getChildren().add(rate);
        rate.setOnMouseClicked(e -> {
            if (hasRated) {
                e.consume();
            } else {
                hasRated = true;
                rateProduct(rate);
            }
        });
        setRatesAndProgresses();
    }

    private void setRatesAndProgresses(){
        ArrayList<Score> scores = product.getAllScores();
        scoreLabel.setText(String.format("%.1f", product.getTotalScore()));
        long numberOfScores = scores.size();
        numberOfScoresLabel.setText("from " + numberOfScores + " rates");
        long numberOf1Scores = scores.stream().filter(e -> e.getScore() == 1).count();
        progressBar1.setProgress((double) numberOf1Scores / numberOfScores);
        numberOfScores1.setText(numberOf1Scores + " rates");
        long numberOf2Scores = scores.stream().filter(e -> e.getScore() == 2).count();
        progressBar2.setProgress((double) numberOf2Scores / numberOfScores);
        numberOfScores2.setText(numberOf2Scores + " rates");
        long numberOf3Scores = scores.stream().filter(e -> e.getScore() == 3).count();
        progressBar3.setProgress((double) numberOf3Scores / numberOfScores);
        numberOfScores3.setText(numberOf3Scores + " rates");
        long numberOf4Scores = scores.stream().filter(e -> e.getScore() == 4).count();
        progressBar4.setProgress((double) numberOf4Scores / numberOfScores);
        numberOfScores4.setText(numberOf4Scores + " rates");
        long numberOf5Scores = scores.stream().filter(e -> e.getScore() == 5).count();
        progressBar5.setProgress((double) numberOf5Scores / numberOfScores);
        numberOfScores5.setText(numberOf5Scores + " rates");
    }

    private void rateProduct(Rating rating){
        Account account = Account.getLoggedInAccount();
        boolean flag = false;
        if (account == null){
            noteForRateLabel.setText("You must log in to rate product");
            flag = true;
        }
        else if (account instanceof Customer){
            Customer customer = (Customer) account;
            product.addRate(customer, (int) (rating.getRating() + 0.5));
            setRates();
        }
        else {
            noteForRateLabel.setText("You must be logged in as a customer");
            flag = true;
        }
        if (flag){
            noteForRateLabel.setTextFill(Color.RED);
        }
    }

    private void resizeImage() {
        for (Node image : imageStackPane.getChildren()) {
            ((ImageView)image).setFitWidth(450);
        }
    }

    private void setPropertiesLabel(){
        HashMap<String, Integer> properties = product.getSpecialFeatures();
        if (properties != null) {
            StringBuilder features = new StringBuilder();
            for (Map.Entry<String, Integer> entry : properties.entrySet()) {
                features.append(entry.getKey()).append(": ").append(entry.getValue());
            }
            propertiesLabel.setText(features.toString());
        }
        else {
            propertiesLabel.setOpacity(0);
            propertiesTitleLabel.setOpacity(0);
        }
    }

    public void magnifierClicked(MouseEvent event) {
        if (!searchField.getText().isBlank()){
            try {
                ProductsController productsController = (ProductsController) App.setRoot("productsMenu");
                productsController.magnifierClicked(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void magnifierMouseEntered(MouseEvent mouseEvent) {
        magnifier.setOpacity(0.25);
    }

    public void magnifierMouseExited(MouseEvent mouseEvent) {
        magnifier.setOpacity(1);
    }

    private void mouseEntered(Label label){
        label.setOpacity(0.5);
    }

    private void mouseExited(Label label){
        label.setOpacity(1);
    }

    private void shadowOnMouseHover(Node node){
        shadowOnMouseEntered(node);
        shadowOnMouseExited(node);
    }

    private void shadowOnMouseEntered(Node node){
        node.setOnMouseEntered(e -> {
            node.setOpacity(0.75);
            node.setStyle("-fx-border-color :  #c5c5c5; -fx-border-radius: 10");
            node.getScene().setCursor(Cursor.HAND);
        });
    }

    private void shadowOnMouseExited(Node node){
        node.setOnMouseExited(e -> {
            node.setOpacity(1);
            node.setStyle("-fx-border-color :  #c5c5c5; -fx-border-radius: 0");
            node.getScene().setCursor(Cursor.DEFAULT);
        });
    }

    public StackPane getImageStackPane() {
        return imageStackPane;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void leaveCommentButtonPressed(ActionEvent actionEvent) {
        Account account = Account.getLoggedInAccount();
        boolean flag = false;
        if (account == null){
            helpUsLabel.setText("You must log in to rate product");
            flag = true;
        }
        else if (account instanceof Customer){
            Customer customer = (Customer) account;
            openCommentPage();
        }
        else {
            helpUsLabel.setText("You must be logged in as a customer");
            flag = true;
        }
        if (flag){
            helpUsLabel.setTextFill(Color.RED);
        }
    }

    private void openCommentPage(){
        if (commentPopUp == null) {
            commentPopUp = new Stage();
            Scene scene;
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("commentPage.fxml"));
                scene = new Scene(fxmlLoader.load());
                ProductPageController newPage = ((ProductPageController) fxmlLoader.getController());
                newPage.setProduct(product);
                newPage.setParentForCommentPage(this);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            commentPopUp.setScene(scene);
            commentPopUp.setTitle("Leave a comment");
            commentPopUp.setResizable(false);
            commentPopUp.initStyle(StageStyle.UNDECORATED);
            commentPopUp.showAndWait();
        }
    }

    public void sendCommentButtonPressed(ActionEvent actionEvent) {
        if (!commentTextArea.getText().isBlank()){
            String comment = commentTextArea.getText();
            String title = titleTextField.getText();
            new Comment(Account.getLoggedInAccount(), product, comment, title);
            parentForCommentPage.setComments();
            parentForCommentPage.commentPopUp.close();
            parentForCommentPage.commentPopUp = null;
        }
        else {
            commentNoteLabel.setText("Comment Text Can't Be Blank");
            commentNoteLabel.setTextFill(Color.RED);
        }
    }

    public void XMouseEntered(MouseEvent mouseEvent) {
        mouseEntered(XLabel);
    }

    public void XMouseExited(MouseEvent mouseEvent) {
        mouseExited(XLabel);
    }

    public void XMouseClicked(MouseEvent mouseEvent) {
        parentForCommentPage.commentPopUp.close();
        parentForCommentPage.commentPopUp = null;
    }

    public void setParentForCommentPage(ProductPageController parentForCommentPage) {
        this.parentForCommentPage = parentForCommentPage;
    }
}
