import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * 
 */

/**
 * @author Nam Nguyen T152678
 */
public class pHW01_Calculator_T152678 extends JFrame {
	JTextField txtin = new JTextField();
	JPanel panbox = new JPanel(), pro = new JPanel(), pr = new JPanel();
	JRadioButton optde = new JRadioButton("Degrees"), optra = new JRadioButton("Radians"),
			optgr = new JRadioButton("Grads"), opthex = new JRadioButton("Hex"), optdec = new JRadioButton("Dec"),
			optoct = new JRadioButton("Oct"), optbin = new JRadioButton("Bin"), optqword = new JRadioButton("Qword"),
			optdword = new JRadioButton("Dword"), optword = new JRadioButton("Word"), optbit = new JRadioButton("Byte");
	JMenuBar mnbbar = new JMenuBar();
	JMenu mnuview = new JMenu("View"), mnuedit = new JMenu("Edit"), mnuhelp = new JMenu("Help");
	JMenuItem mnistand = new JMenuItem("Standard", KeyEvent.VK_D),
			mniscientific = new JMenuItem("Scientific", KeyEvent.VK_S),
			mniprogrammer = new JMenuItem("Programmer", KeyEvent.VK_P), mniexit = new JMenuItem("Exit", KeyEvent.VK_X),
			mnicop = new JMenuItem("Copy", KeyEvent.VK_C), mnipas = new JMenuItem("Paste", KeyEvent.VK_V),
			mniabout = new JMenuItem("About", KeyEvent.VK_A);
	ImageIcon imcop = new ImageIcon("icon/copy.gif"), impas = new ImageIcon("icon/pas.gif"),
			imabout = new ImageIcon("icon/info.gif");
	ButtonGroup btgselect = new ButtonGroup(), sel = new ButtonGroup(), se = new ButtonGroup();

	int width = 50, height = 25;
	double dMemory = 0, dSo = 0, result;
	boolean selected = false;
	String sCal = "", backspace = null;

