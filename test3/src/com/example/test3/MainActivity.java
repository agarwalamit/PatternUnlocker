package com.example.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
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

	public List<Figure> list ;
	int tapCount = 0;
	int successCount = 0;
	boolean unlock = false;
	int patternLength = getLengthFromDataBase();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this));


		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Figure f = list.get(position);
				tapCount++;
				String selectionString = getStringFromDataBase();
				boolean status = getSelectionStatus(selectionString,f);
				if(status==false){
					unlock= false;
				}
				successCount++;






			}
		});





	}
	public void unlock(){
		if(tapCount != patternLength)
		{
			showFailiureandRedrawPattern();
			return;
		}
		if(successCount == patternLength){
			//succeess
			System.out.println("UNLOCKED SUCCESS :-) ");

		}


	}
	public boolean getSelectionStatus(String selectionString,Figure f){
		if(f.getColorName().equalsIgnoreCase(selectionString))
			return true;
		if(f.getSize().equalsIgnoreCase(selectionString))
			return true;
		if(f.getShape().equalsIgnoreCase(selectionString))
			return true;
		return false;
	}




	public class ImageAdapter extends BaseAdapter {




		private Context mContext;

		public ImageAdapter(Context c) {
			mContext = c;
			list = new ArrayList<Figure>(20);
			createList(list);
			System.out.println("size"+list.size());
			//			for(int i=0;i<9;i++)
			//				list.add(new Figure("RED","SMALL","RECTANGLE"));
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
			int height = displaymetrics.heightPixels;
			int width = displaymetrics.widthPixels;

			if (convertView == null) {  // if it's not recycled, initialize some attributes
				v = new View(mContext);
				v.setLayoutParams(new GridView.LayoutParams(width/3, width/3));//view size


			} else {
				v = (View) convertView;
			}

			int bitmapSize = 351;

			Bitmap bmp = Bitmap.createBitmap(bitmapSize, bitmapSize , Bitmap.Config.ARGB_8888);
			Canvas c = new Canvas(bmp);
			Paint p = new Paint();
			Figure f = list.get(position);
			int a = Color.RED;
			p.setColor(f.getColor());
			if(f.getShape().equalsIgnoreCase("CIRCLE"))
				c.drawCircle(bitmapSize/2,bitmapSize/2, 150, p);
			else if(f.getShape().equalsIgnoreCase("RECTANGLE"))
				c.drawRect(0, 0, width, width, p);

			else if(f.getShape().equalsIgnoreCase("TRIANGLE")){

				float verts[] = {
						0, 0, 100,100,100, 0
				};

				c.drawVertices(Canvas.VertexMode.TRIANGLES, verts.length, verts, 0, null, 0, null,   0, null, 0, 0, p);

			}
			v.setBackgroundDrawable(new BitmapDrawable(bmp));
			return v;
		}

	}


	public void createList(List<Figure> list){
		String color,size,shape;

		int minTimes = 1;
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
			list.add(f);
		}
	}

	public String getStringFromDataBase(){
		return new String("RED");
	}
	public int getLengthFromDataBase(){
		return 3;
	}
	public void showFailiureandRedrawPattern(){
		System.out.println("UNLOCKED FAILURE :(");

	}
}











