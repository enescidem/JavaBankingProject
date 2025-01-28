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

public class SifremiUnuttumSayfasi {
	

	private JFrame SifremiUnuttumSayfasiFrame;
	private JTextField Kimlik_tf;
	private JTextField Ad_tf;
	private JTextField Soyad_tf;
	private JTextField Tel_tf;
	private JPasswordField YeniSifre_tf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SifremiUnuttumSayfasi window = new SifremiUnuttumSayfasi();
					window.SifremiUnuttumSayfasiFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SifremiUnuttumSayfasi() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	

	 
	private void initialize() {
		SifremiUnuttumSayfasiFrame = new JFrame();
		SifremiUnuttumSayfasiFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\enesc\\Desktop\\JavaProje\\BankaSourceFolder\\images\\bank.png"));
		SifremiUnuttumSayfasiFrame.setResizable(false);
		SifremiUnuttumSayfasiFrame.getContentPane().setForeground(Color.DARK_GRAY);
		SifremiUnuttumSayfasiFrame.getContentPane().setBackground(Color.DARK_GRAY);
		SifremiUnuttumSayfasiFrame.getContentPane().setLayout(null); 
		
		
		
		JLabel L_BankaAd = new JLabel("SUW\u0130 BANKASI");
		L_BankaAd.setToolTipText("SUW\u0130 BANKASI");
		L_BankaAd.setFont(new Font("Segoe Print", L_BankaAd.getFont().getStyle() | Font.BOLD, L_BankaAd.getFont().getSize() + 43));
		L_BankaAd.setForeground(Color.GRAY);
		L_BankaAd.setHorizontalAlignment(SwingConstants.CENTER);
		L_BankaAd.setBounds(31, 268, 482, 65);
		SifremiUnuttumSayfasiFrame.getContentPane().add(L_BankaAd);
		
		JLabel Kimlik_lb = new JLabel("TC Kimlik No");
		Kimlik_lb.setFont(new Font("Comic Sans MS", Font.PLAIN, 31));
		Kimlik_lb.setForeground(Color.GRAY);
		Kimlik_lb.setBounds(51, 376, 185, 30);
		SifremiUnuttumSayfasiFrame.getContentPane().add(Kimlik_lb);
		
		JLabel YeniSifre_lb = new JLabel("Yeni Şifre");
		YeniSifre_lb.setFont(new Font("Comic Sans MS", Font.PLAIN, 31));
		YeniSifre_lb.setForeground(Color.GRAY);
		YeniSifre_lb.setBounds(86, 546, 150, 32);
		SifremiUnuttumSayfasiFrame.getContentPane().add(YeniSifre_lb);
		
		JLabel bankaicon = new JLabel("");
		bankaicon.setBounds(141, 11, 256, 246);
		SifremiUnuttumSayfasiFrame.getContentPane().add(bankaicon);
		bankaicon.setIcon(new ImageIcon(SifremiUnuttumSayfasi.class.getResource("/images/bank.png")));
		

		Kimlik_tf = new JTextField();
		Kimlik_tf.setFont(new Font("Tahoma", Font.BOLD, 20));
		Kimlik_tf.setForeground(Color.BLACK);
		Kimlik_tf.setBackground(Color.LIGHT_GRAY);
		Kimlik_tf.setBounds(246, 376, 216, 32);
		SifremiUnuttumSayfasiFrame.getContentPane().add(Kimlik_tf);
		KeyList kl = new KeyList();
		Kimlik_tf.addKeyListener(kl);
		
		JButton SifreDegistir_Btn = new JButton("Şifre Değiştir");
		SifreDegistir_Btn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		SifreDegistir_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String _kimlik,_ad,_soyad,_tel,_sifre;
				_kimlik=Kimlik_tf.getText();
				_ad=Ad_tf.getText();
				_soyad=Soyad_tf.getText();
				_tel=Tel_tf.getText();
				_sifre=YeniSifre_tf.getText();
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
						PreparedStatement stmt=con.prepareStatement("UPDATE girissayfasi SET Sifre ='"+_sifre+"' WHERE TcKimlikNo='"+_kimlik+"' AND Ad='"+_ad+"' AND Soyad='"+_soyad+"'AND Telefon='"+_tel+"'");
						
						int i=stmt.executeUpdate();
						if(i==1) {
							JOptionPane.showMessageDialog(null, "Başarılı Bir Şekilde Şifre Değiştirildi");
							AnaSayfa.main(null);
							SifremiUnuttumSayfasiFrame.setVisible(false);
						}
						else
							JOptionPane.showInternalMessageDialog(null, "Bu bilgiler yanlış! Şifre değiştirilemedi! Girdiğiniz Bilgileri tekrar kontrol ediniz!", "HATA", 0);
						
					}catch (Exception e1) {
						System.out.println(e1);
					}
					
				}
			}
		});
		SifreDegistir_Btn.setForeground(new Color(77, 173, 254));
		SifreDegistir_Btn.setToolTipText("");
		SifreDegistir_Btn.setBackground(Color.DARK_GRAY);
		SifreDegistir_Btn.setIcon(new ImageIcon(SifremiUnuttumSayfasi.class.getResource("/images/password.png")));
		SifreDegistir_Btn.setSelectedIcon(new ImageIcon("C:\\Users\\enesc\\Desktop\\JavaProje\\BankaSourceFolder\\images\\password.png"));
		SifreDegistir_Btn.setBounds(155, 589, 256, 44);
		SifremiUnuttumSayfasiFrame.getContentPane().add(SifreDegistir_Btn);
		
		JLabel Ad_lb = new JLabel("Ad");
		Ad_lb.setForeground(Color.GRAY);
		Ad_lb.setFont(new Font("Comic Sans MS", Font.PLAIN, 31));
		Ad_lb.setBounds(197, 417, 39, 32);
		SifremiUnuttumSayfasiFrame.getContentPane().add(Ad_lb);
		
		JLabel soyad_lb = new JLabel("Soyad");
		soyad_lb.setForeground(Color.GRAY);
		soyad_lb.setFont(new Font("Comic Sans MS", Font.PLAIN, 31));
		soyad_lb.setBounds(149, 460, 87, 32);
		SifremiUnuttumSayfasiFrame.getContentPane().add(soyad_lb);
		
		Ad_tf = new JTextField();
		Ad_tf.setForeground(Color.BLACK);
		Ad_tf.setFont(new Font("Tahoma", Font.BOLD, 20));
		Ad_tf.setBackground(Color.LIGHT_GRAY);
		Ad_tf.setBounds(246, 419, 216, 32);
		SifremiUnuttumSayfasiFrame.getContentPane().add(Ad_tf);
		
		Soyad_tf = new JTextField();
		Soyad_tf.setForeground(Color.BLACK);
		Soyad_tf.setFont(new Font("Tahoma", Font.BOLD, 20));
		Soyad_tf.setBackground(Color.LIGHT_GRAY);
		Soyad_tf.setBounds(246, 460, 216, 32);
		SifremiUnuttumSayfasiFrame.getContentPane().add(Soyad_tf);
		
		JLabel Tel_lb = new JLabel("Telefon No");
		Tel_lb.setForeground(Color.GRAY);
		Tel_lb.setFont(new Font("Comic Sans MS", Font.PLAIN, 31));
		Tel_lb.setBounds(75, 503, 161, 32);
		SifremiUnuttumSayfasiFrame.getContentPane().add(Tel_lb);
		
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
		SifremiUnuttumSayfasiFrame.getContentPane().add(Tel_tf);
		
		YeniSifre_tf = new JPasswordField();
		YeniSifre_tf.setForeground(Color.BLACK);
		YeniSifre_tf.setFont(new Font("Tahoma", Font.BOLD, 20));
		YeniSifre_tf.setBackground(Color.LIGHT_GRAY);
		YeniSifre_tf.setBounds(246, 546, 216, 32);
		SifremiUnuttumSayfasiFrame.getContentPane().add(YeniSifre_tf);
		
		JLabel showpassword_lb = new JLabel("");
		JLabel hidepassword_lb = new JLabel("");
		hidepassword_lb.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				showpassword_lb.setVisible(true);
				YeniSifre_tf.setEchoChar('●');
				hidepassword_lb.setVisible(false);
			}
		});
		hidepassword_lb.setBackground(Color.DARK_GRAY);
		hidepassword_lb.setIcon(new ImageIcon(SifremiUnuttumSayfasi.class.getResource("/images/hide.png")));
		hidepassword_lb.setBounds(462, 546, 32, 32);
		SifremiUnuttumSayfasiFrame.getContentPane().add(hidepassword_lb);
		hidepassword_lb.setVisible(false);
		
		
		showpassword_lb.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				hidepassword_lb.setVisible(true);
				YeniSifre_tf.setEchoChar((char)0);
				showpassword_lb.setVisible(false);
			}
		});
		showpassword_lb.setIcon(new ImageIcon(SifremiUnuttumSayfasi.class.getResource("/images/show.png")));
		showpassword_lb.setBackground(Color.DARK_GRAY);
		showpassword_lb.setBounds(462, 546, 32, 32);
		SifremiUnuttumSayfasiFrame.getContentPane().add(showpassword_lb);
		
		JLabel L_SifreDegistirmeBilgi = new JLabel("Şifre Değiştirmek İçin Lütfen Bilgilerinizi Giriniz");
		L_SifreDegistirmeBilgi.setFont(new Font("Tahoma", Font.BOLD, 21));
		L_SifreDegistirmeBilgi.setToolTipText("Hesap Açma");
		L_SifreDegistirmeBilgi.setHorizontalAlignment(SwingConstants.CENTER);
		L_SifreDegistirmeBilgi.setForeground(Color.GRAY);
		L_SifreDegistirmeBilgi.setBounds(10, 336, 520, 35);
		SifremiUnuttumSayfasiFrame.getContentPane().add(L_SifreDegistirmeBilgi);
		
		
		JButton OncekiSayfa = new JButton("");
		OncekiSayfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnaSayfa.main(null);
				SifremiUnuttumSayfasiFrame.setVisible(false);
			}
		});
		OncekiSayfa.setBackground(Color.DARK_GRAY);
		OncekiSayfa.setIcon(new ImageIcon(SifremiUnuttumSayfasi.class.getResource("/images/back.png")));
		OncekiSayfa.setBounds(0, 0, 64, 64);
		SifremiUnuttumSayfasiFrame.getContentPane().add(OncekiSayfa);
		
		SifremiUnuttumSayfasiFrame.setTitle("SUW\u0130 BANK");
		SifremiUnuttumSayfasiFrame.setFont(new Font("Segoe UI Semilight", Font.BOLD | Font.ITALIC, 30));
		SifremiUnuttumSayfasiFrame.setForeground(Color.DARK_GRAY);
		SifremiUnuttumSayfasiFrame.setBackground(Color.DARK_GRAY);
		SifremiUnuttumSayfasiFrame.setBounds(100, 100, 556, 700);
		SifremiUnuttumSayfasiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SifremiUnuttumSayfasiFrame.setLocationRelativeTo(null);
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

