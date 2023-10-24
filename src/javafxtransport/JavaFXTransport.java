package javafxtransport;

import javafx.application.Application;
import javafx.stage.Stage;
import tn.esprit.Services.ServiceTransport;
import tn.esprit.entity.Transport;
import tn.esprit.tools.MyDB;

public class JavaFXTransport extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MyDB db = new MyDB();
        System.out.println("TTTTT");
        Transport t1 = new Transport(3, "bateau", "13/5/2022", "14/5/2022",123);
        ServiceTransport st = new ServiceTransport();
        st.addTransport(t1);
        System.out.println(t1);
        
      
    }
}
