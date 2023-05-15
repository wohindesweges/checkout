package com.tryouts.restapi.parser;

import com.tryouts.restapi.dataAdapter.WsfAdapter;
import com.tryouts.restapi.entity.District;
import com.tryouts.restapi.entity.PowerInput;
import com.tryouts.restapi.entity.PowerInputType;
import com.tryouts.restapi.repository.DistrictRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Year;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Controller
public class DistrictDataParser implements IParser {

    private final DistrictRepository districtRepository;
    protected WsfAdapter dataAdapter;
    protected Document doc;
    protected District currentDistrict;
    protected LinkedList<PowerInput> powerInputs;
    protected HashSet<District> districts;
    Logger LOG = LogManager.getLogger(DistrictDataParser.class);
    private URI uri;
    private PowerInputType powerInputType;
    private String districtInputElementName;


    @Autowired
    public DistrictDataParser(WsfAdapter dataAdapter, DistrictRepository districtRepository) {
        this.dataAdapter = dataAdapter;

        this.districtRepository = districtRepository;
    }

    private static Stream<Node> getNodeStream(NodeList nodeList) {
        return IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item);
    }

    public String getDistrictInputElementName() {
        return districtInputElementName;
    }

    public void setDistrictInputElementName(String districtInputElementName) {
        this.districtInputElementName = districtInputElementName;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    @Override
    public void readInput() {
        districts = new HashSet<>();
        powerInputs = new LinkedList<>();
        try {
            dataAdapter.setUri(uri);
            byte[] input = dataAdapter.getData();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = builder.parse(new ByteArrayInputStream(input));
            doc.getDocumentElement().normalize();
        } catch (IOException | SAXException | ParserConfigurationException | URISyntaxException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void parse() {
        if (!StringUtils.hasText(districtInputElementName)) {
            throw new MissingFormatArgumentException("Node declaration missing");
        }
        NodeList nodeList = doc.getElementsByTagName("fis:" + districtInputElementName);
        List<Node> bezirksNodes = getNodeStream(nodeList).toList();

        for (Node bezirksNode : bezirksNodes) {
            getNodeStream(bezirksNode.getChildNodes()).forEach(node1 -> {
                String nodeName = node1.getNodeName();
                nodeName = nodeName.substring(4);
                if (nodeName.startsWith("bezirk")) {
                    parseBezirk(node1);

                }
                if (nodeName.startsWith("j")) {
                    parseYearPowerInput(node1);
                }

            });
        }
    }

    @Override
    public void clearData() {
        currentDistrict = null;
        powerInputs = new LinkedList<>();
        districts = new HashSet<>();
    }

    private void parseYearPowerInput(Node node1) {
        String yearSubstring = node1.getNodeName().substring(5, node1.getNodeName().length() - 1);
        String nodeValue = node1.getFirstChild().getNodeValue();
        PowerInput powerInput = new PowerInput();
        powerInput.setYearOfData(Year.parse(yearSubstring));
        powerInput.setPowerinput(Double.valueOf(nodeValue));
        powerInput.setPowerInputType(powerInputType);
        powerInput.setDistrict(currentDistrict);
        powerInputs.add(powerInput);
    }

    private void parseBezirk(Node node1) {
        District district = new District(node1.getFirstChild().getNodeValue());
        District byName = districtRepository.findByName(district.getName());
        //TODO MATCHER
        this.currentDistrict = Objects.requireNonNullElse(byName, district);
        if (currentDistrict.getId() == null) {
            districts.add(district);
        }

    }

    public LinkedList<PowerInput> getPowerInputs() {
        return powerInputs;
    }

    public HashSet<District> getDistricts() {
        return districts;
    }

    public List<PowerInputType> getPowerInputType() {
        return List.of(powerInputType);
    }

    public void setPowerInputType(PowerInputType powerInputType) {
        this.powerInputType = powerInputType;
    }
}
