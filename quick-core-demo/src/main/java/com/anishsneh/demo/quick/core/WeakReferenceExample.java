package com.anishsneh.demo.quick.core;

import java.lang.ref.WeakReference;

public class WeakReferenceExample {
        public static void main(final String[] args) throws InterruptedException {
 
        	//Weak references are collected eagerly than soft references
            WeakReference r = new WeakReference(new String("I'm here"));
            WeakReference sr = new WeakReference("I'm here");
            System.out.println("before gc: r=" + r.get() + ", static=" + sr.get());
            System.gc();
            Thread.sleep(2000);
 
            // only r.get() becomes null the sr is coming from pool it seems
            System.out.println("after gc: r=" + r.get() + ", static=" + sr.get());
 
        }
}