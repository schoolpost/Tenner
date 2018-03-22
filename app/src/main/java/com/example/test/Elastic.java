package com.example.test;

/**
 * Created by moc on 3/22/18.
 */

class Elastic {
    private static final Elastic ourInstance = new Elastic();

    static Elastic getInstance() {
        return ourInstance;
    }

    private Elastic() {
    }
}
