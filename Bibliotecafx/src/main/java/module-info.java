module org.example.bibliotecafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    opens org.example.bibliotecafx to javafx.fxml;
    opens org.example.bibliotecafx.entities to org.hibernate.orm.core, javafx.fxml;

    exports org.example.bibliotecafx;
    exports org.example.bibliotecafx.entities;

}
