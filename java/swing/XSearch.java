package beyondx.desktop;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class XSearch extends JFrame {

	private static final long serialVersionUID = 1L;

	private Font font = new Font(Font.DIALOG, Font.BOLD, 13);
	private JTextField inputText = new JTextField(35);
	private JButton searchButton = new JButton();
	private JLabel selectLabel = new JLabel();
	private JLabel inputLabel = new JLabel();
	private JLabel timeTipLabel = new JLabel();
	private JLabel timeLable = new JLabel();
	private JComboBox rootBox = null;
	private JList list = new JList();

	private JScrollPane scrollPane = new JScrollPane(list);
	private JPanel topArea = new JPanel();

	private String input = null;
	private List<String> resultList = new ArrayList<String>();
	private List<String> nameList = new ArrayList<String>();

	private void initUI() {
		selectLabel.setText("Please select:");
		inputLabel.setText("input:");
		timeTipLabel.setText("Time:");
		searchButton.setText("Search");

		searchButton.addActionListener(new Search());
		inputText.addKeyListener(new Search());
		Container contentPane = this.getContentPane();

		rootBox = new JComboBox(this.getRoots().toArray());

		topArea.setLayout(new FlowLayout(5));
		topArea.add(selectLabel);
		topArea.add(rootBox);
		topArea.add(inputLabel);
		topArea.add(inputText);
		topArea.add(searchButton);
		topArea.add(timeTipLabel);
		topArea.add(timeLable);

		list.addMouseListener(new Open());
		list.setFont(font);

		contentPane.setLayout(new BorderLayout());
		contentPane.add(topArea, BorderLayout.NORTH);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setTitle("XSearch");
		this.setSize(new Dimension(800, 600));
		this.setLocation(250, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private class Search implements ActionListener, KeyListener {

		public void actionPerformed(ActionEvent e) {
			searchFile();
		}

		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			if (code == e.VK_ENTER) {
				searchFile();
			}
		}

		public void keyReleased(KeyEvent e) {

		}

		public void keyTyped(KeyEvent e) {

		}

		public void searchFile() {
			Long startTime = System.currentTimeMillis();
			String path = null;
			if (nameList.size() > 0 || nameList != null) {
				nameList.clear();
			}
			input = inputText.getText();
			path = rootBox.getSelectedItem().toString();
			if (!path.equals("")) {
				nameList = getSearchResult(path);
				if (nameList.size() == 0 || nameList == null) {
					nameList.add("");
				}
				list.setListData(nameList.toArray());
			} else {
				for (int i = 1; i < getRoots().size(); i++) {
					nameList = getSearchResult(getRoots().get(i));
					if (nameList.size() == 0 || nameList == null) {
						nameList.add("");
					}
					list.setListData(nameList.toArray());
				}
			}
			Long endTime = System.currentTimeMillis();
			timeLable.setText(String.valueOf(endTime - startTime) + "ms");
		}

	}

	private class Open implements MouseListener {

		public void mouseClicked(MouseEvent e) {

			if (e.getClickCount() == 2) {
				String selectValue = list.getSelectedValue().toString();
				String cmd = "rundll32 url.dll FileProtocolHandler file://"
						+ selectValue;
				try {
					Runtime.getRuntime().exec(cmd);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {

		}

	}

	public List<String> getSearchResult(String filePath) {

		File file = new File(filePath);
		/*
		 * FileSystemView view = FileSystemView.getFileSystemView(); Icon icon =
		 * view.getSystemIcon(file); imageLabel.setIcon(icon);
		 */
		if (file.isFile()) {
			String fileName = file.getName();
			if (fileName.indexOf(input.toUpperCase()) > -1
					|| fileName.indexOf(input.toLowerCase()) > -1) {
				resultList.add(file.getParent() + "\\" + fileName);
			}
		}
		if (file.isDirectory()) {
			String[] files = file.list();
			if (file.getName().indexOf(input.toUpperCase()) > -1
					|| file.getName().indexOf(input.toLowerCase()) > -1) {
				resultList.add(file.getPath());
			}
			if (files == null || files.length < 0) {
				return null;
			}
			for (int i = 0; i < files.length; i++) {
				String childFilePath = filePath + "/" + files[i];
				getSearchResult(childFilePath);
			}
		}

		return resultList;
	}

	public List<String> getRoots() {
		File[] roots = File.listRoots();
		List<String> rootList = new ArrayList<String>();
		rootList.add("");
		for (int i = 0; i < roots.length; i++) {
			rootList.add(roots[i].getPath());
		}
		return rootList;
	}

	public static void main(String[] args) {
		XSearch xSearch = new XSearch();
		xSearch.initUI();
	}

}
