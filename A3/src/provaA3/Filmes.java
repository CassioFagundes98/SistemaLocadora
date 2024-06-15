package provaA3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Filmes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private ConectaBanco banco;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Filmes frame = new Filmes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Filmes() {
		banco = new ConectaBanco();

		setTitle("Estoque");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(109, 9, 178, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("ID Filme");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 89, 17);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 39, 89, 17);
		contentPane.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(109, 37, 178, 20);
		contentPane.add(textField_1);

		JLabel lblNewLabel_2 = new JLabel("Ano");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 67, 89, 17);
		contentPane.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(109, 65, 178, 20);
		contentPane.add(textField_2);

		JButton btnNewButton = new JButton("Consultar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "SELECT * FROM filmes";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);

					ResultSet rs = ps.executeQuery();
					while (rs.next()) {

						int codigo = rs.getInt("idFilme");
						String nome = rs.getString("nomeFilme");
						int anoFilme = rs.getInt("anofilme");
						int disponivel = rs.getInt("disponivel");
						float preço = rs.getFloat("preço");
						int faixaEtaria = rs.getInt("faixaEtaria");
						String categoria = rs.getString("categoria");

						String aux = String.format(
								"Código: %d\n Nome: %s\n Ano do Filme: %s\n Disponibilidade: %s\n "
										+ "preço: %s\n Faixa Etaria: %s\n categoria: %s\n",
								codigo, nome, anoFilme, disponivel, preço, faixaEtaria, categoria);
						JOptionPane.showMessageDialog(null, aux);

					}
				} catch (Exception w) {
					w.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(307, 195, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "INSERT INTO filmes(nomeFilme,anofilme,disponivel,preço,faixaEtaria,categoria) VALUES(?,?,?,?,?,?)";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setString(1, textField_1.getText());
					ps.setInt(2, Integer.parseInt(textField_2.getText()));
					ps.setString(3, textField_3.getText());
					ps.setDouble(4, Double.parseDouble(textField_4.getText()));
					ps.setInt(5, Integer.parseInt(textField_5.getText()));
					ps.setString(6, textField_6.getText());
					ps.execute();
					JOptionPane.showMessageDialog(null, "Salvo");
				} catch (Exception w) {
					w.printStackTrace();
				}

				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");

			}
		});
		btnNewButton_1.setBounds(10, 195, 89, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Alterar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "UPDATE filmes SET nomeFilme = ?, anofilme = ?, disponivel = ?, preço = ?, faixaEtaria = ?, categoria = ? WHERE idFilme = ?";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);

					ps.setString(1, textField_1.getText());
					ps.setInt(2, Integer.parseInt(textField_2.getText()));
					ps.setInt(3, Integer.parseInt(textField_3.getText()));
					ps.setDouble(4, Double.parseDouble(textField_4.getText()));
					ps.setInt(5, Integer.parseInt(textField_5.getText()));
					ps.setString(6, textField_6.getText());
					ps.setInt(7, Integer.parseInt(textField.getText()));

					ps.execute();
					JOptionPane.showMessageDialog(null,
							"Filme " + textField.getText() + " " + textField_1.getText() + " Alterado com sucesso!!!");
				} catch (Exception w) {
					w.printStackTrace();
				}
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");

			}
		});
		btnNewButton_2.setBounds(109, 195, 89, 23);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Excluir");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "delete from filmes Where idFilme = ? ";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setInt(1, Integer.parseInt(textField.getText()));
					ps.execute();
					JOptionPane.showMessageDialog(null, "Filme " + textField.getText() + " Excluido com sucesso");
				} catch (Exception w) {
					w.printStackTrace();
				}
				textField.setText("");
				textField_1.setText("");

			}
		});
		btnNewButton_3.setBounds(208, 195, 89, 23);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Fechar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		btnNewButton_4.setBounds(158, 229, 89, 23);
		contentPane.add(btnNewButton_4);

		JLabel lblNewLabel_3 = new JLabel("Disponível");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(10, 95, 89, 14);
		contentPane.add(lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setBounds(109, 92, 178, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Preço");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(10, 120, 89, 14);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Faixa Etaria");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(10, 145, 89, 14);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Gêneros");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(10, 170, 89, 14);
		contentPane.add(lblNewLabel_6);

		textField_4 = new JTextField();
		textField_4.setBounds(109, 117, 178, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setBounds(109, 142, 178, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField();
		textField_6.setBounds(109, 167, 178, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
	}
}