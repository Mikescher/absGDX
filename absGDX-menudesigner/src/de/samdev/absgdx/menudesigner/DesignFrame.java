package de.samdev.absgdx.menudesigner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import de.samdev.absgdx.framework.layer.AgdxmlLayer;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;
import javax.swing.ScrollPaneConstants;

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
	private JPanel pnlProperties;
	private JLabel lblNewLabel;
	private JLabel lblWidth;
	private JSpinner spinnerHeight;
	private JSpinner spinnerWidth;
	private JPanel pnlBottomLeft;
	private JScrollPane scrollPane_0;
	private JTextArea memoError;
	private JScrollPane scrollPane_1;

	public DesignFrame() {
		try {
			initGUI();
		} catch (AgdxmlParsingException e) {
			e.printStackTrace();
		}
	}
	
	private void initGUI() throws AgdxmlParsingException {
		setTitle("absGDX - Menu Designer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 949, 818);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(0.565);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		lblDraw = new GUIPreviewPanel(900, 300, new AgdxmlLayer(new AgdxPreviewGameDummy(), null, "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<frame><panel position=\"10,10\" height=\"80\" width=\"200\"><button position=\"50,50\" height=\"20\" width=\"80\" /></panel></frame>") {
			@Override
			public void onResize() {super.onResize();}
		});
		splitPane.setLeftComponent(lblDraw);
		lblDraw.setLayout(null);
		
		pnlBottom = new JPanel();
		splitPane.setRightComponent(pnlBottom);
		pnlBottom.setLayout(new BorderLayout(0, 0));
		
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
		pnlSettings.setSelectedIndex(2);
		GridBagLayout gbl_tabSettings = new GridBagLayout();
		gbl_tabSettings.columnWidths = new int[] {100, 100};
		gbl_tabSettings.columnWeights = new double[]{0.0, 0.0};
		gbl_tabSettings.rowHeights = new int[] {0, 0, 0};
		gbl_tabSettings.rowWeights = new double[]{0.0, 0.0, 1.0};
		tabSettings.setLayout(gbl_tabSettings);
		
		lblNewLabel = new JLabel("Height");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		tabSettings.add(lblNewLabel, gbc_lblNewLabel);
		
		spinnerHeight = new JSpinner();
		spinnerHeight.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblDraw.setRenderSize((Integer)spinnerWidth.getValue(), (Integer)spinnerHeight.getValue());
			}
		});
		spinnerHeight.setModel(new SpinnerNumberModel(new Integer(300), null, null, new Integer(1)));
		GridBagConstraints gbc_spinnerHeight = new GridBagConstraints();
		gbc_spinnerHeight.anchor = GridBagConstraints.NORTHWEST;
		gbc_spinnerHeight.fill = GridBagConstraints.BOTH;
		gbc_spinnerHeight.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerHeight.gridx = 1;
		gbc_spinnerHeight.gridy = 1;
		tabSettings.add(spinnerHeight, gbc_spinnerHeight);
		
		lblWidth = new JLabel("Width");
		GridBagConstraints gbc_lblWidth = new GridBagConstraints();
		gbc_lblWidth.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblWidth.fill = GridBagConstraints.BOTH;
		gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
		gbc_lblWidth.gridx = 0;
		gbc_lblWidth.gridy = 0;
		tabSettings.add(lblWidth, gbc_lblWidth);
		
		spinnerWidth = new JSpinner();
		spinnerWidth.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				lblDraw.setRenderSize((Integer)spinnerWidth.getValue(), (Integer)spinnerHeight.getValue());
			}
		});
		spinnerWidth.setModel(new SpinnerNumberModel(new Integer(900), null, null, new Integer(1)));
		GridBagConstraints gbc_spinnerWidth = new GridBagConstraints();
		gbc_spinnerWidth.anchor = GridBagConstraints.NORTHWEST;
		gbc_spinnerWidth.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerWidth.fill = GridBagConstraints.BOTH;
		gbc_spinnerWidth.gridx = 1;
		gbc_spinnerWidth.gridy = 0;
		tabSettings.add(spinnerWidth, gbc_spinnerWidth);
		
		pnlBottomLeft = new JPanel();
		pnlBottom.add(pnlBottomLeft, BorderLayout.CENTER);
		pnlBottomLeft.setLayout(new BorderLayout(0, 0));
		
		scrollPane_0 = new JScrollPane();
		scrollPane_0.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlBottomLeft.add(scrollPane_0, BorderLayout.SOUTH);
		
		memoError = new JTextArea();
		memoError.setLineWrap(true);
		memoError.setForeground(Color.RED);
		memoError.setRows(8);
		scrollPane_0.setViewportView(memoError);
		
		scrollPane_1 = new JScrollPane();
		pnlBottomLeft.add(scrollPane_1, BorderLayout.CENTER);
		
		edCode = new RSyntaxTextArea();
		scrollPane_1.setViewportView(edCode);
		edCode.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent de) {
				memoError.setText("");
				try {
					lblDraw.setMenuLayer(edCode.getText());
				} catch (AgdxmlParsingException e) {
					memoError.setText(e.toString());

					lblDraw.drawError();
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent de) {
				memoError.setText("");
				try {
					lblDraw.setMenuLayer(edCode.getText());
				} catch (AgdxmlParsingException e) {
					memoError.setText(e.toString());

					lblDraw.drawError();
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent de) {
				memoError.setText("");
				try {
					lblDraw.setMenuLayer(edCode.getText());
				} catch (AgdxmlParsingException e) {
					memoError.setText(e.toString());

					lblDraw.drawError();
				}
			}
		});
		edCode.setText("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<frame>\r\n\t<!-- content -->\r\n</frame>");
		edCode.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
		edCode.setCodeFoldingEnabled(true);
	}
}
