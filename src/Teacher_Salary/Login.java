package Teacher_Salary;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private Stage window;
    private final StackPane stackpane = new StackPane();
    private final ImageView imageview = new ImageView(
            new Image("file:D:\\IJ_WorkSpace\\out\\production\\IJ_WorkSpace\\Teacher_Salary\\image\\R-C.jpg"));
    private final HBox hbox = new HBox(10);
    private final GridPane gridpane = new GridPane();
    private final Button Bt_Login = new Button("Login"); // 设置登录按钮
    private final Button Bt_Singup = new Button("SingUp"); // 设置注册按钮
    private final Label lb1 = new Label("account:"); // 设置用户名标签
    private final Label lb2 = new Label("passwd:"); // 设置密码标签
    private TextField txfd1 = null; // 设置用户名填充域
    private PasswordField txfd2 = null; // 设置密码填充域 密码不回显
    private Statement stmt1;
    private Statement stmt2;
    private ResultSet rs1 = null;
    private ResultSet rs2 = null;

    public void start(Stage stage) throws Exception {
        window = stage;
        Bt_Login.setStyle("-fx-background-color:DODGERBLUE ;-fx-text-fill: white");
        Bt_Singup.setStyle("-fx-background-color:DODGERBLUE ;-fx-text-fill: white");

        Txfd1_attribute(); // 设置用户名填充属性
        Txfd2_attribute(); // 设置密码填充属性
        body();
        LogIn(); // 调用登录方法
        SingeUp(); // 调用注册方法

        hbox.getChildren().add(Bt_Singup);
        stackpane.getChildren().addAll(imageview, hbox, gridpane);
        Scene scene = new Scene(stackpane, 300, 300);
        window.setX(500);
        window.setY(300);
        window.setScene(scene);
        window.setTitle("login");
        window.show();

    }

    private void LogIn() {
        Choice choice = new Choice();


        //登录监听器
        Bt_Login.setOnAction(e -> {

            try {
                choice.start(window);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
/*
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
                stmt1 = con.createStatement();
                stmt2 = con.createStatement();
                rs1 = stmt1.executeQuery("select `account` from passwd_date\n");
                rs2 = stmt2.executeQuery("select passwd from passwd_date");

            } catch (Exception ex) {
                ex.getStackTrace();
            }
/*
            try {
                do {
                    rs1.next();  //用于将光标从当前位置移动到下一行。
                    if (rs1.getString(1).matches(txfd1.getText())) {

                        do {
                            rs2.next();
                            if (rs2.getString(1).matches(txfd2.getText())) {

                                choice.start(window);

                            } else {
                                System.out.println("hhhh");
                            }

                        } while (rs2.next());

                    } else {
                        System.out.println("用户不存在");
                    }

                } while (rs1.next());
            } catch (Exception ex) {
                ex.getStackTrace();
            }

            */
        });


    }

    private void SingeUp() {
        //注册用户监听器
        Bt_Singup.setOnAction(e -> {
            Register register = new Register();
            try {
                register.start(window);
            } catch (Exception e1) {
                // TODO 自动生成的 catch 块
                e1.printStackTrace();
            }
        });
    }

    private void body() {

        imageview.setFitHeight(810);
        imageview.setFitWidth(1535); // 背景图片属性
        hbox.setAlignment(Pos.BOTTOM_LEFT); // 注册按钮位置设置在左下
        hbox.setPadding(new Insets(0, 0, 10, 10));
        gridpane.setHgap(5); // 节点与页面上下边距
        gridpane.setVgap(5); // 节点与页面左右距离
        gridpane.add(lb1, 0, 0);
        gridpane.add(txfd1, 1, 0);
        gridpane.add(lb2, 0, 1);
        gridpane.add(txfd2, 1, 1);
        gridpane.add(Bt_Login, 1, 2);
        gridpane.setAlignment(Pos.CENTER); //节点初始位置
    }

    public void Txfd1_attribute() {
        txfd1 = new TextField() {
            public void replaceText(int start, int end, String text) {
                if (!text.matches("[a~z .,/]")) {
                    super.replaceText(start, end, text);
                }
            }

            public void replaceSelection(String text) {
                if (text.matches("[a~z .,/]")) {
                    super.replaceSelection(text);
                }
            }
        };
        txfd1.setPromptText("8~20数字、字母 不能存在符号");
    }

    public void Txfd2_attribute() {
        txfd2 = new PasswordField() {
            @Override
            public void replaceText(int start, int end, String text) {
                if (!text.matches("[. /:]")) {
                    super.replaceText(start, end, text);
                }
            }

            public void replaceSelection(String text) {
                if (!text.matches("[. /;:]")) {
                    super.replaceSelection(text);
                }
            }
        };
        txfd2.setPromptText("8~20数字、字母 能存在符号");   //文本域提示语
        txfd2.setPrefColumnCount(13);    // 文本域长度
    }

}
