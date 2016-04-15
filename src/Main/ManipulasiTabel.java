package Main;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ManipulasiTabel {
	static Koneksi kon = new Koneksi();
	Statement stmt = null;
	Connection con = null;
	Scanner scan = new Scanner(System.in);
	
	// MANIPULASI TABEL //////////////////////////////////////////////////////////////////////////////////
		public void buatTabel(String Db, String Tbl){
			String namaDB = Db;
			String namaTabel = Tbl;
			int inputInt = 0;
			System.out.println("-------MENU PEMBUATAN TABEL-------");

			// konek ke database yang dimasukin
			con = kon.konekNamaDB(namaDB);

			// masukin jumlah kolom nya
			System.out.println("Masukkan jumlah kolom : ");
			int jumlahKolom = scan.nextInt();
			scan.nextLine();  //buat buang scanner nya ke bawah..jadi bisa nerima inputan lagi
			
			
			// looping untuk masukin nama kolom nya sesuai dengan jumlah kolom yang di input
			String[] namaKoloms = new String[jumlahKolom];
			for(int hitung=0; hitung<jumlahKolom; hitung++){
				System.out.println("Masukkan nama kolom ke " + (hitung+1));
				namaKoloms[hitung] = scan.nextLine();
			}
			
			// nama tabel dan kolom udah dapet, generate script sql nya
			String sql = "CREATE TABLE " + namaTabel + "(";
			for(int hitung=0; hitung<namaKoloms.length; hitung++){
				sql += namaKoloms[hitung] + " varchar(255)";
				
				// kalo belum kolom terakhir, tambahin tanda koma, kalo udah terakhir gausah
				int jumlahkolom = namaKoloms.length;
				if(hitung != jumlahkolom-1){
					sql += ",";
				}
			}
			sql += ")";
			
			// script sql udah dapet, lalu eksekusi
			try{
				stmt = con.createStatement();
				stmt.executeUpdate(sql);
				System.out.println("Tabel " + namaTabel + " berhasil dibuat pada Database " + namaDB);
			}
			catch(SQLException se){
				System.out.println("Ada kesalahan. Periksa nama tabel, kolom, atau database yang Anda input");
			}
			finally{
				try{ con.close(); }
				catch(SQLException se) { se.printStackTrace(); }
			}
		}
		
		public void hapusTabel(String Db, String Tbl){
			String namaDB = Db;
			String namaTabel = Tbl;

			System.out.println("-------MENU HAPUS TABEL-------");
			con = kon.konekNamaDB(namaDB);
			
			String sql = "DROP TABLE " + namaTabel;
			try{
				stmt = con.createStatement();
				stmt.executeUpdate(sql);
				System.out.println("Tabel " + namaTabel + " pada Database " + namaDB + " berhasil dihapus");
			}
			catch(SQLException se){
				System.out.println("Ada kesalahan. Periksa nama tabel dan database yang Anda input");
			}
			finally{
				try{ con.close(); }
				catch(SQLException se) { se.printStackTrace(); }
			}
		}
		
		public void tampilListTabel(String Db){
			String namaDB = Db;
			con = kon.konekNamaDB(namaDB);
			
			try{
				DatabaseMetaData dbMeta = con.getMetaData();
				ResultSet rs = dbMeta.getTables(null, null, "%", null);
				
				System.out.println("Berikut daftar tabel pada Database " + namaDB);
				while(rs.next()){
					System.out.println(rs.getString(3));
				}
			}
			catch(SQLException se){
				System.out.println("Ada kesalahan. Periksa nama database yang Anda input");
			}
			finally{
				try{ con.close(); }
				catch(SQLException se) { se.printStackTrace(); }
			}
		}
		// END OF MANIPULASI TABEL ///////////////////////////////////////////////////////////////////////////
}
