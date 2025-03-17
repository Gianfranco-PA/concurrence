module disneyapi {
    requires java.net.http;
    requires org.json;
    exports org.gianfranco.disneyapi.service;
    exports org.gianfranco.disneyapi.model;
}