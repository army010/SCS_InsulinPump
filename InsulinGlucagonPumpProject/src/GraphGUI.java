import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
/**
 * An example to show how we can create a dynamic chart.
*/
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class GraphGUI extends ApplicationFrame implements ActionListener {

    /** The time series data. */
    private TimeSeries series;
    double gt = 100;
    double gol = 0;
    double t = 6;
    double time = 0;
    double go = 104.8558461;
    double t1, t2;
    double k = 230.5246798;
    double al = 0.538558627;
    double bet = 1.062200113;
    double del = 1.592755148;
    double temp1,temp2;
    double oldt = 5;
    double newdose = 2;
    double start = -1.0;
	double end = 1.0;
	double start1 = 0.0;
	double end1 = 0.5;
	double start2 = 0.0;
	double end2 = 0.5;
	double start3 = 1.0;
	double end3 = 2.0;
	double ins = 0;
	double glu = 0;
	double coui = 0;
	double coug = 0;
	

    /** The most recent value added. */
    private double lastValue = 0;
   
    /** Timer to refresh graph after every 1/4th of a second */
    private Timer timer = new Timer(1000, this);
    /**
     * @wbp.nonvisual location=137,19
     */
   
    /**
     * Constructs a new dynamic chart application.
     *
     * @param title  the frame title.
     */
    public GraphGUI(final String title) {
    	
    	
        super(title);
        
        this.series = new TimeSeries("BSL Data", Millisecond.class);
       
        final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        final JFreeChart chart = createChart(dataset);
       
        timer.setInitialDelay(1000);
       
        //Sets background color of chart
        chart.setBackgroundPaint(Color.WHITE);
       
        //Created JPanel to show graph on screen
        final JPanel content = new JPanel(new BorderLayout());
       
        //Created Chartpanel for chart area
        final ChartPanel chartPanel = new ChartPanel(chart);
       
        //Added chartpanel to main panel
        content.add(chartPanel);
        
        //Sets the size of whole window (JPanel)
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
       
        //Puts the whole content on a Frame
        setContentPane(content);
       
        timer.start();
        JFrame frame = new JFrame("Control"); 
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	RefineryUtilities.positionFrameRandomly(frame);
		frame.setLayout(new GridLayout(0, 1, 2, 8));
		
		JButton addHighButton = new JButton("Add Food");
		frame.add(addHighButton);
		addHighButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				double random3 = new Random().nextDouble();
	    		double result3 = start3 + (random3 * (end3 - start3));
	    		gt+=result3;
	    		ins+=0.25;
			}
		});
		
		JButton addMedButton = new JButton("Work Out");
		frame.add(addMedButton);
		addMedButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent aE) {
				double random3 = new Random().nextDouble();
	    		double result3 = start3 + (random3 * (end3 - start3));
	    		gt-=result3;
	    		glu+=0.25;
			}
		});
		
		frame.pack();
		frame.setVisible(true);

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset  the dataset.
     *
     * @return A sample chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
            "BSL TimeSeries Chart",
            "Time",
            "Value",
            dataset,
            true,
            true,
            false
        );
        
        result.getXYPlot().setRenderer(new XYSplineRenderer(5));

        
       
        final XYPlot plot = result.getXYPlot();
       
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.lightGray);
                
        ValueAxis xaxis = plot.getDomainAxis();
        xaxis.setAutoRange(true);
       
        //Domain axis would show data of 60 seconds for a time
        xaxis.setFixedAutoRange(60000.0);  // 60 seconds
        xaxis.setVerticalTickLabels(true);
       
        ValueAxis yaxis = plot.getRangeAxis();
        yaxis.setRange(0.0, 200.0);
       
        return result;
    }
    /**
     * Generates an random entry for a particular call made by time for every 1/4th of a second.
     *
     * @param e  the action event.
     */
    public void actionPerformed(final ActionEvent e) {
    	
    	
       
        //final double factor = 0.9 + 0.2*Math.random();
        this.lastValue = gt;
        calculate();
        final Millisecond now = new Millisecond();
        this.series.add(new Millisecond(), this.lastValue);
       
        System.out.println("Current Time in Milliseconds = " + now.toString()+", Current Value : "+this.lastValue);
    }
    
    public void calculate(){
    	/*t2 = Math.cos((bet*t)-del);
    	t1 = Math.exp(-al*t);
    	gt = go + k*t1*t2;
    	//go = gt;
    	t=t+0.001;
    	//value = num/den;
    	//num = 121.7*0.0453(Math.exp(a))*/
    	double random5 = new Random().nextDouble();
		double result5 = start2 + (random5 * (end2 - start2));
    	if (glu>0)
    	{
    		coug=result5;
    	}
    	else if (ins>0){
    		coui=result5;
    	}
    	
    	if (gt>110){
    		/*gol=gt;
    		temp2 = (t-oldt)/(newdose*0.04);
        	temp1 = Math.exp(-temp2);
        	gt=gol-240*temp1;*/
    		double random2 = new Random().nextDouble();
    		double result2 = start2 + (random2 * (end2 - start2));
    		gt=gt-result2-coui;
    		coui=0;
        		
    	}
    	else if (gt<70)
    	{
    		/*t2 = Math.cos((bet*time)-del);
        	t1 = Math.exp(-al*time);
        	gt = go + k*t1*t2;*/
    		double random1 = new Random().nextDouble();
    		double result1 = start1 + (random1 * (end1 - start1));
    		gt+=(result1+coug);
    		coug=0;
    	}
    	else
    	{
    		double random = new Random().nextDouble();
    		double result = start + (random * (end - start));
    		gt = gt + result + coui + coug;
    		coui = coug = 0;
    	}
    	
    	if (glu>0)
    	{
    		glu-=0.05;
    	}
    	else if (ins>0){
    		ins-=0.05;
    	}
    	t=t+1;
    	time = time + 0.0001;
    	gol=gt;
    	
   	
    }
    
   

    /**
     * Starting point for the dynamic graph application.
     *
     * @param args  ignored.
     */
    public static void main(final String[] args) {

        final GraphGUI demo = new GraphGUI("BSL Simulation");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}  