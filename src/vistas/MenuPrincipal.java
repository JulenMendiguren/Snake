package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;
	private JPanel panelBottom;
	private JButton btnJugar;
	private static MenuPrincipal elMenu;
	private JPanel panelRight;
	private JPanel panel;
	private JLabel lblModificadores;
	private JRadioButton rdbtnManzEnv;
	private JRadioButton rdbtnRapido;
	private JRadioButton rdbtnContrarreloj;
	private JRadioButton rdbtnConParedes;
	private JLabel lblSnake;
	private JLabel label;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		abrirMenuPrincipal();
	}

	private static void abrirMenuPrincipal() {
		System.out.println("MAIN");

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					elMenu = new MenuPrincipal();
					elMenu.setTitle("Snake");
					elMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getPanel_1());
		contentPane.add(getPanel_1_1());
		contentPane.add(getPanel());
	}

	private JPanel getPanel_1() {
		if (panelBottom == null) {
			panelBottom = new JPanel();
			panelBottom.setBounds(5, 223, 424, 33);
			panelBottom.add(getBtnJugar());
		}
		return panelBottom;
	}

	private JButton getBtnJugar() {
		if (btnJugar == null) {
			btnJugar = new JButton("JUGAR");
			btnJugar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					iniciarJuego();
					cerrarMenu();
				}
			});
		}
		return btnJugar;
	}

	private void cerrarMenu() {
		setVisible(false);
		elMenu = null;
		dispose();
	}

	private JPanel getPanel_1_1() {
		if (panelRight == null) {
			panelRight = new JPanel();
			panelRight.setBounds(5, 11, 160, 201);
			panelRight.setLayout(null);
			panelRight.add(getLblSnake());
		}
		return panelRight;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(175, 11, 254, 201);
			panel.setLayout(null);
			panel.add(getLabel());
			panel.add(getComboBox_1());
			panel.add(getLblModificadores());
			panel.add(getRdbtnManzEnv());
			panel.add(getRdbtnRapido());
			panel.add(getRdbtnContrarreloj());
			panel.add(getRdbtnConParedes());
		}
		return panel;
	}

	private JLabel getLblModificadores() {
		if (lblModificadores == null) {
			lblModificadores = new JLabel("Modificadores");
			lblModificadores.setBounds(17, 56, 85, 15);
			lblModificadores.setFont(new Font("Tahoma", Font.BOLD, 12));
		}
		return lblModificadores;
	}

	private JRadioButton getRdbtnManzEnv() {
		if (rdbtnManzEnv == null) {
			rdbtnManzEnv = new JRadioButton("Manzanas envenenadas");
			rdbtnManzEnv.setBounds(17, 78, 191, 23);
		}
		return rdbtnManzEnv;
	}

	private JRadioButton getRdbtnRapido() {
		if (rdbtnRapido == null) {
			rdbtnRapido = new JRadioButton("M\u00E1s r\u00E1pido");
			rdbtnRapido.setBounds(17, 104, 191, 23);
		}
		return rdbtnRapido;
	}

	private JRadioButton getRdbtnContrarreloj() {
		if (rdbtnContrarreloj == null) {
			rdbtnContrarreloj = new JRadioButton("Modo contrarreloj");
			rdbtnContrarreloj.setBounds(17, 156, 214, 23);
		}
		return rdbtnContrarreloj;
	}

	private JRadioButton getRdbtnConParedes() {
		if (rdbtnConParedes == null) {
			rdbtnConParedes = new JRadioButton("Con paredes");
			rdbtnConParedes.setBounds(17, 130, 200, 23);
		}
		return rdbtnConParedes;
	}

	private JLabel getLblSnake() {
		if (lblSnake == null) {
			lblSnake = new JLabel("SNAKE");
			lblSnake.setForeground(new Color(0, 100, 0));
			lblSnake.setFont(new Font("Ravie", Font.ITALIC, 28));
			lblSnake.setBounds(20, 69, 140, 66);
		}
		return lblSnake;
	}

	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("Seleccionar aspecto");
			label.setBounds(17, 26, 120, 15);
			label.setFont(new Font("Tahoma", Font.BOLD, 12));
		}
		return label;
	}

	private JComboBox getComboBox_1() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "Verde", "Azul", "Rosa", "Naranja" }));
			comboBox.setBounds(159, 24, 85, 20);
		}
		return comboBox;
	}

	private void iniciarJuego() {
		String skin = (String) comboBox.getSelectedItem();
		int skinIndex = 0;
		if (skin.equals("Verde"))
			skinIndex = 0;
		if (skin.equals("Azul"))
			skinIndex = 1;
		if (skin.equals("Rosa"))
			skinIndex = 2;
		if (skin.equals("Naranja"))
			skinIndex = 3;

		boolean[] modificadores = new boolean[4];
		modificadores[0] = rdbtnManzEnv.isSelected();
		modificadores[1] = rdbtnRapido.isSelected();
		modificadores[2] = rdbtnConParedes.isSelected();
		modificadores[3] = rdbtnContrarreloj.isSelected();

		JuegoUI juego = new JuegoUI(skinIndex, modificadores);

	}
}
