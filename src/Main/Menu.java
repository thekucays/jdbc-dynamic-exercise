package Main;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Menu {
	public static void main(String[] args)throws SQLException{
		//ManipulasiDatabase m = new ManipulasiDatabase();
		//m.buatDatabase("TestAntar");
		//m.hapusDatabase("TestAntar");
		//m.tampilListDatabase();
		//m.buatTabel();
		//m.hapusTabel();
		//m.tampilListTabel();
		
		//ManipulasiDatabase md = new ManipulasiDatabase();
		//md.buatDatabase("AntarDB");
		
		//ManipulasiTabel mt = new ManipulasiTabel();
		//mt.buatTabel();
		//mt.tampilListTabel();
		//mt.hapusTabel();
		
		
		/*ManipulasiKueri mk = new ManipulasiKueri();
		try {
			mk.tampilRecord();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		int menu = 0;
		int pilihan;
		Scanner scan = new Scanner(System.in);
		
		// objek class nya
		ManipulasiDatabase md = new ManipulasiDatabase();
		ManipulasiTabel mt = new ManipulasiTabel();
		ManipulasiKueri mk = new ManipulasiKueri();
		
		while(menu == 0){
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("Silahkan pilih menu : ");
			System.out.println("Menu Database");
			System.out.println("1. Buat Database");
			System.out.println("2. Tampil Database");
			System.out.println("3. Hapus Database");
			System.out.println(" ");
			System.out.println("Menu Tabel");
			System.out.println("4. Buat Tabel");
			System.out.println("5. Tampil Tabel");
			System.out.println("6. Hapus Tabel");
			System.out.println(" ");
			System.out.println("Menu Kueri");
			System.out.println("7. Isi Record");
			System.out.println("8. Edit Record");
			System.out.println("9. Hapus Record");
			System.out.println("10. Tampil Record");
			System.out.println(" ");
			System.out.println("0. Keluar");
			
			System.out.println("Masukkan pilihan : ");
			pilihan = scan.nextInt();
			scan.nextLine();
			String namaDB = "";
			
			switch(pilihan){
				case 1:
					System.out.println("Masukkan nama Database : ");
					namaDB = scan.nextLine();
					md.buatDatabase(namaDB);
					break;
				case 2:
					md.tampilListDatabase();
					break;
				case 3:
					System.out.println("Masukkan nama Database : ");
					namaDB = scan.nextLine();
					md.hapusDatabase(namaDB);
					break;
				case 4:
					mt.buatTabel();
					break;
				case 5:
					mt.tampilListTabel();
					break;
				case 6:
					mt.tampilListTabel();
					break;
				case 7:
					mk.isiRecord();
					break;
				case 8:
					mk.editRecord();
					break;
				case 9:
					mk.hapusRecord();
					break;
				case 10:
					mk.tampilRecord();
					break;
				case 0:
					System.exit(0);
					break;
				default:
					System.out.println("Pilihan tidak ada");
					break;
			}
		}
	}
}
