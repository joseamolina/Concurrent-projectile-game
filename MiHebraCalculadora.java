package lab5;

import javax.swing.JTextField;

public class MiHebraCalculadora extends Thread {
	private Proyectil1a p;
	private CanvasCampoTiro1a canva;
	private JTextField txtfila;
	private String mensaje;
	
	
	public MiHebraCalculadora(CanvasCampoTiro1a canva, JTextField txtfila, Proyectil1a p){
		this.p=p;
		this.canva=canva;
		this.txtfila=txtfila;
		this.mensaje= "";
	}
	
	public void run(){
		while( p.getEstadoProyectil() == 0 )  {

            // Muestra en pantalla los datos del proyectil p.
            p.muestra();

            // Mueve el proyectil durante un incremental de tiempo.
            p.mueveDuranteUnIncremental( canva.getObjetivoX(),
                                         canva.getObjetivoY() );

            // Dibuja el proyectil.
            p.dibujaProyectil( canva );

            // Comprueba si el proyectil ha impactado contra el suelo o no.
            if( p.getEstadoProyectil() != 0 ) {
              // El proyectil ha impactado contra el suelo.
              // Construye y muestra mensaje adecuado.
              if( p.getEstadoProyectil() == 2 ) {
                mensaje = "Destruido!";
              } else {
                mensaje = "Fallado. El objetivo esta en: " + 
                          canva.getObjetivoX() + 
                          "  Has disparado a: " + p.getIntPosX();
              }
              txtfila.setText( mensaje );
            }
          }
	}
	
}
