import java.io.OutputStream;
import java.util.Enumeration;


import gnu.io.*;


public class test {
	
	private OutputStream output=null;
    SerialPort serialPort;
    private final String PUERTO="/dev/ttyS88";
    
    private static final int TIMEOUT=2000; //Milisegundos
    
    private static final int DATA_RATE=9600;
	
	public void inicializarConexion(){
        CommPortIdentifier puertoID=null;
        Enumeration puertoEnum=CommPortIdentifier.getPortIdentifiers();
        System.out.println(puertoEnum.toString());
        for (Enumeration e = puertoEnum ; e.hasMoreElements() ;) {
    	    System.out.println(e.nextElement());
    	}
        
        //System.out.println("vera:"+puertoEnum.nextElement());
        while(puertoEnum.hasMoreElements()){
            CommPortIdentifier actualPuertoID=(CommPortIdentifier) puertoEnum.nextElement();
            System.out.println("vera:"+actualPuertoID);
            if(PUERTO.equals(actualPuertoID.getName())){
                puertoID=actualPuertoID;
                break;
            }
        }
        
        if(puertoID==null){
            mostrarError("No se puede conectar al puerto");
            
        }
        
        try{
            serialPort = (SerialPort) puertoID.open(this.getClass().getName(), TIMEOUT);
            //Parámetros puerto serie
            
            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            
            output = serialPort.getOutputStream();
        } catch(Exception e){
            mostrarError(e.getMessage());
            //System.exit(e);
            
        }
    }
	
	public void mostrarError(String mensaje){
        System.out.println(mensaje);
    }
	
	

}
