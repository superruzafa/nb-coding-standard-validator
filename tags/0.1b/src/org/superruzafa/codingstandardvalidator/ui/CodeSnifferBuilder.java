package org.superruzafa.codingstandardvalidator.ui;

import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;
import org.openide.util.lookup.ServiceProvider;
import org.superruzafa.codingstandardvalidator.codesniffer.CodeSniffer;
import org.superruzafa.codingstandardvalidator.codesniffer.CodeSnifferReportType;

/**
 * Code Sniffer builder from settings.
 *
 * @author Alfonso Ruzafa <superruzafa@gmail.com>
 */
@ServiceProvider(service = CodeSnifferBuilder.class)
public class CodeSnifferBuilder implements PreferenceChangeListener {

    private CodeSniffer codeSniffer;

    public CodeSnifferBuilder() {
        codeSniffer = new CodeSniffer();
        NbPreferences.forModule(getClass()).addPreferenceChangeListener(this);
        loadPreferences();
    }

    protected final void loadPreferences() {
        Preferences preferences = NbPreferences.forModule(getClass());
        codeSniffer.setScriptPath(preferences.get("code-sniffer-script", ""));
        codeSniffer.setCodingStandardName(preferences.get("coding-standard", ""));
        try {
            codeSniffer.setReportType(CodeSnifferReportType.valueOf(preferences.get("report-type", "Default")));
        } catch (Exception e) {
            codeSniffer.setReportType(CodeSnifferReportType.Default);
        }
    }

    CodeSniffer getInstance() {
        return codeSniffer;
    }

    @Override
    public void preferenceChange(PreferenceChangeEvent evt) {
        loadPreferences();
    }
}
