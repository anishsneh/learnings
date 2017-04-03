package com.anishsneh.demo.quick.core;

import java.io.Serializable;

public class SingletonStrict implements Serializable, Cloneable{
	
    private static final long serialVersionUID = 3119105548371608200L;
    private static final SingletonStrict STRICT_SINGLETON = new SingletonStrict();
    
    //AVOID NEW INSTANCE BY REFLECTION 
    private SingletonStrict() { 
    	if( SingletonStrict.STRICT_SINGLETON != null ) {
            throw new InstantiationError( "Creating of this object is not allowed." );
        }
    }
    
    public static SingletonStrict getInstance(){
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

