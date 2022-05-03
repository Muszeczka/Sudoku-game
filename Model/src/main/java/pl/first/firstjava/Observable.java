package pl.first.firstjava;

public interface Observable {
    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObservers(String text);
}
