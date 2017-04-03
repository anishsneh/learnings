package com.anishsneh.demo.quick.core;

import java.util.HashMap;
import java.util.Iterator;
 
/**
 * The Class FinalClassExample.
 */
public final class FinalClassExample {
 
    /** The id. */
    private final int id;
 
    /** The name. */
    private final String name;
 
    /** The test map. */
    private final HashMap<String,String> testMap;
 
    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }
 
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
 
    /**
     * Accessor function for mutable objects.
     *
     * @return the test map
     */
    public HashMap<String, String> getTestMap() {
        //return testMap;
        return (HashMap<String, String>) testMap.clone();
    }
 
    /**
     * Constructor performing Deep Copy.
     *
     * @param i the i
     * @param n the n
     * @param hm the hm
     */
 
    public FinalClassExample(final int i, final String n, final HashMap<String,String> hm){
        System.out.println("Performing Deep Copy for Object initialization");
        this.id=i;
        this.name=n;
        final HashMap<String,String> tempMap = new HashMap<String,String>();
        String key;
        final Iterator<String> it = hm.keySet().iterator();
        while(it.hasNext()){
            key=it.next();
            tempMap.put(key, hm.get(key));
        }
        this.testMap=tempMap;
    }
 
    /**
     * Constructor performing Shallow Copy.
     *
     * @param args the arguments
     */
    /**
    public FinalClassExample(int i, String n, HashMap<String,String> hm){
        System.out.println("Performing Shallow Copy for Object initialization");
        this.id=i;
        this.name=n;
        this.testMap=hm;
    }
    */
 
    /**
     * To test the consequences of Shallow Copy and how to avoid it with Deep Copy for creating immutable classes
     * @param args
     */
    public static void main(final String[] args) {
    	final HashMap<String, String> h1 = new HashMap<String,String>();
        h1.put("1", "first");
        h1.put("2", "second");
 
        String s = "original";
 
        int i=10;
 
        FinalClassExample ce = new FinalClassExample(i,s,h1);
 
        //Lets see whether its copy by field or reference
        System.out.println(s==ce.getName());
        System.out.println(h1 == ce.getTestMap());
        //print the ce values
        System.out.println("ce id:"+ce.getId());
        System.out.println("ce name:"+ce.getName());
        System.out.println("ce testMap:"+ce.getTestMap());
        //change the local variable values
        i=20;
        s="modified";
        h1.put("3", "third");
        //print the values again
        System.out.println("ce id after local variable change:"+ce.getId());
        System.out.println("ce name after local variable change:"+ce.getName());
        System.out.println("ce testMap after local variable change:"+ce.getTestMap());
 
        HashMap<String, String> hmTest = ce.getTestMap();
        hmTest.put("4", "new");
 
        System.out.println("ce testMap after changing variable from accessor methods:"+ce.getTestMap());
 
    }
 
}
