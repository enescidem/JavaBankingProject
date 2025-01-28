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


public class AnaSayfa {
	
	public static String _kimlik;
	private JFrame AnaSayfaFrame;
	private JTextField Kimlik_tf;
	private JPasswordField Sifre_tf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					AnaSayfa window = new AnaSayfa();
					window.AnaSayfaFrame.setVisible(true);
				} catch (Exception e) { 
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AnaSayfa() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	

	 
	private void initialize() {
		AnaSayfaFrame = new JFrame();
		AnaSayfaFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(AnaSayfa.class.getResource("/images/bank.png")));
		AnaSayfaFrame.setResizable(false);
		AnaSayfaFrame.getContentPane().setForeground(Color.DARK_GRAY);
		AnaSayfaFrame.getContentPane().setBackground(Color.DARK_GRAY);
		AnaSayfaFrame.getContentPane().setLayout(null); 
		
		
		
		
		JLabel L_BankaAd = new JLabel("SUW\u0130 BANKASI");
		L_BankaAd.setToolTipText("SUW\u0130 BANKASI");
		L_BankaAd.setFont(new Font("Segoe Print", L_BankaAd.getFont().getStyle() | Font.BOLD, L_BankaAd.getFont().getSize() + 47));
		L_BankaAd.setForeground(Color.GRAY);
		L_BankaAd.setHorizontalAlignment(SwingConstants.CENTER);
		L_BankaAd.setBounds(31, 287, 482, 84);
		AnaSayfaFrame.getContentPane().add(L_BankaAd);
		
		JLabel Kimlik_lb = new JLabel("TC Kimlik No");
		Kimlik_lb.setFont(new Font("Comic Sans MS", Font.PLAIN, 31));
		Kimlik_lb.setForeground(Color.GRAY);
		Kimlik_lb.setBounds(10, 408, 185, 44);
		AnaSayfaFrame.getContentPane().add(Kimlik_lb);
		
		JLabel Sifre_lb = new JLabel("\u015Eifre");
		Sifre_lb.setFont(new Font("Comic Sans MS", Font.PLAIN, 31));
		Sifre_lb.setForeground(Color.GRAY);
		Sifre_lb.setBounds(116, 476, 79, 44);
		AnaSayfaFrame.getContentPane().add(Sifre_lb);
		
		
		JLabel bankaicon = new JLabel("");
		bankaicon.setBounds(141, 20, 256, 256);
		AnaSayfaFrame.getContentPane().add(bankaicon);
		bankaicon.setIcon(new ImageIcon(AnaSayfa.class.getResource("/images/bank.png")));
		

		Kimlik_tf = new JTextField();
		Kimlik_tf.setForeground(Color.BLACK);
		Kimlik_tf.setFont(new Font("Tahoma", Font.BOLD, 20));
		Kimlik_tf.setBackground(Color.LIGHT_GRAY);
		Kimlik_tf.setBounds(228, 408, 248, 44);
		AnaSayfaFrame.getContentPane().add(Kimlik_tf);
		KeyList k1 = new KeyList();//sadece sayı girmesini sağlayan classdan nesne oluşturma
		Kimlik_tf.addKeyListener(k1);
		
	
		
		Sifre_tf = new JPasswordField();
		Sifre_tf.setForeground(Color.BLACK);
		Sifre_tf.setFont(new Font("Tahoma", Font.BOLD, 20));
		Sifre_tf.setBackground(Color.LIGHT_GRAY);
		Sifre_tf.setBounds(228, 485, 248, 44);
		AnaSayfaFrame.getContentPane().add(Sifre_tf);
		
		JButton GirisYap_Btn = new JButton("G\u0130R\u0130\u015E YAP");
		GirisYap_Btn.setIcon(new ImageIcon(AnaSayfa.class.getResource("/images/login.png")));
		GirisYap_Btn.setSelectedIcon(new ImageIcon(AnaSayfa.class.getResource("/images/login.png")));
		GirisYap_Btn.setForeground(new Color(77, 173, 254));
		GirisYap_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String _sifre;
				_kimlik=Kimlik_tf.getText();
				_sifre=Sifre_tf.getText();
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankaproje?useUnicode=true&characterEncoding=utf-8","root","");  
					PreparedStatement stmt=con.prepareStatement("SELECT count(HesapNo) as giris FROM girissayfasi WHERE TcKimlikNo = '"+_kimlik+"' AND Sifre = '"+_sifre+"'");
					ResultSet rs=stmt.executeQuery();
					while(rs.next()) {
						if(rs.getInt("giris")==1) {
							GirisSayfasi.main(null);
							AnaSayfaFrame.setVisible(false);
						}
						else {
							JOptionPane.showInternalMessageDialog(null, "Hatalı TC Kimlik No veya Şifre", "HATA", 0);
						}
					}

				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		GirisYap_Btn.setFont(new Font("Tahoma", Font.PLAIN, 27));
		GirisYap_Btn.setBackground(Color.DARK_GRAY);
		GirisYap_Btn.setBounds(141, 543, 256, 64);
		AnaSayfaFrame.getContentPane().add(GirisYap_Btn);
		
		JButton HesapAc_Btn = new JButton("Yeni Hesap A\u00E7");
		HesapAc_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					YeniHesapSayfasi.main(null);
					AnaSayfaFrame.setVisible(false);
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
	
			}
		});
		HesapAc_Btn.setForeground(new Color(77, 173, 254));
		HesapAc_Btn.setToolTipText("");
		HesapAc_Btn.setBackground(Color.DARK_GRAY);
		HesapAc_Btn.setIcon(new ImageIcon(AnaSayfa.class.getResource("/images/add-user.png")));
		HesapAc_Btn.setSelectedIcon(new ImageIcon(AnaSayfa.class.getResource("/images/add-user.png")));
		HesapAc_Btn.setBounds(100, 618, 162, 32);
		AnaSayfaFrame.getContentPane().add(HesapAc_Btn);
		
		JButton SifremiUnuttum_Btn = new JButton("\u015Eifremi Unuttum");
		SifremiUnuttum_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SifremiUnuttumSayfasi.main(null);
					AnaSayfaFrame.setVisible(false);
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		SifremiUnuttum_Btn.setForeground(new Color(77, 173, 254));
		SifremiUnuttum_Btn.setSelectedIcon(new ImageIcon(AnaSayfa.class.getResource("/images/forgot.png")));
		SifremiUnuttum_Btn.setIcon(new ImageIcon(AnaSayfa.class.getResource("/images/forgot.png")));
		SifremiUnuttum_Btn.setToolTipText("");
		SifremiUnuttum_Btn.setBackground(Color.DARK_GRAY);
		SifremiUnuttum_Btn.setBounds(272, 618, 162, 32);
		AnaSayfaFrame.getContentPane().add(SifremiUnuttum_Btn);
		
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
		hidepassword_lb.setIcon(new ImageIcon(AnaSayfa.class.getResource("/images/hide.png")));
		hidepassword_lb.setBounds(476, 491, 32, 32);
		AnaSayfaFrame.getContentPane().add(hidepassword_lb);
		hidepassword_lb.setVisible(false);
		
		
		showpassword_lb.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				hidepassword_lb.setVisible(true);
				Sifre_tf.setEchoChar((char)0);
				showpassword_lb.setVisible(false);
			}
		});
		showpassword_lb.setIcon(new ImageIcon(AnaSayfa.class.getResource("/images/show.png")));
		showpassword_lb.setBackground(Color.DARK_GRAY);
		showpassword_lb.setBounds(476, 491, 32, 32);
		AnaSayfaFrame.getContentPane().add(showpassword_lb);
		AnaSayfaFrame.setTitle("SUW\u0130 BANK");
		AnaSayfaFrame.setFont(new Font("Segoe UI Semilight", Font.BOLD | Font.ITALIC, 30));
		AnaSayfaFrame.setForeground(Color.DARK_GRAY);
		AnaSayfaFrame.setBackground(Color.DARK_GRAY);
		AnaSayfaFrame.setBounds(100, 100, 556, 700);
		AnaSayfaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AnaSayfaFrame.setLocationRelativeTo(null);
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

