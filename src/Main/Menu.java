package Main;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Menu {
	Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args){
		//Menu m = new Menu();
		//m.buatDatabase("AntarDB");
		//m.hapusDatabase("AntarDB");
		//m.tampilListDatabase();
		//m.buatTabel();
		//m.hapusTabel();
		//m.tampilListTabel();
		
		//ManipulasiDatabase md = new ManipulasiDatabase();
		//md.buatDatabase("AntarDB");
		
		//ManipulasiTabel mt = new ManipulasiTabel();
		//mt.buatTabel();
		
		ManipulasiKueri mk = new ManipulasiKueri();
		try {
			mk.tampilRecord();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
