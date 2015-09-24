package de.samdev.absgdx.menudesigner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import com.badlogic.gdx.math.GridPoint2;

import de.samdev.absgdx.framework.util.AndroidResolutions;
import de.samdev.absgdx.framework.util.exceptions.AgdxmlParsingException;
import de.samdev.absgdx.menudesigner.renderPreview.RenderPreviewPanel;

public class DesignFrame extends JFrame {
	private static final long serialVersionUID = 5936312660450931611L;

	private final static String EMPTYDOC = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<frame>\r\n\t<!-- content -->\r\n</frame>";
	private final static String EMPTYTEXDEF = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<texturedefinitions>\r\n\t<!-- content -->\r\n</texturedefinitions>";
	
	private JPanel contentPane;
	private JSplitPane splitPane;
	private GUIPreviewPanel lblDraw;
	private JPanel pnlBottom;
	private JTabbedPane pnlSettings;
	private RSyntaxTextArea edCode;
	private JList<AGDXMLTagDefinition> lstComponents;
	private DefaultListModel<AGDXMLTagDefinition> listModel;
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
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpen;
	private JMenuItem mntmSave;
	private JMenuItem mntmNew;
	private JMenuItem mntmSaveAs;
	private JSeparator separator;

	private String filename_agdxml = null;
	private String filename_agdtexdef = null;
	private JPanel rootPane;
	private JToolBar toolBar;
	private JButton btnNew;
	private JButton btnOpenAgdxml;
	private JButton btnSaveAs;
	private JButton btnSave;
	private JComboBox<GridPoint2> cbxSize;
	private JList<AGDXMLTagDefinition.TagAttribute> lstAttributes;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JPanel panel_1;
	private JButton btnRenderRefresh;
	private JButton btnRenderDebug;
	private RenderPreviewPanel pnlRenderGUI;
	private JTabbedPane tabbedPane_1;
	private JScrollPane scrollPane;
	private RSyntaxTextArea edTextureDef;
	private JPanel pnlAGDTEXDEF;
	private JTextField edTexturePath;
	private JMenuItem mntmOpenAgdtexdef;
	private JButton btnOpenTexDef;
	private JPanel pnlAGDXML;
	private JTabbedPane tabbedPane_2;
	
	public DesignFrame() {
		try {
			initGUI();
		} catch (AgdxmlParsingException e) {
			e.printStackTrace();
		}
	}
	
	private void initGUI() throws AgdxmlParsingException {
		updateTitle();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 949, 818);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		listModel = new DefaultListModel<AGDXMLTagDefinition>();
		
		for (AGDXMLTagDefinition tag : AGDXMLTagDefinition.TAGS) listModel.addElement(tag);
		
		rootPane = new JPanel();
		rootPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(rootPane, BorderLayout.CENTER);
		rootPane.setLayout(new BorderLayout(0, 0));
		
		splitPane = new JSplitPane();
		rootPane.add(splitPane);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(0.565);
		
		pnlBottom = new JPanel();
		splitPane.setRightComponent(pnlBottom);
		pnlBottom.setLayout(new BorderLayout(0, 0));
		
		pnlSettings = new JTabbedPane(JTabbedPane.TOP);
		pnlSettings.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		pnlBottom.add(pnlSettings, BorderLayout.EAST);
		
		tabSettings = new JPanel();
		pnlSettings.addTab("Settings", null, tabSettings, null);
		GridBagLayout gbl_tabSettings = new GridBagLayout();
		gbl_tabSettings.columnWidths = new int[] {100, 100};
		gbl_tabSettings.columnWeights = new double[]{1.0, 0.0};
		gbl_tabSettings.rowHeights = new int[] {0, 0, 0, 0, 0};
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
			@Override
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
			@Override
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
		
		cbxSize = new JComboBox<GridPoint2>();
		GridBagConstraints gbc_cbxSize = new GridBagConstraints();
		gbc_cbxSize.gridwidth = 2;
		gbc_cbxSize.anchor = GridBagConstraints.NORTH;
		gbc_cbxSize.insets = new Insets(0, 0, 0, 5);
		gbc_cbxSize.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbxSize.gridx = 0;
		gbc_cbxSize.gridy = 2;

