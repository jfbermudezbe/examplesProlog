//Libreria para frames
import javax.swing.JFrame
//Libreria para lineas
import java.awt.BasicStroke;
//Libreria para colores
import java.awt.Color;
//JfreeChart
import org.jfree.chart.ChartFrame
import org.jfree.chart.ChartFactory
import org.jfree.chart.JFreeChart
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import org.jfree.chart.ChartFrame
import org.jfree.chart.axis.ValueAxis
import org.jfree.chart.annotations.XYTextAnnotation

//Figuras geometricas
import java.awt.geom.Ellipse2D
import java.awt.Rectangle
import java.awt.Polygon

//Scala-graph
import scalax.collection.edge.WLUnDiEdge
import scalax.collection.mutable.Graph

object Main extends App {
  
  /* Creo un grafo con Tipo objetos en nodos String para el proyecto seran Intersecciones
   * Mis aristas seran weighted labeled undirected edge por recomendacion del profe
   * es val para que funcione en el algoritmo Dijikstra
   */
  
  val g = Graph[String, WLUnDiEdge]()
  
  
  /* Creo aristas en mi grafo no añado nodos, ellos ya se crean
   * WLUnDiEdge(Nodo Origen, Nodo Fin)(Peso, Arista)
   * Nodo Origen, Nodo Fin y Arista son objetos, 
   * En nuestro caso Nodos son intersecciones, Arista seran nuestras vias
   */
  g.add(WLUnDiEdge("Nodo1","Nodo2")(40,"arista1"))
  g.add(WLUnDiEdge("Nodo1","Nodo3")(50,"arista2"))
  g.add(WLUnDiEdge("Nodo1","Nodo4")(20,"arista3"))
  g.add(WLUnDiEdge("Nodo2","Nodo3")(10,"arista4"))
  g.add(WLUnDiEdge("Nodo4","Nodo3")(30,"arista5"))
  g.add(WLUnDiEdge("Nodo3","Nodo1")(80,"arista6"))
  
  //Forma de obtener mi nodo apartir de un objeto, en este caso el objeto es tipo String
  g.get("Nodo1")
  
  /* Para hallar el camino mas corto entre dos intersecciones, obtengo el nodo
   * de mi interseccion y el da la interseccion a la quiero ir con g.get(Mi interseccion)
   */
  
  /* El camino mas corto entre nodos necesita de dos nodos y aplico la siguiente funcion
   */
  println(g.get("Nodo2").shortestPathTo(g.get("Nodo4")))
  
  /* Lo que devuelve es los nodos y las aristas por las que pasa.
   * En nuestro caso en el trabajo nos devolveria la referencia de la interseccion y vias.
   */
  
  
  //JfreeChart
  
  //Conjunto de datos
  var dataset: XYSeriesCollection = new XYSeriesCollection();
  
  //Puntos que representan mis diferentes vehiculos
  var punto1: XYSeries = new XYSeries("1")
  punto1.add(0, 0)
  dataset.addSeries(punto1)
  
  var punto2: XYSeries = new XYSeries("2")
  punto2.add(0, 1000)
  dataset.addSeries(punto2)
  
  var punto3: XYSeries = new XYSeries("3")
  punto3.add(1000, 1000)
  dataset.addSeries(punto3)
  
  var punto4: XYSeries = new XYSeries("4")
  punto4.add(1000, 0)
  dataset.addSeries(punto4)
  
  var punto5: XYSeries = new XYSeries("5")
  punto5.add(1000, 500)
  dataset.addSeries(punto5)
  
  //Segmentos de recta que representan mis vias
	var via1: XYSeries  = new XYSeries("via1")
	via1.add(0, 0)
	via1.add(0, 1000)
  dataset.addSeries(via1)
	
  var via2: XYSeries  = new XYSeries("via2")
	via2.add(0, 1000)
	via2.add(1000, 1000)
	dataset.addSeries(via2)
  
  var via3: XYSeries  = new XYSeries("via3")
	via3.add(1000, 1000)
	via3.add(1000, 0)
	dataset.addSeries(via3)
  
  var via4: XYSeries  = new XYSeries("via4")
	via4.add(1000, 0)
	via4.add(0, 0)
	dataset.addSeries(via4)
	
	//Crear el grafico como Scatter que es una representacion de puntos
	var xyScatterChart: JFreeChart = ChartFactory.createScatterPlot(
  	null, 
  	null, 
  	null, 
  	dataset,
  	PlotOrientation.VERTICAL, false, false, false)

	
	//Obtengo mi grafico para agregar estilos
	var plot: XYPlot = xyScatterChart.getXYPlot()
	
	// Añadimos estilos
	//Fondo blanco
	plot.setBackgroundPaint(Color.WHITE)
	
	//Se quitan los ejes cordenados
	var range:  ValueAxis = plot.getRangeAxis()
  range.setVisible(false)
  var domain: ValueAxis = plot.getDomainAxis()
  domain.setVisible(false)
  
  //Añado anotaciones, que sirven para nombrar mis intersecciones
  var m1: XYTextAnnotation = new XYTextAnnotation("hola", 500, 990)
	m1.setPaint(Color.decode("#001dfa"))
	plot.addAnnotation(m1)
	
  /* XYLineAndShapeRenderer sirve para cambiar colores, formas y demas a los datos,
   * segun su orden de entrada
   */
  var renderer: XYLineAndShapeRenderer = new XYLineAndShapeRenderer()
  
	//Para evitar un error al momento de settear un valor por default de color o demas
	renderer.setAutoPopulateSeriesStroke(false)
	renderer.setAutoPopulateSeriesPaint(false)
	
	// Color y ancho por defecto
  renderer.setBaseStroke(new BasicStroke(4))
  renderer.setBasePaint(Color.decode("#cccccc"))
	
	//Quitar la forma de las lineas o vias en los extremos
	renderer.setSeriesShapesVisible(5, false)
	renderer.setSeriesShapesVisible(6, false)
	renderer.setSeriesShapesVisible(7, false)
	renderer.setSeriesShapesVisible(8, false)
	
  
  //-4 -4  son para centrarlos conforme a la linea
	//Formas y colores para los diferentes vehiculos
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
  
  //Seteo todos los cambios a mi grafico
	plot.setRenderer(renderer)
	
	/* Creamos la ventana, ponemos un titulo, le ponemos el grafico al contenedor,
	 * lo hacemos visible y le damos un tamaño pre definido
	 */
	var ventana: ChartFrame = new ChartFrame("vehTraffic", xyScatterChart);
	ventana.setVisible(true);
	ventana.setSize(1300, 700);
	//Al cerrar la ventana acabe el programa
	ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//Un pequeño ejemplo de como mover nuestros vehiculos
	val r = scala.util.Random
	//Cambiar posicion random cada 2 segundos
	while(true){
	  Thread.sleep(2000)
    dataset.getSeries(0).clear()
    dataset.getSeries(0).add(r.nextInt(1000),r.nextInt(1000))
	}
		
}

