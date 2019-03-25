package me.rueno.Sortingalgorithms.Logic.Algorithms;

import javax.swing.JLabel;

import me.rueno.Sortingalgorithms.Logic.DefaultVisualizedSortingAlgorithm;

public class QuickSort extends DefaultVisualizedSortingAlgorithm{
	
	public QuickSort(){
		this(null);
	}
	
	public QuickSort(JLabel[] labels){
		super(labels);
	}
	
	@Override
	public <C extends Comparable<C>> void sortVisualized(C[] list){
		qSortVis(list, 0, list.length-1);
	}
	
	private <C extends Comparable<C>> void qSortVis(C[] list, int start, int end){
		if(start >= end) return;
		
		int middle = start + (end - start) / 2;
		C pivot = list[middle];
		
		int i = start, j = end;
		while(i <= j && !shouldInterrupt()){
			while(compareVisualized(list, i, indexOf(list, pivot), 4) < 0 && !shouldInterrupt()) i++;
			while(compareVisualized(list, j, indexOf(list, pivot), 4) > 0 && !shouldInterrupt()) j--;
//			while(list[i].compareTo(pivot) < 0) i++;
//			while(list[j].compareTo(pivot) > 0) j--;
			
			if(i <= j && !shouldInterrupt()){
				swapVisualized(list, i, j, 4);
				i++;
				j--;
			}
		}
		
		if(start < j && !shouldInterrupt()) qSortVis(list, start, j);
		if(end > i && !shouldInterrupt()) qSortVis(list, i, end);
		
	}
	
	@Override
	protected <C extends Comparable<C>> long[] sort(C[] list){
		long[] data = qSort(list, 0, list.length-1);
		return data;
	}
	
	/**
	 * @return {resaves, comparasons}
	 */
	private <C extends Comparable<C>> long[] qSort(C[] list, int start, int end){
		if(start >= end) return new long[] {0, 0};
		
		long resaves = 0, comparasons = 0;
		
		int pivIndex = start + (end - start) / 2;
		C pivot = list[pivIndex];
		
		int i = start, j = end;
		
		while(i <= j && !shouldInterrupt()){ comparasons++;
			while(list[i].compareTo(pivot) < 0 && !shouldInterrupt()){ comparasons++;
				i++; resaves++;
			} comparasons++;
			while(list[j].compareTo(pivot) > 0 && !shouldInterrupt()){ comparasons++;
				j--; resaves++;
			} comparasons++;
			
			if(i <= j && !shouldInterrupt()){
				swap(list, i, j); resaves += 3;
				i++; resaves++;
				j--; resaves++;
			} comparasons++;
		}
		
		if(start < j && !shouldInterrupt()){
			long[] data = qSort(list, start, j);
			resaves += data[0];
			comparasons += data[1];
		} comparasons++;
		if(end > i && !shouldInterrupt()){
			long[] data = qSort(list, i, end);
			resaves += data[0];
			comparasons += data[1];
		} comparasons++;
		return new long[] {resaves, comparasons};
	}
	
	@Override
	public String getAlgorithmInfoText(){
		return getName();
	}
	
}