package com.tech.aicapital.datalist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MYDB extends SQLiteOpenHelper {
	static String dbName = "MYDATABASE";
	static int version1 = 1;
	public static final String DB_NAME = "MYDATABASE";
	public static final String TABLE_NAME = "menuitem";
	public static final String COLUMN_itemid = "itemid";
	public static final String COLUMN_itemname = "itemname";
	public static final String COLUMN_itemprice = "itemprice";
	public static final String COLUMN_category = "category";
	public static final String COLUMN_STATUS = "status";


	public MYDB(Context context, String name, CursorFactory factory, int version) {
		super(context, dbName, factory, version1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String regi = "create table chatmessages (id INTEGER PRIMARY KEY AUTOINCREMENT,customerid text," +
				"sendertype text,message text,dates text,times text,status text)";
		String ite = "create table cart (id INTEGER PRIMARY KEY AUTOINCREMENT,itemid text," +
				"itemname text,image text,itemprice text,discount text,itemunit text,quantity text,value text,orderstatus text,date text,time text)";
		String reg = "create table logintabel (id INTEGER PRIMARY KEY AUTOINCREMENT,mobileno text,names text,status text)";
		String addr = "create table address(id INTEGER PRIMARY KEY AUTOINCREMENT," +
				"mobileno text,names text,address1 text,address2 text,city text,state text,pincode text)";
		String banner = "create table banners (id INTEGER PRIMARY KEY AUTOINCREMENT,image text,name text)";
		String category = "create table categories (id INTEGER PRIMARY KEY AUTOINCREMENT,catid text,name text,image text)";
		String brand = "create table brands (id INTEGER PRIMARY KEY AUTOINCREMENT,brandid text,name text,image text)";

		db.execSQL(banner);
		db.execSQL(category);
		db.execSQL(brand);

		db.execSQL(regi);
		db.execSQL(ite);
        db.execSQL(reg);
		db.execSQL(addr);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

//		Log.e("Tag ", "Updating table from " + oldVersion + " to " + newVersion);
//
//		//Added new column to book table - book rating
//		if (oldVersion < 2){
//			db.execSQL(DROP + BookEntry.TABLE_NAME);
//			db.execSQL(BookEntry.SQL_CREATE_BOOK_ENTRY_TABLE);
//		}
//		//Rename table to book_information - this is where things will start failing.
//		if (oldVersion < 3){
//			db.execSQL(DROP + BookEntry.TABLE_NAME);
//			db.execSQL(BookEntry.SQL_CREATE_BOOK_ENTRY_TABLE);
//		}
//		if (oldVersion < 4){
//			db.execSQL("ALTER TABLE " + BookEntry.TABLE_NAME  + " ADD COLUMN calculated_pages_times_rating INTEGER;");
//		}

	}

	public void addtoTabelOrder(String customerid, String sendertype, String message, String dates,
								String times) {
		ContentValues cv = new ContentValues();
		cv.put("customerid", customerid);
		cv.put("sendertype", sendertype);
		cv.put("message", message);
		cv.put("dates", dates);
		cv.put("times", times);
		cv.put("status", "no");

		SQLiteDatabase DB = getWritableDatabase();
		DB.insert("chatmessages", null, cv);

	}
	public void addtoBanners(String image, String name) {
		ContentValues cv = new ContentValues();
		cv.put("image", image);
		cv.put("name", name);
		SQLiteDatabase DB = getWritableDatabase();
		DB.insert("banners", null, cv);
	}
	public void addtoCategory(String image, String name)
	{
		ContentValues cv = new ContentValues();
		cv.put("catid", image);
		cv.put("image", image);
		cv.put("name", name);
		SQLiteDatabase DB = getWritableDatabase();
		DB.insert("categories", null, cv);

	}
	public void addtoBrand(String image, String name)
	{
		ContentValues cv = new ContentValues();
		cv.put("brandid", image);
		cv.put("image", image);
		cv.put("name", name);
		SQLiteDatabase DB = getWritableDatabase();
		DB.insert("brands", null, cv);

	}
	public boolean addtoAddress(String mobileno, String names, String address1,String address2,String city,
								String state,String pincode) {
		ContentValues cv = new ContentValues();
		cv.put("mobileno", mobileno);
		cv.put("names", names);
		cv.put("address1", address1);
		cv.put("address2", address2);
		cv.put("city", city);
		cv.put("state", state);
		cv.put("pincode", pincode);

		SQLiteDatabase DB = getWritableDatabase();
		DB.insert("address", null, cv);
		return true;

	}
	public boolean addtoLogin(String mobileno,String name) {
		ContentValues cv = new ContentValues();
		cv.put("mobileno", mobileno);
		cv.put("status", "yes");
		cv.put("names",name);
		SQLiteDatabase DB = getWritableDatabase();
		DB.insert("logintabel", null, cv);
		Log.v("inserted", mobileno);
		return true;
	}
	public void addtoTabelOrder2(String customerid, String sendertype, String message, String dates,
								String times) {
		ContentValues cv = new ContentValues();
		cv.put("customerid", customerid);
		cv.put("sendertype", sendertype);
		cv.put("message", message);
		cv.put("dates", dates);
		cv.put("times", times);
		cv.put("status", "yes");

		SQLiteDatabase DB = getWritableDatabase();
		DB.insert("chatmessages", null, cv);

	}

	public void addtocart(String itemid, String itemname, String image, String itemprice,String discount, String itemunit,
								String quantity,String value,String orderstatus,String date,String time) {
		ContentValues cv = new ContentValues();


		cv.put("itemid", itemid);
		cv.put("itemname", itemname);
		cv.put("image",image);
		cv.put("itemprice", itemprice);
		cv.put("discount", discount);
		cv.put("itemunit", itemunit);
		cv.put("quantity", quantity);
		cv.put("value", value);
		cv.put("orderstatus","no");
		cv.put("date",date);
		cv.put("time",time);

		SQLiteDatabase DB = getWritableDatabase();
		DB.insert("cart", null, cv);


	}
	public Cursor getUnsyncedNames() {
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT  * FROM menuitem where backupstatus = '" + "no" + "'";

//		String sql = "SELECT * FROM menuitem " + TABLE_NAME + " WHERE " + COLUMN_STATUS + " = 0;";
		Cursor c = db.rawQuery(selectQuery, null);
		return c;
	}
	public boolean updateNameStatus(int id, int status) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_STATUS, status);
		db.update(TABLE_NAME, contentValues, COLUMN_itemid + "=" + id, null);
		db.close();
		return true;
	}
	public String[] totalQuantity(String tabelid) {

		String[] s1;
		s1 = new String[1];
		s1[0] = tabelid;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cr = null;

		cr = db.rawQuery("select sum(quantity) from tabelorder where tabelid=?", s1);
		String[] res = new String[2];
		if (cr.moveToFirst()) {

			do {

				res[0] = cr.getString(0);

			} while (cr.moveToNext());
		}
		return res;

	}

	public String[] totalBill(String tabelid) {

		String[] s1;
		s1 = new String[1];
		s1[0] = tabelid;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cr = null;

		cr = db.rawQuery("select sum(bill) from tabelorder where tabelid=?", s1);
		String[] res = new String[2];
		if (cr.moveToFirst()) {

			do {

				res[0] = cr.getString(0);

			} while (cr.moveToNext());
		}
		return res;

	}

	public ArrayList<String> selectedOrderList(String tabelid) {

		ArrayList<String> Sname2;
		String a = "@&#;&@";
		Sname2 = new ArrayList<String>();

		String[] s1;
		s1 = new String[1];
		s1[0] = tabelid;

		SQLiteDatabase DB = getReadableDatabase();

		Cursor cr = DB.rawQuery("select * from chatmessages",null);

		cr.moveToFirst();
		for (int i = 0; i < cr.getCount(); i++) {

//			String name1 = cr.getString(0);
			String name2 = cr.getString(1);
			String name3 = cr.getString(2);
			String name4 = cr.getString(3);
			String name5 = cr.getString(4);
			String name6 = cr.getString(5);
			String name7 = cr.getString(6);
			//String name5 = cr.getString(cr.getColumnIndex("itemstatus"));

			String name77 = name2 + a + name3 + a + name4 + a + name5 + a + name6 + a + name7;
			Sname2.add(name77);
			cr.moveToNext();
			Log.v("inserted", name7);

		}
		return Sname2;

	}

	public String[] profileInfo(String uid) {

		String[] s1;
		s1 = new String[1];
		s1[0] = uid;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cr = null;

		cr = db.rawQuery("select hotelname,emailid,mobile,enquiryno,ownername,message from signup where id=?", s1);
		String[] res = new String[6];
		if (cr.moveToFirst()) {

			do {

				res[0] = cr.getString(cr.getColumnIndex("hotelname"));
				res[1] = cr.getString(cr.getColumnIndex("emailid"));
				res[2] = cr.getString(cr.getColumnIndex("mobile"));
				res[3] = cr.getString(cr.getColumnIndex("enquiryno"));
				res[4] = cr.getString(cr.getColumnIndex("ownername"));
				res[5] = cr.getString(cr.getColumnIndex("message"));

			} while (cr.moveToNext());
		}
		return res;

	}
	public String[] profileInfo2() {

		String[] s1;
		s1 = new String[1];
		String uid="yes";
		s1[0] = uid;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cr = null;

		cr = db.rawQuery("select mobileno,status,names from logintabel where status=?", s1);
		String[] res = new String[3];
		if (cr.moveToFirst()) {

			do {

				res[0] = cr.getString(cr.getColumnIndex("mobileno"));
				res[1] = cr.getString(cr.getColumnIndex("status"));
				res[2] = cr.getString(cr.getColumnIndex("names"));


			} while (cr.moveToNext());
		}
		return res;

	}
	public String[] profileInformation(String uid) {

		String[] s1;
		s1 = new String[1];
		s1[0] = uid;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cr = null;

		cr = db.rawQuery("select hotelname,emailid,mobile,enquiryno,ownername,message from signup where mobile=?", s1);
		String[] res = new String[6];
		if (cr.moveToFirst()) {

			do {

				res[0] = cr.getString(cr.getColumnIndex("hotelname"));
				res[1] = cr.getString(cr.getColumnIndex("emailid"));
				res[2] = cr.getString(cr.getColumnIndex("mobile"));
				res[3] = cr.getString(cr.getColumnIndex("enquiryno"));
				res[4] = cr.getString(cr.getColumnIndex("ownername"));
				res[5] = cr.getString(cr.getColumnIndex("message"));

			} while (cr.moveToNext());
		}
		return res;

	}

	public Boolean updateQuantity(String tabelid, String itemid, String quantity, String bill) {

		SQLiteDatabase db = getWritableDatabase();
		String[] s1;
		s1 = new String[2];
		s1[0] = tabelid;
		s1[1] = itemid;

		Cursor cr = null;
		ContentValues value = new ContentValues();
		value.put("quantity", quantity);
		value.put("bill", bill);
		try {
			db.update("tabelorder", value, "tabelid=? and itemid=?", s1);
			Log.v("inserted", quantity + "Item id :" + itemid);
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}

	public Boolean updateLoggedIn(String uid) {

		SQLiteDatabase db = getWritableDatabase();
		String[] s1;
		s1 = new String[1];
		s1[0] = uid;


		Cursor cr = null;
		ContentValues value = new ContentValues();
		value.put("loginstatus", "4");

		try {
			db.update("logintabel", value, "uid=?", s1);
			Log.v("inserted", uid + "Item id :" + uid);
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}public Boolean updateOrder() {

		SQLiteDatabase db = getWritableDatabase();
		String[] s1;
		s1 = new String[1];
		String uid="no";
		s1[0] = uid;


		Cursor cr = null;
		ContentValues value = new ContentValues();
		value.put("orderstatus", "yes");

		try {
			db.update("cart", value, "orderstatus=?", s1);
			Log.v("inserted", uid + "Item id :" + uid);
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}
	public Boolean updateLoggedOffbyserver(String uid) {

		SQLiteDatabase db = getWritableDatabase();
		String[] s1;
		s1 = new String[1];
		s1[0] = uid;


		Cursor cr = null;
		ContentValues value = new ContentValues();
		value.put("loginstatus", "2");

		try {
			db.update("logintabel", value, "uid=?", s1);
			Log.v("inserted", uid + "Item id :" + uid);
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}

	public Boolean updateLogOut(String uid) {

		SQLiteDatabase db = getWritableDatabase();
		String[] s1;
		s1 = new String[1];
		s1[0] = uid;


		Cursor cr = null;
		ContentValues value = new ContentValues();
		value.put("loginstatus", "3");

		try {
			db.update("logintabel", value, "uid=?", s1);
			Log.v("inserted", uid + "Item id :" + uid);
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}

	public Boolean deleteTabel(String tabelId) {
		SQLiteDatabase db = getWritableDatabase();
		String[] s1;
		s1 = new String[1];
		s1[0] = tabelId;
		Cursor cr = null;
		ContentValues value = new ContentValues();
		value.remove(tabelId);
		try {
			db.delete("tabelorder", "tabelid=?", s1);
			// cr = db.rawQuery(qry, null);
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}

	public Boolean deleteItem(String itemid) {

		SQLiteDatabase db = getWritableDatabase();
		String[] s1;
		s1 = new String[1];

		s1[0] = itemid;
		Cursor cr = null;
		ContentValues value = new ContentValues();
		value.remove(itemid);
		//value.remove(datesT);
		try {
			db.delete("cart", "itemid=?", s1);
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}

	public Boolean deleteMenuItem(String itemid) {
		SQLiteDatabase db = getWritableDatabase();
		String[] s1;
		s1 = new String[1];
		s1[0] = itemid;
		Cursor cr = null;
		ContentValues value = new ContentValues();
		value.remove(itemid);
		try {
			db.delete("cart", "itemid=?", s1);
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}

	public Boolean deleteTabelId(String tabelid) {

		SQLiteDatabase db = getWritableDatabase();
		String[] s1;
		s1 = new String[1];
		s1[0] = tabelid;
		Cursor cr = null;
		ContentValues value = new ContentValues();
		value.remove(tabelid);
		try {
			db.delete("tabelStatus", "tabelid=?", s1);
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}

	public void saveMenuItem(String category, String itemName, String titemPrice) {

		Log.v("inserted", itemName + "itemprice  :" + titemPrice);
		ContentValues cv = new ContentValues();
		cv.put("itemname", itemName);
		cv.put("itemprice", titemPrice);
		cv.put("category", category);
		cv.put("backupstatus", "no" );

		SQLiteDatabase DB = getWritableDatabase();
		DB.insert("menuitem", null, cv);
		Log.v("inserted", itemName + "itemprice  :" + titemPrice);

	}
	public Boolean updateMenuItem(String itemid, String quantity, double val) {

		SQLiteDatabase db = getWritableDatabase();
		String[] s1;
		s1 = new String[1];
		s1[0] = itemid;


		Cursor cr = null;
		ContentValues value = new ContentValues();
		value.put("quantity",quantity);
		value.put("value",val);


		try {
			db.update("cart", value, "itemid=?", s1);

			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}
	public Boolean updateTabel(String tabelid,String tabelno,
								  String tabelname) {

		SQLiteDatabase db = getWritableDatabase();
		String[] s1;
		s1 = new String[1];
		s1[0] = tabelid;


		Cursor cr = null;
		ContentValues value = new ContentValues();
		value.put("tabelno",tabelno);
		value.put("tabelname",tabelname);


		try {
			db.update("tabelStatus", value, "tabelid=?", s1);
			Log.v("inserted", tabelid + "Item id :" + tabelid);
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}

	public boolean saveLoginDetails(String userid, String userpass, String loginstatus) {

		Log.v("inserted", loginstatus + "itemprice  :" + userpass);
		ContentValues cv = new ContentValues();
		cv.put("userid", userid);
		cv.put("userpass", userpass);
		cv.put("loginstatus", loginstatus);

		SQLiteDatabase DB = getWritableDatabase();
		DB.insert("logintabel", null, cv);
		Log.v("inserted", userpass + "itemprice  :" + userpass);
		return true;

	}

	public ArrayList<String> selectMenuItemList(String category) {

		ArrayList<String> Sname2;
		String a = "@&#;&@";
		Sname2 = new ArrayList<String>();

		String[] s1;
		s1 = new String[1];
		s1[0] = category;

		SQLiteDatabase DB = getReadableDatabase();

		Cursor cr = DB.rawQuery("select * from menuitem", null);

		cr.moveToFirst();
		for (int i = 0; i < cr.getCount(); i++) {
			String name0 = cr.getString(cr.getColumnIndex("itemid"));
			String name1 = cr.getString(cr.getColumnIndex("itemname"));
			String name2 = cr.getString(cr.getColumnIndex("itemprice"));
			String name3 = cr.getString(cr.getColumnIndex("category"));
			String name4 = cr.getString(cr.getColumnIndex("backupstatus"));

			String name7 = name0 + a + name1 + a + name2 + a + name3 + a + name4;
			Sname2.add(name7);
			cr.moveToNext();
			Log.v("inserted", name7);

		}
		cr.close();
		return Sname2;

	}

	public ArrayList<String> selectMenuItemListCategory(String category) {

		ArrayList<String> Sname2;
		String a = "@&#;&@";
		Sname2 = new ArrayList<String>();

		String[] s1;
		s1 = new String[1];
		s1[0] = category;

		SQLiteDatabase DB = getReadableDatabase();

		Cursor cr = DB.rawQuery("select * from menuitem where category=?", s1);

		cr.moveToFirst();
		for (int i = 0; i < cr.getCount(); i++) {
			String name0 = cr.getString(cr.getColumnIndex("itemid"));
			String name1 = cr.getString(cr.getColumnIndex("itemname"));
			String name2 = cr.getString(cr.getColumnIndex("itemprice"));
			String name3 = cr.getString(cr.getColumnIndex("category"));

			String name7 = name0 + a + name1 + a + name2 + a + name3;
			Sname2.add(name7);
			cr.moveToNext();
			Log.v("inserted", name7);

		}
		return Sname2;

	}

	///******************************OFFLINE SECTION *****************************////
	public boolean saveSignUp(String hotelname, String owner, String email,
							  String mobile,String message,String enquiry) {

//		Log.v("inserted", itemName + "itemprice  :" + titemPrice);
		ContentValues cv = new ContentValues();
		cv.put("emailid", email);
		cv.put("mobile", mobile);
		cv.put("hotelname", hotelname);
		cv.put("message", message);
		cv.put("ownername", owner);
		cv.put("enquiryno", enquiry);


		cv.put("loginstatus", "1");
		cv.put("validate", "1");
		SQLiteDatabase DB = getWritableDatabase();
		DB.insert("signup", null, cv);
		Log.v("inserted", message + "itemprice  :" + message);
		return true;


	}

	public ArrayList<String> selectSignup(String category) {

		ArrayList<String> Sname2;
		String a = "@&#;&@";
		Sname2 = new ArrayList<String>();

		String[] s1;
		s1 = new String[1];
		String loginstatus = "2";
		s1[0] = loginstatus;

		SQLiteDatabase DB = getReadableDatabase();

		Cursor cr = DB.rawQuery("select * from signup where loginstatus=?", s1);

		cr.moveToFirst();
		for (int i = 0; i < cr.getCount(); i++) {
			String name0 = cr.getString(cr.getColumnIndex("ownername"));
			String name1 = cr.getString(cr.getColumnIndex("hotelname"));
			String name2 = cr.getString(cr.getColumnIndex("validate"));
			String name3 = cr.getString(cr.getColumnIndex("emailid"));
			String name4 = cr.getString(cr.getColumnIndex("mobile"));


			String name7 = name0 + a + name1 + a + name2;
			Sname2.add(name7);
			cr.moveToNext();
			Log.v("inserted", name7);

		}
		return Sname2;

	}


	public boolean addtoOfflineTabelOrder(String billno, String tabelid, String tabelNo, String itemid, String itemname, String category,
										  String quantity, String itemprice,
										  String value, String tabelstatus, String dates, String times) {
		ContentValues cv = new ContentValues();
		cv.put("billno", billno);
		cv.put("tabelid", tabelid);
		cv.put("tabelno", tabelNo);
		cv.put("itemid", itemid);
		cv.put("itemname", itemname);
		cv.put("category", category);
		cv.put("quantity", quantity);
		cv.put("itemprice", itemprice);
		cv.put("value", value);
		cv.put("tabelstatus", tabelstatus);
		cv.put("dates", dates);
		cv.put("times", times);
		cv.put("backupstatus", "no");
		SQLiteDatabase DB = getWritableDatabase();
		DB.insert("offlineOrder", null, cv);
		Log.v("inserted", itemid + "Tabel id :" + tabelid + "billno :" +
				billno + "Staus" + category + "Bill :" + value);
		return true;
	}public boolean addtoOfflineTabelOrder2(String billno, String tabelid, String tabelNo, String itemid, String itemname, String category,
										  String quantity, String itemprice,
										  String value, String tabelstatus, String dates, String times) {
		ContentValues cv = new ContentValues();
		cv.put("billno", billno);
		cv.put("tabelid", tabelid);
		cv.put("tabelno", tabelNo);
		cv.put("itemid", itemid);
		cv.put("itemname", itemname);
		cv.put("category", category);
		cv.put("quantity", quantity);
		cv.put("itemprice", itemprice);
		cv.put("value", value);
		cv.put("tabelstatus", tabelstatus);
		cv.put("dates", dates);
		cv.put("times", times);
		cv.put("backupstatus", "Yes");
		SQLiteDatabase DB = getWritableDatabase();
		DB.insert("offlineOrder", null, cv);
		Log.v("inserted", itemid + "Tabel id :" + tabelid + "billno :" +
				billno + "Staus" + category + "Bill :" + value);
		return true;
	}

    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
//         db.delete(TABLE_NAME,null,null);
//        db.execSQL("delete * from offlineOrder");
		db.delete("chatmessages",null,null);
//        db.execSQL("TRUNCATE table offlineOrder");
        db.close();
    }

	public boolean addtoOfflineMenuitem(String itemid, String itemname, String itemprice, String category)
	{
		ContentValues cv = new ContentValues();
		cv.put("itemid", itemid);
		cv.put("itemname", itemname);
		cv.put("itemprice", itemprice);
		cv.put("category", category);
		cv.put("backupstatus", "yes");
		SQLiteDatabase DB = getWritableDatabase();
		DB.insert("menuitem", null, cv);
		Log.v("inserted", itemid + "Tabel id :" + category + "billno :" +
				category + "Staus" + category + "Bill :" + category);
		return true;
	}
	public boolean addtoOfflineTabel(String tabelid, String tabelname, String tabelstatus, String tabelno)
	{
		ContentValues cv = new ContentValues();
		cv.put("tabelid", tabelid);
		cv.put("tabelname", tabelname);
		cv.put("tabelstatus", tabelstatus);
		cv.put("tabelno", tabelno);
		cv.put("backupstatus", "Yes");
		SQLiteDatabase DB = getWritableDatabase();
		DB.insert("tabelStatus", null, cv);
		Log.v("inserted", tabelid + "Tabel id :" + tabelno + "billno :" +
				tabelno + "Staus" + tabelno + "Bill :" + tabelno);
		return true;
	}

	public ArrayList<String> selectTabellist() {

		ArrayList<String> Sname2;
		String a = "@&#;&@";
		Sname2 = new ArrayList<String>();



		SQLiteDatabase DB = getReadableDatabase();

		Cursor cr = DB.rawQuery("select * from tabelStatus", null);

		cr.moveToFirst();
		for (int i = 0; i < cr.getCount(); i++) {
			String name0 = cr.getString(cr.getColumnIndex("tabelid"));
			String name1 = cr.getString(cr.getColumnIndex("tabelname"));
			String name2 = cr.getString(cr.getColumnIndex("tabelstatus"));


			String name7 = name0 + a + name1 + a + name2;
			Sname2.add(name7);
			cr.moveToNext();
			Log.v("inserted", name7);

		}
		return Sname2;

	}
    public int getRecords()
    {
        String selectQuery = "SELECT id FROM offlineOrder where backupstatus='no'";
        SQLiteDatabase DB = getReadableDatabase();
        Cursor c = DB.rawQuery(selectQuery, null);
        c.moveToFirst();
        int total = c.getCount();
        c.close();

        return total;
    }
    public int getTabelscount()
    {
        String selectQuery = "SELECT tabelid FROM tabelStatus where backupstatus='no'";
        SQLiteDatabase DB = getReadableDatabase();
        Cursor c = DB.rawQuery(selectQuery, null);
        c.moveToFirst();
        int total = c.getCount();
        c.close();

        return total;
    }  public int getMenuscount()
    {
        String selectQuery = "SELECT itemid FROM menuitem where backupstatus='no' ";
        SQLiteDatabase DB = getReadableDatabase();
        Cursor c = DB.rawQuery(selectQuery, null);
        c.moveToFirst();
        int total = c.getCount();
        c.close();

        return total;
    }
	public ArrayList<String> selectTabellist2() {

		ArrayList<String> Sname2;
		String a = "@&#;&@";
		Sname2 = new ArrayList<String>();



		SQLiteDatabase DB = getReadableDatabase();

		Cursor cr = DB.rawQuery("select * from tabelStatus order by tabelid ASC", null);

		cr.moveToFirst();
		for (int i = 0; i < cr.getCount(); i++) {
			String name0 = cr.getString(cr.getColumnIndex("tabelid"));
			String name1 = cr.getString(cr.getColumnIndex("tabelname"));
			String name2 = cr.getString(cr.getColumnIndex("tabelstatus"));
            String name3 = cr.getString(cr.getColumnIndex("tabelno"));
			 String name4 = cr.getString(cr.getColumnIndex("backupstatus"));


			String name7 = name0 + a + name1 + a + name2 + a + name3 + a + name4;
			Sname2.add(name7);
			cr.moveToNext();
			Log.v("inserted", name7);

		}
		cr.close();
		return Sname2;

	}
	public ArrayList<String> selectTabellist3(String mob) {

		ArrayList<String> Sname2;
		String a = "@&#;&@";
		Sname2 = new ArrayList<String>();
		String[] s1=new String[1];
		String backupstatus="no";
		s1[0]=backupstatus;



		SQLiteDatabase DB = getReadableDatabase();

		Cursor cr = DB.rawQuery("select * from tabelStatus where backupstatus=? order by tabelid ASC", s1);

		cr.moveToFirst();
		for (int i = 0; i < cr.getCount(); i++) {
			String name0 = cr.getString(cr.getColumnIndex("tabelid"));
			String name1 = cr.getString(cr.getColumnIndex("tabelname"));
			String name2 = cr.getString(cr.getColumnIndex("tabelstatus"));
            String name3 = cr.getString(cr.getColumnIndex("tabelno"));
			 String name4 = mob;


			String name7 = name0 + a + name1 + a + name2 + a + name3 + a + name4;
			Sname2.add(name7);
			cr.moveToNext();
			Log.v("inserted", name7);

		}
		cr.close();
		return Sname2;


	}

	public void addNewTabel(String tabelname,String tabelno) {


		ContentValues cv = new ContentValues();
		cv.put("tabelname", tabelname);
		cv.put("tabelstatus", "0");
		cv.put("tabelno", tabelno);
		cv.put("backupstatus", "no");

		SQLiteDatabase DB = getWritableDatabase();
		DB.insert("tabelStatus", null, cv);
		Log.v("inserted", tabelname + "tabelname  :" + tabelname);

	}

//	public String[] offlinetotalQuantity() {
//
//		String[] s1;
//		s1 = new String[2];
//
//		String tabelstatus = "0";
//		s1[1] = tabelstatus;
//		SQLiteDatabase db = getWritableDatabase();
//		Cursor cr = null;
//
//		cr = db.rawQuery("select count(itemid) from cart where orderstatus=?", s1);
//		String[] res = new String[1];
//		if (cr.moveToFirst()) {
//
//			do {
//
//				res[0] = cr.getString(0);
//
//			} while (cr.moveToNext());
//		}
//		return res;
//	}

	public int getProfilesCount() {

		String countQuery = "SELECT  * FROM cart where orderstatus='no'";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int cnt = cursor.getCount();
		cursor.close();
		return cnt;
	}

	public String[] offlinetotalBill() {

		String[] s1;
		s1 = new String[1];

		String tabelstatus = "no";
		s1[0] = tabelstatus;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cr = null;

		cr = db.rawQuery("select sum(value) as value from cart where orderstatus=?", s1);
		String[] res = new String[1];
		if (cr.moveToFirst()) {

			do {

				res[0] = cr.getString(0);


			} while (cr.moveToNext());
		}
		return res;

	}
	public String getItemQty(String itemid) {

		String[] s1;
		s1 = new String[2];

		String tabelstatus = "no";
		s1[0] = tabelstatus;
		s1[1] = itemid;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cr = null;

		cr = db.rawQuery("select quantity from cart where orderstatus=? and itemid=?", s1);
		String res ="0";
		if (cr.moveToFirst()) {

			do {

				res = cr.getString(0);


			} while (cr.moveToNext());
		}
		return res;

	}
	public String[] getAddress(String mobile) {

		String[] s1;
		s1 = new String[1];

		String tabelstatus = "no";
		s1[0] = mobile;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cr = null;

		cr = db.rawQuery("select names,address1,city,state,pincode from address where mobileno=?", s1);
		String[] res = new String[5];
		if (cr.moveToFirst()) {

			do {

				res[0] = cr.getString(0);
				res[1] = cr.getString(1);
				res[2] = cr.getString(2);
				res[3] = cr.getString(3);
				res[4] = cr.getString(4);


			} while (cr.moveToNext());
		}
		return res;

	}


	public String[] checkTabelStatus(String tabelid) {

		String[] s1;
		s1 = new String[1];
		s1[0] = tabelid;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cr = null;

		cr = db.rawQuery("select tabelstatus from tabelStatus where tabelid=?", s1);
		String[] res = new String[2];
		if (cr.moveToFirst()) {

			do {

				res[0] = cr.getString(0);

			} while (cr.moveToNext());
		}
		return res;

	}

	public String checkLoginStatus() {

		String[] s1;
		String loginstatus = "4";
		s1 = new String[1];
		s1[0] = loginstatus;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cr = null;

		cr = db.rawQuery("select uid from logintabel where loginstatus=?", s1);
		String res = null;
		if (cr.moveToFirst()) {

			do {

				res = cr.getString(cr.getColumnIndex("uid"));

			} while (cr.moveToNext());
		}
		return res;

	}
	public String retrivemobile() {

		String[] s1;
		String loginstatus = "4";
		s1 = new String[1];
		s1[0] = loginstatus;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cr = null;

		cr = db.rawQuery("select userid from logintabel where loginstatus=?", s1);
		String res = null;
		if (cr.moveToFirst()) {

			do {

				res = cr.getString(cr.getColumnIndex("userid"));

			} while (cr.moveToNext());
		}
		return res;

	}
	public String getQty(String itemid) {

		String[] s1;
		String orderstatus = "no";
		s1 = new String[2];
		s1[0] = itemid;	s1[1] = orderstatus;
		SQLiteDatabase db = getReadableDatabase();
		Cursor cr = null;

		cr = db.rawQuery("select quantity from cart where itemid=? and orderstatus=?", s1);

//		cr = db.rawQuery("select mobileno,status,names from logintabel where status=?", s1);
		String res = null;
		if (cr.moveToFirst()) {

			do {

				res = cr.getString(6);

			} while (cr.moveToNext());
		}
		return res;

	}

	public Boolean updateProfile(String uid, String hname, String mail, String mob, String address) {
		SQLiteDatabase db = getWritableDatabase();
		String[] s1;
		s1 = new String[1];
		s1[0] = uid;
		Cursor cr = null;
		ContentValues value = new ContentValues();
		value.put("hotelname", hname);
		value.put("emailid", mail);
		value.put("enquiryno", mob);
		value.put("message", address);

		try {
			db.update("signup", value, "id=?", s1);
			Log.v("inserted", uid + "Item id :" + uid);
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}

	public String checknotEmpty() {

		String[] s1;
		String loginstatus = "2";
		s1 = new String[1];
		s1[0] = loginstatus;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cr = null;

		cr = db.rawQuery("select * from logintabel where loginstatus=?", s1);
		String res = null;
		if (cr.moveToFirst()) {

			do {

				res = cr.getString(cr.getColumnIndex("uid"));

			} while (cr.moveToNext());
		}
		return res;

	}
	public String checkWhetherLogout() {

		String[] s1;
		String loginstatus = "3";
		s1 = new String[1];
		s1[0] = loginstatus;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cr = null;

		cr = db.rawQuery("select * from logintabel where loginstatus=?", s1);
		String res = null;
		if (cr.moveToFirst()) {

			do {

				res = cr.getString(cr.getColumnIndex("uid"));

			} while (cr.moveToNext());
		}
		return res;

	}

	public String verifyLogin(String userid, String userpass) {

		String[] s1;
		s1 = new String[2];
		s1[0] = userid;
		s1[1] = userpass;
		SQLiteDatabase db = getReadableDatabase();
		Cursor cr = null;

		cr = db.rawQuery("select uid from logintabel where userid=? and userpass=?", s1);
		String res = null;
		if (cr.moveToFirst()) {

			do {

				res = cr.getString(cr.getColumnIndex("uid"));

			} while (cr.moveToNext());
		}
		return res;

	}

	public Boolean offlineupdateQuantity(String tabelid, String itemid, String quantity,
										 String bill) {

		SQLiteDatabase db = getWritableDatabase();
		String[] s1;
		s1 = new String[2];
		s1[0] = tabelid;
		s1[1] = itemid;

		Cursor cr = null;
		ContentValues value = new ContentValues();
		value.put("quantity", quantity);
		value.put("value", bill);
		try {
			db.update("offlineOrder", value, "tabelid=? and itemid=?", s1);
			Log.v("inserted", quantity + "Item id :" + itemid);
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}

	public Boolean offlinedeleteItem(String tabelId, String itemid) {

		SQLiteDatabase db = getWritableDatabase();
		String[] s1;
		s1 = new String[2];
		s1[0] = tabelId;
		s1[1] = itemid;
		Cursor cr = null;
		ContentValues value = new ContentValues();
		value.remove(tabelId);
		//value.remove(datesT);
		try {
			db.delete("offlineOrder", "tabelid=? and itemid=?", s1);
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}
	public Boolean clearCart() {

		SQLiteDatabase db = getWritableDatabase();

		Cursor cr = null;
		ContentValues value = new ContentValues();
//		value.remove(tabelId);
		//value.remove(datesT);
		try {
			db.delete("cart", null, null);
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}

	public List<ProductDataList> offlineselectedOrderList() {

		List<ProductDataList> Sname2;
		String a = "@&#;&@";
		Sname2 = new ArrayList<>();
		String tabelstatus = "no";
		String[] s1;
		s1 = new String[1];
//		s1[0] = tabelid;
		s1[0] = tabelstatus;

		SQLiteDatabase DB = getReadableDatabase();

		Cursor cr = DB.rawQuery("select * from cart where orderstatus=?", s1);

		cr.moveToFirst();
		for (int i = 0; i < cr.getCount(); i++) {

			String name1 = cr.getString(cr.getColumnIndex("itemid"));
			String name2 = cr.getString(cr.getColumnIndex("itemname"));
			String name3 = cr.getString(cr.getColumnIndex("itemunit"));
			String name4 = cr.getString(cr.getColumnIndex("itemprice"));
			String quantity = cr.getString(cr.getColumnIndex("quantity"));

			String image = cr.getString(cr.getColumnIndex("image"));
			String itemunit = cr.getString(cr.getColumnIndex("itemunit"));
			String value = cr.getString(cr.getColumnIndex("value"));
			String discount = cr.getString(cr.getColumnIndex("discount"));

			ProductDataList productDataList=null;

//			String name7 = name1 + a + name2 + a + name3 + a + name4 + a + quantity;
			Sname2.add(productDataList);
			cr.moveToNext();
//			Log.v("inserted", name7);

		}
		return Sname2;

	}
	public ArrayList<String> detailOrderedList(String billno) {

		ArrayList<String> Sname2;
		String a = "@&#;&@";
		Sname2 = new ArrayList<String>();

		String[] s1;
		s1 = new String[1];
		s1[0] = billno;
//		s1[1] = tabelstatus;

		SQLiteDatabase DB = getReadableDatabase();

		Cursor cr = DB.rawQuery("select * from offlineOrder where billno=?", s1);

		cr.moveToFirst();
		for (int i = 0; i < cr.getCount(); i++) {

			String name1 = cr.getString(cr.getColumnIndex("itemid"));
			String name2 = cr.getString(cr.getColumnIndex("itemname"));
			String name3 = cr.getString(cr.getColumnIndex("category"));
			String name4 = cr.getString(cr.getColumnIndex("itemprice"));
			String name5 = cr.getString(cr.getColumnIndex("quantity"));
//			String name6 = cr.getString(cr.getColumnIndex("bill"));
			//String name5 = cr.getString(cr.getColumnIndex("itemstatus"));

			String name7 = name1 + a + name2 + a + name3 + a + name4 + a + name5;
			Sname2.add(name7);
			cr.moveToNext();
			Log.v("inserted", name7);

		}
		return Sname2;

	}

	public ArrayList<String> offlineSearchlist() {

		ArrayList<String> Sname2;
		String a = "@&#;&@";
		Sname2 = new ArrayList<String>();

//		String[] s1;
//		s1 = new String[1];
//		s1[0] = tabelid;

		SQLiteDatabase DB = getReadableDatabase();

		Cursor cr = DB.rawQuery("select * from menuitem", null);

		cr.moveToFirst();
		for (int i = 0; i < cr.getCount(); i++) {

			String name1 = cr.getString(cr.getColumnIndex("itemid"));
			String name2 = cr.getString(cr.getColumnIndex("itemname"));
			String name3 = cr.getString(cr.getColumnIndex("category"));
			String name4 = cr.getString(cr.getColumnIndex("itemprice"));
//			String name5 = cr.getString(cr.getColumnIndex("quantity"));
//			String name6 = cr.getString(cr.getColumnIndex("bill"));
			//String name5 = cr.getString(cr.getColumnIndex("itemstatus"));

			String name7 = name1 + a + name2 + a + name3 + a + name4;
			Sname2.add(name7);
			cr.moveToNext();
			Log.v("inserted", name7);

		}
		return Sname2;

	}

	public ArrayList<String> dailyReport(String todaysdate) {

		ArrayList<String> Sname2;
		String a = "@&#;&@";
		Sname2 = new ArrayList<String>();

		String[] s1;
		s1 = new String[1];
		s1[0] = todaysdate;

		SQLiteDatabase DB = getReadableDatabase();

		Cursor cr = DB.rawQuery("select SUM(value) as total,billno,tabelno,times,backupstatus from offlineOrder where dates=? GROUP BY billno ORDER BY billno ASC ", s1);

		cr.moveToFirst();
		for (int i = 0; i < cr.getCount(); i++) {

			String name1 = cr.getString(cr.getColumnIndex("billno"));
			String name2 = cr.getString(cr.getColumnIndex("tabelno"));
			String name3 = cr.getString(cr.getColumnIndex("total"));
			String name4 = cr.getString(cr.getColumnIndex("times"));
			String name5 = cr.getString(cr.getColumnIndex("backupstatus"));

			String name7 = name1 + a + name2 + a + name3 + a + name4 + a + name5;
			Sname2.add(name7);
			cr.moveToNext();
			Log.v("inserted", name7);

		}
		return Sname2;

	}

	public ArrayList<String> dailyItemwiseReport(String todaysdate) {
		ArrayList<String> Sname2;
		String a = "@&#;&@";
		Sname2 = new ArrayList<String>();

		String[] s1;
		s1 = new String[1];
		s1[0] = todaysdate;

		SQLiteDatabase DB = getReadableDatabase();

		Cursor cr = DB.rawQuery("select SUM(quantity) as total,itemid,itemname,itemprice from offlineOrder where dates=? GROUP BY itemid ORDER BY itemid ASC", s1);

		cr.moveToFirst();
		for (int i = 0; i < cr.getCount(); i++) {

			String name1 = cr.getString(cr.getColumnIndex("itemid"));
			String name2 = cr.getString(cr.getColumnIndex("itemname"));
			String name3 = cr.getString(cr.getColumnIndex("total"));
			String name4 = cr.getString(cr.getColumnIndex("itemprice"));

			String name7 = name1 + a + name2 + a + name3 + a + name4;
			Sname2.add(name7);
			cr.moveToNext();
			Log.v("inserted", name7);

		}
		return Sname2;

	}

	public ArrayList<String> monthlyReport(String fromdate,String todate) {

		ArrayList<String> Sname2;
		String a = "@&#;&@";
		Sname2 = new ArrayList<String>();

		String[] s1;
		s1 = new String[2];
		s1[0] = fromdate;
		s1[1] = todate;

		SQLiteDatabase DB = getReadableDatabase();

		Cursor cr = DB.rawQuery("select SUM(value) as total,dates from offlineOrder where dates between ? and ? GROUP BY dates", s1);

		cr.moveToFirst();
		for (int i = 0; i < cr.getCount(); i++) {

			String name1 = cr.getString(cr.getColumnIndex("dates"));

			String name3 = cr.getString(cr.getColumnIndex("total"));


			String name7 = name1 + a + name3;
			Sname2.add(name7);
			cr.moveToNext();
			Log.v("inserted", name7);

		}
		return Sname2;

	}

	public Boolean updateTabelStatusFree(String tabelid) {

		SQLiteDatabase db = getWritableDatabase();
		String[] s1;
		s1 = new String[1];
		s1[0] = tabelid;


		Cursor cr = null;
		ContentValues value = new ContentValues();
		value.put("tabelstatus", "0");

		try {
			db.update("tabelStatus", value, "tabelid=?", s1);
			Log.v("inserted", tabelid + "Item id :" + tabelid);
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}

	public Boolean updateTabelStatusEngage(String tabelid) {

		SQLiteDatabase db = getWritableDatabase();
		String[] s1;
		s1 = new String[1];
		s1[0] = tabelid;


		Cursor cr = null;
		ContentValues value = new ContentValues();
		value.put("tabelstatus", "1");

		try {
			db.update("tabelStatus", value, "tabelid=?", s1);
			Log.v("inserted", tabelid + "Item id :" + tabelid);
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}

	public Boolean updateOrderTabelStatusEngage(String tabelid) {

		SQLiteDatabase db = getWritableDatabase();
		String[] s1;
		s1 = new String[1];
		s1[0] = tabelid;
		String tabelstatus = "0";

		Cursor cr = null;
		ContentValues value = new ContentValues();
		value.put("tabelstatus", tabelstatus);

		try {
			db.update("offlineOrder", value, "tabelid=?", s1);
			Log.v("inserted", tabelid + "Item id :" + tabelid);
			return true;
		} catch (Exception e) {
			// TODO: handle exception

			return false;
		}

	}

	public String ExistingmaxOrderno(String tabelid) {

		String[] s1;
		s1 = new String[2];
		s1[0] = tabelid;
		String tabelstatus = "1";
		s1[1] = tabelstatus;
		SQLiteDatabase db = getReadableDatabase();
		Cursor cr = null;

		cr = db.rawQuery("select billno from offlineOrder where tabelid=? and tabelstatus=?", s1);
		String res = "";
		if (cr.moveToFirst()) {
			do {
				res = cr.getString(cr.getColumnIndex("billno"));
			} while (cr.moveToNext());
		}
		return res;

	}
	public String[] Selectmobile() {

		String[] s1;
		s1 = new String[1];
//		s1[0] = tabelid;
		String status = "yes";
		s1[0] = status;
		SQLiteDatabase db = getReadableDatabase();
		Cursor cr = null;

		cr = db.rawQuery("select * from logintabel where status=?",s1);
		String[] res = new String[3];
		if (cr.moveToFirst()) {
			do {
				res[0] = cr.getString(1);
				res[1] = cr.getString(3);
				res[2] = cr.getString(2);
			} while (cr.moveToNext());
		}
		return res;

	}
	public String checkLoginStatus2() {
		String[] s1;
		String loginstatus = "yes";
		s1 = new String[1];
		s1[0] = loginstatus;
		SQLiteDatabase db = getWritableDatabase();
		Cursor cr = null;
		cr = db.rawQuery("select mobileno from logintabel", null);
		String res = null;
		if (cr.moveToFirst()) {
			do {
				res = cr.getString(cr.getColumnIndex("mobileno"));
			} while (cr.moveToNext());
		}
		return res;
	}

	public String newmaxOrderno() {

		SQLiteDatabase db = getReadableDatabase();
		Cursor cr = null;
		cr = db.rawQuery("select max(billno) as bill from offlineOrder", null);
		String res = "";
		if (cr.moveToFirst()) {
			do {
				res = cr.getString(cr.getColumnIndex("bill"));
			} while (cr.moveToNext());
		}
		return res;

	}


	///   Synchronous
	public ArrayList<HashMap<String, String>> getAllUsers() {
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM chatmessages";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", cursor.getString(0));
				map.put("customerid", cursor.getString(1));
				wordList.add(map);
			} while (cursor.moveToNext());
		}
		database.close();
		return wordList;
	}	///   Synchronous
	public List<BannerDataList> getAllbanners() {

		List<BannerDataList> Sname2;
		Sname2 = new ArrayList<>();
		SQLiteDatabase DB = getReadableDatabase();
		Cursor cr = DB.rawQuery("select * from banners", null);
		cr.moveToFirst();
		for (int i = 0; i < cr.getCount(); i++)
		{
			String name1 = cr.getString(cr.getColumnIndex("id"));
			String name2 = cr.getString(cr.getColumnIndex("image"));
			String name3 = cr.getString(cr.getColumnIndex("name"));
			BannerDataList productDataList=new BannerDataList(name1,name3,name2,"1");
			Sname2.add(productDataList);
			cr.moveToNext();
		}
		DB.close();
		return Sname2;
	}
	public ArrayList<HashMap<String, String>> getAllUsersCart() {
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM cart";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", cursor.getString(0));
				map.put("itemid", cursor.getString(1));
				wordList.add(map);
			} while (cursor.moveToNext());
		}
		database.close();
		return wordList;
	}
	public ArrayList<HashMap<String, String>> getAllMenuitem(){
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM menuitem";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("itemid", cursor.getString(0));
				map.put("itemname", cursor.getString(1));
				wordList.add(map);
			} while (cursor.moveToNext());
		}
		database.close();
		return wordList;
	}
	public ArrayList<HashMap<String, String>> getAllTabelstatus() {
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM tabelStatus";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("tabelid", cursor.getString(0));
				map.put("tabelstatus", cursor.getString(1));
				wordList.add(map);
			} while (cursor.moveToNext());
		}
		database.close();
		return wordList;
	}
	public String composeJSONfromSQLiteOfflineOrder(String sendername,String emailid,String cityid) {
		ArrayList<HashMap<String, String>> orderList;
//		String mob=mobile;
		orderList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM chatmessages where status = '" + "no" + "'";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {


			do {
				HashMap<String, String> map = new HashMap<String, String>();
//				map.put("customer_id", cursor.getString(0));
				map.put("customer_id", cursor.getString(1));
				map.put("sender_type", cursor.getString(2));
				map.put("message", cursor.getString(3));
				map.put("date", cursor.getString(4));
				map.put("time", cursor.getString(5));
				map.put("status", cursor.getString(6));
				map.put("sender_name", sendername);
				map.put("mobileno", emailid);	map.put("city_id", cityid);


				orderList.add(map);
			} while (cursor.moveToNext());
		}
		database.close();
		Gson gson = new GsonBuilder().create();
		//Use GSON to serialize Array List to JSON
		return gson.toJson(orderList);
	}
	public String composeJSONfromSQLiteOfflineOrderCart(String sendername,String emailid) {
		ArrayList<HashMap<String, String>> orderList;
		orderList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM cart where orderstatus = '" + "no" + "'";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("product_id", cursor.getString(1));
				map.put("itemname", cursor.getString(2));
				map.put("product_price", cursor.getString(4));
				map.put("product_qty", cursor.getString(7));

				map.put("unit_value", cursor.getString(8));
				map.put("product_unit", cursor.getString(6));

				orderList.add(map);
			} while (cursor.moveToNext());
		}
		database.close();
		Gson gson = new GsonBuilder().create();
		//Use GSON to serialize Array List to JSON
		return gson.toJson(orderList);
	}
	public String composeJSONfromSQLiteMenuItem(String mobile) {
		ArrayList<HashMap<String, String>> orderList;
		String mob=mobile;
		orderList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM menuitem where backupstatus = '" + "no" + "'";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("itemid", cursor.getString(0));
				map.put("itemname", cursor.getString(1));
				map.put("itemprice", cursor.getString(2));
				map.put("category", cursor.getString(3));
				map.put("mobile", mob);

				orderList.add(map);
			} while (cursor.moveToNext());
		}
		database.close();
		Gson gson = new GsonBuilder().create();
		//Use GSON to serialize Array List to JSON
		return gson.toJson(orderList);
	}
	public String composeJSONfromSQLiteTabelStatus(String mobile) {
		ArrayList<HashMap<String, String>> orderList;
		String mob=mobile;
		orderList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT  * FROM tabelStatus where backupstatus = '" + "no" + "'";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("tabelid", cursor.getString(0));
				map.put("tabelname", cursor.getString(1));
				map.put("tabelstatus", cursor.getString(2));
				map.put("tabelno", cursor.getString(3));

				map.put("mobile",mob);

				orderList.add(map);
			} while (cursor.moveToNext());
		}
		database.close();
		Gson gson = new GsonBuilder().create();
		//Use GSON to serialize Array List to JSON
		return gson.toJson(orderList);
	}

	public String getSyncStatus() {
		String msg = null;
		if (this.dbSyncCount() == 0) {
			msg = "SQLite and Remote MySQL DBs are in Sync!";
		} else {
			msg = "DB Sync needed";
		}
		return msg;
	}
	public int dbSyncCount() {
		int count = 0;
		String selectQuery = "SELECT  * FROM chatmessages where status = '" + "no" + "'";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		count = cursor.getCount();
		database.close();
		return count;
	}
	public int dbSyncCountCart() {
		int count = 0;
		String selectQuery = "SELECT  * FROM cart where orderstatus = '" + "no" + "'";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		count = cursor.getCount();
		database.close();
		return count;
	}
	public int dbSyncCount2() {
		int count = 0;
		String selectQuery = "SELECT  * FROM menuitem where backupstatus = '" + "no" + "'";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		count = cursor.getCount();
		database.close();
		return count;
	}public int dbSyncCount3() {
		int count = 0;
		String selectQuery = "SELECT  * FROM tabelStatus where backupstatus = '" + "no" + "'";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		count = cursor.getCount();
		database.close();
		return count;
	}


	public void updateSyncStatusOfflineOrder(String status) {
		SQLiteDatabase database = this.getWritableDatabase();
		String updateQuery = "Update chatmessages set status = '" + status + "'";
		Log.d("query", updateQuery);
		database.execSQL(updateQuery);
		database.close();
	}
	public void updateSyncStatusMenuitem(String id, String status) {
		SQLiteDatabase database = this.getWritableDatabase();
		String updateQuery = "Update menuitem set backupstatus = '" + status + "' where itemid=" + "'" + id + "'";
		Log.d("query", updateQuery);
		database.execSQL(updateQuery);
		database.close();
	}public void updateSyncStatusTabel(String id, String status) {
		SQLiteDatabase database = this.getWritableDatabase();
		String updateQuery = "Update tabelStatus set backupstatus = '" + status + "' where tabelid=" + "'" + id + "'";
		Log.d("query", updateQuery);
		database.execSQL(updateQuery);
		database.close();
	}


}