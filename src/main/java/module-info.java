module Trabalho_posto.Posto_combustivel {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.base;

    opens Trabalho_posto.Posto_combustivel to javafx.fxml;
    
    exports classes;
    exports Trabalho_posto.Posto_combustivel;
}
