package services;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class TimestampXMLAdapter extends XmlAdapter<String, java.sql.Timestamp> {

    @Override
    public String marshal(java.sql.Timestamp v) throws Exception {
        return v.toString();
    }

    @Override
    public java.sql.Timestamp unmarshal(String v) throws Exception {

        return new java.sql.Timestamp(Long.parseLong(v));
    }

}