package beyondx.desktop;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

/**
 * @author kxt
 */
public class XSearch extends JFrame {

	private static final long serialVersionUID = 1L;

	private Font font = null;
	private JTextField inputText = null;
	private JButton searchButton = null;
	private JButton stopButton = null;
	private JLabel selectLabel = null;
	private JLabel inputLabel = null;
	private JLabel timeTipLabel = null;
	private JLabel timeLable = null;
	private JComboBox<Object> rootBox = null;
	private JList<Object> list = null;

	private JScrollPane scrollPane = null;
	private JPanel topArea = null;

	private String input = null;
	private long startTime = 0;
	private List<String> resultList = null;
	public static boolean isSearchFinish = false;
	private SearchThread st = null;
	
	private static final String ALL = "All";
	private static final String NO_RESULT_BE_FOUND = "No result be found";
	private static final String SLASH = "/";
	private static final String BACKSLASH = "\\";

	private void initUI() {
		// New object
		font = new Font(Font.DIALOG, Font.BOLD, 13);
		inputText = new JTextField(35);
		searchButton = new JButton();
		stopButton = new JButton();
		selectLabel = new JLabel();
		inputLabel = new JLabel();
		timeTipLabel = new JLabel();
		timeLable = new JLabel();
		list = new JList<Object>();
		scrollPane = new JScrollPane(list);
		topArea = new JPanel();
		resultList = new ArrayList<String>();
		
		// Init
		selectLabel.setText("Please select:");
		inputLabel.setText("Input:");
		timeTipLabel.setText("Time:");
		searchButton.setText("Search");
		stopButton.setText("Stop");

		searchButton.addActionListener(new Search());
		stopButton.addMouseListener(new Stop());
		stopButton.setEnabled(false);
		inputText.addKeyListener(new Search());
		Container contentPane = this.getContentPane();

		rootBox = new JComboBox<Object>(this.getRoots().toArray());

		topArea.setLayout(new FlowLayout(5));
		topArea.add(selectLabel);
		topArea.add(rootBox);
		topArea.add(inputLabel);
		topArea.add(inputText);
		topArea.add(searchButton);
		topArea.add(stopButton);
		topArea.add(timeTipLabel);
		topArea.add(timeLable);

		list.addMouseListener(new Open());
		list.setFont(font);

		contentPane.setLayout(new BorderLayout());
		contentPane.add(topArea, BorderLayout.NORTH);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
		}
		this.setTitle("XSearch");
		this.setSize(new Dimension(700, 400));
		this.setLocation(300, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void createAndOpenFrame(){
		st = new SearchThread();
		this.initUI();
	}
	
	private class SearchThread extends Thread {
		public void run() {
			while(true){
				if(isSearchFinish) break;
				searchFile();
			}
		}
	}
	
	private void searchFile() {
		String path = null;
		if (resultList.size() > 0) {
			resultList.clear();
		}
		input = inputText.getText();
		path = rootBox.getSelectedItem().toString();
		startTime = System.currentTimeMillis();
		if (!ALL.equals(path)) {
			getSearchResult(path);
			if (resultList.size() == 0) {
				resultList.add(NO_RESULT_BE_FOUND);
			}
			list.setListData(resultList.toArray());
		} else {
			List<String> rootList = getRoots();
			for (int i = 1; i < rootList.size(); ++i) {
				getSearchResult(rootList.get(i));
				if (resultList.size() == 0) {
					resultList.add(NO_RESULT_BE_FOUND);
				}
				list.setListData(resultList.toArray());
			}
		}
		Long endTime = System.currentTimeMillis();
		timeLable.setText((endTime - startTime)/1000 + "s");
	}

	private class Search extends KeyAdapter implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if(st != null && !st.isAlive()){
				stopButton.setEnabled(true);
				st.start();
			}
		}

		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			if (code == KeyEvent.VK_ENTER) {
				if(st != null && !st.isAlive()){
					stopButton.setEnabled(true);
					st.start();
				}
			}
		}
	}
	
	private class Stop extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			if(st.isAlive()){
				st.interrupt();
			}
			if (resultList.size() == 0) {
				resultList.add(NO_RESULT_BE_FOUND);
			}
			list.setListData(resultList.toArray());
			Long endTime = System.currentTimeMillis();
			timeLable.setText((endTime - startTime)/1000 + "s");
		}
	}

	private class Open extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				String selectValue = list.getSelectedValue().toString();
				String cmd = "rundll32 url.dll FileProtocolHandler file://"
						+ selectValue;
				try {
					Runtime.getRuntime().exec(cmd);
				} catch (IOException e1) {
				}
			}
		}
	}

	private void getSearchResult(String fileRoot) {
		File file = new File(fileRoot);
		if (file.isFile()) {
			String fileName = file.getName();
			if (file.getName().indexOf(input.toUpperCase()) > -1
					|| file.getName().indexOf(input.toLowerCase()) > -1) {
				resultList.add(file.getParent() + BACKSLASH + fileName);
				list.setListData(resultList.toArray());
			}
		}
		if (file.isDirectory()) {
			if (file.getName().indexOf(input.toUpperCase()) > -1
					|| file.getName().indexOf(input.toLowerCase()) > -1) {
				resultList.add(file.getPath());
				list.setListData(resultList.toArray());
			}
			String[] files = file.list();
			if(files != null){
				String childFilePath = null;
				for (int i = 0; i < files.length; ++i) {
					childFilePath = fileRoot + SLASH + files[i];
					getSearchResult(childFilePath);
				}
			}
		}
		stopButton.setEnabled(false);
		isSearchFinish = true;
	}

	private List<String> getRoots() {
		File[] roots = File.listRoots();
		List<String> rootList = new ArrayList<String>();
		rootList.add(ALL);
		for (int i = 0; i < roots.length; ++i) {
			rootList.add(roots[i].getPath());
		}
		return rootList;
	}
}
