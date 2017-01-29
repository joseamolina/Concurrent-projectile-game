package lab5;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MiHebraCalculadoraB extends Thread {
	private CanvasCampoTiro1b canva;
	private JTextField txtfila;
	private String mensaje;
	private ArrayList<Proyectil1b> lP;
	private ZonaIntercambio zI;
	
	public MiHebraCalculadoraB(CanvasCampoTiro1b canva, JTextField txtfila,ZonaIntercambio zI){
		this.canva=canva;
		lP = new ArrayList<Proyectil1b>();
		this.txtfila=txtfila;
		this.mensaje= "";
		this.zI = zI;
	}
	
	public void run(){
		
		while(true){
			
			while (lP.size()==0 || zI.hayDisparos()){
				Nuevodisparo nD = zI.recoge();
				Proyectil1b p = new Proyectil1b( nD.dameVel(), nD.dameAng());
				lP.add(p);
			}
			
				for (int i = 0; i < lP.size(); i++){
					Proyectil1b proc = lP.get(i);
		            // Muestra en pantalla los datos del proyectil p.
					
					try {
						SwingUtilities.invokeAndWait(new Runnable(){
							@Override
							public void run() {
								proc.muestra();
							}
						});
					} catch (InvocationTargetException | InterruptedException e) {
						e.printStackTrace();
					}
		            
		            // Mueve el proyectil durante un incremental de tiempo.
		            proc.mueveDuranteUnIncremental( canva.getObjetivoX(),
		                                         canva.getObjetivoY() );
		            // Dibuja el proyectil.
		            
		            try {
						SwingUtilities.invokeAndWait(new Runnable(){
							@Override
							public void run() {
						        proc.dibujaProyectil( canva );
							}
						});
					} catch (InvocationTargetException | InterruptedException e) {
						e.printStackTrace();
					}
		            // Comprueba si el proyectil ha impactado contra el suelo o no.
		            if( proc.getEstadoProyectil() != 0 ) {
		            	lP.remove(i);
		            	i--;
		            	
		            	if( proc.getEstadoProyectil() == 2 ) {
		            		mensaje = "Destruido!";
		            	} else {
		            		mensaje = "Fallado. El objetivo esta en: " + 
		                          canva.getObjetivoX() + 
		                          "  Has disparado a: " + proc.getIntPosX();
		            	}
		            	try {
							SwingUtilities.invokeAndWait(new Runnable(){
								@Override
								public void run() {
									txtfila.setText( mensaje );
								}
							});
						} catch (InvocationTargetException
								| InterruptedException e) {
							e.printStackTrace();
						}
		            }
				}
				
			}
		
	}
}
