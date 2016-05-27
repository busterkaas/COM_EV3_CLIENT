package Helpers;


import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;

import IHelpers.ICommunicationObserver;

public class ComSupport extends Thread {
	
	List<ICommunicationObserver> observers;
	
	private DataInputStream dis;
	private String ev3Message;
	
	public ComSupport(DataInputStream dis, ICommunicationObserver obs){
		this.dis = dis;
		observers = new ArrayList<ICommunicationObserver>();
		addAsObserver(obs);	
	}
	
	public void run(){
		while(true){
			try{
				if(dis.available()>0){
					ev3Message = dis.readUTF();
					sendMsgToObservers();
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			}
			
		}
	
	private void sendMsgToObservers(){
		for(ICommunicationObserver obs : observers){
			obs.ev3Message(ev3Message);
		}
	}
	
	
	public void addAsObserver(ICommunicationObserver observer){
		observers.add(observer);
	}
	public void removeAsObserver(ICommunicationObserver observer){
		observers.remove(this);
	}
}
