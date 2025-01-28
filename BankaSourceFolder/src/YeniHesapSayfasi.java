import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import com.sun.tools.javac.Main;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JProgressBar;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.reflect.Executable;
import java.awt.Toolkit;

public class YeniHesapSayfasi {
	

	private JFrame YeniHesapSayfasiFrame;
	private JTextField Kimlik_tf;
	private JTextField Ad_tf;
	private JTextField Soyad_tf;
	private JTextField Tel_tf;
	private JPasswordField Sifre_tf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YeniHesapSayfasi window = new YeniHesapSayfasi();
					window.YeniHesapSayfasiFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public YeniHesapSayfasi() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	

	 
	private void initialize() {
		YeniHesapSayfasiFrame = new JFrame();
		YeniHesapSayfasiFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\enesc\\Desktop\\JavaProje\\BankaSourceFolder\\images\\bank.png"));
		YeniHesapSayfasiFrame.setResizable(false);
		YeniHesapSayfasiFrame.getContentPane().setForeground(Color.DARK_GRAY);
		YeniHesapSayfasiFrame.getContentPane().setBackground(Color.DARK_GRAY);
		YeniHesapSayfasiFrame.getContentPane().setLayout(null); 
		
		
		
		JLabel L_BankaAd = new JLabel("SUW\u0130 BANKASI");
		L_BankaAd.setToolTipText("SUW\u0130 BANKASI");
		L_BankaAd.setFont(new Font("Segoe Print", L_BankaAd.getFont().getStyle() | Font.BOLD, L_BankaAd.getFont().getSize() + 43));
		L_BankaAd.setForeground(Color.GRAY);
		L_BankaAd.setHorizontalAlignment(SwingConstants.CENTER);
		L_BankaAd.setBounds(31, 268, 482, 65);
		YeniHesapSayfasiFrame.getContentPane().add(L_BankaAd);
		
		JLabel Kimlik_lb = new JLabel("TC Kimlik No");
		Kimlik_lb.setFont(new Font("Comic Sans MS", Font.PLAIN, 31));
		Kimlik_lb.setForeground(Color.GRAY);
		Kimlik_lb.setBounds(51, 376, 185, 30);
		YeniHesapSayfasiFrame.getContentPane().add(Kimlik_lb);
		
		JLabel Sifre_lb = new JLabel("\u015Eifre");
		Sifre_lb.setFont(new Font("Comic Sans MS", Font.PLAIN, 31));
		Sifre_lb.setForeground(Color.GRAY);
		Sifre_lb.setBounds(157, 546, 79, 32);
		YeniHesapSayfasiFrame.getContentPane().add(Sifre_lb);
		
		JLabel bankaicon = new JLabel("");
		bankaicon.setBounds(141, 11, 256, 246);
		YeniHesapSayfasiFrame.getContentPane().add(bankaicon);
		bankaicon.setIcon(new ImageIcon(YeniHesapSayfasi.class.getResource("/images/bank.png")));
		

		Kimlik_tf = new JTextField();
		Kimlik_tf.setFont(new Font("Tahoma", Font.BOLD, 20));
		Kimlik_tf.setForeground(Color.BLACK);
		Kimlik_tf.setBackground(Color.LIGHT_GRAY);
		Kimlik_tf.setBounds(246, 376, 216, 32);
		YeniHesapSayfasiFrame.getContentPane().add(Kimlik_tf);
		KeyList kl = new KeyList();
		Kimlik_tf.addKeyListener(kl);
		
		JButton HesapAc_Btn = new JButton("Hesap Olu\u015Ftur");
		HesapAc_Btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		HesapAc_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String _kimlik,_ad,_soyad,_tel,_sifre;
				_kimlik=Kimlik_tf.getText();
				_ad=Ad_tf.getText();
				_soyad=Soyad_tf.getText();
				_tel=Tel_tf.getText();
				_sifre=Sifre_tf.getText();
				if(_kimlik.length()!=11) 
					JOptionPane.showInternalMessageDialog(null, "Lütfen 11 Haneli TC Kimlik No Giriniz!", "HATA", 0);
				else if(_tel.length()!=10)
					JOptionPane.showInternalMessageDialog(null, "Lütfen 10 Haneli Telefon No Giriniz!", "HATA", 0);
				else if(_ad.length()==0 || _soyad.length()==0 || _sifre.length()==0)
					JOptionPane.showInternalMessageDialog(null, "Lütfen Ad, Soyad, Sifre Kısımlarını Doldurunuz!", "HATA", 0);
				else {
					try {
						
						Class.forName("com.mysql.jdbc.Driver");
						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankaproje?useUnicode=true&characterEncoding=utf-8","root","");  
						PreparedStatement stmt=con.prepareStatement("INSERT INTO girissayfasi ( TcKimlikNo, Ad, Soyad, Telefon, Sifre) VALUES(?,?,?,?,?)");
						stmt.setString(1, _kimlik);
						stmt.setString(2, _ad);
						stmt.setString(3, _soyad);
						stmt.setString(4, _tel);
						stmt.setString(5, _sifre);
						int i=stmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "Başarılı Bir Şekilde Hesap Oluşturuldu");
						AnaSayfa.main(null);
						YeniHesapSayfasiFrame.setVisible(false);
					}catch (Exception e1) {
						JOptionPane.showInternalMessageDialog(null, "Bu Bilgilere Sahip Bir Kullanıcı Zaten Var", "HATA", 0);
					}
				}
			}
		});
		HesapAc_Btn.setForeground(new Color(77, 173, 254));
		HesapAc_Btn.setToolTipText("");
		HesapAc_Btn.setBackground(Color.DARK_GRAY);
		HesapAc_Btn.setIcon(new ImageIcon(YeniHesapSayfasi.class.getResource("/images/add-user.png")));
		HesapAc_Btn.setSelectedIcon(new ImageIcon("C:\\Users\\enesc\\Desktop\\JavaProje\\BankaSourceFolder\\images\\add-user.png"));
		HesapAc_Btn.setBounds(155, 589, 256, 44);
		YeniHesapSayfasiFrame.getContentPane().add(HesapAc_Btn);
		
		JLabel Ad_lb = new JLabel("Ad");
		Ad_lb.setForeground(Color.GRAY);
		Ad_lb.setFont(new Font("Comic Sans MS", Font.PLAIN, 31));
		Ad_lb.setBounds(197, 417, 39, 32);
		YeniHesapSayfasiFrame.getContentPane().add(Ad_lb);
		
		JLabel soyad_lb = new JLabel("Soyad");
		soyad_lb.setForeground(Color.GRAY);
		soyad_lb.setFont(new Font("Comic Sans MS", Font.PLAIN, 31));
		soyad_lb.setBounds(149, 460, 87, 32);
		YeniHesapSayfasiFrame.getContentPane().add(soyad_lb);
		
		Ad_tf = new JTextField();
		Ad_tf.setForeground(Color.BLACK);
		Ad_tf.setFont(new Font("Tahoma", Font.BOLD, 20));
		Ad_tf.setBackground(Color.LIGHT_GRAY);
		Ad_tf.setBounds(246, 419, 216, 32);
		YeniHesapSayfasiFrame.getContentPane().add(Ad_tf);
		
		Soyad_tf = new JTextField();
		Soyad_tf.setForeground(Color.BLACK);
		Soyad_tf.setFont(new Font("Tahoma", Font.BOLD, 20));
		Soyad_tf.setBackground(Color.LIGHT_GRAY);
		Soyad_tf.setBounds(246, 460, 216, 32);
		YeniHesapSayfasiFrame.getContentPane().add(Soyad_tf);
		
		JLabel Tel_lb = new JLabel("Telefon No");
		Tel_lb.setForeground(Color.GRAY);
		Tel_lb.setFont(new Font("Comic Sans MS", Font.PLAIN, 31));
		Tel_lb.setBounds(75, 503, 161, 32);
		YeniHesapSayfasiFrame.getContentPane().add(Tel_lb);
		
		Tel_tf = new JTextField();
		Tel_tf.setForeground(Color.BLACK);
		Tel_tf.addMouseListener(new MouseAdapter() {
			boolean sayac=true;
			@Override
			public void mouseReleased(MouseEvent e) {
				if(sayac)
					Tel_tf.setText(null);
				sayac=false;
			}
		});
		Tel_tf.setText("örn.(1112223344)");
		Tel_tf.setFont(new Font("Tahoma", Font.BOLD, 20));
		Tel_tf.setBackground(Color.LIGHT_GRAY);
		Tel_tf.setBounds(246, 503, 216, 32);
		Tel_tf.addKeyListener(kl);
		YeniHesapSayfasiFrame.getContentPane().add(Tel_tf);
		
		Sifre_tf = new JPasswordField();
		Sifre_tf.setForeground(Color.BLACK);
		Sifre_tf.setFont(new Font("Tahoma", Font.BOLD, 20));
		Sifre_tf.setBackground(Color.LIGHT_GRAY);
		Sifre_tf.setBounds(246, 546, 216, 32);
		YeniHesapSayfasiFrame.getContentPane().add(Sifre_tf);
		
		JLabel showpassword_lb = new JLabel("");
		JLabel hidepassword_lb = new JLabel("");
		hidepassword_lb.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				showpassword_lb.setVisible(true);
				Sifre_tf.setEchoChar('●');
				hidepassword_lb.setVisible(false);
			}
		});
		hidepassword_lb.setBackground(Color.DARK_GRAY);
		hidepassword_lb.setIcon(new ImageIcon(YeniHesapSayfasi.class.getResource("/images/hide.png")));
		hidepassword_lb.setBounds(462, 546, 32, 32);
		YeniHesapSayfasiFrame.getContentPane().add(hidepassword_lb);
		hidepassword_lb.setVisible(false);
		
		
		showpassword_lb.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				hidepassword_lb.setVisible(true);
				Sifre_tf.setEchoChar((char)0);
				showpassword_lb.setVisible(false);
			}
		});
		showpassword_lb.setIcon(new ImageIcon(YeniHesapSayfasi.class.getResource("/images/show.png")));
		showpassword_lb.setBackground(Color.DARK_GRAY);
		showpassword_lb.setBounds(462, 546, 32, 32);
		YeniHesapSayfasiFrame.getContentPane().add(showpassword_lb);
		
		JLabel L_BankaAd_1 = new JLabel("Hesap Oluşturmak İçin Lütfen Bilgilerinizi Giriniz");
		L_BankaAd_1.setFont(new Font("Tahoma", Font.BOLD, 21));
		L_BankaAd_1.setToolTipText("Hesap Açma");
		L_BankaAd_1.setHorizontalAlignment(SwingConstants.CENTER);
		L_BankaAd_1.setForeground(Color.GRAY);
		L_BankaAd_1.setBounds(10, 336, 520, 35);
		YeniHesapSayfasiFrame.getContentPane().add(L_BankaAd_1);
		
		JButton OncekiSayfa = new JButton("");
		OncekiSayfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnaSayfa.main(null);
				YeniHesapSayfasiFrame.setVisible(false);
			}
		});
		OncekiSayfa.setBackground(Color.DARK_GRAY);
		OncekiSayfa.setIcon(new ImageIcon(YeniHesapSayfasi.class.getResource("/images/back.png")));
		OncekiSayfa.setBounds(0, 0, 64, 64);
		YeniHesapSayfasiFrame.getContentPane().add(OncekiSayfa);
		
		YeniHesapSayfasiFrame.setTitle("SUW\u0130 BANK");
		YeniHesapSayfasiFrame.setFont(new Font("Segoe UI Semilight", Font.BOLD | Font.ITALIC, 30));
		YeniHesapSayfasiFrame.setForeground(Color.DARK_GRAY);
		YeniHesapSayfasiFrame.setBackground(Color.DARK_GRAY);
		YeniHesapSayfasiFrame.setBounds(100, 100, 556, 700);
		YeniHesapSayfasiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		YeniHesapSayfasiFrame.setLocationRelativeTo(null);
	}
	
	 public class KeyList implements KeyListener {	

		    public void keyTyped(KeyEvent e) {
		        char caracter = e.getKeyChar();//hangi tusa basildigini okuyoruz
		        if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {// bu if sart kontrolünde sadece 0 ile 9 arasinda rakamlarin girilebilecegini belirtiyoruz
		            e.consume();
		        }
        
		    }

		    public void keyPressed(KeyEvent e) {//tusa basildiginda aktif olur.
		    }
		    
		    public void keyReleased(KeyEvent e) {//tus birakildiginda aktiflesir.
		    }
		}
}

