package com.safenar;
/**
 * This interface is used to decide which classes should have been initialized as main classes in storypack.
 * Class implementing this interface isn't required for proper working.
 * *///at least 4 now
public interface Initializer {
    void load();
    void init();
}
