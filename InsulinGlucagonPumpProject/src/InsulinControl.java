import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class InsulinControl {
	public static int insulinQty = 100;
	private static boolean autorun = true;
	public static int sugarLevel = 100;
	static MainGUI panel;
	
	public static int getInsulinQty() {
        return insulinQty;
    }

    public static void setInsulinQty(int qty) {
        insulinQty = qty;
    }

    public static void inject() {

		if( Dose.compInsulinDose > 0.0D ) {
			
			int insulin = (int) Dose.compInsulinDose;
                        
                        System.out.println(insulin);
                        injectInsulin(insulin);
			reduceSugarLevel(insulin);
		}
		}
    
    public static void reduceSugarLevel(int insulin) {
		
		int delay = 1000;
        
        for(int reduce = insulin * 4;reduce>0;reduce--){
        
           Timer timer3 = new Timer(delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	                   
                    int sugarLevel = InsulinControl.sugarLevel;
                    sugarLevel--;
                    InsulinControl.sugarLevel = sugarLevel;
                    //Bolus.setSugarLevel(sugarLevel);
                    //Home.setSugarLevel(sugarLevel);
                    
                } 
            });
            timer3.setRepeats(false);
            timer3.start();
        }
		
	}

    
    public static void injectInsulin(int dose){
    	
    	int qty = getInsulinQty();

    	if( dose > qty ){
    		
    		qty = 0;
    		
    	} else {
    		
    		qty -= dose;
        	
    	}
    	
    	setInsulinQty(qty);
    	panel.setInsulin(qty);
    	 
    }
    
    
    public static boolean isAutorun() {
		return autorun;
	}

	public static void setAutorun(boolean autorun) {
		InsulinControl.autorun = autorun;
	}
    
	public static void main(String[] args) {
		panel = new MainGUI();
		java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            
        }
    });
}
}
