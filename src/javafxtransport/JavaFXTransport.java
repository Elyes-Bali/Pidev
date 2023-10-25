package javafxtransport;

import javafx.application.Application;
import javafx.stage.Stage;
import Besttrip.agence.services.ServiceTransport;
import Besttrip.agence.entity.Transport;
import Besttrip.agence.tools.MyDB;

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
