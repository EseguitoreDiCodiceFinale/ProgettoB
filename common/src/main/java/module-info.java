module common {
 requires java.rmi;

 opens org.example.common to clientES, serverES;
 exports org.example.common;
}