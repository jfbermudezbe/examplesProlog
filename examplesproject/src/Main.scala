
import java.awt.BasicStroke;
import java.awt.Color;
 
import javax.swing.JFrame;
 
import org.jfree.chart.ChartFrame
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import org.jfree.chart.ChartFrame
import org.jfree.chart.axis.ValueAxis
import org.jfree.chart.annotations.XYTextAnnotation
import java.awt.geom.Ellipse2D
import java.awt.Rectangle
import java.awt.Polygon

object Main extends App {
  
  //Informacion
    var dataset: XYSeriesCollection = new XYSeriesCollection();
    
    var punto1: XYSeries = new XYSeries("1")
    punto1.add(0, 0)
    dataset.addSeries(punto1);
    
    var punto2: XYSeries = new XYSeries("2")
    punto2.add(0, 1000)
    dataset.addSeries(punto2);
    
    var punto3: XYSeries = new XYSeries("3")
    punto3.add(1000, 1000)
    dataset.addSeries(punto3);
    
    var punto4: XYSeries = new XYSeries("4")
    punto4.add(1000, 0)
    dataset.addSeries(punto4);
    
    var punto5: XYSeries = new XYSeries("5")
    punto5.add(1000, 500)
    dataset.addSeries(punto5);
    
		var via1: XYSeries  = new XYSeries("via1")
		via1.add(0, 0)
		via1.add(0, 1000)
    dataset.addSeries(via1);
		
    var via2: XYSeries  = new XYSeries("via2")
		via2.add(0, 1000)
		via2.add(1000, 1000)
		dataset.addSeries(via2);
    
    var via3: XYSeries  = new XYSeries("via3")
		via3.add(1000, 1000)
		via3.add(1000, 0)
		dataset.addSeries(via3);
    
    var via4: XYSeries  = new XYSeries("via4")
		via4.add(1000, 0)
		via4.add(0, 0)
		dataset.addSeries(via4);
		
    
    
		 var xyScatterChart: JFreeChart = ChartFactory.createScatterPlot(
				null, 
				null, 
				null, 
				dataset,
				PlotOrientation.VERTICAL, false, false, false);

		
		// Añadimos estilos
		var plot: XYPlot = xyScatterChart.getXYPlot()
		plot.setBackgroundPaint(Color.WHITE)
		var range:  ValueAxis = plot.getRangeAxis()
    range.setVisible(false)
    var domain: ValueAxis = plot.getDomainAxis()
    domain.setVisible(false)
    
    var m1: XYTextAnnotation = new XYTextAnnotation("hola", 1000, 990)
		m1.setPaint(Color.decode("#001dfa"))
		
		plot.addAnnotation(m1)
		
    
	  var renderer: XYLineAndShapeRenderer = new XYLineAndShapeRenderer()
		//Para evitar un error
		renderer.setAutoPopulateSeriesStroke(false)
		renderer.setAutoPopulateSeriesPaint(false)
		/*Quitar la forma de todos lo puntos
    renderer.setBaseShapesVisible(false)*/
		//Quitar la forma de las lineas
		renderer.setSeriesShapesVisible(5, false)
		renderer.setSeriesShapesVisible(6, false)
		renderer.setSeriesShapesVisible(7, false)
		renderer.setSeriesShapesVisible(8, false)
		// Color y ancho por defecto
    renderer.setBaseStroke(new BasicStroke(4))
    renderer.setBasePaint(Color.decode("#cccccc"))
    
    //-4 -4  son para centrarlos conforme a la linea
    renderer.setSeriesShape(0, new Rectangle(-4,-4,6,6))
    renderer.setSeriesPaint(0,Color.decode("#1aff00"))
    
    renderer.setSeriesShape(1, new Ellipse2D.Double(-4,-4,8,10))
    renderer.setSeriesPaint(1,Color.decode("#ff1100"))
    
    renderer.setSeriesShape(2, new Rectangle(-2,-4,4,14))
    renderer.setSeriesPaint(2,Color.decode("#ff00f7"))
    
    renderer.setSeriesShape(3, new Polygon(Array(-5,0,5),Array(-5,5,-5),3))
    renderer.setSeriesPaint(3,Color.decode("#00ffc8"))
    
    renderer.setSeriesShape(4, new Polygon(Array(-3,3,5,0,-5),Array(-6,-6,0,4,0),5))
    renderer.setSeriesPaint(4,Color.decode("#ff9500"))
    
		plot.setRenderer(renderer)
		
		// Creamos la ventana
		var ventana: ChartFrame = new ChartFrame("vehTraffic", xyScatterChart);
		ventana.setVisible(true);
		ventana.setSize(1300, 700);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		val r = scala.util.Random
		//Cambiar posicion random cada 2 segundos
		while(true){
		  Thread.sleep(2000)
      dataset.getSeries(0).clear()
      dataset.getSeries(0).add(r.nextInt(1000),r.nextInt(1000))
		}
		
}

