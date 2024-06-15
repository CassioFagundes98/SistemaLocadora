package provaA3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class Carrinho extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<FilmeDAO> listaFilme = new ArrayList<FilmeDAO>();
	private JComboBox<FilmeDAO> comboBox = new JComboBox<FilmeDAO>();

	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Carrinho frame = new Carrinho();
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
	public Carrinho() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {

				String sql = "SELECT * FROM filmes";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);

					ResultSet rs = ps.executeQuery();
					while (rs.next()) {

						FilmeDAO filme = new FilmeDAO();

						filme.setAnoFilme(rs.getInt("anofilme"));
						filme.setIdFilme(rs.getInt("idFilme"));
						filme.setNomeFilme(rs.getString("nomeFilme"));
						filme.setCategoria(rs.getString("categoria"));
						filme.setFaixaEtaria(rs.getInt("faixaEtaria"));
						filme.setDisponivel(rs.getInt("disponivel"));
						filme.setPreço(rs.getFloat("preço"));

						listaFilme.add(filme);

					}

					listaFilme.forEach(f -> {
						comboBox.addItem(f);
					});
				} catch (Exception w) {
					w.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 191, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("CPF");
		lblNewLabel.setBounds(10, 11, 77, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ID_Funcionário");
		lblNewLabel_1.setBounds(10, 36, 77, 14);
		contentPane.add(lblNewLabel_1);

		comboBox.setBounds(10, 86, 376, 22);
		contentPane.add(comboBox);

		textField = new JTextField();
		textField.setBounds(97, 8, 209, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(97, 33, 209, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("consultar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "SELECT * FROM carrinho WHERE cliente_cpf = ?";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);

					ps.setString(1, textField.getText());

					ResultSet rs = ps.executeQuery();
					while (rs.next()) {

						String cpf = rs.getString("cliente_cpf");
						int idfunc = rs.getInt("idFunc");
						int filmeID = rs.getInt("filme_id");

						String sql1 = "SELECT * FROM filmes WHERE idFilme = ?";
						PreparedStatement ps1 = c.prepareStatement(sql1);

						ps1.setInt(1, filmeID);

						ResultSet rs1 = ps1.executeQuery();
						String nome = "";
						int valor = 0;
						while (rs1.next()) {
							nome = rs1.getString("nomeFilme");
							valor = rs1.getInt("preço");
						}

						String aux = String.format("CPF: %s\n Funcionario: %s\n filme: %s\n valor: %d\n ",

								cpf, idfunc, nome, valor);
						JOptionPane.showMessageDialog(null, aux);

					}
				} catch (Exception w) {
					w.printStackTrace();
				}

			}
		});
		btnNewButton.setBounds(44, 136, 114, 43);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("comprar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String sql = "INSERT INTO carrinho(cliente_cpf,idFunc,filme_id) VALUES(?,?,?)";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);

					ps.setString(1, textField.getText());
					ps.setInt(2, Integer.parseInt(textField_1.getText()));
					ps.setInt(3, ((FilmeDAO) comboBox.getSelectedItem()).getIdFilme());
					ps.execute();
					JOptionPane.showMessageDialog(null, "Compra efetuada.");

				} catch (Exception w) {
					w.printStackTrace();
				}
			}
		});

		btnNewButton_1.setBounds(168, 136, 114, 43);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Voltar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		btnNewButton_2.setBounds(149, 207, 114, 43);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Excluir");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql = "delete from carrinho Where cliente_cpf = ? ";
				ConectaBanco factory = new ConectaBanco();
				try (Connection c = factory.obtemConexao()) {
					PreparedStatement ps = c.prepareStatement(sql);
					ps.setString(1, textField.getText());
					ps.execute();
					JOptionPane.showMessageDialog(null, "Compra " + textField.getText() + " Finalizada com sucesso");
				} catch (Exception w) {
					w.printStackTrace();
				}
				textField.setText("");

			}
		});
		btnNewButton_3.setBounds(292, 136, 114, 43);
		contentPane.add(btnNewButton_3);

	}
}
