package com.goemans.primefinder;

import android.app.Activity;
import android.os.Bundle; 
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class PrimeFinder extends Activity 
{
	public class PrimeSearch implements View.OnClickListener
	{
		public void onClick(View view)
		{
			Spinner size = (Spinner) findViewById( R.id.PrimeSize );
			int count = Integer.parseInt( size.getSelectedItem().toString() );
			
			EditText ss = (EditText) findViewById( R.id.SearchSpace );
			Editable text = ss.getText();
			
			try
			{
				search( text.toString(), count );
			}
			catch( Exception e )
			{
			}	
		}
		
		public void search( String space, int size ) throws Exception
		{
			if( size > space.length() )
				throw new Exception("Space smaller than required size");
			
			for( int i =0; i<=space.length()-size; i++ )
			{
				String current = space.substring(i, i+size);
				long toCheck = Long.parseLong( current );
				if( isPrime( toCheck ) )
				{
					EditText result = (EditText) findViewById(R.id.ResultBox);
					result.setText( Long.toString(toCheck) );
					return;
				}
				else
				{
				}
			}
			
			EditText result = (EditText) findViewById(R.id.ResultBox);
			result.setText( "None Found" );
		}
		
		public boolean isPrime( long input )
		{
			long limit = (long) Math.sqrt( (double)input );
			
			
			for( long i = 2; i <= limit; i++ )
			{
				long rem = input % i;
				
				
				
				if( rem == 0 )
				{
					return false;
				}
			}
			return true;
		}
		
	}

	public static String[] options = { "1", "2","3", "4","5", "6","7", "8", "9", "10" };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button findButton = (Button) findViewById( R.id.GoButton );
        findButton.setOnClickListener( new PrimeSearch() );
        
        Spinner sizeWidget = (Spinner) findViewById(R.id.PrimeSize);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getApplicationContext(),android.R.layout.simple_spinner_item,options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeWidget.setAdapter(adapter);
        
        TextView result = (TextView) findViewById(R.id.ResultLabel);
		result.setText( "Result:" );
    }
}