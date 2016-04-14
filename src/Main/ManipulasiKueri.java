package Main;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

public class ManipulasiKueri {
	static Koneksi kon = new Koneksi();
	Statement stmt = null;
	Connection con = null;
	Scanner scan = new Scanner(System.in);
	
	public void isiRecord(){
		
	}
	
	public void editRecord(){
		
	}
	
	public void hapusRecord(){
		
	}
	
	public void tampilRecord() throws SQLException{
		String namaDB = "";
		String namaTabel = "";
		int jumlahkolom = 0;
		ResultSet rs;
		ResultSetMetaData rsMeta;
		DatabaseMetaData dbMeta;
		
		// tampilin tabel yang ada di database dulu
		ManipulasiTabel mt = new ManipulasiTabel();
		mt.tampilListTabel();
		
		// masukin nama db
		System.out.println("Masukkan nama Database : ");
		namaDB = scan.nextLine();
		con = kon.konekNamaDB(namaDB);
		
		// masukin nama tabel yang mau di kueri
		System.out.println("Masukkan nama tabel ");
		namaTabel = scan.nextLine();
		
		// kueri jumlah kolom yang ada di tabel nya
		dbMeta = con.getMetaData();
		rs = dbMeta.getColumns(null, null, namaTabel, null);
		while(rs.next()){
			jumlahkolom++;
		}
		
		// simpen nama kolom nya ke array
		String[] namakoloms = new String[jumlahkolom];
		int hitung = 0;
		rs.beforeFirst(); // balikin kursor nya ke awal
		while(rs.next()){
			namakoloms[hitung] = rs.getString("COLUMN_NAME");
			hitung++;
		}
		hitung = 0;
		
		// jumlah kolom udah dapet, nama kolom udah dapet, terakhir kueri data nya
		String sql = "SELECT * FROM " + namaTabel;
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			// loop berdasarkan jumlah kolom nya
			System.out.println("------------------------------------------");
			for(int i=0; i<jumlahkolom; i++){
				System.out.println(namakoloms[i] + " : " + rs.getString(namakoloms[i]));
			}
			System.out.println("------------------------------------------");
		}
		hitung = 0;
	}
}
