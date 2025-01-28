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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
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
import java.util.ArrayList;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.border.CompoundBorder;
import javax.swing.JScrollPane;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;




public class HavaleSayfasi {
	
	
	public static String _kimlik;
	private JFrame HavaleSayfasiFrame;
	private JTextField Ad_tf;
	private JTextField Soyad_tf;
	private JTextField HesapNo_tf;
	private JTextField Ucret_tf;
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HavaleSayfasi window = new HavaleSayfasi();
					window.HavaleSayfasiFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); 
		
		
	}

	public HavaleSayfasi() {
		initialize();
	}

	 
	private void initialize() {
		
		HavaleSayfasiFrame = new JFrame();
		HavaleSayfasiFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\enesc\\Desktop\\JavaProje\\BankaSourceFolder\\images\\bank.png"));
		HavaleSayfasiFrame.setResizable(false);
		HavaleSayfasiFrame.getContentPane().setForeground(Color.DARK_GRAY);
		HavaleSayfasiFrame.getContentPane().setBackground(Color.DARK_GRAY);
		HavaleSayfasiFrame.getContentPane().setLayout(null);
		
		JLabel L_Havale = new JLabel("HAWALE SAYFASI");
		L_Havale.setFont(new Font("Tahoma", Font.BOLD, 54));
		L_Havale.setToolTipText("SUWİ BANKASI");
		L_Havale.setHorizontalAlignment(SwingConstants.CENTER);
		L_Havale.setForeground(Color.GRAY);
		L_Havale.setBounds(10, 287, 520, 64);
		HavaleSayfasiFrame.getContentPane().add(L_Havale);
		
		JButton Gonder_Btn = new JButton(" GÖNDER");
		Gonder_Btn.setIcon(new ImageIcon(HavaleSayfasi.class.getResource("/images/send.png")));
		Gonder_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
					int bakiye=GirisSayfasi.bakiye;
					String m=Ucret_tf.getText();
					int CekilenTutar=Integer.parseInt(m);
					if(CekilenTutar>bakiye) {
						JOptionPane.showMessageDialog(null, "Bakiyenizde o kadar para bulunmamakta!\nBakiyeniz: "+GirisSayfasi.bakiye+" TL", "Uyarı", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						String ad, soyad,hesapno;
						ad=Ad_tf.getText(); soyad=Soyad_tf.getText(); hesapno=HesapNo_tf.getText();
						try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankaproje?useUnicode=true&characterEncoding=utf-8","root","");  
							PreparedStatement stmt=con.prepareStatement("SELECT count(HesapNo) as giris FROM girissayfasi WHERE HesapNo = '"+hesapno+"' AND Ad = '"+ad+"' AND Soyad='"+soyad+"'");
							ResultSet rs=stmt.executeQuery();
							while(rs.next()) {
								if(rs.getInt("giris")==1) {
									try {
										Class.forName("com.mysql.jdbc.Driver");
										Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankaproje?useUnicode=true&characterEncoding=utf-8","root","");  
										PreparedStatement stmt1=con1.prepareStatement("UPDATE girissayfasi SET Bakiye =(SELECT (SELECT Bakiye FROM girissayfasi WHERE HesapNo='"+hesapno+"') +"+CekilenTutar+") WHERE Ad='"+ad+"' AND Soyad='"+soyad+"'AND HesapNo='"+hesapno+"'");
										int i=stmt1.executeUpdate();
										bakiye-=CekilenTutar;
										GirisSayfasi.SQLSorgu1(bakiye);
										JOptionPane.showMessageDialog(null, CekilenTutar+" TL "+ad+" "+soyad+" Kişisine Gönderildi.\nKalan Bakiye: "+bakiye+" TL", "Bilgilendirme", JOptionPane.INFORMATION_MESSAGE);
										GirisSayfasi.SqlSorgu2("Havale");
										GirisSayfasi.main(null);
										HavaleSayfasiFrame.setVisible(false);
									} catch (Exception e1) {
										System.out.println(e1);
									}	
								}
								else {
									JOptionPane.showInternalMessageDialog(null, "Bilgiler Hatalı Kontrol Ediniz Lütfen", "HATA", 0);
								}
							}
							
							
						} catch (Exception e2) {
							// TODO: handle exception
						}
						
							
							
					}
				
			}
		});
		Gonder_Btn.setForeground(new Color(77, 173, 254));
		Gonder_Btn.setFont(new Font("Tahoma", Font.BOLD, 46));
		Gonder_Btn.setBackground(Color.DARK_GRAY);
		Gonder_Btn.setBounds(10, 586, 520, 64);
		HavaleSayfasiFrame.getContentPane().add(Gonder_Btn);
		
		JLabel Ad_lb = new JLabel("Ad");
		Ad_lb.setForeground(Color.GRAY);
		Ad_lb.setFont(new Font("Comic Sans MS", Font.PLAIN, 31));
		Ad_lb.setBounds(162, 444, 39, 32);
		HavaleSayfasiFrame.getContentPane().add(Ad_lb);
		
		Ad_tf = new JTextField();
		Ad_tf.setForeground(Color.BLACK);
		Ad_tf.setFont(new Font("Tahoma", Font.BOLD, 20));
		Ad_tf.setBackground(Color.LIGHT_GRAY);
		Ad_tf.setBounds(211, 444, 216, 32);
		HavaleSayfasiFrame.getContentPane().add(Ad_tf);
		
		JLabel soyad_lb = new JLabel("Soyad");
		soyad_lb.setForeground(Color.GRAY);
		soyad_lb.setFont(new Font("Comic Sans MS", Font.PLAIN, 31));
		soyad_lb.setBounds(114, 487, 87, 32);
		HavaleSayfasiFrame.getContentPane().add(soyad_lb);
		
		Soyad_tf = new JTextField();
		Soyad_tf.setForeground(Color.BLACK);
		Soyad_tf.setFont(new Font("Tahoma", Font.BOLD, 20));
		Soyad_tf.setBackground(Color.LIGHT_GRAY);
		Soyad_tf.setBounds(211, 487, 216, 32);
		HavaleSayfasiFrame.getContentPane().add(Soyad_tf);
		
		JLabel HesapNo_lb = new JLabel("Hesap No");
		HesapNo_lb.setVerticalAlignment(SwingConstants.BOTTOM);
		HesapNo_lb.setHorizontalAlignment(SwingConstants.CENTER);
		HesapNo_lb.setForeground(Color.GRAY);
		HesapNo_lb.setFont(new Font("Comic Sans MS", Font.PLAIN, 31));
		HesapNo_lb.setBounds(62, 405, 139, 32);
		HavaleSayfasiFrame.getContentPane().add(HesapNo_lb);
		
		HesapNo_tf = new JTextField();
		HesapNo_tf.setForeground(Color.BLACK);
		HesapNo_tf.setFont(new Font("Tahoma", Font.BOLD, 20));
		HesapNo_tf.setBackground(Color.LIGHT_GRAY);
		HesapNo_tf.setBounds(211, 401, 216, 32);
		HavaleSayfasiFrame.getContentPane().add(HesapNo_tf);
		
		JLabel L_HawaleBilgi = new JLabel("Hawale Yapmak İstediğiniz Kişinin Bilgilerini Giriniz");
		L_HawaleBilgi.setToolTipText("Hesap Açma");
		L_HawaleBilgi.setHorizontalAlignment(SwingConstants.CENTER);
		L_HawaleBilgi.setForeground(Color.GRAY);
		L_HawaleBilgi.setFont(new Font("Tahoma", Font.BOLD, 19));
		L_HawaleBilgi.setBounds(10, 349, 520, 41);
		HavaleSayfasiFrame.getContentPane().add(L_HawaleBilgi);
		
		JLabel ucret_lb = new JLabel("Ücret");
		ucret_lb.setHorizontalAlignment(SwingConstants.CENTER);
		ucret_lb.setForeground(Color.GRAY);
		ucret_lb.setFont(new Font("Comic Sans MS", Font.PLAIN, 31));
		ucret_lb.setBounds(114, 530, 87, 32);
		HavaleSayfasiFrame.getContentPane().add(ucret_lb);
		
		Ucret_tf = new JTextField();
		Ucret_tf.setText("0");
		Ucret_tf.setForeground(Color.BLACK);
		Ucret_tf.setFont(new Font("Tahoma", Font.BOLD, 20));
		Ucret_tf.setBackground(Color.LIGHT_GRAY);
		Ucret_tf.setBounds(211, 530, 216, 32);
		HavaleSayfasiFrame.getContentPane().add(Ucret_tf);
		
		JLabel bankaicon = new JLabel("");
		bankaicon.setIcon(new ImageIcon(HavaleSayfasi.class.getResource("/images/bank.png")));
		bankaicon.setBounds(141, 20, 256, 256);
		HavaleSayfasiFrame.getContentPane().add(bankaicon);
		
		JButton OncekiSayfa = new JButton("");
		OncekiSayfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GirisSayfasi.main(null);
				HavaleSayfasiFrame.setVisible(false);
			}
		});
		OncekiSayfa.setBackground(Color.DARK_GRAY);
		OncekiSayfa.setIcon(new ImageIcon(HavaleSayfasi.class.getResource("/images/back.png")));
		OncekiSayfa.setBounds(0, 0, 64, 64);
		HavaleSayfasiFrame.getContentPane().add(OncekiSayfa);
		
		HavaleSayfasiFrame.setTitle("SUW\u0130 BANK");
		HavaleSayfasiFrame.setFont(new Font("Segoe UI Semilight", Font.BOLD | Font.ITALIC, 30));
		HavaleSayfasiFrame.setForeground(Color.DARK_GRAY);
		HavaleSayfasiFrame.setBackground(Color.DARK_GRAY);
		HavaleSayfasiFrame.setBounds(100, 100, 556, 700);
		HavaleSayfasiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HavaleSayfasiFrame.setLocationRelativeTo(null);
	}
	
}

