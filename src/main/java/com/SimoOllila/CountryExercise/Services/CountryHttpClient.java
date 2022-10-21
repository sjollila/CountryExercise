package com.SimoOllila.CountryExercise.Services;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.SimoOllila.CountryExercise.Models.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class contains all the functionality to get JSon schema from the service and convert it into Country classes
 * Services:
 * getResource
 */
public class CountryHttpClient {
    //TODO put BASEURL into some propertyfile
    private final String BASEURL = "https://restcountries.com/v3.1/";
    private String JSONSTRING = "";

    /**
     *
     * @param path                  name of the Country searched. e.g. Finland. Notice the uppercase first letter.
     * @return ArrayList<Country>
     */
    public ArrayList<Country> getResource(String path) {
        return queryAPI(path);
    }
    public ArrayList<Country> queryAPI(String path) {
        try {
            JSONSTRING = "";
            URL url = new URL(BASEURL + path);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();
            StringBuilder content = new StringBuilder();
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
            }
            JSONSTRING = content.toString();
            conn.disconnect();
        } catch (MalformedURLException ex) {
            Logger.getLogger(CountryHttpClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CountryHttpClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parseJSONString(JSONSTRING);
    }

    public ArrayList<Country> parseJSONString(String JSONSTRING) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = null;
        ArrayList<Country> countries = new ArrayList<>();
        try {
            actualObj = mapper.readTree(JSONSTRING);
        } catch (IOException ex) {
            Logger.getLogger(CountryHttpClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (JsonNode node : actualObj) {
            countries.add(convertJSONToCountryObject(node));
        }
        return countries;
    }

    public Country convertJSONToCountryObject(JsonNode data) {
        Country country = new Country();
        country.setName(generateName(data));
        country.setTld(generateStringArrayList(data, "/tld"));
        country.setCca2(data.path("cca2").textValue());
        country.setCcn3(data.path("ccn3").textValue());
        country.setCca3(data.path("cca3").textValue());
        country.setCioc(data.path("cioc").textValue());
        country.setIndependent(data.path("independent").asBoolean());
        country.setStatus(data.path("status").asText());
        country.setUnMember(data.path("unMember").asBoolean());
        country.setCurrencies(generateCountryCurrency(data));
        country.setIdd(generateIDD(data));
        country.setCapital(generateStringArrayList(data, "/capital"));
        country.setAltSpellings(generateStringArrayList(data, "/altSpellings"));
        country.setRegion(data.path("region").textValue());
        country.setSubregion(data.path("subregion").textValue());
        country.setLanguages(generateLanguages(data));
        country.setTranslations(generateTranslations(data));
        country.setLatlng(generateDoubleArrayList(data, "/latlng"));
        country.setLandlocked(data.path("landlocked").asBoolean());
        country.setBorders(generateStringArrayList(data, "/borders"));
        country.setArea(data.path("area").asDouble());
        country.setDemonyms(generateDemonyms(data));
        country.setFlag(data.path("flag").asText());
        country.setMaps(generateMaps(data));
        country.setPopulation(data.path("population").asInt());
        country.setGini(generateGini(data));
        country.setFifa(data.path("fifa").asText());
        country.setCar(generateCar(data));
        country.setTimezones(generateStringArrayList(data, "/timezones"));
        country.setContinents(generateStringArrayList(data, "/continents"));
        country.setFlags(generatePictureResource(data, "flags"));
        country.setCoatOfArms(generatePictureResource(data, "coatOfArms"));
        country.setStartOfWeek(data.path("startOfWeek").asText());
        country.setCapitalInfo(generateCapitalInfo(data));
        country.setPostalCode(generatePostalCode(data));
        return country;
    }

    public PostalCode generatePostalCode(JsonNode data) {
        PostalCode p = new PostalCode();
        p.setFormat(data.path("postalCode").path("format").asText());
        p.setRegex(data.path("postalCode").path("regex").asText());
        return p;
    }

    public CapitalInfo generateCapitalInfo(JsonNode data) {
        CapitalInfo c = new CapitalInfo();
        c.setLatlng(generateDoubleArrayList(data, "/capitalInfo/latlng"));
        return c;
    }

    public PictureResource generatePictureResource(JsonNode data, String rootNode) {
        PictureResource p = new PictureResource();
        p.setPng(data.path(rootNode).path("png").asText());
        p.setSvg(data.path(rootNode).path("svg").asText());
        return p;
    }

    public Car generateCar(JsonNode data) {
        Car c = new Car();
        c.setSigns(generateStringArrayList(data, "/car/signs"));
        c.setSide(data.path("car").path("side").asText());
        return c;
    }

    public Gini generateGini(JsonNode data) {
        Gini g = new Gini();
        Iterator<String> fieldNames = data.path("gini").fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            g.setValue(data.path("gini").path(fieldName).asDouble());
            g.setYear(fieldName);
        }
        return g;
    }

    public Maps generateMaps(JsonNode data) {
        Maps m = new Maps();
        m.setGoogleMaps(data.path("maps").path("googleMaps").asText());
        m.setOpenStreetMaps(data.path("maps").path("openStreetMaps").asText());
        return m;
    }

    public ArrayList<Demonym> generateDemonyms(JsonNode data) {
        ArrayList<Demonym> list = new ArrayList<>();
        Iterator<String> fieldNames = data.path("demonyms").fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            Demonym d = new Demonym();
            d.setLanguage(fieldName);
            d.setF(data.path("demonyms").path(fieldName).path("f").asText());
            d.setM(data.path("demonyms").path(fieldName).path("m").asText());
            list.add(d);
        }
        return list;
    }

