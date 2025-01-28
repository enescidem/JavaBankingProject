import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Toolkit;

public class GirisSayfasi {
	
	public static String ad="",soyad="",hesapno="",tel="",sifre="";
	public static int bakiye=0;
	private JFrame GirisSayfasiFrame;
	
	public static void SQLSorgu1(int bakiye) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankaproje?useUnicode=true&characterEncoding=utf-8","root","");  
			PreparedStatement stmt=con.prepareStatement("UPDATE girissayfasi SET Bakiye ='"+bakiye+"' WHERE TcKimlikNo='"+AnaSayfa._kimlik+"'");
			int i=stmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}	
	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GirisSayfasi window = new GirisSayfasi();
					window.GirisSayfasiFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GirisSayfasi() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	

	 
	private void initialize() {
		GirisSayfasiFrame = new JFrame();
		GirisSayfasiFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\enesc\\Desktop\\JavaProje\\BankaSourceFolder\\images\\bank.png"));
		GirisSayfasiFrame.setResizable(false);
		GirisSayfasiFrame.getContentPane().setForeground(Color.DARK_GRAY);
		GirisSayfasiFrame.getContentPane().setBackground(Color.DARK_GRAY);
		GirisSayfasiFrame.getContentPane().setLayout(null); 
		
		
		
		
		JLabel L_HosGeldin = new JLabel("SUWİ BANKASI'NA HOŞ GELDİN");
		L_HosGeldin.setToolTipText("SUW\u0130 BANKASI");
		L_HosGeldin.setFont(new Font("Segoe Print", L_HosGeldin.getFont().getStyle() | Font.BOLD, L_HosGeldin.getFont().getSize() + 18));
		L_HosGeldin.setForeground(Color.GRAY);
		L_HosGeldin.setHorizontalAlignment(SwingConstants.CENTER);
		L_HosGeldin.setBounds(10, 11, 520, 36);
		GirisSayfasiFrame.getContentPane().add(L_HosGeldin);
		
		JLabel islemler_lb = new JLabel("İŞLEMLER");
		islemler_lb.setHorizontalAlignment(SwingConstants.CENTER);
		islemler_lb.setFont(new Font("Comic Sans MS", Font.BOLD, 33));
		islemler_lb.setForeground(Color.GRAY);
		islemler_lb.setBounds(10, 137, 520, 44);
		GirisSayfasiFrame.getContentPane().add(islemler_lb);
		
		//labela ad soyad çekebilmek için  sql sorgusu
		PreparedStatement stmt;
		Connection con;
		ResultSet rs;
		
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankaproje?useUnicode=true&characterEncoding=utf-8","root","");  
				stmt=con.prepareStatement("SELECT * FROM girissayfasi WHERE TcKimlikNo = '"+AnaSayfa._kimlik+"'");
				rs=stmt.executeQuery();
				rs.next();
				ad=rs.getString(3);
				soyad=rs.getString(4);
				hesapno=rs.getString(1);
				tel=rs.getString(5);
				sifre=rs.getString(6);
				bakiye=rs.getInt(7);
				
				
			}catch (Exception e) {
				// TODO: handle exception
				}
		
		JLabel L_KullaniciAdSoyad = new JLabel("SAYIN "+ad+" "+soyad);
		L_KullaniciAdSoyad.setFont(new Font("Segoe Print", Font.BOLD, 25));
		L_KullaniciAdSoyad.setToolTipText("SUWİ BANKASI");
		L_KullaniciAdSoyad.setHorizontalAlignment(SwingConstants.CENTER);
		L_KullaniciAdSoyad.setForeground(Color.GRAY);
		L_KullaniciAdSoyad.setBounds(10, 58, 520, 36);
		GirisSayfasiFrame.getContentPane().add(L_KullaniciAdSoyad);
		
		JLabel L_HesapNo = new JLabel("HESAP NUMARANIZ: "+hesapno);
		L_HesapNo.setToolTipText("SUWİ BANKASI");
		L_HesapNo.setHorizontalAlignment(SwingConstants.CENTER);
		L_HesapNo.setForeground(Color.GRAY);
		L_HesapNo.setFont(new Font("Segoe Print", Font.BOLD, 25));
		L_HesapNo.setBounds(10, 97, 520, 36);
		GirisSayfasiFrame.getContentPane().add(L_HesapNo);
	            
		JButton ParaCek_Btn = new JButton(" PARA ÇEK");
		ParaCek_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bakiye==0) {
					JOptionPane.showMessageDialog(null, "Bakiyeniz 0 Para Çekemezsiniz.", "Bakiye Yok", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					JTextField tf = new JTextField();
					String m = JOptionPane.showInputDialog(null, "Çekilecek Tutarı Giriniz", "Para Çekme", JOptionPane.QUESTION_MESSAGE );
					int CekilenTutar= Integer.parseInt(m);
					if(CekilenTutar>bakiye) {
						JOptionPane.showMessageDialog(null, "Bakiyenizde o kadar para bulunmamakta!\n Çekebileceğiniz Tutar: "+bakiye , "Uyarı", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						bakiye-=CekilenTutar;
						SQLSorgu1(bakiye);
						JOptionPane.showMessageDialog(null, CekilenTutar+" TL Hesaptan Çekildi. \nKalan Bakiye: "+bakiye+" TL", "Bilgilendirme", JOptionPane.INFORMATION_MESSAGE);
						try {
							
							Class.forName("com.mysql.jdbc.Driver");
							Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankaproje?useUnicode=true&characterEncoding=utf-8","root","");  
							PreparedStatement stmt=con.prepareStatement("INSERT INTO hareketdokumu ( HesapNo, Islem) VALUES(?,?)");
							stmt.setString(1, hesapno);
							stmt.setString(2, "Para Çekme");
							int i=stmt.executeUpdate();
						
						}catch (Exception e1) {
							System.out.println(e1);
						}
					}
				}
			}
		});
		ParaCek_Btn.setIcon(new ImageIcon(GirisSayfasi.class.getResource("/images/withdraw.png")));
		ParaCek_Btn.setForeground(new Color(77, 173, 254));
		ParaCek_Btn.setFont(new Font("Tahoma", Font.BOLD, 24));
		ParaCek_Btn.setBackground(Color.DARK_GRAY);
		ParaCek_Btn.setBounds(10, 192, 260, 108);
		GirisSayfasiFrame.getContentPane().add(ParaCek_Btn);
		
		JButton ParaYatir_Btn = new JButton(" PARA YATIR");
		ParaYatir_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField tf = new JTextField();
				String m = JOptionPane.showInputDialog(null, "Yatırılacak Tutarı Giriniz: ", "Para Yatırma", JOptionPane.QUESTION_MESSAGE );
				int YatirilanTutar= Integer.parseInt(m);
				bakiye+=YatirilanTutar;
				SQLSorgu1(bakiye);
				JOptionPane.showMessageDialog(null, YatirilanTutar+" TL Hesaba Yatırıldı.\nToplam Bakiye: "+bakiye+" TL oldu.", "Bilgilendirme", JOptionPane.INFORMATION_MESSAGE);
				try {
					
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankaproje?useUnicode=true&characterEncoding=utf-8","root","");  
					PreparedStatement stmt=con.prepareStatement("INSERT INTO hareketdokumu ( HesapNo, Islem) VALUES(?,?)");
					stmt.setString(1, hesapno);
					stmt.setString(2, "Para Yatırma");
					int i=stmt.executeUpdate();
				
				}catch (Exception e1) {
					System.out.println(e1);
				}
				
			}
		});
		ParaYatir_Btn.setIcon(new ImageIcon(GirisSayfasi.class.getResource("/images/money.png")));
		ParaYatir_Btn.setForeground(new Color(77, 173, 254));
		ParaYatir_Btn.setFont(new Font("Tahoma", Font.BOLD, 24));
		ParaYatir_Btn.setBackground(Color.DARK_GRAY);
		ParaYatir_Btn.setBounds(270, 192, 260, 108);
		GirisSayfasiFrame.getContentPane().add(ParaYatir_Btn);
		
		JButton Havale_Btn = new JButton(" HAWALE");
		Havale_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bakiye==0) {
					JOptionPane.showMessageDialog(null, "Bakiyeniz 0 Hawale Yapamazsınız.", "Bakiye Yok", JOptionPane.INFORMATION_MESSAGE);
				}else {
				GirisSayfasiFrame.setVisible(false);
				HavaleSayfasi.main(null);
				}
			}
		});
		Havale_Btn.setIcon(new ImageIcon(GirisSayfasi.class.getResource("/images/money-transfer.png")));
		Havale_Btn.setForeground(new Color(77, 173, 254));
		Havale_Btn.setFont(new Font("Tahoma", Font.BOLD, 24));
		Havale_Btn.setBackground(Color.DARK_GRAY);
		Havale_Btn.setBounds(10, 300, 260, 108);
		GirisSayfasiFrame.getContentPane().add(Havale_Btn);
		
		JButton Bakiye_Btn = new JButton(" BAKİYE");
		Bakiye_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Bakiyeniz: "+bakiye+" TL", "Bakiye", JOptionPane.INFORMATION_MESSAGE);
				SqlSorgu2("Bakiye");
			}
		});
		Bakiye_Btn.setIcon(new ImageIcon(GirisSayfasi.class.getResource("/images/wallet.png")));
		Bakiye_Btn.setForeground(new Color(77, 173, 254));
		Bakiye_Btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		Bakiye_Btn.setBackground(Color.DARK_GRAY);
		Bakiye_Btn.setBounds(270, 300, 260, 108);
		GirisSayfasiFrame.getContentPane().add(Bakiye_Btn);
		
		JButton Ayarlar_Btn = new JButton(" AYARLAR");
		Ayarlar_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AyarlarSayfasi.main(null);
				GirisSayfasiFrame.setVisible(false);
			}
		});
		Ayarlar_Btn.setIcon(new ImageIcon(GirisSayfasi.class.getResource("/images/setting.png")));
		Ayarlar_Btn.setForeground(new Color(77, 173, 254));
		Ayarlar_Btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		Ayarlar_Btn.setBackground(Color.DARK_GRAY);
		Ayarlar_Btn.setBounds(10, 515, 260, 108);
		GirisSayfasiFrame.getContentPane().add(Ayarlar_Btn);
		
		JButton CıkısYap_Btn = new JButton(" ÇIKIŞ YAP");
		CıkısYap_Btn.setIcon(new ImageIcon(GirisSayfasi.class.getResource("/images/exit.png")));
		CıkısYap_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showConfirmDialog(null,"Hesaptan çıkmak istediğinize emin misiniz?", "Çıkış Yap", JOptionPane.YES_NO_OPTION);
			     if(n==	 JOptionPane.YES_OPTION) {
			    	 AnaSayfa.main(null);
			    	 GirisSayfasiFrame.setVisible(false);
			     }	
			}
		});
		CıkısYap_Btn.setForeground(new Color(77, 173, 254));
		CıkısYap_Btn.setFont(new Font("Tahoma", Font.BOLD, 25));
		CıkısYap_Btn.setBackground(Color.DARK_GRAY);
		CıkısYap_Btn.setBounds(270, 515, 260, 108);
		GirisSayfasiFrame.getContentPane().add(CıkısYap_Btn);
		
		JButton IslemGecmisi_Btn = new JButton(" İŞLEM  GEÇMİŞİ");
		IslemGecmisi_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HareketDokumuSayfasi.main(null);
				GirisSayfasiFrame.setVisible(false);
			}
		});
		IslemGecmisi_Btn.setIcon(new ImageIcon(GirisSayfasi.class.getResource("/images/transaction-history.png")));
		IslemGecmisi_Btn.setForeground(new Color(77, 173, 254));
		IslemGecmisi_Btn.setFont(new Font("Tahoma", Font.BOLD, 18));
		IslemGecmisi_Btn.setBackground(Color.DARK_GRAY);
		IslemGecmisi_Btn.setBounds(270, 408, 260, 108);
		GirisSayfasiFrame.getContentPane().add(IslemGecmisi_Btn);
		
		JButton DovizKuru_Btn = new JButton(" DÖVİZ KURLARI");
		DovizKuru_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DovizKuruSayfasi.main(null);
				GirisSayfasiFrame.setVisible(false);
			}
		});
		DovizKuru_Btn.setIcon(new ImageIcon(GirisSayfasi.class.getResource("/images/conversion.png")));
		DovizKuru_Btn.setForeground(new Color(77, 173, 254));
		DovizKuru_Btn.setFont(new Font("Tahoma", Font.BOLD, 18));
		DovizKuru_Btn.setBackground(Color.DARK_GRAY);
		DovizKuru_Btn.setBounds(10, 408, 260, 108);
		GirisSayfasiFrame.getContentPane().add(DovizKuru_Btn);
		
		GirisSayfasiFrame.setTitle("SUW\u0130 BANK");
		GirisSayfasiFrame.setFont(new Font("Segoe UI Semilight", Font.BOLD | Font.ITALIC, 30));
		GirisSayfasiFrame.setForeground(Color.DARK_GRAY);
		GirisSayfasiFrame.setBackground(Color.DARK_GRAY);
		GirisSayfasiFrame.setBounds(100, 100, 556, 700);
		GirisSayfasiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GirisSayfasiFrame.setLocationRelativeTo(null);
	}
	public static void SqlSorgu2(String Islem) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankaproje?useUnicode=true&characterEncoding=utf-8","root","");  
			PreparedStatement stmt=con.prepareStatement("INSERT INTO hareketdokumu ( HesapNo, Islem) VALUES(?,?)");
			stmt.setString(1, GirisSayfasi.hesapno);
			stmt.setString(2, Islem);
			int i=stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}

