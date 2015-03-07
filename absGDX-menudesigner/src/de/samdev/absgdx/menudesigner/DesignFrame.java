package de.samdev.absgdx.menudesigner;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.apache.commons.io.IOUtils;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

public class DesignFrame extends JFrame {
	private static final long serialVersionUID = 5936312660450931611L;
	
	private JPanel contentPane;
	private JSplitPane splitPane;
	private GUIPreviewPanel lblDraw;
	private JPanel pnlBottom;
	private JTabbedPane pnlSettings;
	private RSyntaxTextArea edCode;
	private JList list;
	private JPanel tabSettings;
	private JPanel tabComponents;
	private Component horizontalStrut;
	private JPanel pnlProperties;

	public DesignFrame() {
		initGUI();
	}
	
	private void initGUI() {
		setTitle("absGDX - Menu Designer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 949, 724);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		lblDraw = new GUIPreviewPanel(10, 10);
		splitPane.setLeftComponent(lblDraw);
		lblDraw.setLayout(null);
		
		pnlBottom = new JPanel();
		splitPane.setRightComponent(pnlBottom);
		pnlBottom.setLayout(new BorderLayout(0, 0));
		
		edCode = new RSyntaxTextArea();
		edCode.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
		edCode.setCodeFoldingEnabled(true);
		pnlBottom.add(edCode, BorderLayout.CENTER);
		
		pnlSettings = new JTabbedPane(JTabbedPane.TOP);
		pnlSettings.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		pnlBottom.add(pnlSettings, BorderLayout.EAST);
		
		tabComponents = new JPanel();
		pnlSettings.addTab("Components", null, tabComponents, null);
		tabComponents.setLayout(new BorderLayout(0, 0));
		
		list = new JList();
		tabComponents.add(list);
		
		pnlProperties = new JPanel();
		pnlSettings.addTab("Properties", null, pnlProperties, null);
		
		tabSettings = new JPanel();
		pnlSettings.addTab("Settings", null, tabSettings, null);
		
		horizontalStrut = Box.createHorizontalStrut(200);
		tabSettings.add(horizontalStrut);
		
		lblDraw.setRenderSize(350, 150);
	}
}
