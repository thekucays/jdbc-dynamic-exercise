package Main;

import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {
		//settingan database 
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		static final String DB_URL = "jdbc:mysql://localhost/";
		static final String Username = "root";
		static final String Password = "";
		
		// konek yang ini dipake pas bikin database.. tanpa nama database
		public Connection konek(){
			try{
				Class.forName(JDBC_DRIVER);
				//System.out.println("Database berhasil terkoneksi");
				return DriverManager.getConnection(DB_URL, Username, Password);
			}
			catch(Exception e){
				System.out.println("Ada kesalahan koneksi");
				e.printStackTrace();
			}
			return null;
		}
		
		// konek yang ini dipake untuk manipulasi tabel nya.. nama database nya dimasukin
		public Connection konekNamaDB(String namaDB){
			try{
				Class.forName(JDBC_DRIVER);
				return DriverManager.getConnection(DB_URL + namaDB, Username, Password);
			}
			catch(Exception e){
				System.out.println("Ada kesalahan koneksi");
				e.printStackTrace();
			}
			return null;
		}
}
