module com.htn.quanlithuvien {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.htn.quanlithuvien to javafx.fxml;
    exports com.htn.quanlithuvien;
    exports com.htn.pojo;
    exports com.htn.services;
    exports com.htn.utils;
}
