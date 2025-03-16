module artapi {
    requires java.net.http;
    requires org.json;
    exports org.gianfranco.artapi.service;
    exports org.gianfranco.artapi.model;
}