package com.anishsneh.demo.quick.core.patterns.creational.singleton;

import java.io.Serializable;

public class StrictSingleton implements Serializable{
	
    private static final long serialVersionUID = 3119105548371608200L;
    private static final StrictSingleton STRICT_SINGLETON = new StrictSingleton();
    
    //AVOID NEW INSTANCE BY REFLECTION 
    private StrictSingleton() { 
    	if( StrictSingleton.STRICT_SINGLETON != null ) {
            throw new InstantiationError( "Creating of this object is not allowed." );
        }
    }
    
    public static StrictSingleton getInstance(){
        return STRICT_SINGLETON;
    }
    
    //AVOID NEW INSTANCE BY CLONING
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning of this class is not allowed"); 
    }
    
    //AVOID NEW INSTANCE BY DESERIALIZATION
    protected Object readResolve() {
        return STRICT_SINGLETON;
    }    
}

