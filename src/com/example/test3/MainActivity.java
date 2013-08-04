package com.example.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;

public class MainActivity extends Activity {

	public List<Figure> list  ;
	int tapCount = 0;
	int successCount = 0;
	boolean unlock = true;
	
	int tap=-1;
	String [] input = new String[]{"RED","CIRCLE","BIG"};
	int patternLength = getLengthFromDataBase();
	@Override  
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this));


		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				int status=1;
				Figure f = list.get(position);
				System.out.println("tapped"+f.getColorName()+" "+f.getShape()+" "+f.getSize());
				tapCount++;
				String selectionString = getStringFromDataBase();
				System.out.println("reqd="+selectionString);
				if(selectionString == null){
					unlock=false;
					System.out.println("sel =null");
				}

				else{
					status = getSelectionStatus(selectionString,f);
					System.out.println("status"+status);
				}
				if(status==0){
					unlock= false;
				} 
				else if(status==1)
					successCount++;
			}
		});





	}
	public void unlock(){
		System.out.println("tapcount"+tapCount+" succ"+successCount +"patlen"+patternLength);
		if(tapCount != patternLength)
		{
			showFailiureandRedrawPattern();			
			return;
		}
		System.out.println("unlock="+unlock);
		if(unlock==false){
			showFailiureandRedrawPattern();
			return;
		}

		if(successCount == patternLength){
			//succeess
			System.out.println("UNLOCKED SUCCESS :-) ");
			Dialog d=new Dialog(this);
			d.setTitle("SUCCESS");
			d.show();

		}



	}
	public int getSelectionStatus(String selectionString,Figure f){
		

		if(f.getColorName()!=null && f.getColorName().equalsIgnoreCase(selectionString))
			return 1;

				if(f.getSize()!=null && f.getSize().equalsIgnoreCase(selectionString))
					return 1;
		if(f.getShape()!=null && f.getShape().equalsIgnoreCase(selectionString))
			return 1;
		return 0;
	}




	public class ImageAdapter extends BaseAdapter {




		private Context mContext;

		public ImageAdapter(Context c) {
			mContext = c;
			list = new ArrayList<Figure>(9);
			createList(list);


		}

		public int getCount() {
			return 9;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		// create a new ImageView for each item referenced by the Adapter
		public View getView(int position, View convertView, ViewGroup parent) {
			View v;
			DisplayMetrics displaymetrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

			int width = displaymetrics.widthPixels;

			if (convertView == null) {  // if it's not recycled, initialize some attributes
				v = new View(mContext);
				v.setLayoutParams(new GridView.LayoutParams(width/3, width/3));//view size


			} else {
				v = (View) convertView;
			}

			int bitmapSize = width/4;

			Bitmap bmp = Bitmap.createBitmap(bitmapSize, bitmapSize , Bitmap.Config.ARGB_8888);
			Canvas c = new Canvas(bmp);
			Paint p = new Paint();
			Figure f = list.get(position);

			int large=30;
			int medium=15;
			int small=8;

			p.setColor(f.getColor());
			if(f.getShape().equalsIgnoreCase("CIRCLE"))
			{

				System.out.println("aaaaaaaaaaa");
				if(f.getSize().equalsIgnoreCase("BIG"))
					c.drawCircle(30,30, large, p);
				else if(f.getSize().equalsIgnoreCase("MEDIUM"))
					c.drawCircle(30,30, medium, p);
				else if(f.getSize().equalsIgnoreCase("SMALL"))
					c.drawCircle(30,30, small, p);
			}
			else if(f.getShape().equalsIgnoreCase("RECTANGLE"))
			{
				if(f.getSize().equalsIgnoreCase("BIG"))
					c.drawRect(0, 0, width/6, width/6 , p);
				else if(f.getSize().equalsIgnoreCase("MEDIUM"))
					c.drawRect(0, 0, width/8, width/8 , p);
				else if(f.getSize().equalsIgnoreCase("SMALL"))
					c.drawRect(0, 0, width/12, width/12 , p);

			}

			else if(f.getShape().equalsIgnoreCase("TRIANGLE")){
				int tlarge=50,tmedium=20,tsmall=5;


				int verticesColors[] = {
						f.getColor(),f.getColor(),f.getColor(),f.getColor(),f.getColor(),f.getColor()
				};
				if(f.getSize().equalsIgnoreCase("BIG")){
					float verts[] = {
							0, 0, tlarge,tlarge,tlarge, 0
					};
					c.drawVertices(Canvas.VertexMode.TRIANGLES, verts.length, verts, 0, null, 0, verticesColors,   0, null, 0, 0, p);
				}
				else if(f.getSize().equalsIgnoreCase("MEDIUM")){
					float verts[] = {
							0, 0, tmedium,tmedium,tmedium, 0
					}; 
					c.drawVertices(Canvas.VertexMode.TRIANGLES, verts.length, verts, 0, null, 0, verticesColors,   0, null, 0, 0, p);
				}
					
				else if(f.getSize().equalsIgnoreCase("SMALL")){
					float verts[] = {
							0, 0, tsmall,tsmall,tsmall, 0
					};
					c.drawVertices(Canvas.VertexMode.TRIANGLES, verts.length, verts, 0, null, 0, verticesColors,   0, null, 0, 0, p);
				}
					
				


			}
			v.setBackgroundDrawable(new BitmapDrawable(bmp));
			return v;
		}

	}


	public void createList(List<Figure> list){
		String color,size,shape;


		String matrix[][] = new String[][]{{"RED","GREEN","BLUE"},{"BIG","MEDIUM","SMALL"},{"TRIANGLE","CIRCLE","RECTANGLE"}};
		int integerMat[][] = new int[3][3];
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				integerMat[i][j]=0;

		int count = 0;
		int row = 0;
		for(int i=0;i<3;i++){
			color = matrix[row][i];
			integerMat[row][i] = 1;
			int randomNumber = new Random().nextInt(3) ;
			size = matrix[1][randomNumber];
			integerMat[1][randomNumber] = 1;
			randomNumber = new Random().nextInt(3) ;
			shape = matrix[2][randomNumber];
			integerMat[2][randomNumber] = 1;	

			Figure f = new Figure(color, size, shape);
			System.out.println("alloted"+color+" "+size+" "+shape);
			list.add(f);
		}
		count = 3;
		row =1;
		for(int i=0;i<3;i++){
			if(integerMat[row][i] == 1)
				continue;
			size = matrix[row][i];
			int randomNumber = new Random().nextInt(3) ;
			color = matrix[0][randomNumber];			
			randomNumber = new Random().nextInt(3) ;
			shape = matrix[2][randomNumber];
			integerMat[2][randomNumber]=1; 
			count++;
			Figure f = new Figure(color, size, shape);
			System.out.println("alloted"+color+" "+size+" "+shape);
			list.add(f);
		}

		row = 2;
		for(int i=0;i<3;i++){
			if(integerMat[row][i] == 1)
				continue;
			shape = matrix[row][i];
			int randomNumber = new Random().nextInt(3) ;
			color = matrix[0][randomNumber];
			randomNumber = new Random().nextInt(3) ;
			size = matrix[1][randomNumber];
			count++;
			Figure f = new Figure(color, size, shape);
			System.out.println("alloted"+color+" "+size+" "+shape);
			list.add(f);
		}

		for(int i=count+1;i<=9;i++){
			int randomNumber = new Random().nextInt(3) ;
			shape = matrix[2][randomNumber];
			randomNumber = new Random().nextInt(3) ;
			color = matrix[0][randomNumber];
			randomNumber = new Random().nextInt(3) ;
			size = matrix[1][randomNumber];				
			Figure f = new Figure(color, size, shape);
			System.out.println("alloted"+color+" "+size+" "+shape);
			list.add(f);
		}
	}

	public String getStringFromDataBase(){

		tap++;
		if(tap>=patternLength)
			return null;
		return input[tap];

	}
	public int getLengthFromDataBase(){
		return input.length;
	}
	public void showFailiureandRedrawPattern(){
		System.out.println("UNLOCKED FAILURE :(");
		Dialog d=new Dialog(this);
		d.setTitle("UNLOCKED FAILURE :(");
		d.show();

	}
	public void onClickOk(View v){
		unlock();
		tap=-1;
		tapCount=0;
		unlock =true;
		successCount=0;
	}
}











