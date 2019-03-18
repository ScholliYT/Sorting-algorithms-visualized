package me.rueno.Sortingalgorithms.Logic;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.UIManager;

public abstract class DefaultVisualizedSortingAlgorithm implements ISortingAlgorithm{
	
	private JLabel[] labels;
	
	private final Color defaultBackground;
	
	public DefaultVisualizedSortingAlgorithm(JLabel[] panels){
		this.labels = panels;
		this.defaultBackground = UIManager.getColor("Panel.background");
	}
	
	public void setNewLabels(JLabel[] labels){
		this.labels = labels;
	}
	
	@Override
	public abstract long mearsureSortTime();
	
	@Override
	public abstract <C extends Comparable<C>> void sortVisualized(C[] list);
	
	@Override
	public final <C extends Comparable<C>> void swapVisualized(C[] list, int posX, int posY, int currentSpeed){
		if(posX == posY) return;
		
		C temp = list[posX];
		list[posX] = list[posY];
		list[posY] = temp;
		
		JLabel panelX = labels[posX];
		JLabel panelY = labels[posY];
		
		panelX.setBackground(Color.green);
		panelY.setBackground(Color.green);
		
		int x = panelX.getLocation().x;
		int y = panelY.getLocation().x;
		
		//runter schieben
		for(int currentYDeviation = 0; currentYDeviation < (int) (panelX.getHeight() * 1.5D); currentYDeviation += currentSpeed){
			panelX.setLocation(panelX.getX(), panelX.getY() + currentSpeed);
			panelY.setLocation(panelY.getX(), panelY.getY() + currentSpeed);
			sleepAppropiateAmountOfTimeForSwap(currentSpeed);
		}
		
		//Zum jeweiligen anderen Standpunkt verschieben
		long loops;
		int lastAddX, lastAddY;
		int deviationX, deviationY;
		if(x > y) {
			loops = (x - y) / currentSpeed;
			int lastAdd = (x - y) % currentSpeed;
			lastAddX = -lastAdd;
			lastAddY = lastAdd;
			deviationX = -currentSpeed;
			deviationY = currentSpeed;
		}else{
			loops = (y - x) / currentSpeed;
			int lastAdd = (x - y) % currentSpeed;
			lastAddX = lastAdd;
			lastAddY = -lastAdd;
			deviationX = currentSpeed;
			deviationY = -currentSpeed;
		}
		
		for(int i = 0; i < loops; i++) {
//			PanelX verschieben
			panelX.setLocation(panelX.getX() + deviationX, panelX.getY());
			//PanelY verschieben
			panelY.setLocation(panelY.getX() + deviationY, panelY.getY());
			sleepAppropiateAmountOfTimeForSwap(currentSpeed);
		}
		
		panelX.setLocation((int) (panelX.getX() + lastAddX), panelX.getY());
		panelY.setLocation((int) (panelY.getX() + lastAddY), panelY.getY());
		
//		while(panelX.getX() != y){
//			//PanelX verschieben
//			panelX.setLocation(panelX.getX() + 1, panelX.getY());
//			//PanelY verschieben
//			panelY.setLocation(panelY.getX() - 1, panelY.getY());
//			sleepAppropiateAmountOfTimeForSwap(currentSpeed);
//		}
		
		//Hinauf schieben (Fast �quivalent zu runter schieben, eventuell eine Mehtode anlegen?
		for(int currentYDeviation = (int) (panelX.getHeight() * 1.5D); currentYDeviation > 0; currentYDeviation -= currentSpeed){
			panelX.setLocation(panelX.getX(), panelX.getY() - currentSpeed);
			panelY.setLocation(panelY.getX(), panelY.getY() - currentSpeed);
			sleepAppropiateAmountOfTimeForSwap(currentSpeed);
		}
		
		//Jetzt noch die Panel im Panelarray vertauschen
		JLabel tempPanel = labels[posX];
		labels[posX] = labels[posY];
		labels[posY] = tempPanel;
		
		labels[posX].setBackground(defaultBackground);
		labels[posY].setBackground(defaultBackground);
		sleepAfterStep(currentSpeed);
	}
	
	/**
	 * Visualises a comparation. Equivalent to calling list[posX].compareTo(list[posY])
	 */
	@Override
	public final <C extends Comparable<C>> int compareVisualized(C[] list, int posX, int posY, int speed){
		//Visualisieren
		if(posX == posY) list[posX].compareTo(list[posY]);
		labels[posX].setBackground(Color.RED);
		labels[posY].setBackground(Color.RED);
		sleepAppropiateAmountOfTimeForCompare(speed);
		
		labels[posX].setBackground(defaultBackground);
		labels[posY].setBackground(defaultBackground);
		sleepAfterStep(speed);
		
		return list[posX].compareTo(list[posY]);
	}
	
	private void sleepAppropiateAmountOfTimeForCompare(int speed){
//		try{
//			Thread.sleep(500);
//		}catch(InterruptedException interrupted){
//			interrupted.printStackTrace();
//		}
	}
	
	private void sleepAfterStep(int speed){
//		try{
//			Thread.sleep(300);
//		}catch(InterruptedException interrupted){
//			interrupted.printStackTrace();
//		}
	}
	
	private void sleepAppropiateAmountOfTimeForSwap(int speed){
//		try{
//			Thread.sleep(17);
//		}catch(InterruptedException interrupted){
//			interrupted.printStackTrace();
//		}
	}
	
}