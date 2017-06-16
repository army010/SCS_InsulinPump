import javax.swing.JOptionPane;

public class Dose {
	static int r0; // the reading before r1
	static int r1; // the previous reading
	static double compInsulinDose = 0.0D; // insulin dose required according to the insulin dosage computation
	static double cumulativeInsulinDose; // total insulin dose that has already been delivered over the day
	static double maxInsulinDailyDose = 35.0D;
	static double maxInsulinSingleDose = 5.0D;
	static double minInsulinDose = 1.0D;
	static double compGlucagonDose = 0.0D; // glucagon dose required according to the glucagon dosage computation
	static double cumulativeGlucagonDose; // total glucagon dose that has already been delivered over the day
	static double maxGlucagonDailyDose = 35.0D;
	static double maxGlucagonSingleDose = 5.0D;
	static double minGlucagonDose = 1.0D;
	
	static int safeMin = 70;
	static int safeMax = 130;
	
        int sugarLevel;
	
	double basalDose = 1.0D;
	Messages messages = new Messages();
        
	
	public double getBasalDose() {
		return basalDose;
	}
	
	public double getInsulinDose() {
		return compInsulinDose;
	}
	
	public double getGlucagonDose() {
		return compGlucagonDose;
	}
	
	public static void setCumulativeDose(){
		//cumulativeInsulinDose = cumulativeInsulinDose + compInsulinDose;
		//cumulativeGlucagonDose = cumulativeGlucagonDose + compGlucagonDose;
                cumulativeInsulinDose += compInsulinDose;
                cumulativeGlucagonDose += compGlucagonDose;
	}
	public static void computeDose(int r2){
		
		if( r0 == 0 || r1 == 0 ){
			
			compInsulinDose = 0.0D;
			compGlucagonDose = 0.0D;
			
		} else if( r2 < safeMin) { // reading is below the safe minimum
			
			// do not inject insulin
			compInsulinDose = 0.0D;
			//Alarm is on and a warning message must be displayed
			
			//sugar level decreasing.
			if (r2 < r1 ) {
				
				// compute glucagon dose
				compGlucagonDose = Math.round((r1 - r2) / 4);
				
				// a minimum glucagon dose must be delivered if dose is rounded to zero
				if( compGlucagonDose == 0.0D ) {
					
					compGlucagonDose = minGlucagonDose;
				}				
				
			}  else if (r2 == r1) { // sugar level stable
				
				compGlucagonDose = minGlucagonDose;
				
			} else if (r2 > r1) { // sugar level is increasing
				
				if( (r2 - r1) >= (r1 - r0) ){ // rate of increase is increasing
					
					compGlucagonDose = 0.0D;
					
				} else { // rate of increase is decreasing
					
					compGlucagonDose = minGlucagonDose;
				}				
				
			}					
			
		} else if ( r2 > safeMin && r2 < safeMax ) { // reading is within the safe zone			
			
			if ( r2 == r1 ){ //sugar level stable or falling
				
				compInsulinDose = 0.0D;
				compGlucagonDose = 0.0D;
				
			} else if ((r2 > r1)) { // sugar level increasing
				
				compGlucagonDose = 0.0D; // do not inject glucagon
				
				if( (r2 - r1) < (r1 - r0) ) { // rate of increasing falling
					
					compInsulinDose = 0.0D;
					
				} else { // rate of increase increasing, compute insulin dose
					
					compInsulinDose = Math.round((r2 - r1) / 4);
					
					// a minimum dose must be delivered if dose is rounded to zero
					if ( compInsulinDose == 0 ) {
						
						compInsulinDose = minInsulinDose;
						
					}
				}				
				
			} else if ( r2 < r1 ){ // sugar level is falling
				
				compInsulinDose = 0.0D;
				
				if( (r1 - r2) < (r0 - r1) ) { // rate of decreasing falling
					
					compGlucagonDose = 0.0D;
					
				} else { // rate of decrease increasing, compute glucagon dose
					
					compGlucagonDose = Math.round((r1 - r2) / 4);
					
					// a minimum dose must be delivered if dose is rounded to zero
					if ( compGlucagonDose == 0.0D ) {
						
						compGlucagonDose = minGlucagonDose;
						
					}
				}
			}
			 
		} else if ( r2 > safeMax ) { //sugar level increasing
			
			//sugar level increasing.
			if (r2 > r1) {
				
				// compute insulin dose
				compInsulinDose = Math.round((r2 - r1) / 4);
				
				// a minimum insulin dose must be delivered if dose is rounded to zero
				if( compInsulinDose == 0.0D ) {
					
					compInsulinDose = minInsulinDose;
				}				
				
			}  else if (r2 == r1) { // sugar level stable
				
				compInsulinDose = minInsulinDose;
				
			} else if (r2 < r1) { // sugar level is falling
				
				if( (r2 - r1) <= (r1 - r0) ){ // rate of decrease is increasing
					
					compInsulinDose = 0.0D;
					
				} else { // rate of decrease is decreasing
					
					compInsulinDose = minInsulinDose;
				}				
				
			}
			
			// do not inject glucagon
			compGlucagonDose = 0.0D;
			
		}
		
		r0 = r1;
		r1 = r2;	
		
	}
	
public static void manualModeSafetyCheck(){
		
		if( compInsulinDose > 0.0D) { // insulin						
			
			if( ( cumulativeInsulinDose + compInsulinDose) > maxInsulinDailyDose ) { // maximum daily dose is exceeded
				//MainPanel.showWarning(Messages.WARNING_07); // Alert the user
                                JOptionPane.showMessageDialog(null, Messages.WARNING_07);
			}
}
}
}