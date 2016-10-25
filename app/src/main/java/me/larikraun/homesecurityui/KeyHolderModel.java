package me.larikraun.homesecurityui;

/**
 * Created by omolara on 10/25/16.
 */
public class KeyHolderModel {
	String name;
	String time;
	String day;
	String status;

	public KeyHolderModel (String name, String day, String time, String status) {
		this.name = name;
		this.time = time;
		this.day = day;
		this.status = status;
	}

	public String getName () {
		return name;
	}

	public String getTime () {
		return time;
	}

	public String getDay () {
		return day;
	}

	public String getStatus () {
		return status;
	}
}
