package org.superruzafa.codingstandardvalidator;

import java.util.Date;
import org.openide.filesystems.FileObject;

/**
 * A CodingStandardValidationReport builder.
 * 
 * @author Alfonso Ruzafa <superruzafa@gmail.com>
 */
public class CodingStandardValidationReportBuilder {

    protected boolean valid;
    protected FileObject fileObject;
    protected String codingStandard;
    protected CodingStandardViolation[] violations;
    protected String validator;
    protected Date dateTime;

    public CodingStandardValidationReportBuilder()
    {
        valid = false;
        fileObject = null;
        codingStandard = "";
        violations = new CodingStandardViolation[0];
        validator = "";
        dateTime = new Date();
    }

    public CodingStandardValidationReport getReport()
    {
        EditableCodingStandardValidationReport report = new EditableCodingStandardValidationReport();
        report.setDateTime(dateTime);
        report.setIsValid(valid);
        report.setFileObject(fileObject);
        report.setValidator(validator);
        report.setCodingStandard(codingStandard);
        report.setViolations(violations);

        return report;
    }

    public void setIsValid(boolean valid)
    {
        this.valid = valid;
    }

    public void setFileObject(FileObject fileObject)
    {
        this.fileObject = fileObject;
    }

    public void setCodingStandard(String codingStandard)
    {
        this.codingStandard = codingStandard;
    }

    public void setViolations(CodingStandardViolation[] violations)
    {
        this.violations = violations;
    }

    public void setValidator(String validator)
    {
        this.validator = validator;
    }

    public void setDateTime(Date date)
    {
        this.dateTime = date;
    }
}

class EditableCodingStandardValidationReport extends CodingStandardValidationReport
{
    public void setIsValid(boolean valid)
    {
        this.valid = valid;
    }

    public void setFileObject(FileObject fileObject)
    {
        this.fileObject = fileObject;
    }

    public void setCodingStandard(String codingStandard)
    {
        this.codingStandard = codingStandard;
    }

    public void setViolations(CodingStandardViolation[] violations)
    {
        this.violations = violations;
    }

    public void setValidator(String validator)
    {
        this.validator = validator;
    }

    public void setDateTime(Date date)
    {
        this.dateTime = date;
    }
}
