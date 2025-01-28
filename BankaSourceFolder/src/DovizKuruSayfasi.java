

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
import DovizPackage.*;

public class DovizKuruSayfasi {
	

	private JFrame DovizKuruSayfasiFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DovizKuruSayfasi window = new DovizKuruSayfasi();
					window.DovizKuruSayfasiFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DovizKuruSayfasi() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	

	 
	private void initialize() {
		DovizKuruSayfasiFrame = new JFrame();
		DovizKuruSayfasiFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\enesc\\Desktop\\JavaProje\\BankaSourceFolder\\images\\bank.png"));
		DovizKuruSayfasiFrame.setResizable(false);
		DovizKuruSayfasiFrame.getContentPane().setForeground(Color.DARK_GRAY);
		DovizKuruSayfasiFrame.getContentPane().setBackground(Color.DARK_GRAY);
		DovizKuruSayfasiFrame.getContentPane().setLayout(null); 
		
		
		
		
		JLabel L_BankaAd = new JLabel("SUW\u0130 BANKASI");
		L_BankaAd.setToolTipText("SUW\u0130 BANKASI");
		L_BankaAd.setFont(new Font("Segoe Print", L_BankaAd.getFont().getStyle() | Font.BOLD, L_BankaAd.getFont().getSize() + 47));
		L_BankaAd.setForeground(Color.GRAY);
		L_BankaAd.setHorizontalAlignment(SwingConstants.CENTER);
		L_BankaAd.setBounds(25, 42, 482, 84);
		DovizKuruSayfasiFrame.getContentPane().add(L_BankaAd);
		
		JLabel Kimlik_lb_1 = new JLabel("Güncel Döviz Kurları Sayfası");
		Kimlik_lb_1.setForeground(Color.GRAY);
		Kimlik_lb_1.setFont(new Font("Comic Sans MS", Font.BOLD, 37));
		Kimlik_lb_1.setBounds(10, 121, 520, 49);
		DovizKuruSayfasiFrame.getContentPane().add(Kimlik_lb_1);
		
		JLabel Tarih_lb = new JLabel("Tarih:");
		Tarih_lb.setHorizontalAlignment(SwingConstants.CENTER);
		Tarih_lb.setForeground(Color.GRAY);
		Tarih_lb.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		Tarih_lb.setBounds(77, 181, 132, 49);
		DovizKuruSayfasiFrame.getContentPane().add(Tarih_lb);
		
		JLabel DovizCinsi_lb = new JLabel("Döviz Cinsi");
		DovizCinsi_lb.setHorizontalAlignment(SwingConstants.CENTER);
		DovizCinsi_lb.setForeground(Color.GRAY);
		DovizCinsi_lb.setFont(new Font("Comic Sans MS", Font.BOLD, 31));
		DovizCinsi_lb.setBounds(10, 241, 279, 44);
		DovizKuruSayfasiFrame.getContentPane().add(DovizCinsi_lb);
		
		JLabel Alis_lb = new JLabel("Alış");
		Alis_lb.setHorizontalAlignment(SwingConstants.CENTER);
		Alis_lb.setForeground(Color.GRAY);
		Alis_lb.setFont(new Font("Comic Sans MS", Font.BOLD, 31));
		Alis_lb.setBounds(299, 241, 99, 44);
		DovizKuruSayfasiFrame.getContentPane().add(Alis_lb);
		
		JLabel Satis_lb = new JLabel("Satış");
		Satis_lb.setHorizontalAlignment(SwingConstants.CENTER);
		Satis_lb.setForeground(Color.GRAY);
		Satis_lb.setFont(new Font("Comic Sans MS", Font.BOLD, 31));
		Satis_lb.setBounds(408, 241, 99, 44);
		DovizKuruSayfasiFrame.getContentPane().add(Satis_lb);
		
		CurrencyFactory currencyFactory1 = new CurrencyFactory(Moneys.US_DOLLAR);
	    Currency currency1 = currencyFactory1.getCurrency();
		JLabel dc_lb_1 = new JLabel(currency1.getName());
		dc_lb_1.setHorizontalAlignment(SwingConstants.CENTER);
		dc_lb_1.setForeground(Color.GRAY);
		dc_lb_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		dc_lb_1.setBounds(10, 296, 279, 37);
		DovizKuruSayfasiFrame.getContentPane().add(dc_lb_1);
		
		JLabel a_lb_1 = new JLabel(Float.toString(currency1.getBuyingPrice()));
		a_lb_1.setHorizontalAlignment(SwingConstants.CENTER);
		a_lb_1.setForeground(Color.GRAY);
		a_lb_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		a_lb_1.setBounds(299, 296, 99, 37);
		DovizKuruSayfasiFrame.getContentPane().add(a_lb_1);
		
		JLabel s_lb_1 = new JLabel(Float.toString(currency1.getSellingPrice()));
		s_lb_1.setHorizontalAlignment(SwingConstants.CENTER);
		s_lb_1.setForeground(Color.GRAY);
		s_lb_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		s_lb_1.setBounds(408, 296, 99, 37);
		DovizKuruSayfasiFrame.getContentPane().add(s_lb_1);
		
		JLabel t_lb_1 = new JLabel(currency1.getDate());
		t_lb_1.setHorizontalAlignment(SwingConstants.CENTER);
		t_lb_1.setForeground(Color.GRAY);
		t_lb_1.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
		t_lb_1.setBounds(219, 181, 243, 49);
		DovizKuruSayfasiFrame.getContentPane().add(t_lb_1);
		
		
		CurrencyFactory currencyFactory2 = new CurrencyFactory(Moneys.AUSTRALIAN_DOLLAR);
	    Currency currency2 = currencyFactory2.getCurrency();
		JLabel dc_lb_2 = new JLabel(currency2.getName());
		dc_lb_2.setHorizontalAlignment(SwingConstants.CENTER);
		dc_lb_2.setForeground(Color.GRAY);
		dc_lb_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		dc_lb_2.setBounds(10, 344, 279, 37);
		DovizKuruSayfasiFrame.getContentPane().add(dc_lb_2);
		
		JLabel a_lb_2 = new JLabel(Float.toString(currency2.getBuyingPrice()));
		a_lb_2.setHorizontalAlignment(SwingConstants.CENTER);
		a_lb_2.setForeground(Color.GRAY);
		a_lb_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		a_lb_2.setBounds(299, 344, 99, 37);
		DovizKuruSayfasiFrame.getContentPane().add(a_lb_2);
		
		JLabel s_lb_2 = new JLabel(Float.toString(currency2.getSellingPrice()));
		s_lb_2.setHorizontalAlignment(SwingConstants.CENTER);
		s_lb_2.setForeground(Color.GRAY);
		s_lb_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		s_lb_2.setBounds(408, 344, 99, 37);
		DovizKuruSayfasiFrame.getContentPane().add(s_lb_2);
		
		CurrencyFactory currencyFactory3 = new CurrencyFactory(Moneys.EURO);
	    Currency currency3 = currencyFactory3.getCurrency();
		JLabel dc_lb_2_1 = new JLabel(currency3.getName());
		dc_lb_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		dc_lb_2_1.setForeground(Color.GRAY);
		dc_lb_2_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		dc_lb_2_1.setBounds(10, 392, 279, 37);
		DovizKuruSayfasiFrame.getContentPane().add(dc_lb_2_1);
		
		JLabel a_lb_2_1 = new JLabel(Float.toString(currency3.getBuyingPrice()));
		a_lb_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		a_lb_2_1.setForeground(Color.GRAY);
		a_lb_2_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		a_lb_2_1.setBounds(299, 392, 99, 37);
		DovizKuruSayfasiFrame.getContentPane().add(a_lb_2_1);
		
		JLabel s_lb_2_1 = new JLabel(Float.toString(currency3.getSellingPrice()));
		s_lb_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		s_lb_2_1.setForeground(Color.GRAY);
		s_lb_2_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		s_lb_2_1.setBounds(408, 392, 99, 37);
		DovizKuruSayfasiFrame.getContentPane().add(s_lb_2_1);
		
		CurrencyFactory currencyFactory4 = new CurrencyFactory(Moneys.JAPANESE_YEN);
	    Currency currency4 = currencyFactory4.getCurrency();
		JLabel dc_lb_2_1_1 = new JLabel(currency4.getName());
		dc_lb_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		dc_lb_2_1_1.setForeground(Color.GRAY);
		dc_lb_2_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		dc_lb_2_1_1.setBounds(10, 440, 279, 37);
		DovizKuruSayfasiFrame.getContentPane().add(dc_lb_2_1_1);
		
		JLabel a_lb_2_1_1 = new JLabel(Float.toString(currency4.getBuyingPrice()));
		a_lb_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		a_lb_2_1_1.setForeground(Color.GRAY);
		a_lb_2_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		a_lb_2_1_1.setBounds(299, 440, 99, 37);
		DovizKuruSayfasiFrame.getContentPane().add(a_lb_2_1_1);
		
		JLabel s_lb_2_1_1 = new JLabel(Float.toString(currency4.getSellingPrice()));
		s_lb_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		s_lb_2_1_1.setForeground(Color.GRAY);
		s_lb_2_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		s_lb_2_1_1.setBounds(408, 440, 99, 37);
		DovizKuruSayfasiFrame.getContentPane().add(s_lb_2_1_1);
		
		CurrencyFactory currencyFactory5 = new CurrencyFactory(Moneys.RUSSIAN_ROUBLE);
	    Currency currency5 = currencyFactory5.getCurrency();
		JLabel dc_lb_2_1_1_1 = new JLabel(currency5.getName());
		dc_lb_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		dc_lb_2_1_1_1.setForeground(Color.GRAY);
		dc_lb_2_1_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		dc_lb_2_1_1_1.setBounds(10, 488, 279, 37);
		DovizKuruSayfasiFrame.getContentPane().add(dc_lb_2_1_1_1);
		
		JLabel a_lb_2_1_1_1 = new JLabel(Float.toString(currency5.getBuyingPrice()));
		a_lb_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		a_lb_2_1_1_1.setForeground(Color.GRAY);
		a_lb_2_1_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		a_lb_2_1_1_1.setBounds(299, 488, 103, 37);
		DovizKuruSayfasiFrame.getContentPane().add(a_lb_2_1_1_1);
		
		JLabel s_lb_2_1_1_1 = new JLabel(Float.toString(currency5.getSellingPrice()));
		s_lb_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		s_lb_2_1_1_1.setForeground(Color.GRAY);
		s_lb_2_1_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		s_lb_2_1_1_1.setBounds(408, 488, 103, 37);
		DovizKuruSayfasiFrame.getContentPane().add(s_lb_2_1_1_1);
		
		CurrencyFactory currencyFactory6 = new CurrencyFactory(Moneys.KUWAITI_DINAR);
	    Currency currency6 = currencyFactory6.getCurrency();
		JLabel dc_lb_2_1_1_1_1 = new JLabel(currency6.getName());
		dc_lb_2_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		dc_lb_2_1_1_1_1.setForeground(Color.GRAY);
		dc_lb_2_1_1_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 23));
		dc_lb_2_1_1_1_1.setBounds(10, 536, 279, 37);
		DovizKuruSayfasiFrame.getContentPane().add(dc_lb_2_1_1_1_1);
		
		JLabel a_lb_2_1_1_1_1 = new JLabel(Float.toString(currency6.getBuyingPrice()));
		a_lb_2_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		a_lb_2_1_1_1_1.setForeground(Color.GRAY);
		a_lb_2_1_1_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		a_lb_2_1_1_1_1.setBounds(299, 536, 103, 37);
		DovizKuruSayfasiFrame.getContentPane().add(a_lb_2_1_1_1_1);
		
		JLabel s_lb_2_1_1_1_1 = new JLabel(Float.toString(currency6.getSellingPrice()));
		s_lb_2_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		s_lb_2_1_1_1_1.setForeground(Color.GRAY);
		s_lb_2_1_1_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 26));
		s_lb_2_1_1_1_1.setBounds(408, 536, 103, 37);
		DovizKuruSayfasiFrame.getContentPane().add(s_lb_2_1_1_1_1);

		JButton Yenile_Btn = new JButton(" YENİLE");
		Yenile_Btn.setIcon(new ImageIcon(DovizKuruSayfasi.class.getResource("/images/refresh.png")));
		Yenile_Btn.setSelectedIcon(new ImageIcon(DovizKuruSayfasi.class.getResource("/images/refresh.png")));
		Yenile_Btn.setForeground(new Color(77, 173, 254));
		Yenile_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				s_lb_1.setText(Float.toString(currency1.getSellingPrice()));
				s_lb_2.setText(Float.toString(currency2.getSellingPrice()));
				s_lb_2_1.setText(Float.toString(currency3.getSellingPrice()));
				s_lb_2_1_1.setText(Float.toString(currency4.getSellingPrice()));
				s_lb_2_1_1_1.setText(Float.toString(currency5.getSellingPrice()));
				s_lb_2_1_1_1_1.setText(Float.toString(currency6.getSellingPrice()));
				
				a_lb_1.setText(Float.toString(currency1.getBuyingPrice()));
				a_lb_2.setText(Float.toString(currency2.getBuyingPrice()));
				a_lb_2_1.setText(Float.toString(currency3.getBuyingPrice()));
				a_lb_2_1_1.setText(Float.toString(currency4.getBuyingPrice()));
				a_lb_2_1_1_1.setText(Float.toString(currency5.getBuyingPrice()));
				a_lb_2_1_1_1_1.setText(Float.toString(currency6.getBuyingPrice()));
			}
		});
		Yenile_Btn.setFont(new Font("Tahoma", Font.PLAIN, 27));
		Yenile_Btn.setBackground(Color.DARK_GRAY);
		Yenile_Btn.setBounds(141, 586, 256, 64);
		DovizKuruSayfasiFrame.getContentPane().add(Yenile_Btn);
		
		JButton OncekiSayfa = new JButton("");
		OncekiSayfa.setSelectedIcon(new ImageIcon(DovizKuruSayfasi.class.getResource("/images/back.png")));
		OncekiSayfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GirisSayfasi.main(null);
				DovizKuruSayfasiFrame.setVisible(false);  
			}  
		}); 
		OncekiSayfa.setIcon(new ImageIcon(DovizKuruSayfasi.class.getResource("/images/back.png")));
		OncekiSayfa.setBackground(Color.DARK_GRAY);
		OncekiSayfa.setBounds(0, 0, 64, 64);
		DovizKuruSayfasiFrame.getContentPane().add(OncekiSayfa);
		
	
		DovizKuruSayfasiFrame.setTitle("SUW\u0130 BANK");
		DovizKuruSayfasiFrame.setFont(new Font("Segoe UI Semilight", Font.BOLD | Font.ITALIC, 30));
		DovizKuruSayfasiFrame.setForeground(Color.DARK_GRAY);
		DovizKuruSayfasiFrame.setBackground(Color.DARK_GRAY);
		DovizKuruSayfasiFrame.setBounds(100, 100, 556, 700);
		DovizKuruSayfasiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DovizKuruSayfasiFrame.setLocationRelativeTo(null);
	}
}

