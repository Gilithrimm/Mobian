package com.safenar;

public class Thread implements Runnable{
    @Override
    public void run() {//We all know that you're bad at thread making, so why is it here in a first place?
        //What to do here actually? I'm lost. I should go sleep...  10pm.
    }
    public void run(ThreadFunc func){
        run();//Why is it here in a first place?
        func.exec();
    }
}
