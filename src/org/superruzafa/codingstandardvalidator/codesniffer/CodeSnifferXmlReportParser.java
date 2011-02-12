package org.superruzafa.codingstandardvalidator.codesniffer;

import java.io.ByteArrayInputStream;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.openide.util.Exceptions;
import org.superruzafa.codingstandardvalidator.CodingStandardViolation;
import org.superruzafa.codingstandardvalidator.CodingStandardViolationSeverity;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * PHP Code Sniffer Xml reports parser.
 * 
 * @author Alfonso Ruzafa <superruzafa@gmail.com>
 * @since 0.1
 */
public class CodeSnifferXmlReportParser extends CodeSnifferReportParser {

    /**
     * xPath object.
     */
    protected final static XPath xPath = XPathFactory.newInstance().newXPath();

    @Override
    public boolean parse(String report) {

        boolean parsed = false;
        violations.clear();
        try {
            InputSource is = new InputSource(new ByteArrayInputStream(report.getBytes()));
            CodingStandardViolation violation;
            XPathExpression xPathExpression = xPath.compile("/phpcs/file/error|/phpcs/file/warning");
            NodeList nodelist = (NodeList) xPathExpression.evaluate(is, XPathConstants.NODESET);
            for (int i = 0; i < nodelist.getLength(); ++i) {
                violation = parseViolationNode(nodelist.item(i));
                if (violation != null) {
                    violations.add(violation);
                }
            }
            parsed = true;
        } catch (XPathExpressionException ex) {
            Exceptions.printStackTrace(ex);
            parsed = false;
        }
        return parsed;
    }

    /**
     * Parses an XML violation node.
     *
     * @param node XML violation node.
     *
     * @return The parsed violation.
     */
    protected CodingStandardViolation parseViolationNode(Node node) {

        EditableCodingStandardViolation violation = new EditableCodingStandardViolation();

        if ("error".equals(node.getNodeName())) {
            violation.setSeverity(CodingStandardViolationSeverity.Error);
        } else if ("warning".equals(node.getNodeName())) {
            violation.setSeverity(CodingStandardViolationSeverity.Warning);
        }

        violation.setLine(Integer.parseInt(node.getAttributes().getNamedItem("line").getTextContent()));
        violation.setColumn(Integer.parseInt(node.getAttributes().getNamedItem("column").getTextContent()));
        violation.setMessage(node.getTextContent());

        return violation;
    }
}
