package lab5;

public class Nuevodisparo {
	
	private double ang;
	private double vel;

	public Nuevodisparo(double velocidad, double angulo){
		this.vel = velocidad;
		this.ang = angulo;
	}
	

	public double dameVel() {
		return this.vel;
	}

	public double dameAng() {
		return this.ang;
	}
	
}
