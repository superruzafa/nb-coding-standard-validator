/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superruzafa.codingstandardvalidator.ui;

import java.awt.Component;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.cookies.LineCookie;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.text.Line;
import org.openide.windows.Mode;
import org.superruzafa.codingstandardvalidator.*;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.superruzafa.codingstandardvalidator.ui//CodingStandardViolations//EN",
autostore = false)
public final class CodingStandardViolationsTopComponent extends TopComponent {

    private static CodingStandardViolationsTopComponent instance;
    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "org/superruzafa/codingstandardvalidator/ui/codingstandardviolations.png";
    private static final String PREFERRED_ID = "CodingStandardViolationsTopComponent";
    private CodingStandardViolationsTableModel model;
    private DataObject currentDataObject;

    public CodingStandardViolationsTopComponent() {
        initComponents();
        validCode.setVisible(false);

        setName(NbBundle.getMessage(CodingStandardViolationsTopComponent.class, "CTL_CodingStandardViolationsTopComponent"));
        setToolTipText(NbBundle.getMessage(CodingStandardViolationsTopComponent.class, "HINT_CodingStandardViolationsTopComponent"));
        setIcon(ImageUtilities.loadImage(ICON_PATH, true));

        violationsTable.getColumnModel().getColumn(0).setMinWidth(24);
        violationsTable.getColumnModel().getColumn(0).setMaxWidth(24);
        violationsTable.getColumnModel().getColumn(0).setResizable(false);
        violationsTable.getColumnModel().getColumn(1).setMaxWidth(92);

        model = (CodingStandardViolationsTableModel) violationsTable.getModel();
        model.setSeverityVisibility(CodingStandardViolationSeverity.Error, true);
        model.setSeverityVisibility(CodingStandardViolationSeverity.Warning, true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        showErrors = new javax.swing.JToggleButton();
        showWarnings = new javax.swing.JToggleButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        violationsTable = new javax.swing.JTable();
        validCode = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);
        jToolBar1.setOrientation(1);
        jToolBar1.setRollover(true);

        showErrors.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/superruzafa/codingstandardvalidator/ui/error_16.png"))); // NOI18N
        showErrors.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(showErrors, org.openide.util.NbBundle.getMessage(CodingStandardViolationsTopComponent.class, "CodingStandardViolationsTopComponent.showErrors.text")); // NOI18N
        showErrors.setToolTipText(org.openide.util.NbBundle.getMessage(CodingStandardViolationsTopComponent.class, "CodingStandardViolationsTopComponent.showErrors.toolTipText")); // NOI18N
        showErrors.setFocusable(false);
        showErrors.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        showErrors.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        showErrors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showErrorsActionPerformed(evt);
            }
        });
        jToolBar1.add(showErrors);

        showWarnings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/superruzafa/codingstandardvalidator/ui/warning_16.png"))); // NOI18N
        showWarnings.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(showWarnings, org.openide.util.NbBundle.getMessage(CodingStandardViolationsTopComponent.class, "CodingStandardViolationsTopComponent.showWarnings.text")); // NOI18N
        showWarnings.setToolTipText(org.openide.util.NbBundle.getMessage(CodingStandardViolationsTopComponent.class, "CodingStandardViolationsTopComponent.showWarnings.toolTipText")); // NOI18N
        showWarnings.setFocusable(false);
        showWarnings.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        showWarnings.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        showWarnings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showWarningsActionPerformed(evt);
            }
        });
        jToolBar1.add(showWarnings);

        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
        jScrollPane1.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                jLayered1Resized(evt);
            }
        });

        violationsTable.setModel(new CodingStandardViolationsTableModel());
        violationsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        violationsTable.setFillsViewportHeight(true);
        violationsTable.setRowHeight(20);
        violationsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        violationsTable.getTableHeader().setReorderingAllowed(false);
        violationsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                violationsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(violationsTable);

        jScrollPane1.setBounds(20, 30, 190, 100);
        jLayeredPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        validCode.setBackground(javax.swing.UIManager.getDefaults().getColor("text"));
        validCode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        validCode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/superruzafa/codingstandardvalidator/ui/ok_16.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(validCode, org.openide.util.NbBundle.getMessage(CodingStandardViolationsTopComponent.class, "CodingStandardViolationsTopComponent.validCode.text")); // NOI18N
        validCode.setOpaque(true);
        validCode.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                jLayered1Resized(evt);
            }
        });
        validCode.setBounds(220, 30, 160, 70);
        jLayeredPane1.add(validCode, javax.swing.JLayeredPane.MODAL_LAYER);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void open() {
        Mode m = WindowManager.getDefault().findMode("output");
        if (m != null) {
            m.dockInto(this);
        }
        super.open();
    }

    private void showErrorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showErrorsActionPerformed
        model.setSeverityVisibility(CodingStandardViolationSeverity.Error, showErrors.getModel().isSelected());
    }//GEN-LAST:event_showErrorsActionPerformed

    private void showWarningsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showWarningsActionPerformed
        model.setSeverityVisibility(CodingStandardViolationSeverity.Warning, showWarnings.getModel().isSelected());
    }//GEN-LAST:event_showWarningsActionPerformed

    private void jLayered1Resized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jLayered1Resized
        Component parent = evt.getComponent().getParent();
        evt.getComponent().setBounds(0, 0, parent.getWidth(), parent.getHeight());
    }//GEN-LAST:event_jLayered1Resized

    private void violationsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_violationsTableMouseClicked
        if (evt.getClickCount() == 2
                && violationsTable.getSelectedRow() != -1) {
            CodingStandardViolation violation = model.getRow(violationsTable.getSelectedRow());
            DataObject dataObject = null;
            try {
                dataObject = DataObject.find(currentDataObject.getPrimaryFile());
            } catch (DataObjectNotFoundException e) {
            }
            if (dataObject != null) {
                LineCookie lineCookie = (LineCookie) dataObject.getCookie(LineCookie.class);
                if (lineCookie != null) {
                    Line line = lineCookie.getLineSet().getOriginal(violation.getLine() - 1);
                    line.show(Line.ShowOpenType.OPEN, Line.ShowVisibilityType.FOCUS);
                }
            }

        }
    }//GEN-LAST:event_violationsTableMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToggleButton showErrors;
    private javax.swing.JToggleButton showWarnings;
    private javax.swing.JLabel validCode;
    private javax.swing.JTable violationsTable;
    // End of variables declaration//GEN-END:variables

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized CodingStandardViolationsTopComponent getDefault() {
        if (instance == null) {
            instance = new CodingStandardViolationsTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the CodingStandardViolationsTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized CodingStandardViolationsTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(CodingStandardViolationsTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof CodingStandardViolationsTopComponent) {
            return (CodingStandardViolationsTopComponent) win;
        }
        Logger.getLogger(CodingStandardViolationsTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    Object readProperties(java.util.Properties p) {
        if (instance == null) {
            instance = this;
        }
        instance.readPropertiesImpl(p);
        return instance;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    public void setViolations(CodingStandardViolation[] violations, DataObject dataObject) {
        this.currentDataObject = dataObject;
        model.clear();
        if (violations.length == 0) {
            jScrollPane1.setVisible(false);
            validCode.setVisible(true);
        } else {
            for (CodingStandardViolation violation : violations) {
                model.add(violation);
            }
            validCode.setVisible(false);
            jScrollPane1.setVisible(true);
        }
    }
}

class CodingStandardViolationsTableModel extends AbstractTableModel {

    private static final String WARNING_ICON_PATH = "org/superruzafa/codingstandardvalidator/ui/warning_16_small.png";
    private static final String ERROR_ICON_PATH = "org/superruzafa/codingstandardvalidator/ui/error_16_small.png";
    private static final Image warningIcon = ImageUtilities.loadImage(WARNING_ICON_PATH);
    private static final Image errorIcon = ImageUtilities.loadImage(ERROR_ICON_PATH);
    /**
     * @todo I18n
     */
    private static final String[] columnNames = {"", "Line", "Message"};
    private static final Class<?>[] columnClasses = {ImageIcon.class, String.class, String.class};
    private ArrayList<CodingStandardViolation> visibleViolations;
    private ArrayList<ArrayList<CodingStandardViolation>> violationsBySeverity;
    private boolean[] severityVisibility;

    public CodingStandardViolationsTableModel() {
        visibleViolations = new ArrayList<CodingStandardViolation>();
        violationsBySeverity = new ArrayList<ArrayList<CodingStandardViolation>>();
        for (CodingStandardViolationSeverity severity : CodingStandardViolationSeverity.values()) {
            violationsBySeverity.add(new ArrayList<CodingStandardViolation>());
        }
        severityVisibility = new boolean[CodingStandardViolationSeverity.values().length];
    }

    @Override
    public int getRowCount() {
        return visibleViolations.size();
    }

    @Override
    public int getColumnCount() {
        return columnClasses.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object value = null;

        CodingStandardViolation violation = visibleViolations.get(rowIndex);
        switch (columnIndex) {
            case 0: // Severity
                switch (violation.getSeverity()) {
                    case Warning:
                        value = warningIcon;
                        break;
                    case Error:
                    default:
                        value = errorIcon;
                        break;
                }
                break;

            case 1:
                value = violation.getLine();
                break;
            case 2:
                value = violation.getMessage();
                break;
        }

        return value;
    }

    public CodingStandardViolation getRow(int selectedRow) {
        return visibleViolations.get(selectedRow);
    }

    public boolean getSeverityVisibility(CodingStandardViolationSeverity severity) {
        return severityVisibility[severity.ordinal()];
    }

    public void setSeverityVisibility(CodingStandardViolationSeverity severity, boolean visible) {
        severityVisibility[severity.ordinal()] = visible;
        if (violationsBySeverity.get(severity.ordinal()).size() > 0) {
            buildAllViolations();
            notifyListeners();
        }
    }

    public void add(CodingStandardViolation violation) {
        violationsBySeverity.get(violation.getSeverity().ordinal()).add(violation);
        if (severityVisibility[violation.getSeverity().ordinal()]) {
            visibleViolations.add(violation);
            notifyListeners();
        }
    }

    void clear() {
        visibleViolations.clear();
        for (CodingStandardViolationSeverity severity : CodingStandardViolationSeverity.values()) {
            violationsBySeverity.get(severity.ordinal()).clear();
        }
        notifyListeners();
    }

    private void buildAllViolations() {
        visibleViolations.clear();
        for (CodingStandardViolationSeverity severity : CodingStandardViolationSeverity.values()) {
            if (severityVisibility[severity.ordinal()]) {
                visibleViolations.addAll(violationsBySeverity.get(severity.ordinal()));
            }
        }
        Collections.sort(visibleViolations, new Comparator<CodingStandardViolation>() {

            @Override
            public int compare(CodingStandardViolation o1, CodingStandardViolation o2) {
                int comparation = 0;

                if (o1.getLine() < o2.getLine()) {
                    comparation = -1;
                } else if (o1.getLine() > o2.getLine()) {
                    comparation = +1;
                } else if (o1.getSeverity() == CodingStandardViolationSeverity.Error) {
                    comparation = -1;
                } else {
                    comparation = 0;
                }

                return comparation;
            }
        });
    }

    private void notifyListeners() {
        TableModelEvent event = new TableModelEvent(this);
        for (TableModelListener listener : getTableModelListeners()) {
            listener.tableChanged(event);
        }
    }
}