	public pHW01_Calculator_T152678() {
		// setting Hotkey
		mnistand.setMnemonic(KeyEvent.VK_D);
		mniscientific.setMnemonic(KeyEvent.VK_S);
		mniprogrammer.setMnemonic(KeyEvent.VK_P);
		mniexit.setMnemonic(KeyEvent.VK_X);
		mnicop.setMnemonic(KeyEvent.VK_C);
		mnipas.setMnemonic(KeyEvent.VK_V);
		mniabout.setMnemonic(KeyEvent.VK_A);
		// Setting the accelerator:
		mnistand.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		mniscientific.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		mniprogrammer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
		mniexit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
		mnicop.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
		mnipas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
		mniabout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
		// setIcon
		mnicop.setIcon(imcop);
		mnipas.setIcon(impas);
		mniabout.setIcon(imabout);
		// Size
		this.setPreferredSize(new Dimension(520, 450));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("cHW01_Calculator_T152678");
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		// Border
		panbox.setLayout(new BorderLayout(14, 10));
		pro.setLayout(new FlowLayout(FlowLayout.LEFT));
		pr.setLayout(new FlowLayout(FlowLayout.LEFT));
		// Events
		inittalizeMenu();
		Events();
		initScien();
		initStand();
		initProgram();
		initEvent();
		displayMode(3);
		// ButtonGroup
		btgselect.add(optde);
		btgselect.add(optra);
		// btgselect.add(optgr);
		optde.setSelected(true);
		optdec.setSelected(true);
		optqword.setSelected(true);
		// programmer
		sel.add(opthex);
		sel.add(optoct);
		sel.add(optdec);
		sel.add(optbin);
		// 2
		se.add(optqword);
		se.add(optdword);
		se.add(optword);
		se.add(optbit);
		// add
		this.add(txtin);
		txtin.setText("0");
		txtin.setEditable(false);
		this.add(panbox);
		this.add(pro);
		this.add(pr);
		Font c = txtin.getFont();
		txtin.setFont(new Font(c.getName(), Font.BOLD, c.getSize() + 15));
		txtin.setHorizontalAlignment(txtin.RIGHT);

		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource() == mnistand) {
					displayMode(1);
				} else if (e.getSource() == mniscientific) {
					displayMode(2);
				} else if (e.getSource() == mniprogrammer) {
					displayMode(3);
				} else if (e.getSource() == mniexit) {
					close();
				}
			}
		};
		mniscientific.addActionListener(action);
		mnistand.addActionListener(action);
		mniprogrammer.addActionListener(action);
		mniexit.addActionListener(action);

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}
		});
	}

	public void actionPerformed() {
		ActionListener actMniFuntions = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Clipboard cBoard = getToolkit().getDefaultToolkit().getSystemClipboard();
				if (e.getSource() == mnicop) {
					String sdata = txtin.getText();
					StringSelection data = new StringSelection(sdata);
					cBoard.setContents(data, null);
				} else if (e.getSource() == mnipas) {
					Transferable clipData = cBoard.getContents(this);
					String s;
					try {
						s = (String) (clipData.getTransferData(DataFlavor.stringFlavor));
						txtin.setText(s);
					} catch (Exception ex) {
						txtin.setText("");
					}
				} else if (e.getSource() == mniabout) {
					String message = "Calculator ver. 1.0\nCopyright 2016\nAll rights reserved";
					JOptionPane aboutPanel = new JOptionPane();
					aboutPanel.showMessageDialog(null, message, "About Calculator", aboutPanel.INFORMATION_MESSAGE);
				}
				// selected = false;
			}
		};
		mnicop.addActionListener(actMniFuntions);
		mnipas.addActionListener(actMniFuntions);
		mniabout.addActionListener(actMniFuntions);
	}

	public void initEvent() {
		ActionListener actIn = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JButton btnIn = (JButton) arg0.getSource();
				String sIn = btnIn.getText();
				String sS1 = txtin.getText();
				if (selected == true) {
					if (sS1.equals(0)) {
						txtin.setText(sIn);
					} else {
						txtin.setText(sS1 + sIn);
					}
				} else {
					txtin.setText(sIn);
					selected = true;
				}
			}
		};
		btnStandard[2][0].addActionListener(actIn);
		btnStandard[2][1].addActionListener(actIn);
		btnStandard[2][2].addActionListener(actIn);
		btnStandard[3][0].addActionListener(actIn);
		btnStandard[3][1].addActionListener(actIn);
		btnStandard[3][2].addActionListener(actIn);
		btnStandard[4][0].addActionListener(actIn);
		btnStandard[4][1].addActionListener(actIn);
		btnStandard[4][2].addActionListener(actIn);
		btnStandard[5][0].addActionListener(actIn);
		btnStandard[5][1].addActionListener(actIn);
		btnprogram[0][2].addActionListener(actIn);
		btnprogram[1][2].addActionListener(actIn);
		btnprogram[2][2].addActionListener(actIn);
		btnprogram[3][2].addActionListener(actIn);
		btnprogram[4][2].addActionListener(actIn);
		btnprogram[5][2].addActionListener(actIn);

		ActionListener actFuntion = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent g) {
				// TODO Auto-generated method stub
				JButton btnMemory = (JButton) g.getSource();
				String scomm = btnMemory.getText();
				double dCal = Double.parseDouble(txtin.getText());
				if (scomm.equals("MS")) {
					dMemory = Double.parseDouble(txtin.getText());
				} else if (scomm.equals("MR")) {
					String sMemoryValue = Double.toString(dMemory);
					txtin.setText(sMemoryValue);
				} else if (scomm.equals("MC")) {
					dMemory = 0;
					txtin.setText(Double.toString(dMemory));
				} else if (scomm.equals("M+")) {
					dMemory = dMemory + Double.parseDouble(txtin.getText());
					txtin.setText(Double.toString(dMemory));
				} else if (scomm.equals("M-")) {
					dMemory = dMemory - Double.parseDouble(txtin.getText());
					txtin.setText(Double.toString(dMemory));
				} else if (scomm.equals("ln")) {
					result = (Math.log(dCal));
					txtin.setText(String.format("%f", result));
				} else if (scomm.equals("sin")) {
					if (optde.isSelected()) {
						result = Math.sin(Math.toRadians(dCal));
					} else if (optra.isSelected()) {
						result = Math.sin(dCal);
					}
					txtin.setText("" + result);
				} else if (scomm.equals("cos")) {
					if (optde.isSelected()) {
						result = Math.cos(Math.toRadians(dCal));
					} else if (optra.isSelected()) {
						result = Math.cos(dCal);
					}
					txtin.setText("" + result);
				} else if (scomm.equals("tan")) {
					if (optde.isSelected()) {
						result = Math.tan(Math.toRadians(dCal));
					} else if (optra.isSelected()) {
						result = Math.tan(dCal);
					}
					txtin.setText(String.format("%f", result));
				} else if (scomm.equals("log")) {
					result = Math.log10(dCal);
					txtin.setText(String.format("%f", result));
				} else if (scomm.equals("x^3")) {
					result = Math.pow(dCal, 3);
					txtin.setText(String.format("" + result));
				} else if (scomm.equals("x^2")) {
					result = Math.pow(dCal, 2);
					txtin.setText("" + result);
				} else if (scomm.equals("n!")) {
					int j = 1;
					for (int i = 1; i <= dCal; i++) {
						j *= i;
						txtin.setText("" + j);
					}
				} else if (scomm.equals("x^1/3")) {
					result = Math.cbrt(dCal);
					txtin.setText(String.format("%f", result));
				} else if (scomm.equals("10^x")) {
					result = Math.pow(10, dCal);
					txtin.setText(String.format("" + result));
				} else if (scomm.equals("PI")) {
					result = Math.PI;
					txtin.setText("" + result);
				} else if (scomm.equals("Not")) {
					if (dCal >= 0) {
						result = (-dCal - 1);
					} else if (dCal < 0) {
						result = (Math.abs(dCal) - 1);
					}
					txtin.setText("" + result);
				} else if (scomm.equals("RoL")) {
					result = dCal * 2;
					txtin.setText("" + result);
				} else if (scomm.equals("RoR")) {
					result = dCal / 2;
					txtin.setText("" + result);
				}
				selected = false;
			}
		};
		btnStandard[0][0].addActionListener(actFuntion);
		btnStandard[0][1].addActionListener(actFuntion);
		btnStandard[0][2].addActionListener(actFuntion);
		btnStandard[0][3].addActionListener(actFuntion);
		btnStandard[0][4].addActionListener(actFuntion);
		btnscientific[0][2].addActionListener(actFuntion);
		btnscientific[1][2].addActionListener(actFuntion);
		btnscientific[2][2].addActionListener(actFuntion);
		btnscientific[3][2].addActionListener(actFuntion);
		btnscientific[4][3].addActionListener(actFuntion);
		btnscientific[3][3].addActionListener(actFuntion);
		btnscientific[1][3].addActionListener(actFuntion);
		btnscientific[1][4].addActionListener(actFuntion);
		btnscientific[4][4].addActionListener(actFuntion);
		btnscientific[3][4].addActionListener(actFuntion);
		btnscientific[3][0].addActionListener(actFuntion);
		btnprogram[5][0].addActionListener(actFuntion);
		btnprogram[2][0].addActionListener(actFuntion);
		btnprogram[2][1].addActionListener(actFuntion);

		ActionListener actDau = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton btnCal = (JButton) e.getSource();
				dSo = Double.parseDouble(txtin.getText());
				sCal = btnCal.getText();
				selected = false;
			}
		};
		btnStandard[5][2].addActionListener(actDau);
		btnStandard[4][3].addActionListener(actDau);
		btnStandard[3][3].addActionListener(actDau);
		btnStandard[2][3].addActionListener(actDau);
		btnStandard[2][4].addActionListener(actDau);
		btnStandard[3][4].addActionListener(actDau);
		btnStandard[1][4].addActionListener(actDau);
		btnStandard[1][3].addActionListener(actDau);
		btnscientific[2][3].addActionListener(actDau);
		btnscientific[2][4].addActionListener(actDau);
		btnscientific[4][2].addActionListener(actDau);
		btnprogram[0][1].addActionListener(actDau);
		btnprogram[2][0].addActionListener(actDau);
		btnprogram[2][1].addActionListener(actDau);

		ActionListener actCalculate = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				double dCal = Double.parseDouble(txtin.getText());
				if (sCal.equals("+")) {
					result = dSo + dCal;
					txtin.setText("" + result);
				} else if (sCal.equals("-")) {
					result = dSo - dCal;
					txtin.setText("" + result);
				} else if (sCal.equals("*")) {
					result = dSo * dCal;
					txtin.setText("" + result);
				} else if (sCal.equals("/")) {
					result = dSo / dCal;
					txtin.setText("" + result);
				} else if (sCal.equals("1/x")) {
					result = 1 / dCal;
					txtin.setText("" + result);
				} else if (sCal.equals("x^1/2")) {
					result = Math.sqrt(dCal);
					txtin.setText("" + result);
				} else if (sCal.equals("+/-")) {
					txtin.setText("" + (-dCal));
				} else if (sCal.equals("%")) {
					result = (dCal * dCal) / 100;
					txtin.setText(String.format("%.2f", result));
				} else if (sCal.equals("x^y")) {
					result = Math.pow(dSo, dCal);
					txtin.setText("" + result);
				} else if (sCal.equals("x^1/y")) {
					result = Math.pow(dSo, 1 / dCal);
					txtin.setText("" + result);
				} else if (sCal.equals("Mod")) {
					if (dSo >= dCal) {
						result = dSo % dCal;
					} else {
						result = dSo;
					}
					txtin.setText("" + result);
				}
				selected = false;
			}
		};
		btnStandard[5][4].addActionListener(actCalculate);

		ActionListener actBackspace = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				StringBuilder sBuild = new StringBuilder(txtin.getText());
				if (txtin.getText().length() > 1) {
					sBuild.deleteCharAt(txtin.getText().length() - 1);
					backspace = sBuild.toString();
					txtin.setText(backspace);
				} else if (txtin.getText().length() == 1) {
					txtin.setText("0");
				}
				selected = false;
			}
		};
		btnStandard[1][0].addActionListener(actBackspace);

		ActionListener actCe = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtin.setText("0");
				selected = false;
			}
		};
		btnStandard[1][1].addActionListener(actCe);

		ActionListener actC = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtin.setText("0");
				result = 0;
				dMemory = 0;
				selected = false;
			}
		};
		btnStandard[1][2].addActionListener(actC);
	}

	String[][] sscientific = { { "", "Inv", "ln", "(", ")", }, { "Int", "sinh", "sin", "x^2", "n!" },
			{ "dms", "cosh", "cos", "x^y", "x^1/y" }, { "PI", "tanh", "tan", "x^3", "x^1/3" },
			{ "F-E", "Exp", "Mod", "log", "10^x" } };
	JButton[][] btnscientific = new JButton[5][5];
	String[][] sStandard = { { "MC", "MR", "MS", "M+", "M-", }, { "<-", "CE", "C", "+/-", "x^1/2" },
			{ "7", "8", "9", "/", "%" }, { "4", "5", "6", "*", "1/x" }, { "3", "2", "1", "-", "" },
			{ "0", ".", "+", "", "=" } };
	JButton[][] btnStandard = new JButton[6][5];
	String[][] program = { { "", "Mod", "A", }, { "(", ")", "B" }, { "RoL", "RoR", "C" }, { "Or", "Xor", "D" },
			{ "Lsh", "Rsh", "E" }, { "Not", "And", "F" } };
	JButton[][] btnprogram = new JButton[6][3];
	JPanel panStandard = new JPanel();
	JPanel panScientific = new JPanel();
	JPanel panProgrammer = new JPanel();
	int w = 40, h = 40, d = 10;
	int x = 0, y = 0;

	public void initStand() {
		panStandard.setLayout(null);
		Insets isMargin = new Insets(1, 1, 1, 1);
		y = 0;
		for (int i = 0; i < 6; i++) {
			x = 0;
			for (int j = 0; j < 5; j++) {
				btnStandard[i][j] = new JButton(sStandard[i][j]);
				panStandard.add(btnStandard[i][j]);
				btnStandard[i][j].setBounds(x, y, w, h);
				btnStandard[i][j].setMargin(isMargin);
				x = x + w + d;
			}
			y = y + h + d;
		}
		// relocate for the last ROW
		btnStandard[5][4].setSize(w, h + d + h);
		btnStandard[5][0].setSize(w + d + w, h);
		btnStandard[5][1].setLocation(w + d + w + d, y - h - d);
		btnStandard[5][2].setLocation(w + d + w + d + w + d, y - h - d);
		btnStandard[5][4].setLocation(x - 50, y - 100);
		btnStandard[4][4].setVisible(false);
		btnStandard[5][4].setVisible(true);
		this.add(panStandard);
		displayMode(1);
	}

	public void initScien() {
		Insets s = new Insets(1, 1, 1, 1);
		// Khung
		panbox.add(optde, BorderLayout.WEST);
		panbox.add(optra, BorderLayout.CENTER);
		panbox.add(optgr, BorderLayout.EAST);
		panbox.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		y = 0;
		for (int i = 0; i < 5; i++) {
			x = 0;
			for (int j = 0; j < 5; j++) {
				btnscientific[i][j] = new JButton(sscientific[i][j]);
				panScientific.add(btnscientific[i][j]);
				btnscientific[i][j].setBounds(x, y, w, h);
				btnscientific[i][j].setMargin(s);
				x = x + w + d;
			}
			y = y + h + d;
		}
		this.add(panScientific);
		btnscientific[0][1].setEnabled(false);
		btnscientific[0][3].setEnabled(false);
		btnscientific[0][4].setEnabled(false);
		btnscientific[1][1].setEnabled(false);
		btnscientific[2][1].setEnabled(false);
		btnscientific[3][1].setEnabled(false);
		btnscientific[4][1].setEnabled(false);
		btnscientific[1][0].setEnabled(false);
		btnscientific[2][0].setEnabled(false);
		btnscientific[4][0].setEnabled(false);
		optgr.setEnabled(false);
		panScientific.setLayout(null);
		panProgrammer.setVisible(false);
		displayMode(2);
	}

	public void initProgram() {
		panProgrammer.setLayout(null);
		Insets n = new Insets(1, 1, 1, 1);
		// Khung 1
		pro.add(opthex);
		pro.add(optdec);
		pro.add(optoct);
		pro.add(optbin);
		pro.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		// Khung 2
		pr.add(optqword);
		pr.add(optdword);
		pr.add(optword);
		pr.add(optbit);
		pr.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		y = 0;
		for (int i = 0; i < 6; i++) {
			x = 0;
			for (int j = 0; j < 3; j++) {
				btnprogram[i][j] = new JButton(program[i][j]);
				panProgrammer.add(btnprogram[i][j]);
				btnprogram[i][j].setBounds(x, y, w, h);
				btnprogram[i][j].setMargin(n);
				x = x + w + d;
			}
			y = y + h + d;
		}
		btnprogram[0][0].setEnabled(false);
		btnprogram[1][0].setEnabled(false);
		btnprogram[1][1].setEnabled(false);
		btnprogram[3][0].setEnabled(false);
		btnprogram[3][1].setEnabled(false);
		btnprogram[4][0].setEnabled(false);
		btnprogram[4][1].setEnabled(false);
		this.add(panProgrammer);
		displayMode(3);
	}

	public void displayMode(int mode) {
		if (mode == 2) {
			panProgrammer.setVisible(false);
			panScientific.setVisible(true);
			panStandard.setVisible(true);
			panbox.setSize(240, 40);
			panbox.setLocation(10, 100);
			panbox.setVisible(true);
			pro.setVisible(false);
			pr.setVisible(false);
			panScientific.setBounds(10, 150, x + 100, y);
			panStandard.setBounds(260, 100, x + 100, y);
			txtin.setBounds(10, 10, width + 440, height + 55);
			this.setSize(520, 450);
		} else if (mode == 1) {
			panScientific.setVisible(false);
			panProgrammer.setVisible(false);
			panStandard.setVisible(true);
			panbox.setVisible(false);
			pro.setVisible(false);
			pr.setVisible(false);
			panStandard.setBounds(10, 80, x + 100, y);
			txtin.setBounds(10, 10, width + 190, height + 40);
			this.setSize(5 * w + 4 * d + 30, 6 * h + 4 * d + 150);
		} else if (mode == 3) {
			panScientific.setVisible(false);
			panStandard.setVisible(true);
			panProgrammer.setVisible(true);
			panbox.setVisible(false);
			pro.setSize(80, 140);
			pro.setLocation(10, 90);
			pro.setVisible(true);
			pr.setSize(80, 140);
			pr.setLocation(10, 320 - 80);
			pr.setVisible(true);
			btnStandard[5][1].setEnabled(false);
			btnStandard[1][4].setEnabled(false);
			btnStandard[2][4].setEnabled(false);
			btnStandard[3][4].setEnabled(false);
			btnprogram[0][2].setEnabled(false);
			btnprogram[1][2].setEnabled(false);
			btnprogram[2][2].setEnabled(false);
			btnprogram[3][2].setEnabled(false);
			btnprogram[4][2].setEnabled(false);
			btnprogram[5][2].setEnabled(false);
			txtin.setBounds(10, 10, width + 440, height + 45);
			panProgrammer.setBounds(100, 90, x, y);
			panStandard.setBounds(260, 90, x + 100, y);
			this.setSize(520, 440);
		}
	}

	public void Events() {
		ActionListener actOn = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (opthex.isSelected()) {
					btnprogram[0][2].setEnabled(true);
					btnprogram[1][2].setEnabled(true);
					btnprogram[2][2].setEnabled(true);
					btnprogram[3][2].setEnabled(true);
					btnprogram[4][2].setEnabled(true);
					btnprogram[5][2].setEnabled(true);
					btnStandard[2][0].setEnabled(true);
					btnStandard[2][1].setEnabled(true);
					btnStandard[2][2].setEnabled(true);
					btnStandard[3][0].setEnabled(true);
					btnStandard[3][1].setEnabled(true);
					btnStandard[3][2].setEnabled(true);
					btnStandard[4][0].setEnabled(true);
					btnStandard[4][1].setEnabled(true);
				} else if (optdec.isSelected()) {
					btnprogram[0][2].setEnabled(false);
					btnprogram[1][2].setEnabled(false);
					btnprogram[2][2].setEnabled(false);
					btnprogram[3][2].setEnabled(false);
					btnprogram[4][2].setEnabled(false);
					btnprogram[5][2].setEnabled(false);
					btnStandard[2][0].setEnabled(true);
					btnStandard[2][1].setEnabled(true);
					btnStandard[2][2].setEnabled(true);
					btnStandard[3][0].setEnabled(true);
					btnStandard[3][1].setEnabled(true);
					btnStandard[3][2].setEnabled(true);
					btnStandard[4][0].setEnabled(true);
					btnStandard[4][1].setEnabled(true);
				} else if (optoct.isSelected()) {
					btnprogram[0][2].setEnabled(false);
					btnprogram[1][2].setEnabled(false);
					btnprogram[2][2].setEnabled(false);
					btnprogram[3][2].setEnabled(false);
					btnprogram[4][2].setEnabled(false);
					btnprogram[5][2].setEnabled(false);
					btnStandard[2][0].setEnabled(true);
					btnStandard[2][1].setEnabled(false);
					btnStandard[2][2].setEnabled(false);
					btnStandard[3][0].setEnabled(true);
					btnStandard[3][1].setEnabled(true);
					btnStandard[3][2].setEnabled(true);
					btnStandard[4][0].setEnabled(true);
					btnStandard[4][1].setEnabled(true);
				} else if (optbin.isSelected()) {
					btnprogram[0][2].setEnabled(false);
					btnprogram[1][2].setEnabled(false);
					btnprogram[2][2].setEnabled(false);
					btnprogram[3][2].setEnabled(false);
					btnprogram[4][2].setEnabled(false);
					btnprogram[5][2].setEnabled(false);
					btnStandard[2][0].setEnabled(false);
					btnStandard[2][1].setEnabled(false);
					btnStandard[2][2].setEnabled(false);
					btnStandard[3][0].setEnabled(false);
					btnStandard[3][1].setEnabled(false);
					btnStandard[3][2].setEnabled(false);
					btnStandard[4][0].setEnabled(false);
					btnStandard[4][1].setEnabled(false);
				}
			}

		};
		opthex.addActionListener(actOn);
		optdec.addActionListener(actOn);
		optoct.addActionListener(actOn);
		optbin.addActionListener(actOn);

		ActionListener actHex = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (opthex.isSelected()) {
					String sHex = "";
					int iHex = Integer.parseInt(txtin.getText());
					sHex = Integer.toHexString(iHex);
					txtin.setText("" + sHex);
				}
				if (optdec.isSelected()) {
					String sHex = "";
					int iHex = Integer.parseInt(txtin.getText(), 16);
					sHex = Integer.toString(iHex);
					txtin.setText("" + sHex);
					selected = false;
				}
				if (optoct.isSelected()) {
					String sHex = "";
					int iHex = Integer.parseInt(txtin.getText(), 16);
					sHex = Integer.toOctalString(iHex);
					txtin.setText("" + sHex);
					selected = false;
					// }else {
					// String sHex2 = txtin.getText();
					// int iHex3 = Integer.parseInt(sHex2,16);
					// sHex2 = Integer.toOctalString(iHex3);
					// txtin.setText("" + sHex2);
					// selected = false;
				}
				if (optbin.isSelected()) {
					String sHex = "";
					int iHex = Integer.parseInt(txtin.getText(), 16);
					sHex = Integer.toBinaryString(iHex);
					txtin.setText("" + sHex);
					selected = false;
				}
				// if (opthex.isSelected()) {
				// String sHex2 = "";
				// sHex = Integer.toHexString(iHex);
				// txtin.setText("" + sHex);
				// selected = false;

				// txtin.setText("" + sHex);
			}

		};
		opthex.addActionListener(actHex);
		optdec.addActionListener(actHex);
		optoct.addActionListener(actHex);
		optbin.addActionListener(actHex);

		// ActionListener actDec = new ActionListener() {

		// @Override
		// public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// String sDec = "";
		// int iDec = Integer.parseInt(txtin.getText());
		// if (opthex.isSelected()) {
		// int iDec = Integer.parseInt(txtin.getText());
		// sDec = Integer.toHexString(iDec);
		// txtin.setText("" + sDec);
		// } else if (optoct.isSelected()) {
		// int iDec2 = Integer.parseInt(txtin.getText());
		// sDec = Integer.toOctalString(iDec2);
		// txtin.setText("" + sDec);
		// } else if (optbin.isSelected()) {
		// int iDec = Integer.parseInt(txtin.getText(),10);
		// sDec = Integer.toBinaryString(iDec);
		// txtin.setText("" + sDec);
		// }
		// txtin.setText("" + sDec);
		// }
		// };
		// opthex.addActionListener(actDec);
		// optoct.addActionListener(actDec);
		// optbin.addActionListener(actDec);

		// ActionListener actOct = new ActionListener() {

		// @Override
		// public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// String sOct = "";
		// int iOct = Integer.parseInt(txtin.getText(), 8);
		// if (opthex.isSelected()) {
		// sOct = Integer.toHexString(iOct);
		// } else if (optdec.isSelected()) {
		// sOct = Integer.toString(iOct);
		// } else if (optbin.isSelected()) {
		// sOct = Integer.toBinaryString(iOct);
		// }
		// txtin.setText("" + sOct);
		// }
		// };
		// opthex.addActionListener(actOct);
		// optdec.addActionListener(actOct);
		// optbin.addActionListener(actOct);

		// ActionListener actBin = new ActionListener() {

		// @Override
		// public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// String sBin = "";
		// int iBin = Integer.parseInt(txtin.getText(), 2);
		// if (opthex.isSelected()) {
		// sBin = Integer.toHexString(iBin);
		// } else if (optdec.isSelected()) {
		// sBin = Integer.toString(iBin);
		// } else if (optoct.isSelected()) {
		// sBin = Integer.toOctalString(iBin);
		// }
		// txtin.setText("" + sBin);
		// }
		// };
		// opthex.addActionListener(actBin);
		// optdec.addActionListener(actBin);
		// optoct.addActionListener(actBin);

	}

	public void inittalizeMenu() {
		// Them menu vao Bar
		mnbbar.add(mnuview);
		mnbbar.add(mnuedit);
		mnbbar.add(mnuhelp);
		// Them Items vao menu View
		mnuview.add(mnistand);
		mnuview.addSeparator();
		mnuview.add(mniscientific);
		mnuview.add(mniprogrammer);
		mnuview.add(mniexit);
		// Them Items vao menu Edit
		mnuedit.add(mnicop);
		mnuedit.add(mnipas);
		// Them Items vao menu About
		mnuhelp.add(mniabout);
		// Menubar
		setJMenuBar(mnbbar);

	}

	// exit
	public void close() {
		int resultx = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (resultx == JOptionPane.YES_OPTION) {
			System.exit(0);
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		pHW01_Calculator_T152678 main = new pHW01_Calculator_T152678();
		main.setVisible(true);
	}

}
