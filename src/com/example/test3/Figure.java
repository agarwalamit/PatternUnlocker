package com.example.test3;

import android.graphics.Color;

public class Figure {

	String colorName = null;

	String size = null;
	String shape = null;
	int color;

	public Figure(String colorName,String size,String shape){

		if(colorName.equalsIgnoreCase("RED"))
		{
			this.color = Color.RED;
			this.colorName = colorName;
		}
		else if(colorName.equalsIgnoreCase("GREEN")){
			this.color = Color.GREEN;
			this.colorName=colorName;
		}
			
		else if(colorName.equalsIgnoreCase("BLUE"))
			{
			this.color = Color.BLUE;
			this.colorName=colorName;
			}
	
		this.size = size;
		this.shape = shape;
	}
	public int getColor() {

		return color;
	}
	public String getColorName(){
		return this.colorName;
	}
	public void setColor(String color) {
		this.colorName = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}

}
