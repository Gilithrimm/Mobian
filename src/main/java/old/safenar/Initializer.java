package old.safenar;

/**
 * This interface is used to decide which classes should have been initialized.
 * */
public interface Initializer {
    void load();
    void init();
}
