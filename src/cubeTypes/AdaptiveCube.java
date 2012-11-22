package cubeTypes;

import java.util.List;

import controller.SerialInterface;
import jssc.SerialPortList;

public class AdaptiveCube implements SerialInterface  {

	int serialBaudRate 	= 11520;
	int ledsPerCube 	= 4096;
	int xNumberOfLeds 	= 16;
	int yNumberOfLeds 	= 16;
	int zNumberOfLeds 	= 16;
	int base0or1 		= 1; // Some cubes consider 1,1,1 the origin, others consider
						// 0,0,0 the origin
	String comPort;

	public AdaptiveCube() {

	}

	

	public int getSerialBaudrate() {
		return serialBaudRate;
	}

	public int getBase0or1() {
		return base0or1;
	}
	
	public String getComPort() {
		return comPort;
	}
	
	public void setComPort(String comPort) {
		this.comPort = comPort;
	}

	public void writeToSerial(List ledsToWrite) {
		// TODO Auto-generated method stub

	}

	public List getCurrentCubeDisplay() {
		// TODO Auto-generated method stub
		return null;
	}



	public int getxNumberOfLeds() {
		// TODO Auto-generated method stub
		return xNumberOfLeds;
	}


	public int getyNumberOfLeds() {
		// TODO Auto-generated method stub
		return yNumberOfLeds;
	}


	public int getzNumberOfLeds() {
		// TODO Auto-generated method stub
		return zNumberOfLeds;
	}


	public int getLedsPerCube() {
		return ledsPerCube;
	}



}