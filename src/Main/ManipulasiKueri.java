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
	
	public void isiRecord(String Db, String Tbl)throws SQLException{
		String namaDB = Db;
		String namaTabel = Tbl;
		int jumlahkolom = 0;
		ResultSet rs;
		ResultSetMetaData rsMeta;
		DatabaseMetaData dbMeta;
		
		// konek ke database yang dimasukin
		con = kon.konekNamaDB(namaDB);
		
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
		
		// nama dan jumlah kolom udah dapet, lalu masukin data yang mau di insert
		String[] dataInsert = new String[jumlahkolom];
		for(int i=0; i<jumlahkolom; i++){
			System.out.println("Masukkan data " + namakoloms[i]);
			dataInsert[i] = scan.nextLine();
		}
		
		// setelah data udah dapet, generate string sql untuk INSERT tabel nya
		String sql = "insert into " + namaTabel + " values (";
		for(int i=0; i<dataInsert.length; i++){
			sql += "'" + dataInsert[i] + "'";
			
			// kalo belum data terakhir, tambahin tanda koma, kalo udah terakhir gausah
			int jumlahdata = dataInsert.length;
			if(i != jumlahdata-1){
				sql += ",";
			}
		}
		sql += ")";
		
		// string sql untuk INSERT udah dapet, tinggal di eksekusi
		stmt = con.createStatement();
		stmt.executeUpdate(sql);
		
		System.out.println("Data baru berhasil dimasukkan ke dalam tabel " + namaTabel);
		
		// debug lines
		//System.out.println("Generated sql query :  " + sql);
	}
	
	public void editRecord(String Db, String Tbl){
		
	}
	
	public void hapusRecord(String Db, String Tbl)throws SQLException{
		String namaDB = Db;
		String namaTabel = Tbl;
		int jumlahkolom = 0;
		ResultSet rs;
		ResultSetMetaData rsMeta;
		DatabaseMetaData dbMeta;
		
		// konek ke database yang dimasukin
		con = kon.konekNamaDB(namaDB);
		
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
		
		// tampilin record(isi) tabel nya dulu
		tampilRecord(namaDB, Tbl);
		
		// setelah data tampil, pilih kolom pertama pada tabel untuk dijadiin WHERE nya
		System.out.println("Masukkan " + namakoloms[0] + " yang ingin dihapus : ");
		String hapus = scan.nextLine();
		
		// generate string sql untuk DELETE nya, lalu eksekusi
		String sql = "delete from " + namaTabel + " where " + namakoloms[0] + " = '" + hapus + "'";
		stmt = con.createStatement();
		stmt.executeUpdate(sql);
		
		System.out.println("Record " + hapus +" pada tabel " + namaTabel + " berhasil dihapus");
	}
	
	public void tampilRecord(String Db, String Tbl) throws SQLException{
		String namaDB = Db;
		String namaTabel = Tbl;
		int jumlahkolom = 0;
		ResultSet rs;
		ResultSetMetaData rsMeta;
		DatabaseMetaData dbMeta;
		
		// konek ke database yang dimasukin
		con = kon.konekNamaDB(namaDB);
		
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
