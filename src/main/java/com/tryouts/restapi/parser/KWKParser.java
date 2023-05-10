package com.tryouts.restapi.parser;

import com.tryouts.restapi.dataApi.WsfAdapter;
import com.tryouts.restapi.model.District;
import com.tryouts.restapi.model.PowerInput;
import com.tryouts.restapi.model.PowerInputType;
import com.tryouts.restapi.repo.DistrictRepository;
import com.tryouts.restapi.repo.PowerInputRepository;
import com.tryouts.restapi.repo.PowerInputTypeRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.Year;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Controller
public class KWKParser implements IParser {
    private final PowerInputTypeRepository powerInputTypeRepository;
    private final PowerInputRepository powerInputRepository;
    private final DistrictRepository districtRepository;
    Logger LOG = LogManager.getLogger(KWKParser.class);
    WsfAdapter dataAdapter;
    private Document doc;

    private PowerInputType powerInputType = new PowerInputType("Kraft-Wärme-Kopplung");
    private District currentDistrict;

    private LinkedList<PowerInput> powerInputs;


    private HashSet<District> districts;

    @Autowired
    public KWKParser(WsfAdapter dataAdapter, @Qualifier("small") DistrictRepository districtRepository, PowerInputRepository powerInputRepository, PowerInputTypeRepository powerInputTypeRepository) {
        this.dataAdapter = dataAdapter;
        this.districtRepository = districtRepository;
        this.powerInputRepository = powerInputRepository;
        this.powerInputTypeRepository = powerInputTypeRepository;
    }

    private static Stream<Node> getNodeStream(NodeList nodeList) {
        return IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item);
    }

    @Override
    public void readInput() {
        districts = new HashSet<>();
        powerInputs = new LinkedList<>();
        byte[] input = dataAdapter.getData();
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = builder.parse(new ByteArrayInputStream(input));
            doc.getDocumentElement().normalize();

        } catch (IOException | SAXException | ParserConfigurationException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void parse() {
        NodeList nodeList = doc.getElementsByTagName("fis:s_kwk_strom_bez");
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
    public void saveData() {
        if (districts != null && powerInputs != null) {
            districtRepository.saveAll(districts);
            powerInputTypeRepository.save(powerInputType);
            powerInputRepository.saveAll(powerInputs);
        }

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
        this.currentDistrict = district;
        districts.add(district);
    }

    public LinkedList<PowerInput> getPowerInputs() {
        return powerInputs;
    }

    public HashSet<District> getDistricts() {
        return districts;
    }
}