    public ArrayList<Translation> generateTranslations(JsonNode data) {
        ArrayList<Translation> list = new ArrayList<>();
        Iterator<String> fieldNames = data.path("translations").fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            Translation c = new Translation();
            c.setOfficial(data.path("translations").path(fieldName).path("official").asText());
            c.setCommon(data.path("translations").path(fieldName).path("common").asText());
            list.add(c);
        }
        return list;
    }

    public Idd generateIDD(JsonNode data) {
        Idd idd = new Idd();
        idd.setRoot(data.path("idd").path("root").asText());
        idd.setSuffixes(generateStringArrayList(data, "/idd/suffixes"));
        return idd;
    }

    public ArrayList<Language> generateLanguages(JsonNode data) {
        ArrayList<Language> list = new ArrayList<>();
        Iterator<String> fieldNames = data.path("languages").fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            Language l = new Language();
            l.setName(fieldName);
            l.setNativeName(data.path("languages").path(fieldName).asText());
            list.add(l);
        }
        return list;
    }

    public Name generateName(JsonNode data) {
        Name name = new Name();
        name.setCommon(data.path("name").path("common").textValue());
        name.setOfficial(data.path("name").path("official").textValue());
        Isl isl = new Isl();
        isl.setCommon(data.path("name").path("nativeName").path("isl").path("common").textValue());
        isl.setOfficial(data.path("name").path("nativeName").path("isl").path("official").textValue());
        NativeName nativeName = new NativeName();
        nativeName.setIsl(isl);
        name.setNativeName(nativeName);
        return name;
    }

    public ArrayList<Currency> generateCountryCurrency(JsonNode data) {
        ArrayList<Currency> list = new ArrayList<>();
        Iterator<String> fieldNames = data.path("currencies").fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            Currency c = new Currency();
            c.setName(data.path("currencies").path(fieldName).path("name").asText());
            c.setSymbol(data.path("currencies").path(fieldName).path("symbol").asText());
            list.add(c);
        }
        return list;
    }

    public ArrayList<String> generateStringArrayList(JsonNode data, String rootNode) {
        ArrayList<String> list = new ArrayList<>();
        for (JsonNode node : data.at(rootNode)) {
            list.add(node.asText());
        }
        return list;
    }

    public ArrayList<Double> generateDoubleArrayList(JsonNode data, String rootNode) {
        ArrayList<Double> list = new ArrayList<>();
        for (JsonNode node : data.at(rootNode)) {
            list.add(node.asDouble());
        }
        return list;
    }
}
