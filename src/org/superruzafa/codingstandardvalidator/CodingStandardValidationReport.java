package org.superruzafa.codingstandardvalidator;

import java.util.Date;
import org.openide.filesystems.FileObject;

/**
 * Result of a validation operation.
 *
 * @author Alfonso Ruzafa <superruzafa@gmail.com>
 */
public class CodingStandardValidationReport {
    protected boolean valid;
    protected FileObject fileObject;
    protected String codingStandard;
    protected CodingStandardViolation[] violations;
    protected String validator;
    protected Date dateTime;

    public CodingStandardValidationReport()
    {
        valid = false;
        fileObject = null;
        codingStandard = "";
        violations = new CodingStandardViolation[0];
        validator = "";
    }

    public boolean isValid()
    {
        return valid;
    }
    
    public FileObject getFileObject()
    {
        return fileObject;
    }

    public String getCodingStandard()
    {
        return codingStandard;
    }

    public CodingStandardViolation[] getViolations()
    {
        return violations;
    }
    
    public String getValidator()
    {
        return validator;
    }

    public Date getDateTime()
    {
        return dateTime;
    }
}
