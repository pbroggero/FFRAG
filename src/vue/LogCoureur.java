package vue;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import FFRAG.Coureur;
import FFRAG.FFRAG;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogCoureur extends JFrame {

	private JPanel contentPane;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtDate;
	private FFRAG ffrag;
	/**
	 * Launch the application.
	 */
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogCoureur frame = new LogCoureur();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public LogCoureur(FFRAG ffrag) {
		this.ffrag=ffrag;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdentifierVous = new JLabel("Identifier Vous");
		lblIdentifierVous.setFont(new Font("Eras Bold ITC", Font.PLAIN, 17));
		lblIdentifierVous.setBounds(131, 13, 171, 53);
		contentPane.add(lblIdentifierVous);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNom.setBounds(86, 83, 72, 18);
		contentPane.add(lblNom);
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Calibri", Font.BOLD, 15));
		lblPrenom.setBounds(86, 116, 72, 18);
		contentPane.add(lblPrenom);
		
		JLabel lblDateNaissance = new JLabel("Date Naissance");
		lblDateNaissance.setFont(new Font("Calibri", Font.BOLD, 15));
		lblDateNaissance.setBounds(86, 150, 116, 18);
		contentPane.add(lblDateNaissance);
		
		txtNom = new JTextField();
		txtNom.setBounds(240, 79, 86, 24);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		txtPrenom = new JTextField();
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(240, 112, 86, 24);
		contentPane.add(txtPrenom);
		
		JLabel lblFormatAaaammjj = new JLabel("format: aaaa-mm-jj");
		lblFormatAaaammjj.setBounds(176, 173, 150, 18);
		contentPane.add(lblFormatAaaammjj);
		
		txtDate = new JTextField();
		txtDate.setText("AAAA-MM-JJ");
		txtDate.setColumns(10);
		txtDate.setBounds(240, 146, 86, 24);
		contentPane.add(txtDate);
		txtDate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtDate.setText("");
			}
		});
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Coureur coureur;
				coureur = ffrag.confirmeCoureur(txtNom.getText(), txtPrenom.getText());
				if(!(coureur==null)) {
					System.out.println(coureur.getNomCoureur()+" "+coureur.getPrenomCoureur());
					dispose();
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Inscription frame = new Inscription(ffrag, coureur);
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
				else {
					System.out.println("Pas de info dans BD, contactez les organisateurs ou les agences.");
				}
			}
		});
		btnNewButton.setBounds(160, 213, 113, 27);
		contentPane.add(btnNewButton);
		
		
	}
}