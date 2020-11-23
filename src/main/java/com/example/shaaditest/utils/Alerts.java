package com.example.shaaditest.utils;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

public class Alerts {
public static void ShowMessageAlert(Context con, String strTitle, String strMessage)
{
	AlertDialog.Builder builder=new AlertDialog.Builder(con);
	builder.setTitle(strTitle);//("Add New Visit");
	builder.setIcon(android.R.drawable.ic_dialog_info);
	DialogListner listner=new DialogListner();
	builder.setMessage(strMessage);//("New visit Added successfully!");
	builder.setPositiveButton("ok", listner);
	
	AlertDialog diag=builder.create();
	diag.show();
}


//	public static void ShowMessageAlert(final Context con, String strTitle, String strMessage
//			, final Class nextactivity)
//	{
//		AlertDialog.Builder builder=new AlertDialog.Builder(con);
//		builder.setTitle(strTitle);//("Add New Visit");
//		builder.setIcon(android.R.drawable.ic_dialog_info);
//		DialogListner listner=new DialogListner();
//		builder.setMessage(strMessage);//("New visit Added successfully!");
//		builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				Intent i = new Intent (con, nextactivity);
//				con.startActivity(i);
//			}
//		});
//
//		AlertDialog diag=builder.create();
//		diag.show();
//	}


public static void ShowNWD(Context mContext)
{
	/*AlertDialog.Builder builder;
	AlertDialog alertDialog;
	//Context mContext = getApplicationContext();
	LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
	View layout = inflater.inflate(R.layout.noworkingday, (ViewGroup) findViewById(R.id.layout_root));
	TextView text = (TextView) layout.findViewById(R.id.text);
	text.setText("Hello, this is a custom dialog!");
	ImageView image = (ImageView) layout.findViewById(R.id.image);
	image.setImageResource(R.drawable.android);
	builder = new AlertDialog.Builder(mContext);
	builder.setView(layout);
	alertDialog = builder.create();*/
}

/*public static void ShowMessageAlertSelection(OnItemSelectedListener objOnItemSelectedListener,String strTitle,String strMessage)
{
	AlertDialog.Builder builder=new AlertDialog.Builder(Anions.this);
	builder.setTitle(strTitle);//("Add New Visit");
	builder.setIcon(android.R.drawable.ic_dialog_info);
	DialogListner listner=new DialogListner();
	builder.setMessage(strMessage);//("New visit Added successfully!");
	builder.setPositiveButton("ok", listner);
	
	AlertDialog diag=builder.create();
	diag.show();
}*/

static public void CatchError(Context con, String Exception)
{
	Dialog diag=new Dialog(con);
	diag.setTitle("Error");
	TextView txt=new TextView(con);
	txt.setText(Exception);
	diag.setContentView(txt);
	diag.show();
}


}


