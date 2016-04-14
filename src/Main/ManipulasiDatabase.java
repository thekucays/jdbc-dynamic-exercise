package Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ManipulasiDatabase {
	static Koneksi kon = new Koneksi();
	Statement stmt = null;
	Connection con = null;
	Scanner scan = new Scanner(System.in);
	
	// MANIPULASI DATABASE ////////////////////////////////////////////////////////////////////////////////
		public void buatDatabase(String namaDatabase){
			con = kon.konek();
			String sql = "CREATE DATABASE " + namaDatabase;
			
			// jalanin proses bikin db
			try{
				stmt = con.createStatement();
				stmt.executeUpdate(sql);
				System.out.println("Database dengan nama " + namaDatabase + " berhasil terbentuk.");
			}
			catch(SQLException se){
				System.out.println("Ada kesalahan. Periksa nama database apakah sudah ada");
			}
			finally{
				try{ con.close(); }
				catch(SQLException se) { se.printStackTrace(); }
			}
		}
		
		public void hapusDatabase(String namaDatabase){
			con = kon.konek();
			String sql = "DROP DATABASE " + namaDatabase;
			
			// jalanin proses drop
			try{
				stmt = con.createStatement();
				stmt.executeUpdate(sql);
				System.out.println("Database dengan nama " + namaDatabase + " berhasil dihapus.");
			}
			catch(SQLException se){
				System.out.println("Ada kesalahan. Periksa nama database apakah masih ada");
			}
			finally{
				try{ con.close(); }
				catch(SQLException se) { se.printStackTrace(); }
			}
		}
		
		public void tampilListDatabase(){
			con = kon.konek();
			try{
				System.out.println("Berikut adalah list dari database sistem : ");
				ResultSet rs = con.getMetaData().getCatalogs();
				while(rs.next()){
					//System.out.println("TABLE_CAT = " + rs.getString("TABLE_CAT") );
					System.out.print(rs.getString("TABLE_CAT") + " - ");
				}
				rs.close();
			}
			catch(SQLException se){
				System.out.println("Ada kesalahan. Periksa database Anda");
			}
			finally{
				try{ con.close(); }
				catch(SQLException se) { se.printStackTrace(); }
			}
		}
		// END OF MANIPULASI DATABASE ////////////////////////////////////////////////////////////////////////
}