		cbxSize.addItem(new GridPoint2(900, 300));
		cbxSize.addItem(new GridPoint2(1920, 1080));
		cbxSize.addItem(new GridPoint2(960, 720));
		for (GridPoint2 size : AndroidResolutions.RES_ALL) {
			cbxSize.addItem(size);			
			cbxSize.addItem(new GridPoint2(size.y, size.x));
		}
		cbxSize.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				return super.getListCellRendererComponent(list, ((GridPoint2)value).x + " - " + ((GridPoint2)value).y, index, isSelected, cellHasFocus);
			}
		});
		cbxSize.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				spinnerWidth.setValue(((GridPoint2)cbxSize.getSelectedItem()).x);
				spinnerHeight.setValue(((GridPoint2)cbxSize.getSelectedItem()).y);
			}
		});
		tabSettings.add(cbxSize, gbc_cbxSize);
		
		
		
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
		
		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		pnlBottomLeft.add(tabbedPane_1, BorderLayout.CENTER);
		
		pnlAGDXML = new JPanel();
		tabbedPane_1.addTab("AGDXML", null, pnlAGDXML, null);
		pnlAGDXML.setLayout(new BorderLayout(0, 0));
		
		scrollPane_1 = new JScrollPane();
		pnlAGDXML.add(scrollPane_1);
		
		edCode = new RSyntaxTextArea();
		scrollPane_1.setViewportView(edCode);
		edCode.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent de) {
				refreshLblDraw();
			}
			
			@Override
			public void insertUpdate(DocumentEvent de) {
				refreshLblDraw();
			}
			
			@Override
			public void changedUpdate(DocumentEvent de) {
				refreshLblDraw();
			}
		});
		edCode.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				if (lstAttributes == null) return;
				
				DefaultListModel<AGDXMLTagDefinition.TagAttribute> model = new DefaultListModel<AGDXMLTagDefinition.TagAttribute>();
				
				String line = edCode.getText().substring(edCode.getLineStartOffsetOfCurrentLine(), edCode.getLineEndOffsetOfCurrentLine());
				
				Matcher mt = Pattern.compile("<([a-z\\.]*)").matcher(line);
				if (mt.find()) {
					String tag = mt.group(1);
					
					for (AGDXMLTagDefinition tagdef : AGDXMLTagDefinition.TAGS) {
						if (tagdef.tag.equals(tag)) {
							for (AGDXMLTagDefinition.TagAttribute attr : tagdef.attributes) {
								model.addElement(attr);
							}
						}
					}
				}
				
				lstAttributes.setModel(model);
			}
		});
		edCode.setText(EMPTYDOC);
		edCode.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
		edCode.setCodeFoldingEnabled(true);
		new FileDrop(edCode, new FileDrop.Listener() {@Override public void filesDropped(File[] files) { if (files.length > 0) openFileCode(files[0].getAbsolutePath()); }});
		
		tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setPreferredSize(new Dimension(200, 5));
		tabbedPane_2.setMinimumSize(new Dimension(200, 5));
		pnlAGDXML.add(tabbedPane_2, BorderLayout.EAST);
		
		tabComponents = new JPanel();
		tabbedPane_2.addTab("Components", null, tabComponents, null);
		tabComponents.setLayout(new BorderLayout(0, 0));
		
		lstComponents = new JList<AGDXMLTagDefinition>();
		lstComponents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int caretPos = edCode.getLineEndOffsetOfCurrentLine();
					String line = edCode.getText().substring(edCode.getLineStartOffsetOfCurrentLine(), edCode.getLineEndOffsetOfCurrentLine());
					
					String start = edCode.getText().substring(0, caretPos);
					String end = edCode.getText().substring(caretPos);
					
					String mid = "<" + lstComponents.getSelectedValue().tag + " />\r\n";
					
					Matcher mt = Pattern.compile("^[\\t ]*").matcher(line);
					if (mt.find()) mid = mt.group() + mid;
					
					edCode.setText(start + mid + end);
					edCode.setCaretPosition(start.length() + mid.length() - 4);
				}
			}
		});
		lstComponents.setModel(listModel);
		tabComponents.add(lstComponents);
		
		pnlProperties = new JPanel();
		tabbedPane_2.addTab("Properties", null, pnlProperties, null);
		pnlProperties.setLayout(new BorderLayout(0, 0));
		
		lstAttributes = new JList<AGDXMLTagDefinition.TagAttribute>();
		lstAttributes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pnlProperties.add(lstAttributes);
		
		pnlAGDTEXDEF = new JPanel();
		tabbedPane_1.addTab("AGDTEXDEF", null, pnlAGDTEXDEF, null);
		pnlAGDTEXDEF.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		pnlAGDTEXDEF.add(scrollPane);
		
		edTextureDef = new RSyntaxTextArea();
		edTextureDef.setSyntaxEditingStyle("text/xml");
		edTextureDef.setBracketMatchingEnabled(false);
		edTextureDef.setCloseCurlyBraces(false);
		edTextureDef.setCloseMarkupTags(false);
		edTextureDef.setCodeFoldingEnabled(true);
		edTextureDef.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent de) {
				refreshLblDraw();
			}
			
			@Override
			public void insertUpdate(DocumentEvent de) {
				refreshLblDraw();
			}
			
			@Override
			public void changedUpdate(DocumentEvent de) {
				refreshLblDraw();
			}
		});
		scrollPane.setViewportView(edTextureDef);
		edTextureDef.setText(EMPTYTEXDEF);
		new FileDrop(edTextureDef, new FileDrop.Listener() {@Override public void filesDropped(File[] files) { if (files.length > 0) openFileTexDef(files[0].getAbsolutePath()); }});
		
		edTexturePath = new JTextField();
		edTexturePath.setText("path to texture");
		edTexturePath.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent de) {
				refreshLblDraw();
			}
			
			@Override
			public void insertUpdate(DocumentEvent de) {
				refreshLblDraw();
			}
			
			@Override
			public void changedUpdate(DocumentEvent de) {
				refreshLblDraw();
			}
		});
		pnlAGDTEXDEF.add(edTexturePath, BorderLayout.NORTH);
		edTexturePath.setColumns(10);
		new FileDrop(edTexturePath, new FileDrop.Listener() {@Override public void filesDropped(File[] files) { if (files.length > 0) openFileTexture(files[0].getAbsolutePath()); }});
		
		menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmNew = new JMenuItem("New");
		mnFile.add(mntmNew);
		mntmNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				newDocument();
			}
		});
		
		mntmOpen = new JMenuItem("Open AGDXML");
		mnFile.add(mntmOpen);
		mntmOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openDocument_AGDXML();
			}
		});
		
		mntmOpenAgdtexdef = new JMenuItem("Open AGDTEXDEF");
		mntmOpenAgdtexdef.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				openDocument_AGDTEXDEF();
			}
		});
		mnFile.add(mntmOpenAgdtexdef);
		
		separator = new JSeparator();
		mnFile.add(separator);
		
		mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		mntmSaveAs = new JMenuItem("Save As..");
		mnFile.add(mntmSaveAs);
		mntmSaveAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveDocumentAs();
			}
		});
		mntmSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveDocument();
			}
		});
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.SOUTH);
		
		btnNew = new JButton("NEW");
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newDocument();
			}
		});
		toolBar.add(btnNew);
		
		btnOpenAgdxml = new JButton("OPEN AGDXML");
		btnOpenAgdxml.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openDocument_AGDXML();
			}
		});
		toolBar.add(btnOpenAgdxml);
		
		btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveDocument();
			}
		});
		
		btnOpenTexDef = new JButton("OPEN AGDTEXDEF");
		btnOpenTexDef.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				openDocument_AGDTEXDEF();
			}
		});
		toolBar.add(btnOpenTexDef);
		toolBar.add(btnSave);
		
		btnSaveAs = new JButton("SAVE AS");
		btnSaveAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveDocumentAs();
			}
		});
		toolBar.add(btnSaveAs);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setLeftComponent(tabbedPane);
		
		lblDraw = new GUIPreviewPanel(900, 300, new AgdxmlPreviewLayerDummy(EMPTYDOC, 900, 300) { @Override public void initialize() { /**/ } });
		tabbedPane.addTab("Layout", null, lblDraw, null);
		lblDraw.setLayout(null);
		
		panel = new JPanel();
		tabbedPane.addTab("Render", null, panel, null);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_1, BorderLayout.NORTH);
		
		btnRenderRefresh = new JButton("Refresh");
		btnRenderRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					pnlRenderGUI.refresh(edCode.getText(), edTextureDef.getText(), edTexturePath.getText());
				} catch (Exception e) {
					memoError.setText(e.toString());
				}
			}
		});
		panel_1.add(btnRenderRefresh);
		
		btnRenderDebug = new JButton("Debug");
		btnRenderDebug.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				pnlRenderGUI.switchDebug();
			}
		});
		panel_1.add(btnRenderDebug);
		
		pnlRenderGUI = new RenderPreviewPanel();
		pnlRenderGUI.setBorder(null);
		panel.add(pnlRenderGUI, BorderLayout.CENTER);
	}

	protected void saveDocument() {
		saveDocumentCode();
		saveDocumentTexDef();
	}

	protected void saveDocumentAs() {
		saveDocumentCodeAs();
		saveDocumentTexDefAs();
	}
	
	protected void saveDocumentCode() {
		if (filename_agdxml == null) {
			saveDocumentCodeAs();
			return;
		}
		
		BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename_agdxml));
            writer.write(edCode.getText());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

	protected void saveDocumentCodeAs() {
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setDialogTitle("Save AGDXML as ...");
		
		if ( fc.showSaveDialog(DesignFrame.this) == JFileChooser.APPROVE_OPTION) {
			filename_agdxml = fc.getSelectedFile().getAbsolutePath();
			saveDocument();
		}
	}
	
	protected void saveDocumentTexDef() {
		if (filename_agdtexdef == null) {
			saveDocumentCodeAs();
			return;
		}
		
		BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filename_agdtexdef));
            writer.write(edTextureDef.getText());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

	protected void saveDocumentTexDefAs() {
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setDialogTitle("Save AGDTEXDEF as ...");
		
		if ( fc.showSaveDialog(DesignFrame.this) == JFileChooser.APPROVE_OPTION) {
			filename_agdtexdef = fc.getSelectedFile().getAbsolutePath();
			saveDocument();
		}
	}

	private void updateTitle() {
		String tile = "absGDX - Menu Designer ";
		
		if (filename_agdxml == null) 
			tile += "<>";
		else
			tile += "<" + (new File(filename_agdxml)).getName() + ">";
		
		tile += " - ";
		
		if (filename_agdtexdef == null) 
			tile += "<>";
		else
			tile += "<" + (new File(filename_agdtexdef)).getName() + ">";
		
		setTitle(tile);
	}

	private void openFileCode(final String path) {
		filename_agdxml = path;
		
		updateTitle();
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename_agdxml));
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		            sb.append(System.lineSeparator());
		            line = br.readLine();
		        }
		        
		        edCode.setText(sb.toString());
		    } finally {
		        br.close();
		    }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void openFileTexDef(final String path) {
		filename_agdtexdef = path;
		
		updateTitle();
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename_agdtexdef));
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		            sb.append(System.lineSeparator());
		            line = br.readLine();
		        }
		        
		        edTextureDef.setText(sb.toString());
		    } finally {
		        br.close();
		    }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void openFileTexture(final String path) {
		edTexturePath.setText(path);
	}

	private void openDocument_AGDXML() {
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return "AGDXML";
			}
			
			@Override
			public boolean accept(File f) {
				return f.getPath().toLowerCase().endsWith(".agdxml");
			}
		});
		
		if ( fc.showOpenDialog(DesignFrame.this) == JFileChooser.APPROVE_OPTION) {
			openFileCode(fc.getSelectedFile().getAbsolutePath());
		}
	}

	private void openDocument_AGDTEXDEF() {
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return "AGDTEXDEF";
			}
			
			@Override
			public boolean accept(File f) {
				return f.getPath().toLowerCase().endsWith(".agdtexdef");
			}
		});
		
		if ( fc.showOpenDialog(DesignFrame.this) == JFileChooser.APPROVE_OPTION) {
			openFileTexDef(fc.getSelectedFile().getAbsolutePath());
		}
	}

	private void newDocument() {
		filename_agdxml = null;
		filename_agdtexdef = null;
		edCode.setText(EMPTYDOC);
		edTextureDef.setText(EMPTYTEXDEF);
		
		updateTitle();
	}

	private void refreshLblDraw() {
		if (lblDraw == null) return;
		
		memoError.setText("");
		try {
			lblDraw.setMenuLayer(edCode.getText());
			pnlRenderGUI.refresh(edCode.getText(), edTextureDef.getText(), edTexturePath.getText());
		} catch (Exception e) {
			memoError.setText(e.toString());

			lblDraw.drawError();
		}
	}
}
